package pingball;

import physics.Circle;
import physics.Vect;
import static physics.Geometry.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.lang.Math;

import javax.swing.JComponent;

/**
 * 
 * This is a ball. It has a position and a velocity. It can collide with other Gadgets and it is effected
 * by gravity and friction. Its representation on the board is a "*".
 *
 */
public class Ball extends Gadget {

    /*
     * Rep invariant: ball must always have a valid position on the board.
     * 
     * Rep: xPos and yPos are the floating-point location on the board. Nearest
     * integer values represent grid lines on the board. 
     * 
     * Mutability Comments: The position of the ball is private to each ball and can only 
     *      mutated by the mutator methods, which checkRep after completion.
     *      
     * Thread Safety Argument: Balls are not passed from board to board (client to client)
     *      instead, when a ball leaves a board (either through linked wall or portal), 
     *      the old ball is destroyed and a new one is created in it's place. 
     *      This ensures that balls are not shared between threads. 
     */
    
    //current position
    private double xPos;
    private double yPos;
    //current velocity
    private double xVel;
    private double yVel;
    //whether physics applies to a ball; whether it is moving
    private boolean ghost;
    private boolean removed; //If the ball should be removed on the next timestep
    
    private final double RADIUS=0.25;
    private String sendToBoard;
    private boolean shouldSend = false;
    
    private final double xMax = 19.75;     // No part of the .25 radius ball can be beyond 20,20
    private final double yMax = 19.75;
    private final double yMin = 0;    
    private final double xMin = 0; 
    
    private final double coefficientOfReflection = 1.0; 
    
    private void checkRep() {
        assert(xPos >= xMin && xPos <= xMax);
        assert(yPos >= yMin && yPos <= yMax);
    }
    
    /**
     * Creates a new ball at at a given position with 0 initial velocity
     * No name.
     * 
     * @param xPos initial x position of the ball relative to the top left corner of the board
     * @param yPos initial y position of the ball relative to the top left corner of the board
     */
    public Ball(double xPos, double yPos){
        this("", xPos, yPos);
        checkRep();
    }
    
    /**
     * Creates a new ball at at a given position with 0 initial velocity and a name
     * 
     * @param name of this ball
     * @param xPos initial x position of the ball relative to the top left corner of the board
     * @param yPos initial y position of the ball relative to the top left corner of the board
     */
    public Ball(String name, double xPos, double yPos){
        this(name, xPos, yPos, 0, 0); //0 initialXVelocity 0 initialYVelocity
        checkRep();
    }
    
    /**
     * Creates a new ball at at a given position with a given initial velocity
     * No name.
     * 
     * @param xPos initial x position of the ball relative to the top left corner of the board
     * @param yPos initial y position of the ball relative to the top left corner of the board
     * @param xVel initial velocity in the positive x direction
     * @param yVel initial velocity in the positive y direction
     */
    public Ball(double xPos, double yPos, double xVel, double yVel){
        this("", xPos, yPos, xVel, yVel);
        checkRep();
    }
    
    /**
     * Creates a new ball at at a given position with a given initial velocity and name
     * 
     * @param name of this ball
     * @param xPos initial x position of the ball relative to the top left corner of the board
     * @param yPos initial y position of the ball relative to the top left corner of the board
     * @param xVel initial velocity in the positive x direction
     * @param yVel initial velocity in the positive y direction
     */
    public Ball(String name, double xPos, double yPos, double xVel, double yVel){
        if (xPos < xMin) {
            xPos = xMin;
        }else if (xPos > xMax) {
            xPos = xMax;
        }
        if (yPos < yMin) {
            yPos = yMin;
        }else if (yPos > yMax) {
            yPos = yMax;
        }
        this.name = name;
        this.xPos=xPos;
        this.yPos=yPos;
        this.xVel=xVel;
        this.yVel=yVel;
        this.ghost = false;
        this.removed = false; 
        checkRep();
    }
    
    /**
     * gets the x position of the ball on the board
     * @return x position of the ball on the board
     */
    public double getXPos() {
        return xPos;
    }

    /**
     * sets the x position of the ball on the board
     * @param xPos new x position of the ball on the board
     */
    public void setXPos(double xPos) {
        if (xPos < xMin) {
            xPos = xMin;
        }else if (xPos > xMax) {
            xPos = xMax;
        }
        this.xPos = xPos;
        checkRep();
    }

    /**
     * gets the y position of the ball on the board
     * @return y position of the ball on the board
     */
    public double getYPos() {
        return yPos;
    }

    /**
     * sets the y position of the ball on the board
     * @param xPos new y position of the ball on the board
     */
    public void setYPos(double yPos) {
        if (yPos < yMin) {
            yPos = yMin;
        }else if (yPos > yMax) {
            yPos = yMax;
        }
        this.yPos = yPos;
        checkRep();
    }

    /**
     * gets the velocity of ball in the x direction
     * @return velocity of the ball in the x direction
     */
    public double getXVel() {
        return xVel;
    }
    
    /**
     * sets the velocity of the ball in the x direction
     * @param xVel new velocity of the ball in the x direction
     */
    public void setXVel(double xVel) {
        this.xVel = xVel;
        checkRep();
    }
    
    /**
     * gets the velocity of ball in the y direction
     * @return velocity of the ball in the y direction
     */
    public double getYVel() {
        return yVel;
    }
    
    /**
     * sets the velocity of the ball in the y direction
     * @param yVel new velocity of the ball in the y direction
     */    
    public void setYVel(double yVel) {
        this.yVel = yVel;
        checkRep();
    }
    
    @Override
    public int getBoardX() {
        //returns x position of the center of the ball
        return (int)Math.floor(xPos);
    }
    @Override
    public int getBoardY() {
        //returns y position of the center of the ball
        return (int)Math.floor(yPos);
    }

    @Override
    public char[][] getSymbol() {
        char[][] symbol = {{'*'}};
        return symbol;
    }
    
    public void sendToBoardMessage(String boardName, wallType type){
        this.shouldSend = true;
        if(type.equals(wallType.LEFT_WALL)){
            int xCoord = 19; //rightmost x coordinate
            sendToBoard = "b "+boardName+" "+xCoord+" "
            +this.getBoardY()+" "+this.getXVel()+" "+this.getYVel();
        }
        else if(type.equals(wallType.RIGHT_WALL)){
            int xCoord = 0; //leftmost x coordinate
            sendToBoard = "b "+boardName+" "+xCoord+" "
            +this.getBoardY()+" "+this.getXVel()+" "+this.getYVel();
        }
        else if(type.equals(wallType.TOP_WALL)){
            int yCoord = 19; //bottommost y coordinate
            sendToBoard = "b "+boardName+" "+this.getBoardX()+" "
            +yCoord+" "+this.getXVel()+" "+this.getYVel();
        }
        else if(type.equals(wallType.BOTTOM_WALL)){
            int yCoord = 0; //topmost y coordinate
            sendToBoard = "b "+boardName+" "+this.getBoardX()+" "
            +yCoord+" "+this.getXVel()+" "+this.getYVel();
        }
    }
    public boolean getShouldSend(){
        return shouldSend;
    }
    public String getSendToBoardMessage(){
        return sendToBoard;
    }

    @Override
    public void updateToTime(double timeToCollision) {
        //Simple linear update
        xPos += xVel * timeToCollision;
        yPos += yVel * timeToCollision;
        if (xPos < xMin) {
            xPos = xMin;
        }else if (xPos > xMax) {
            xPos = xMax;
        }
        if (yPos < yMin) {
            yPos = yMin;
        }else if (yPos > yMax) {
            yPos = yMax;
        }
        checkRep();
    }

    @Override
    public double collisionTime(Ball that) {
        return timeUntilBallBallCollision(this.getCircle(), this.getVelocity(), that.getCircle(), that.getVelocity());
    }

    @Override
    public void collideWithBall(Ball that) {
        VectPair thisNewVelthatNewVel = reflectBalls(this.getCircle().getCenter(), this.getCoefficientOfReflection(), this.getVelocity(),
                                                     that.getCircle().getCenter(), that.getCoefficientOfReflection(), that.getVelocity());
        this.setVelocity(thisNewVelthatNewVel.v1);
        that.setVelocity(thisNewVelthatNewVel.v2);
        trigger();
        checkRep();
    }

    @Override
    public double getCoefficientOfReflection() {
        return this.coefficientOfReflection;
    }

    /**
     * Gets the circle bounding box around the ball to be used for collisions
     * @return circle bounding box around the ball
     */
    public Circle getCircle() {
        return new Circle(getXPos(),getYPos(),RADIUS);
    }
    
    /**
     * Gets the current velocity of the ball
     * @return current velocity of the ball as a Vect
     */
    public Vect getVelocity() {
        return new Vect(getXVel(),getYVel());
    }
    
    /**
     * Sets the velocity of the ball
     * @param theVel Vect representing the new velocity of the ball
     */
    public void setVelocity(Vect theVel) {
        xVel = theVel.x();
        yVel = theVel.y();
        checkRep();
    }
    
    
    /**
     * Sets whether a ball is ghost (whether physics applies/
     * whether the velocity is zero)
     * @return boolean indicating whether a ball is ghost
     */
    public boolean isGhost() {
        return ghost;
    }

    /**
     * Sets whether a ball is ghost (whether the velocity is zero/
     * whether physics applies)
     * @param ghost boolean whether a ball is ghost or not
     */
    public void setGhost(boolean ghost) {
        this.ghost = ghost;
        checkRep();
    }

    /**
     * 
     * @return true if the ball should be removed
     */
    public boolean isRemoved(){
        return this.removed; 
    }
    
    /**
     * Removes the ball on the next timestep. 
     */
    public void remove(){ 
        this.removed = true; 
        checkRep();
    }
    
    /**
     * Applies frictional forces to the ball and mutates the balls velocity accordingly
     * @param mu friction1 constant
     * @param mu2 friction2 constant
     * @param deltaT timestep
     */
    public void applyFriction(double mu, double mu2, double deltaT) {
        // Vnew = Vold × ( 1 - mu × deltat - mu2 × |Vold| × deltat )
        double velMagnitudeOld = Math.hypot(xVel,yVel);
        xVel = xVel * Math.max(0,(1 - (mu*deltaT) - (mu2*velMagnitudeOld*deltaT)));
        yVel = yVel * Math.max(0,(1 - (mu*deltaT) - (mu2*velMagnitudeOld*deltaT)));
        checkRep();
    }
    
    /**
     * Apples gravitational forces to the ball and mutates the y component of the velocity accordingly
     * @param gravity
     * @param deltaT
     */
    public void applyGravity(double gravity, double deltaT) {
        // Vnew = Vold + Accel*dT
        yVel += gravity*deltaT;
        checkRep();
    }

    @Override
    public void action() {
        // Ball has no action      
    }

    @Override
    public void drawCanvas(Graphics2D g2d) {
        System.out.println("printing ball");
        Shape shape = new Ellipse2D.Double( (this.xPos + 1 - this.RADIUS)*scaleFactor, (this.yPos + 1- this.RADIUS)*scaleFactor, 2*this.RADIUS*scaleFactor,2*this.RADIUS*scaleFactor);
        // Multiply the radius by 2 because we want the length and width of the bounding box

        g2d.setColor(Color.BLACK);
        g2d.draw(shape);
        g2d.setColor(Color.YELLOW);
        g2d.fill(shape);
    }
}
