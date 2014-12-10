package pingball;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * A new serverBroadcaster is created for each client that connects to the server.
 * It sends out messages.
 * 
 * Mutability comment:
 * Socket, queue, ad client Connections are mutable.
 *
 * Thread Safety:
 * Only one ServerBroadcaster is spawned by the server. It locks clientConnections when it accesses it. It uses queue which
 * is a BlockingQueue thread safe datatype.
 *
 */
public class ServerBroadcaster implements Runnable {
    
    //rep invariant: none
    
    List<PrintWriter> clientConnections;
    private BlockingQueue<String> queue;
    private String message;
    
    /**
     * Creates a new server broadcaster.
     * @param clientConnections used for communication between this and a client
     * @param queue containing messages to send out
     */
    public ServerBroadcaster(List<PrintWriter> clientConnections, BlockingQueue<String> queue) {
        this.clientConnections=clientConnections;
        this.queue=queue;
    }
    
    /**
     * Sends out message to every connected client
     * @throws IOException 
     */
    public void broadCastMessage(String message) throws IOException{
        synchronized(clientConnections){
            for(PrintWriter socket : clientConnections){
                System.out.println("Server sent: "+message);
                socket.println(message);
            }
        }
    }
    
    @Override
    public void run() {
        while(true){
            try {
                //gets the first element on the queue, pauses until there is something to take
                message = queue.take();
                broadCastMessage(message);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
            
        }

    }

}
