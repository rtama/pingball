package pingball;

import static physics.Geometry.reflectWall;
import static physics.Geometry.timeUntilWallCollision;

import java.awt.Graphics2D;

import physics.LineSegment;

/**
 * Immutable line bumper class.
 * 
 * Gadget used to create more complicated gadgets of different shapes
 * (squares, triangles). A line bumper does not include the endpoints
 * 
 * Mutability Comment: A line bumper is immutable
 * Thread Safety Argument: Gadgets that use line bumpers are contained to a single board so that 
 *      no other thread can access them at the same time. ThreadSafe. 
 * 
 * @author hlzhou, geronm, dmayo2
 */
public class LineBumper extends Gadget{
    
    private final int x1; //(x1, y1) is top left
    private final int y1;
    private final int x2;
    private final int y2;
    private static final double COEFFICIENT_OF_REFLECTION = 1.0;
    private final LineSegment line;
    
    /**
     * Creates a line bumper from a pair of coordinates
     * @param x1 x-value of the first coordinate used to define the line bumper
     * @param y1 y-value of the first coordinate used to define the line bumper
     * @param x2 x-value of the second coordinate used to define the line bumper
     * @param y2 y-value of the second coordinate used to define the line bumper
     */
    public LineBumper(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.line = new LineSegment(this.x1, this.y1, this.x2, this.y2);
    }
    
    /**
     * Creates a line bumper from a line segment
     * @param line LineSegment used to define where the line bumper is placed
     */
    public LineBumper(LineSegment line){
        this.x1 = (int) line.p1().x();
        this.y1 = (int) line.p1().y();
        this.x2 = (int) line.p2().x();
        this.y2 = (int) line.p2().y();
        this.line = line;
    }
    
    @Override
    public int getBoardX() {
        return this.x1;
    }

    @Override
    public int getBoardY() {
        return this.y1;
    }

    @Override
    public double getCoefficientOfReflection() {
        return COEFFICIENT_OF_REFLECTION;
    }

    @Override
    public void collideWithBall(Ball ball) {
        ball.setVelocity(reflectWall(this.line, ball.getVelocity(), getCoefficientOfReflection()));
    }

    @Override
    public void updateToTime(double timeToCollision) {
        // immutable. Nothing to update.
        
    }

    @Override
    public double collisionTime(Ball ball) {
        return timeUntilWallCollision(this.line, ball.getCircle(), ball.getVelocity());
    }

    @Override
    public char[][] getSymbol() {
        char[][] symbol = {{'L'}};
        return symbol;
    }

    @Override
    public void action() {
        // This gadget has no action.        
    }

    @Override
    public void drawCanvas(Graphics2D g2) {
    }

}
