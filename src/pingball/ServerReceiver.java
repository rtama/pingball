package pingball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;


/**
 * A new serverReceiver is spawned for each client that is playing pingball when they connect to the server. 
 * Its job is to receive messages from the client and put them into the shared message queue.
 * 
 * Mutability comment:
 * socket, queue, boardNames, and portal are mutable
 *
 * Thread safety:
 * ServerReceiver is thread safe because it locks when it access boardNames and portals. Queue is a thread safe BlockingQueue data type.
 * 
 */
public class ServerReceiver implements Runnable {
    //rep invariant: none
    
    private Socket socket;
    private BlockingQueue<String> queue;
    
    private Map<Integer, String> boardNames = new HashMap<Integer, String>();
    private final int socketID;
    
    List<ArrayList<String>> portals;
    
    /**
     * Creates a new server receiver
     * 
     * @param socket connection to the client
     * @param queue message queue that is shared between all server receiver and server broadcaster threads
     * @param boardNames list containing the names of every client board
     * @param socketID unique id for the socket
     * @param portals list of portals and what they are connected to
     */
    public ServerReceiver(Socket socket, BlockingQueue<String> queue, Map<Integer, String> boardNames, int socketID, List<ArrayList<String>> portals) {
        this.socket=socket;
        this.queue=queue;
        this.boardNames = boardNames;
        synchronized(this.boardNames) {
            this.boardNames.put(socketID, "");
        }
        this.socketID=socketID;
        this.portals=portals;
    }

    /**
     * Handle a single client connection. When a message is received it is added to shared queue.
     * 
     * @param socket socket where the client is connected
     * @throws IOException if the connection encounters an error or terminates unexpectedly
     */
    public void handleConnection(Socket socket) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        try {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if(!isHello(line)&&!portalCommands(line)){
                    queue.add(line);
                }
            }
        } finally {
            in.close();
        }
    }
    
    /**
     * 
     * @param line to check whether it is a portal command
     * @return true if the line is a portal command, false otherwise
     */
    public boolean portalCommands(String line){
        String[] messageParts = line.split(" ");
        String command = messageParts[0];
        int ballPortalMessageLength = 5;
        if(command.equals("np")){
            ArrayList<String> portalBoard = new ArrayList<String>(); //[[board,portal,looking for board,looking for portal, is linked]]
            if (messageParts.length == ballPortalMessageLength) {
                String senderBoard = messageParts[1]; //sender board
                String senderPortal = messageParts[2]; //sender portal
                String targetBoard = messageParts[3]; //target board
                String targetPortal = messageParts[4]; //target portal
                
                portalBoard.add(senderBoard);
                portalBoard.add(senderPortal);
                portalBoard.add(targetBoard);
                portalBoard.add(targetPortal);

               synchronized(portals){
                    for(ArrayList<String> newPortal:portals){
                        if(newPortal.get(0).equals(targetBoard)&&newPortal.get(1).equals(targetPortal)){
                            String message = "pr " + senderBoard + " "+ senderPortal + " " + "true";
                            queue.add(message);
                        }
                        if(newPortal.get(2).equals(senderBoard)&&newPortal.get(3).equals(senderPortal)){
                            String message = "pr " + newPortal.get(0) + " "+ newPortal.get(1) + " " + "true";
                            queue.add(message);
                        }
        
                        
                    }
                }
                    portals.add(portalBoard);
            }
            return true;
        }
        return false;
    }
 
    /**
     * Checks if line is a hello message
     * @param line
     * @return true if line is a hello message, false otherwise
     */
    private boolean isHello(String line) {
        String[] messageParts = line.split(" ");
        if (messageParts.length != 2) { // since length of hello message is 2
            return false;
        }if (messageParts[0].equals("hello")){
            synchronized(this.boardNames) {
                this.boardNames.put(socketID, messageParts[1]);
            }
            return true;
        }
        return false;
    }

    /**
     * Send out the message that a socket has closed
     */
    private void sendCloseSocketMessage() {
        synchronized(this.boardNames) {
            if(boardNames.containsKey(socketID)){
                synchronized(this.portals){
                    Iterator<ArrayList<String>> iter = portals.iterator();
                    while (iter.hasNext()) {
                        ArrayList<String> portalConnection = iter.next(); 
                        if(portalConnection.get(2).equals(boardNames.get(socketID))){
                            final String INVALID_NAME = "$$";
                            portalConnection.set(2,INVALID_NAME);
                            portalConnection.set(3,INVALID_NAME);
                        }
                        if(portalConnection.get(0).equals(boardNames.get(socketID))){
                            iter.remove();
                        }
                    }
                }
                queue.add("bg "+boardNames.get(socketID));
                boardNames.remove(socketID);
            }
        }
    }
    
    @Override
    public void run() {
        try {
            handleConnection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                sendCloseSocketMessage();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
