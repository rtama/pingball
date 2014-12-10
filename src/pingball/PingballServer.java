package pingball;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * This is a pingball server. It communicates with all of the clients in order to allow balls to travel between boards, link walls, and create portals.
 *  
 * Pingball server takes an optional port argument which must be in the range 0 to 65535 inclusive.
 *
 * Mutability comment:
 * Once created, its port and socket cannot be changed. 
 * 
 * Thread safety:
 * Messages are put in a BlockingQueue which is a thread safe datatype.
 * There is only one ServerBroadcaster thread and it only accesses the BlockingQueue
 * There is one ServerReceiver thread per client which accesses boardNames, clientConnections, portals, and queue. All 
 * accesses to the mutable lists are synchronized.
 * 
 */
public class PingballServer {
    
    //rep invariant:none
    
    /** Default server port. */
    private static final int DEFAULT_PORT = 10987;

    /** Socket for receiving incoming connections. */
    private final ServerSocket serverSocket;
    
    //sockets
    private List<PrintWriter> clientConnections = new ArrayList<PrintWriter>();
    //board names
    private Map<Integer, String> boardNames = new HashMap<Integer, String>();
    
    //thread safe message queue that will be used to communicate between the client threads
    private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
    
    //[[board,portal,looking for portal, is linked]]
    private List<ArrayList<String>> portals = new ArrayList<ArrayList<String>>();
    
    /**
     * Make a PingballServer that listens for connections on port.
     * 
     * @param port port number, requires 0 <= port <= 65535
     * @throws IOException if an error occurs opening the server socket
     */
    public PingballServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    
    public void checkRep(){
        // don't have to check, because using final variables
    }
    
    /**
     * Run the server, listening for client connections and creates a receiver thread for each client and a broadcaster thread that
     * sends messages to all clients. Also creates a thread to accept console input.
     * Never returns unless an exception is thrown.
     * @throws IOException if the main server socket is broken
     *                     (IOExceptions from individual clients do *not* terminate serve())
     */
    public void serve() throws IOException {
        //user input thread
        new Thread()
        {
            public void run() {
                while(true){
                    Scanner reader = new Scanner(System.in);
                    String consoleInput=reader.nextLine();
                    if(isValidInput(consoleInput)){
                        queue.add(consoleInput);
                    }else{
                        System.err.println("Invalid Command!");
                    }
                    //reader.close();
                }
            }
            /**
             * Checks if the console input is valid, meaning it follows the format:
             *      h NAME_left NAME_right (horizontal link)
             *      v NAME_top NAME_bottom (vertical link)
             *      uh NAME_left NAME_right (unlink horizontal)
             *      uv NAME_left NAME_right (unlink vertical)
             *      b NAME xPos yPos xVel yVel (add ball to board)
             *      bp NAME_board NAME_portal xVel yVel (add ball onto portal on board)
             *      np NAME_board NAME_portal NAME_linkboard NAME_linkportal (new portal with
             *          NAME_portal has been created on NAME_board and is linked to NAME_linkportal on NAME_linkboard)
             *      pr NAME_board NAME_portal BOOLEAN (portal reply to NAME_portal on NAME_board, BOOLEAN = 'true' if your target
             *          portal exists; 'false' otherwise)
             * a NAME must be comprised of alphanumeric characters and not begin with a number
             * @return true if console input is valid, false otherwise
             */
            public boolean isValidInput(String consoleInput){
                consoleInput=consoleInput.trim();
                String[] messageParts = consoleInput.split("\\s+");
                //System.out.println(messageParts[0]);
                String nameReq = "[A-Za-z][A-Za-z0-9]*";
                int minMessageLength = 3;
                if (messageParts.length < minMessageLength) {
                    return false;
                }
                String command = messageParts[0];
                if (command.equals("h") || command.equals("v") || command.equals("uh")
                        || command.equals("uv")) {
                    if (messageParts.length != minMessageLength) {
                        return false;
                    }
                    String name1 = messageParts[1];
                    String name2 = messageParts[2];
                    if (name1.matches(nameReq) && name2.matches(nameReq)) {
                        return true;
                    }return false;
                }
                return false;  // invalid command
            }
        }.start();
        
        //broadcasts received messages to every client
        (new Thread(new ServerBroadcaster(clientConnections,this.queue))).start();
        //server loop to that accepts new client connections
        while (true) {
            // block until a client connects
            Socket socket = serverSocket.accept();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //stores socket in list of client connections
            synchronized(clientConnections) {
                clientConnections.add(out);
                //receives messages from the server
                (new Thread(new ServerReceiver(socket,this.queue,this.boardNames,clientConnections.size()-1,portals))).start();
            }
        }
    }
    
    /**
     * This is a pingball server
     * @param args command line arguments: specifies port that the server should communicate on
     * @throws InterruptedException 
     * @throws IOException 
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        final int PORT;
        if(args.length==2&&args[0]=="--port"){
            PORT = Integer.parseInt(args[1]);
        }
        else {
            PORT = DEFAULT_PORT;
        }
        PingballServer server = new PingballServer(PORT);
        server.serve();
    }
}
