package pingball;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;

import javax.swing.JComponent;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pingball.parser.PingballBaseListener;
import pingball.parser.PingballFactory;
import pingball.parser.PingballLexer;
import pingball.parser.PingballParser;
import ui.PingballGUI;

/**
 * This is the client for playing a pingball game
 * A Pingball game has gadgets on the playing area which is 20x20 units, representing bumpers, balls, flippers, and so forth.
 * It takes command line arguments that specify the board that the game should use.
 * A client has the option of a single-player game or a multi-player game, in which a ball exiting one client’s playing area
 * can enter another client’s playing area.
 * 
 * Mutability:
 * A client has a board which is mutable in that its walls can be made to link with other walls.
 * Socket is also mutable.
 * 
 * Thread safety:
 * Only one client receiver thread is spawned. Board is shared with the client receiver thread so it is thread locked.
 * 
 */
public class Pingball {
    
    //rep invariant:
    //none
    
    /** Default server port. */
    private static final int DEFAULT_PORT = 10987;
    private static final String defaultBoardName = "default";
    
    private long fieldTime;
    private final long MILLISECS_PER_FRAME = 100; // Formula: 1000 / MILLLISECS_PER_FRAME = FPS
    //create the board
    private Board board;
    private Socket clientSocket;
    
    private final String fileName;
    
    /**
     * Creates a pingball client in single player mode
     * @param board pingball board that the game will be played on
     */
    public Pingball() {
        this.board = new Board(defaultBoardName,.000025, .025, .000025);
        this.board.setSinglePlayerMode(true);
        this.fileName = defaultBoardName;
        createDefaultBoard(this.board);
    }
    
    public void checkRep(){
        // don't have to check, no rep invariant
    }
    
    /**
     * Creates a pingball client in single player mode with the board specified in the file
     * @param board pingball board that the game will be played on
     * @throws IOException 
     */
    public Pingball(String fileName) throws IOException {
        this.board = parse(fileName,Optional.empty());
        this.board.setSinglePlayerMode(true);
        this.fileName = fileName;
    }

    /**
     * Creates a pingball client in multiplayer mode
     * @param port port that will be used to communicate with the server
     * @param hostName host name of the server
     * @param board pingball board that the game will be played on
     * @throws UnknownHostException 
     * @throws IOException
     */
    public Pingball(int port, String hostName) throws UnknownHostException, IOException {
        clientSocket = new Socket(hostName, port);
        this.board=new Board(defaultBoardName,.000025, .025, .000025, clientSocket);
        createDefaultBoard(this.board);
        this.fileName = defaultBoardName;
        (new Thread(new ClientReceiver(clientSocket,board))).start();
    }
    
    /**
     * Creates a pingball client in multiplayer mode based on a file
     * @param port port that will be used to communicate with the server
     * @param hostName host name of the server
     * @param board pingball board that the game will be played on
     * @throws UnknownHostException 
     * @throws IOException
     */
    public Pingball(int port, String hostName, String fileName) throws UnknownHostException, IOException {
        clientSocket = new Socket(hostName, port);
        this.board=parse(fileName, Optional.of(clientSocket));
        this.fileName = fileName;
        (new Thread(new ClientReceiver(clientSocket,board))).start();
    }
    
    /**
     * Disconnect this pingball client from the server.
     */
    public void disconnect() {
        this.board.disconnect();
    }
    
    /**
     * Connect this pingball client to the specified socket
     * @param socket
     */
    public void connect(Socket socket) {
        this.board.changeSocket(socket);
    }
    
    /**
     * Creates the default board with gadgets specified in
     * http://web.mit.edu/6.005/www/fa14/projects/pb3/pingball-phase1-spec.html
     * @param board to put gadgets on
     */
    public void createDefaultBoard(Board board){
        board.addBall(new Ball(1.25, 1.25, 0, 0));
        board.addGadget(new CircleBumper(1, 10));
        board.addGadget(new TriangleBumper(12, 15, 180));
        board.addGadget(new SquareBumper(0, 17));
        board.addGadget(new SquareBumper(1, 17));
        board.addGadget(new SquareBumper(2, 17));
        board.addGadget(new CircleBumper(7, 18));
        board.addGadget(new CircleBumper(8, 18));
        board.addGadget(new CircleBumper(9, 18));

//        board.addGadget(new TriangleBumper(19, 0, 90));
//        Absorber abs = new Absorber(0,19,20,1);
//        abs.addTriggeredGadget(abs);
//        board.addGadget(abs);
    }
    
    /**
     * Pauses the game.
     */
    public void pause() {
        this.board.pause();
    }
    
    /**
     * Resumes the game.
     */
    public void resume() {
        this.board.resume();
    }
    
    /**
     * Restarts the board to its original state. Disconnects from the server.
     */
    public void restartBoard() {
        System.out.println(this.fileName);
        if (this.fileName.equals(defaultBoardName)) {
            this.board = new Board(defaultBoardName,.000025, .025, .000025);
            this.board.setSinglePlayerMode(true);
            createDefaultBoard(this.board);
        }else {
            try {
                this.board = parse(this.fileName,Optional.empty());
                this.board.setSinglePlayerMode(true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
    /**
     * Respond to a key that was pressed with the action of every gadget triggered by that key press
     * @param key that was pressed
     */
    public void keyPressed(String key) {
        this.board.keyPressed(key);
    }
    
    /**
     * Respond to a key that was released with the action of every gadget triggered by that key release
     * @param key that was released
     */
    public void keyReleased(String key) {
        this.board.keyReleased(key);
    }
    
    /**
     * Draw this game
     * @param g2 where to draw this game
     */
    public void draw(Graphics2D g2) {
        this.board.paintBoardOnGraphics2D(g2);
    }
    
    /**
     * Main game loop that plays pingball and draws the game.
     * @param canvas to draw game onto
     * @throws InterruptedException
     */
    public void gameLoop(JComponent canvas) throws InterruptedException{
        boolean gameIsOver = false;
        
        //game loop
        while(!gameIsOver){
            //used to time how long updating and drawing takes
            fieldTime = System.currentTimeMillis();
            synchronized(board){
                //update gadgets
                board.update(MILLISECS_PER_FRAME);
                
                //print the board and all of the gadgets
                System.out.println(board.toString());
            }
            //sleep to achieve desired FPS
            long elapsedTime = System.currentTimeMillis() - fieldTime;
            if(MILLISECS_PER_FRAME - elapsedTime > 0) {
                Thread.sleep(MILLISECS_PER_FRAME - elapsedTime); //10-20 frames per second
            } else {
                //System.err.println("Running Slow.");
            }
        }
    }
    
    /**
     * This is a pingball program that can be played in single-player or multi-player mode. In the case of multi-player,
     * this client connects to a server which allows this client to connect its board with other boards.
     * @param args command line arguments: specifies filename of board to use (required), and in the case of
     * multi-player, the hostname/IP address (optional) and port number (optional)
     * @throws IOException thrown if there is a problem with the input arguments
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        PingballGUI GUI;
        Pingball client;
        
        if (args.length == 0) {
            // Create a GUI with a blank board
            GUI = new PingballGUI();
            GUI.setVisible(true);
            return;
        }
        boolean singlePlayerMode = true;
        int port = DEFAULT_PORT;
        String hostName = "localhost";
        String filename = "";
        Board board = null;
        //Board board = new Board(defaultBoardName,0,0,0);
        
        for (int i=0; i<args.length; i++) {
            //System.out.println(args[i]);
            if (args[i].equals("--host") && i+1<args.length) {
                singlePlayerMode = false;
                hostName = args[i+1];
            }else if (args[i].equals("--port") && i+1<args.length){
                port = Integer.parseInt(args[i+1]);
            }else {
                if (i>0) {
                    if (!args[i-1].equals("--host") && !args[i-1].equals("--port")) {
                        filename = args[i];
                    }
                }else {
                    filename = args[i];
                }
            }
        }
        
        if (filename.length() > 0) {
            if(singlePlayerMode){
                client = new Pingball(filename);
                //client.gameLoop();
            }
            else{
                client = new Pingball(port,hostName, filename);
                //client.gameLoop();
            }
        }
        else{
            if(singlePlayerMode){
                client = new Pingball();
                //client.gameLoop();
            }
            else{
                client = new Pingball(port,hostName);
                //client.gameLoop();
            }
        }
        GUI = new PingballGUI(client);
        GUI.setVisible(true);
    }
    
    /**
     * @param textFile name of file from which to read Board, if the file follows the grammar specified at
     * http://web.mit.edu/6.005/www/fa14/projects/pb2/pingball-phase2-spec.html.
     * @throws IOException if the file does not match the specified grammar.
     * @return Board instance that represents the file (if in correct format)
     */
    public static Board parse(String textFile, Optional<Socket> socket) throws IOException {
        FileReader file = new FileReader(textFile);
        BufferedReader reader = new BufferedReader(file);
        StringBuilder fileContents = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){  //Read all of the file
            fileContents.append(line+"\n");
        }
        reader.close(); 
        ANTLRInputStream input = new ANTLRInputStream(fileContents.toString()); 
        PingballLexer lexer = new PingballLexer(input); 
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        tokenStream.fill();
        //System.out.println(tokenStream.getTokens());
        PingballParser parser = new PingballParser(tokenStream); 
        
        parser.addParseListener(new PingballBaseListener());
        ParseTree parseTree = parser.boardFile(); 
        ParseTreeListener boardFactory;
        if(socket.isPresent()){
            boardFactory = new PingballFactory(socket.get());
        }
        else{
            boardFactory = new PingballFactory();
        }
        
        ParseTreeWalker.DEFAULT.walk(boardFactory, parseTree);
        
        //System.out.println(parseTree.toStringTree());
        return ((PingballFactory) boardFactory).getBoard(); 
    }
    
}
