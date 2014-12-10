package pingball;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import physics.Vect;

/**
 * A gadget that simulates the ball-return mechanism in a pinball game.
 * When a ball hits an absorber, the absorber stops the ball and holds it 
 * (unmoving) in the bottom right-hand corner of the absorber. The ball’s 
 * center is .25L from the bottom of the absorber and .25L from the right 
 * side of the absorber. The absorber launches any ball being held towards 
 * the top of the board at 50L/sec when it is triggered.
 * 
 */

public class Absorber extends Gadget{
    
    //Rep invariant: any balls held in absorber are held 0.25L from the bottom side of the absorber and 0.25L from the right side of the absorber 
    
    /*
     * Mutability:
     * The position, height, width, and coefficient of reflection of an absorber are
     * immutable. An absorber can hold a ball, which is mutable.
     */
    
    /*
     * Thread safety argument:
     * The only mutable part of the absorber is the ball that it holds. Balls are only moved
     * by the update function of the single board that they are on. The update method has the
     * gadget (in this case the absorber) move the ball. Only once the absorber is done moving the
     * ball will physics be applied to it. So the ball will always be where the absorber thinks it is.
     */
    
    private final int boardX;
    private final int boardY;
    private final int width;
    private final int height;
    private final double COEFFICIENT_OF_REFLECTION = 1.0;
    private final double MAX_BOARD_X = 19.75; // size of board - ball radius
    private final double MAX_BOARD_Y = 19.75;
    private final int SCALE_FACTOR = 20;
    private Color color = Color.RED;
    private final Color LIGHT_RED = new Color(210, 54, 65);
    
    private boolean isHoldingBall = false;
    private List<Ball> heldBall = new ArrayList<Ball>();
    
    private void checkRep() {
        for (Ball ball : heldBall) {
            double ballExpectedX = boardX + width - ball.getCircle().getRadius();
            double ballExpectedY = boardY + height - ball.getCircle().getRadius();
            if (ballExpectedX > MAX_BOARD_X) {
                ballExpectedX = MAX_BOARD_X;
            }
            if (ballExpectedY > MAX_BOARD_Y) {
                ballExpectedY = MAX_BOARD_Y;
            }
            assert(ball.getXPos() == ballExpectedX);
            assert(ball.getYPos() == ballExpectedY);
        }
    }
    
    /**
     * Creates an absorber at the given location with the given height and width
     * No name
     * 
     * @param xPos initial x position of the ball relative to the top left corner of the board
     * @param yPos initial y position of the ball relative to the top left corner of the board
     * @param width width of the absorber, positive integer <=20
     * @param height height of the absorber, positive integer <=20
     */
    public Absorber(int boardX, int boardY, int width, int height){
        this("", boardX, boardY, width, height);
    }
    
    /**
     * Creates an absorber at the given location with the given height and width and name
     * 
     * @param name of the absorber
     * @param xPos initial x position of the ball relative to the top left corner of the board
     * @param yPos initial y position of the ball relative to the top left corner of the board
     * @param width width of the absorber, positive integer <=20
     * @param height height of the absorber, positive integer <=20
     */
    public Absorber(String name, int boardX, int boardY, int width, int height){
        this.name = name;
        this.boardX=boardX;
        this.boardY=boardY;
        this.width = width;
        this.height = height;
        checkRep();
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
        return this.COEFFICIENT_OF_REFLECTION;
    }

    @Override
    public void collideWithBall(Ball ball) {
        //If the absorber is holding a ball, then the action of an absorber, when it is triggered, is to shoot the ball
        //straight upwards in the direction of the top of the playing area. By default, the initial velocity of the ball
        //should be 50L/sec. With the default gravity and the default values for friction, the value of 50L/sec gives
        //the ball enough energy to lightly collide with the top wall, if the bottom of the absorber is at y=20L. If the 
        //absorber is not holding the ball, or if the previously ejected ball has not yet left the absorber, then the
        //absorber takes no action when it receives a trigger signal.
        if (isHoldingBall) this.action(); 
        //this.action();
        //stop the ball and hold it (unmoving) in the bottom right-hand corner of the absorber
        //ball’s center is .25L from the bottom of the absorber and .25L from the right side of the absorber
        isHoldingBall=true;
        ball.setXPos(this.getBoardX()+this.width-ball.getCircle().getRadius());        
        ball.setYPos(this.getBoardY()+this.height-ball.getCircle().getRadius());
        ball.setVelocity(new Vect(0.0,0.0));
        ball.setGhost(true);
        this.heldBall.add(ball);
        
        if (color == Color.RED) 
        	color = LIGHT_RED;
        else
        	color = Color.RED;
        //Note: the absorber can be self triggering, it will move the ball to the bottom right and then shoot it upwards
        this.trigger();
        //this.action(); 
        checkRep();
    }
    
    @Override
    public void action() {
        if(isHoldingBall){
            //if the absorber is holding a ball, when triggered shoot the first ball straight upwards 
            //with an initial velocity of 50L/sec
            this.heldBall.get(0).setGhost(false);
            this.heldBall.get(0).setYPos(this.getBoardY() - heldBall.get(0).getCircle().getRadius());
            this.heldBall.get(0).setXPos(this.getBoardX()+this.width-this.heldBall.get(0).getCircle().getRadius());
            this.heldBall.get(0).setVelocity(new Vect(0.0,-0.050));
            isHoldingBall=false;
            this.heldBall.remove(0);
        }
        //if the absorber is not holding the ball, or if the previously ejected ball has not yet left the absorber,
        //then the absorber takes no action when it receives a trigger signal.
        checkRep();
    }

    @Override
    public void updateToTime(double timeToCollision) {
        if (this.heldBall.size()>0){
            this.heldBall.get(0).setVelocity(new Vect(0.0,0.0));
        }
        checkRep();
    }
    
    /**
     * Observer method indicating the number of balls being held in the absorber
     * @return number of balls being held in the absorber
     */
    public int numberOfHeldBalls(){
        return this.heldBall.size();
    }

    @Override
    public double collisionTime(Ball ball) {
        Gadget topOfAbsorber = new LineBumper(this.boardX, this.boardY, this.boardX + this.width, this.boardY);
        Gadget bottomOfAbsorber = new LineBumper(this.boardX, this.boardY+this.height, this.boardX + this.width, this.boardY+this.height);
        return Math.min(topOfAbsorber.collisionTime(ball), bottomOfAbsorber.collisionTime(ball));
    }

    @Override
    public char[][] getSymbol() {
        char[][] output = new char[this.width][this.height];
        for(int x=0; x<this.width; x++){
            for(int y=0; y<this.height; y++){
                output[x][y] = '=';
            }
        }
        if (this.heldBall.size()>0){    // Subtract 1 from width and height because arrays are indexed at 0
            output[this.width-1][this.height-1] = this.heldBall.get(0).getSymbol()[0][0];   // Get 1st elem in heldBall list
                                                                                            // Get ball's symbol[0][0] because ball is represented by only 1 char
        }
        return output;
    }

    @Override
    public void drawCanvas(Graphics2D g2d) {
        Shape shape = new Rectangle2D.Double((boardX+1)*SCALE_FACTOR, (boardY+1)*SCALE_FACTOR, this.width*SCALE_FACTOR, this.height*SCALE_FACTOR);
        // Add 1 to position to account for outer walls
        g2d.setColor(Color.BLACK);
        g2d.draw(shape);
        g2d.setColor(color);
        g2d.fill(shape);
    }

}
