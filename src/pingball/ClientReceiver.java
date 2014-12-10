package pingball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import physics.Vect;


/**
 * A new ClientReceiver is spawned for each client that is playing pingball 
 * in order for clients to handle messages from the server
 * 
 * Thread safety:
 * Only one ClientReceiver thread is spawned. Board is synchronized in client receiver and pingball which are the only two
 * places where it is accessed. Messages are sent through board which is synchronized.
 * 
 */
public class ClientReceiver implements Runnable {
    
    //rep invariant: none
    
    /*
     * Mutability:
     * Board and socket are both mutable
     */
    
    private Socket socket;
    private Board board;
    /**
     * Creates a new server receiver
     * 
     * @param socket connection to the client
     * @param board board that the client is playing pingball on
     */
    public ClientReceiver(Socket socket, Board board) {
        this.socket = socket;
        this.board = board;
    }

    public void checkRep(){
        // don't have to check, no repinvariants
    }
    
    /**
     * Handles a single client connection.
     * 
     * @throws IOException if the connection encounters an error or terminates unexpectedly
     */
    public void handleConnection() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        try {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                handleRequest(line);
            }
        } finally {
            //out.close();
            in.close();
        }
    }

    /**
     * Handles message received from the server
     * Message must be in one of the forms:
     *      h NAME_left NAME_right (horizontal link)
     *      v NAME_top NAME_bottom (vertical link)
     *      b NAME xPos yPos xVel yVel (add ball to board)
     *      uh NAME_left NAME_right (unlink horizontal)
     *      uv NAME_left NAME_right (unlink vertical)
     *      pr NAME_Board NAME_portal Boolean (Portal Reply)
     *      bp NAME_board NAME_portal (Portal add ball)
     *      bg NAME_board (Board gone)
     * NAMEs must be alphanumeric and not begin with a number     

     * @param message message received from the server
     * @throws IOException error receiving message from the server
     */
    private void handleRequest(String message) throws IOException {

        System.out.println("Client Received:"+message);
        String[] messageParts = message.split(" ");
        String command = messageParts[0];
        final int BG_MESSAGE_LENGTH = 2;
        synchronized(board){
            if (command.equals("bg")) {
                if (messageParts.length == BG_MESSAGE_LENGTH) {
                    // Unlink all outerwalls connected to this board
                    String boardName = messageParts[1];
                    wallType[] walltypes = new wallType[] {wallType.LEFT_WALL, wallType.RIGHT_WALL, wallType.TOP_WALL, wallType.BOTTOM_WALL};
                    for (wallType type : walltypes) {
                        if (board.getBoardLink(type).equals(boardName)) {
                            board.unlinkWall(type);
                        }
                    }
                                   
                    //Get all the portals
                    for (Gadget portal : board.getPortals()){ 
                        if (((Portal) portal).targetInOtherBoard() && ((Portal) portal).getOtherBoardName().equals(boardName)){ 
                            ((Portal) portal).setTargetPortal(false); //Unlink portal
                        }
                    }
                    
                }            
            }
            
            if(message.contains(board.name)){
                // for horizontal linking
                if (command.equals("h")) {
                    String leftBoard = messageParts[1];
                    String rightBoard = messageParts[2];
    
                    if (leftBoard.equals(board.name)) {
                        // this board is the left board, so link the right wall to the other board
                        // first check if this board's wall is already linked; if so, send a message to unlink it
                        String alreadyLinkedBoard = board.getBoardLink(wallType.RIGHT_WALL);
                        if (alreadyLinkedBoard.length() > 0) { // wall already linked
                            // unlink it
                            String unlinkMessage = "uh " + leftBoard + " " + alreadyLinkedBoard;
                            board.sendMessage(unlinkMessage);
                        }
                        //System.out.println("linking " + board.name + " with " + rightBoard);
                        board.linkWall(wallType.RIGHT_WALL, rightBoard);
                    }
                    if (rightBoard.equals(board.name)) {
                        // first check if this board's wall is already linked; if so, send a message to unlink it
                        String alreadyLinkedBoard = board.getBoardLink(wallType.LEFT_WALL);
                        if (alreadyLinkedBoard.length() > 0) { // wall already linked
                            // unlink it
                            String unlinkMessage = "uh " + leftBoard + " " + alreadyLinkedBoard;
                            board.sendMessage(unlinkMessage);
                        }
                        //System.out.println("linking " + board.name + " with " + leftBoard);
                        board.linkWall(wallType.LEFT_WALL, leftBoard);
                    }
                }
                
                // for vertical linking
                else if (command.equals("v")) {
                    String topBoard = messageParts[1];
                    String bottomBoard = messageParts[2];
    
                    if (topBoard.equals(board.name)) {
                        // this board is the top board, so link the bottom wall to the other board
                        // first check if this board's wall is already linked; if so, send a message to unlink it
                        String alreadyLinkedBoard = board.getBoardLink(wallType.BOTTOM_WALL);
                        if (alreadyLinkedBoard.length() > 0) { // wall already linked
                            // unlink it
                            String unlinkMessage = "uv " + topBoard + " " + alreadyLinkedBoard;
                            board.sendMessage(unlinkMessage);
                        }
                        board.linkWall(wallType.BOTTOM_WALL, bottomBoard);
                    }
                    if (bottomBoard.equals(board.name)) {
                        // first check if this board's wall is already linked; if so, send a message to unlink it
                        String alreadyLinkedBoard = board.getBoardLink(wallType.TOP_WALL);
                        if (alreadyLinkedBoard.length() > 0) { // wall already linked
                            // unlink it
                            String unlinkMessage = "uv " + topBoard + " " + alreadyLinkedBoard;
                            board.sendMessage(unlinkMessage);
                        }board.linkWall(wallType.TOP_WALL, topBoard);
                    }
                }
                
                // for horizontal unlinking
                else if (command.equals("uh")) {
                    String leftBoard = messageParts[1];
                    String rightBoard = messageParts[2];
    
                    if (leftBoard.equals(board.name)) {
                        // this board is the left board, so unlink the right wall from the other board
                        // first check if this board's wall is actually linked to that board
                        if (board.getBoardLink(wallType.RIGHT_WALL).equals(rightBoard)) {
                            // confirmed that wall is indeed linked to the other board
                            // unlink it
                            board.unlinkWall(wallType.RIGHT_WALL);
                        }
                    }
                    if (rightBoard.equals(board.name)) {
                        // this board is the right board, so unlink the left wall from the other board
                        // first check if this board's wall is actually linked to that board
                        if (board.getBoardLink(wallType.LEFT_WALL).equals(leftBoard)) {
                            // confirmed that wall is indeed linked to the other board
                            // unlink it
                            board.unlinkWall(wallType.LEFT_WALL);
                        }
                    }
                }
                
                // for vertical unlinking
                else if (command.equals("uv")) {
                    String topBoard = messageParts[1];
                    String bottomBoard = messageParts[2];
    
                    if (topBoard.equals(board.name)) {
                        // this board is the top board, so unlink the bottom wall from the other board
                        // first check if this board's wall is actually linked to that board
                        if (board.getBoardLink(wallType.BOTTOM_WALL).equals(bottomBoard)) {
                            // confirmed that wall is indeed linked to the other board
                            // unlink it
                            board.unlinkWall(wallType.BOTTOM_WALL);
                        }
                    }
                    if (bottomBoard.equals(board.name)) {
                        // this board is the bottom board, so unlink the top wall from the other board
                        // first check if this board's wall is actually linked to that board
                        if (board.getBoardLink(wallType.TOP_WALL).equals(topBoard)) {
                            // confirmed that wall is indeed linked to the other board
                            // unlink it
                            board.unlinkWall(wallType.TOP_WALL);
                        }
                    }
                }
                else if (command.equals("b")) {
                    assert(messageParts[1].equals(board.name));
                    assert(messageParts.length == 6);
                    double xPos = Double.parseDouble(messageParts[2]);
                    double yPos = Double.parseDouble(messageParts[3]);
                    double xVel = Double.parseDouble(messageParts[4]);
                    double yVel = Double.parseDouble(messageParts[5]);
                    Ball newBall = new Ball(xPos, yPos, xVel, yVel);
                    board.addBallNext(newBall);
                }
                
                else if (command.equals("pr")){ //Portal Reply
                    String boardName = messageParts[1]; 
                    String toPortalName = messageParts[2]; 
                    Boolean targetPortalExists = messageParts[3].equals("true");
    
                    
                    if (boardName.equals(board.name)){                  
                        if (board.containsGadget(toPortalName)){ 
                            Portal toPortal = (Portal) board.getPortal(toPortalName); 
                            toPortal.setTargetPortal(targetPortalExists); 
                        }            
                    }
                }
                
                else if (command.equals("bp")){ 
              
                    String boardName = messageParts[1]; 
    
                    if (boardName.equals(board.name)){ 
      
                        String targetPortalName = messageParts[2]; 
                        Double ballXVel = Double.parseDouble(messageParts[3]); 
                        Double ballYVel = Double.parseDouble(messageParts[4]);
                        Portal targetPortal = (Portal) board.getPortal(targetPortalName);
                        
                        
                        Vect vel = new Vect (ballXVel, ballYVel); 
                        vel = vel.times(Portal.RADIUS * 1/vel.length()); 
                        Ball newBall = new Ball(targetPortal.getBoardX()+vel.x(), targetPortal.getBoardY()+vel.y(), ballXVel, ballYVel);
                        
                        board.addBallNext(newBall);
    
                    }           
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            handleConnection();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
