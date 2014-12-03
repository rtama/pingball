package pingball;

import physics.*;

/**
 * 
 * A portal is circular hole with diameter 1L which teleports a ball to another 
 * portal gadget, possibly on a different board.
 *
 */
public class Portal extends Gadget {
    
    private final int boardX;
    private final int boardY;
    final static double RADIUS = 0.25;
    private final Circle circle;
    private Portal targetPortal; 
    private String targetPortalName; 
    private  String otherBoardName; 
    private final Board board; 
    private boolean portalInOtherBoard;
    private boolean targetPortalExists = false; 
    
    private void checkRep(){ 
        assert(boardX <= Board.WIDTH); 
        assert(boardY <= Board.HEIGHT);
    }
    /**
     * Portal Constructor if portal is on the same board. 
     * @param name Name of the Portal
     * @param boardX x-coordinate of the top left corner of the Portal
     * @param boardY y-coordinate of the top left corner of the Portal
     * @param targetPortal Portal to teleport ball to when a ball collides with this portal. 
     *                          Ball, keeps the same velocity vector. 
     *                          
     *  If targetPortal does not exist, or otherBoard is not connected to the server, the portal
     *  is ignored and a ball passes through it as if it where not there. 
     */
    public Portal(String name, int boardX, int boardY, String targetPortal, Board board){
        this.name = name; 
        this.boardX = boardX;
        this.boardY = boardY;
        this.circle = new Circle(boardX + RADIUS, boardY + RADIUS, RADIUS);
        this.board = board; 
        this.portalInOtherBoard = false; 
        this.targetPortalName = targetPortal;  
        
        //Broadcast creation 
        if (! board.getSinglePlayerMode()){ 
            board.sendMessage("np "+board.name + " " + name + " " + board.name + " " + targetPortal ); 
        }
  
        checkRep(); 
    }
    
    /**
     * Portal constructor with otherBoard parameter. For when portal is in another Board. 
     * @param name Name of the Portal
     * @param boardX x-coordinate of the top left corner of the Portal
     * @param boardY y-coordinate of the top left corner of the Portal
     * @param targetPortal Name of the Portal to teleport ball to when a ball collides with this portal. 
     *                          Ball, keeps the same velocity vector. 
     * @param otherBoard Name of Board where targetPortal can be found, if they are not on the same board.
     *  
     *  If targetPortal does not exist, or otherBoard is not connected to the server, the portal
     *  is ignored and a ball passes through it as if it where not there. 
     */
    public Portal(String name, int boardX, int boardY, String targetPortal, String otherBoardName, Board board){ 
        this.name = name; 
        this.boardX = boardX;
        this.boardY = boardY;
        this.circle = new Circle(boardX + RADIUS, boardY + RADIUS, RADIUS);
        this.otherBoardName = otherBoardName; 
        this.portalInOtherBoard = true; 
        this.board = board; 
        this.targetPortalName = targetPortal;
        
        //Broadcast creation 
        if (! board.getSinglePlayerMode()){  
            //System.out.println("np "+board.name + " " + name + " " + otherBoardName + " " + targetPortal ); 
            board.sendMessage("np "+board.name + " " + name + " " + otherBoardName + " " + targetPortal ); 
        }
        
        checkRep(); 
    }
    
    /**
     * 
     * @return true if the target Portal is in the other board
     */
    public boolean targetInOtherBoard(){ 
        return portalInOtherBoard; 
    }
    
    /**
     * Should only be called if portalInOtherBoard is true
     * @return 
     */
    public String getOtherBoardName(){ 
        return otherBoardName; 
    }
    
    @Override
    public int getBoardX() {
        return this.boardX;
    }

    @Override
    public int getBoardY() {
        return this.boardY;
    }

    @Override
    public double getCoefficientOfReflection() {
        //not applicable (the ball either passes the portal unaffected, 
        //or the ball is teleported)
        return 0; 
    }

    @Override
    public void collideWithBall(Ball ball) {
        //Teleport ball to otherPortal
        this.trigger();
        //System.out.println("Hit a portal " + targetPortalExists); 
        

        //Get Target Portal
        if (!portalInOtherBoard) { 
            if ( board.containsGadget(targetPortalName)){ 
                targetPortal = (Portal) board.getPortal(targetPortalName); 
                Vect vel = new Vect (ball.getXVel(), ball.getYVel()); 
                vel = vel.times(RADIUS * 1/vel.length()); 
                Ball newBall = new Ball(targetPortal.getBoardX()+vel.x(), targetPortal.getBoardY()+vel.y(), ball.getXVel(), ball.getYVel());
                ball.remove();
                board.addBallNext(newBall); 
                //System.out.println(newBall.getXVel()) ;
            }
            else {
                Vect vel = new Vect (ball.getXVel(), ball.getYVel()); 
                vel = vel.times(RADIUS * 1/vel.length());
                Ball newBall = new Ball (ball.getXPos() + vel.x(),ball.getYPos() + vel.y(), ball.getXVel(), ball.getYVel()); 
                board.addBallNext(newBall);
                //board.sendMessage("pb " + board.name + " " + this.name + " " + ball.getXVel() + " " + ball.getYVel());
                ball.remove();  
                return; //Target portal Does not exist
            }
        }
        
        else  { //Portal is in another board
            if (targetPortalExists){ 

                if (! board.getSinglePlayerMode()){ 
                    ball.remove();
                    board.sendMessage("bp " + otherBoardName + " " + targetPortalName + " " + ball.getXVel() + " " + ball.getYVel());
                }

            }
            else{ //Target portal does not exist
                //System.out.println("Target Portal does not Exist"); 
                Vect vel = new Vect (ball.getXVel(), ball.getYVel()); 
                vel = vel.times(RADIUS * 1/vel.length());
                Ball newBall = new Ball (ball.getXPos() + vel.x(),ball.getYPos() + vel.y(), ball.getXVel(), ball.getYVel()); 
                board.addBallNext(newBall);
                //board.sendMessage("pb " + board.name + " " + this.name + " " + ball.getXVel() + " " + ball.getYVel());
                ball.remove(); 
                return; 
            }
        }
    }

    /**
     * Sets whether the target Portal exists and updates location
     * @param exists True if target portal exists, false otherwise.
     * @param x x location of the target portal in the otherBoard
     * @param y y location of the target portal in the otherBoard
     */
    public void setTargetPortal(boolean exists){ 

       this.targetPortalExists  = exists; 
    }
    
    @Override
    public void updateToTime(double timeToCollision) {
     // immutable. Nothing to update.  
    }

    @Override
    public double collisionTime(Ball ball) {
        return Geometry.timeUntilCircleCollision(this.circle, ball.getCircle(), ball.getVelocity());
    }

    @Override
    public char[][] getSymbol() {
        char[][] symbol = {{'0'}};
        return symbol;
    }

    @Override
    public void action() {
        // This gadget has no action.
    }
}
