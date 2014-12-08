package pingball;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * The board is a 20L x 20L playing area that can contain gadgets on it
 * such as bumpers, flippers, absorbers, and balls.
 * If the board is multiplayer it can send messages to the host server
 * 
 * Thread safety:
 * Only one board is created per client. All messages are sent through board which makes writing to the socket thread safe.
 * Access to board in other classes is synchronized.
 */
public class Board {

    //rep invariant: has outer walls, height and width are 22 (including outer walls)

    /*
     * Mutability:
     * Board is mutable. Board has a name, width, height, outerwalls, gravity, and friction constants which are 
     * set upon creation and can never be changed. The gadgets and balls on the board can be 
     * mutated as well as removed. The socket is also mutable.
     */

    public final String name;

    //list of balls and gadgets
    public static final int WIDTH = 22;
    public static final int HEIGHT = 22;
    public static final char IGNORE_CHAR = 'N';
    public static final char BALL_CHAR = '*';

    private List<Gadget> gadgets = new ArrayList<Gadget>();
    private List<Ball> balls = new ArrayList<Ball>();
    private List<Ball> newBalls = new ArrayList<Ball>();
    private List<Gadget> portals = new ArrayList<Gadget>(); 

    //walls
    private final OuterWall topWall;
    private final OuterWall rightWall;
    private final OuterWall bottomWall;
    private final OuterWall leftWall;


    private final double mu;
    private final double mu2;
    private final double gravity;

    private boolean singlePlayerMode=false;

    private Socket socket;
    private PrintWriter out;

    private Map<String, ArrayList<String>> keyups;
    private Map<String, ArrayList<String>> keydowns;

    private Boolean paused = false;


    //the board constructors without names are here for testing purposes
    /**
     * Constructor for Board class: initializes a board object to handle the gadgets
     * within a Pingball game.
     * No name.
     * Has default values of friction and gravity:
     *           mu = .000025;
     *           mu2=.025;
     *           gravity=.000025;
     */ 
    public Board() {
        this("",.000025, .025, .000025);
    }

    /**
     * Constructor for Board class allowing for specification of friction and gravity;
     * constants used in the formula  Vnew = Vold × ( 1 - mu × deltat - mu2 × |Vold| × deltat ).
     * No name.
     * 
     * @param mu double specifying coefficient of friction
     * @param mu2 double specifying coefficient of friction
     * @param gravity double specifying the acceleration in the y-direction due to gravity (down being positive)
     */
    public Board(double mu, double mu2, double gravity) {
        this("", mu, mu2, gravity);
    }
    /**
     * Constructor for Board class allowing for specification of friction and gravity;
     * constants used in the formula  Vnew = Vold × ( 1 - mu × deltat - mu2 × |Vold| × deltat ).
     * No name. Multiplayer.
     * 
     * @param mu double specifying coefficient of friction
     * @param mu2 double specifying coefficient of friction
     * @param gravity double specifying the acceleration in the y-direction due to gravity (down being positive)
     * @param clientSocket socket that is used to comunicate to the server
     * @throws IOException if there is a socket error throws exception
     */
    public Board(double mu, double mu2, double gravity, Socket clientSocket) throws IOException {
        this("", mu, mu2, gravity,clientSocket);
    }

    /**
     * Constructor for Board class allowing for specification of friction and gravity and a name;
     * constants used in the formula  Vnew = Vold × ( 1 - mu × deltat - mu2 × |Vold| × deltat ).
     * 
     * @param name of this board
     * @param mu double specifying coefficient of friction
     * @param mu2 double specifying coefficient of friction
     * @param gravity double specifying the acceleration in the y-direction due to gravity (down being positive)
     */
    public Board(String name, double mu, double mu2, double gravity) {
        this.name = name;
        //create the outer walls
        this.topWall = OuterWall.TOP_WALL();
        this.leftWall = OuterWall.LEFT_WALL();
        this.rightWall = OuterWall.RIGHT_WALL();
        this.bottomWall = OuterWall.BOTTOM_WALL();

        gadgets.add(topWall);
        gadgets.add(leftWall);
        gadgets.add(rightWall);
        gadgets.add(bottomWall);

        //set values for friction and gravity
        this.mu = mu;
        this.mu2 = mu2;
        this.gravity = gravity;
    }
    /**
     * Constructor for Board class allowing for specification of friction and gravity and a name;
     * constants used in the formula  Vnew = Vold × ( 1 - mu × deltat - mu2 × |Vold| × deltat ).
     * Multiplayer
     * Sends a hello message to the server
     * 
     * @param name of this board
     * @param mu double specifying coefficient of friction
     * @param mu2 double specifying coefficient of friction
     * @param gravity double specifying the acceleration in the y-direction due to gravity (down being positive)
     * @param clientSocket socket that is used to communicate to the server
     * @throws IOException if there is a socket error throws exception
     */
    public Board(String name, double mu, double mu2, double gravity, Socket clientSocket) throws IOException {
        this(name, mu, mu2, gravity);
        this.socket=clientSocket;
        out = new PrintWriter(socket.getOutputStream(), true);
        //send hello message
        if(this.name!=""){
            out.println("hello "+this.name);
        }
    }

    public void checkRep(){
        // don't have to check, because using final variables
    }

    /**
     * Set whether this Board is being played in single-player or multi-player mode.
     * @param singlePlayerMode true if single-player, false if multi-player
     */
    public void setSinglePlayerMode(boolean singlePlayerMode){
        this.singlePlayerMode=singlePlayerMode;
    }

    /**
     * Check whether this Board is being played in single-player or multi-player mode.
     * @return true if single-player, false if multi-player
     */
    public boolean getSinglePlayerMode(){
        return singlePlayerMode;
    }

    /**
     * Send a message to the server
     * @param message to send
     */
    public void sendMessage(String message){
        if(this.singlePlayerMode==false && socket!=null){
            out.println(message);
        }
    }

    /**
     * Adds a ball to the board.
     * 
     * @param ball Ball object to be added to the board
     */
    public void addBall(Ball ball){
        //balls are gadgets, a reference to them is stored in both balls and
        //gadgets so that we know which gadgets are balls

        balls.add(ball);
        gadgets.add(ball);
    }

    /**
     * Adds a ball after the current timestep is over
     * @param ball Ball object to be added to the ball
     */
    public void addBallNext(Ball ball){
        newBalls.add(ball); 
    }

    /**
     * Adds a gadget to the board.
     * 
     * @param gadget Gadget object to be added to the board
     */
    public void addGadget(Gadget gadget){
        gadgets.add(gadget);
    }


    /**
     * Adds a portal to the board
     * @param portal
     */
    public void addPortal(Gadget portal){ 
        portals.add(portal); 
        this.addGadget(portal); 
    }

    /**
     * Gets a list of all of the portals on the board
     * @return all of the portals in the board
     */
    public List<Gadget> getPortals(){ 
        return this.portals; 
    }

    /**
     * Sets up a gadget on this board to trigger another gadget on this board.
     * @param trigger   name of gadget that is the trigger
     * @param action    name of gadget that responds to the trigger
     */
    public void addTrigger(String trigger, String action) {
        for (Gadget triggerGadget : gadgets) {
            if (triggerGadget.getName().equals(trigger)) {
                for (Gadget actionGadget : gadgets) {
                    if (actionGadget.getName().equals(action)) {
                        triggerGadget.addTriggeredGadget(actionGadget);
                    }
                }
            }
        }
    }

    /**
     * Checks whether this board contains a specified gadget.
     * @param gadgetName to check
     * @return true if this board contains a gadget with the specified name; false otherwise
     */
    public boolean containsGadget(String gadgetName) {
        for (Gadget gadget : gadgets) {
            if (gadget.getName().equals(gadgetName)) {
                return true;
            }
        }return false;
    }

    /**
     * Gets the portal with the specified name. It must exist on this board.
     * @param portalName
     * @return the portal on this board with name portalName
     */
    public Gadget getPortal(String portalName) {
        for (Gadget gadget : gadgets) {
            if (gadget.getName().equals(portalName)) {
                return gadget;
            }
        }
        System.err.println("You disobeyed the spec. Here's a top wall for you instead of a portal.");
        return OuterWall.TOP_WALL(); // This line should only occur if the user disobeyed the spec.
    }

    /**
     * Pauses this board.
     */
    public void pause() {
        synchronized(this.paused) {
            this.paused = true;   
        }
    }

    /**
     * Resumes playing this board.
     */
    public void resume() {
        synchronized(this.paused) {
            this.paused = false;            
        }
    }

    /**
     * Continually updates the position and velocity of gadgets, according 
     * to the physics of ball movement, gadget collision, and friction. 
     * 
     * @param initialTimeToEndOfFrame double indicating the number of milliseconds 
     * in a time frame
     */
    public void update(double initialTimeToEndOfFrame){
        synchronized(this.paused) {
            if (this.paused) {
                return;
            }

            //for every ball, every other ball/every gadget,
            // compute time until collision -> sorted list of collision events
            TreeSet<CollisionEvent> events;

            // time to end of frame. Changes over the course of the calculations.
            double timeToEndOfFrame = initialTimeToEndOfFrame;

            boolean notDoneColliding = true;

            //while first collisionevent time < end of frame time
            while (notDoneColliding) {

                //reset collision events tree
                events = new TreeSet<CollisionEvent>();

                // update all objects to new collision time, update collision times
                for (Ball firstBall : balls) {
                    if (!firstBall.isGhost() && !firstBall.isRemoved()) {
                        for(Gadget gadget : gadgets) {
                            if (!(gadget.isGhost())) {
                                if (firstBall != gadget) {
                                    events.add(new CollisionEvent(firstBall, gadget, gadget.collisionTime(firstBall)));
                                }
                            }
                        }
                    }
                }


                // try to get first collision (u,v)
                CollisionEvent firstCollisionEvent = events.pollFirst();
                if (!events.isEmpty() && firstCollisionEvent.getTimeToCollision() <= timeToEndOfFrame) {

                    // update all gadgets to collision time
                    for(Gadget gadget : gadgets) {
                        gadget.updateToTime(firstCollisionEvent.getTimeToCollision());
                    }

                    // collide gadget with ball, updating ball's velocity
                    firstCollisionEvent.getGadget().collideWithBall(firstCollisionEvent.getBall());                
                    timeToEndOfFrame -= firstCollisionEvent.getTimeToCollision();


                } 
                else {
                    notDoneColliding = false;       
                }

            }

            // update all to final time
            for(Gadget gadget : gadgets) {
                gadget.updateToTime(timeToEndOfFrame);
            }

            //Apply friction and gravity for previous timestep
            for (Ball ball : balls) {

                if (!ball.isGhost()){
                    ball.applyFriction(this.mu, this.mu2, initialTimeToEndOfFrame);
                    ball.applyGravity(this.gravity, initialTimeToEndOfFrame);
                }
            }

            //send balls to other client board
            //System.out.println("singlePlayerMode?" + this.singlePlayerMode);
            if(!this.singlePlayerMode){
                for (Ball ball : balls) {
                    //System.out.println("ball should send?" + ball.getShouldSend());
                    if (ball.getShouldSend()){
                        sendMessage(ball.getSendToBoardMessage());
                        //System.out.println("ball.getsendtoboardmessage" + ball.getSendToBoardMessage());
                    }
                }
            }

            //Remove balls   
            Iterator<Ball> iter = balls.iterator();
            while (iter.hasNext()) {
                Ball currentBall = iter.next(); 
                if (currentBall.isRemoved()) {
                    iter.remove(); //Remove from balls
                    gadgets.remove(currentBall); //Remove from gadgets. 
                }
            }

            //Add balls after the timestep. 
            for (Ball ball : newBalls){ 
                this.addBall(ball); 
            }
            newBalls.clear();   
        }
    }

    /**
     * Unlink the specified wall by making it solid.
     * @param type of wall (top, left, right, or bottom)
     */
    public void unlinkWall(wallType type) {
        if (type.equals(wallType.TOP_WALL)) {
            topWall.makeSolid();
        }else if (type.equals(wallType.LEFT_WALL)) {
            leftWall.makeSolid();
        }else if (type.equals(wallType.RIGHT_WALL)) {
            rightWall.makeSolid();
        }else {
            bottomWall.makeSolid();
        }
    }

    /**
     * Gets the name of the Board that is linked to the specified wall
     * @param type of wall to check (left, right, top or bottom)
     * @return name of Board that is connected to the wall; empty string if wall is unlinked
     */
    public String getBoardLink(wallType type) {
        if (type.equals(wallType.LEFT_WALL)) {
            return leftWall.getLinkedBoardName();
        }else if (type.equals(wallType.RIGHT_WALL)) {
            return rightWall.getLinkedBoardName();
        }else if (type.equals(wallType.TOP_WALL)) {
            return topWall.getLinkedBoardName();
        }else {
            return bottomWall.getLinkedBoardName();
        }        
    }

    /**
     * Links this board's specified wall to another board
     * @param type of wall to link (left, right, top or bottom)
     * @param board name of board to link to
     */
    public void linkWall(wallType type, String board) {
        if (type.equals(wallType.LEFT_WALL)) {
            leftWall.setLinkedBoardName(board);
        }else if (type.equals(wallType.RIGHT_WALL)) {
            rightWall.setLinkedBoardName(board);
        }else if (type.equals(wallType.TOP_WALL)) {
            topWall.setLinkedBoardName(board);
        }else {
            bottomWall.setLinkedBoardName(board);
        }
    }

    /**
     * Ascii representation of the board.
     * 
     * @return String representing the board with all of its gadgets/balls
     */
    @Override
    public String toString(){
        char[][] board = new char[22][22]; //output
        String output = "";

        //set up board
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board[i][j]=' ';
            }
        }

        //loop through gadgets and balls and put their representation in the board char array
        for(Gadget gadget: gadgets){
            //getSymbol size
            char[][] gadgetSymbol = gadget.getSymbol();
            //draw onto board
            for(int x = 0; x<gadgetSymbol.length; x++){
                for(int y = 0; y<gadgetSymbol[0].length; y++){
                    if (gadgetSymbol[x][y] != IGNORE_CHAR) {
                        int gadgetDrawX = 1 + x + gadget.getBoardX();
                        int gadgetDrawY = 1 + y + gadget.getBoardY();
                        if (board[gadgetDrawX][gadgetDrawY] != BALL_CHAR) { //TODO: Don't overwrite the Balls, it causes an array out of bounds exception 22
                            board[gadgetDrawX][gadgetDrawY] = gadgetSymbol[x][y];
                        }
                    }
                }
            }
        }

        //create output string
        for(int y=0; y<board[0].length; y++){
            for(int x=0; x<board.length; x++){
                output += board[x][y];
            }
            output+="\n";
        }
        return output;
    }

    /**
     * Used to create a visual representation of the board - not part of phase 2, just used for testing
     * @param g2 graphics to draw
     */
    public void paintBoardOnGraphics2D(Graphics2D g2) {
        int LPixelLength=10;
        for (Gadget gadget : gadgets) {
            if (!balls.contains(gadget)) {
                g2.draw(new Rectangle(gadget.getBoardX()*LPixelLength,gadget.getBoardY()*LPixelLength,LPixelLength,LPixelLength));
            }
        }

        for (Ball ball : balls) {
            g2.draw(new Ellipse2D.Double((ball.getXPos()-ball.getCircle().getRadius())*LPixelLength,(ball.getYPos()-ball.getCircle().getRadius())*LPixelLength,2*ball.getCircle().getRadius()*LPixelLength,2*ball.getCircle().getRadius()*LPixelLength));
        }
    }

    /**
     * Assign keyup dictionary to this board.
     * @param keys dictionary of keys and the gadgets they trigger upon key release
     */
    public void assignKeyups(Map<String, ArrayList<String>> keys) {
        // Defensive copying
        Map<String, ArrayList<String>> copy = new HashMap<String, ArrayList<String>>(keys);
        this.keyups = copy;
    }

    /**
     * Assign keydown dictionary to this board.
     * @param keys dictionary of keys and the gadgets they trigger upon key press
     */
    public void assignKeydowns(Map<String, ArrayList<String>> keys) {
        // Defensive copying
        Map<String, ArrayList<String>> copy = new HashMap<String, ArrayList<String>>(keys);
        this.keyups = copy;
    }

    /**
     * Respond to a key that was pressed with the action of every gadget triggered by that key press
     * @param key that was pressed
     */
    public void keyPressed(String key) {
        for (String gadgetName : keydowns.get(key)) {
            this.activate(gadgetName);
        }
    }

    /**
     * Respond to a key that was released with the action of every gadget triggered by that key release
     * @param key that was released
     */
    public void keyReleased(String key) {
        for (String gadgetName : keyups.get(key)) {
            this.activate(gadgetName);
        }
    }

    /**
     * Activate the specified gadget on this board (if it exists).
     * @param gadgetName name of gadget to activate
     */
    private void activate(String gadgetName) {
        for (Gadget gadget : gadgets) {
            if (gadget.name.equals(gadgetName)) {
                gadget.action();
            }
        }
    }

    /**
     * Reassigns this board's socket to newSocket
     * @param newSocket
     */
    public void changeSocket(Socket newSocket) {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.socket = newSocket;
    }

    /**
     * Disconnects this board from the server.
     */
    public void disconnect() {
        try {
            this.socket.close();
            this.singlePlayerMode = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // The following methods are used for testing.

    /**
     * 
     * @return the value of this board's gravity
     */
    public double getBoardGravity() {
        return gravity;
    }

    /**
     * 
     * @return the value of this board's mu (drag coefficient)
     */
    public double getBoardDrag() {
        return mu;
    }

    /**
     * 
     * @return the value of this board's mu2 (friction coefficient)
     */
    public double getBoardFriction() {
        return mu2;
    }

}
