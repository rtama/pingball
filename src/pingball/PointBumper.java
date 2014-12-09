package pingball;

import static physics.Geometry.reflectCircle;
import static physics.Geometry.timeUntilCircleCollision;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

import physics.Circle;

/**
 * Immutable point bumper class.
 * 
 * Gadget used to create the corners of more complicated gadgets 
 * of different shapes (squares, triangles)
 * 
 * @author hlzhou, geronm, dmayo2
 */
public class PointBumper extends Gadget{

    private final int xVal;
    private final int yVal;
    private static final double COEFFICIENT_OF_REFLECTION = 1.0;
    private final Circle point;
    
    /**
     * Creates a point bumper from a coordinate
     * @param xVal x-value of the coordinate used to define the point bumper
     * @param yVal y-value of the coordinate used to define the point bumper
     */
    public PointBumper(int xVal, int yVal){
        this.xVal = xVal;
        this.yVal = yVal;
        this.point = new Circle(xVal, yVal, 0);
    }
    
    /**
     * Creates a point bumper from the center of a circle (bumper still has
     * radius of 0, regardless of the size of the circle)
     * @param circle Circle whose center is taken to make a point bumper
     */
    public PointBumper(Circle circle){
        this.xVal = (int) circle.getCenter().x();
        this.yVal = (int) circle.getCenter().y();
        this.point = new Circle(this.xVal, this.yVal, 0);
    }
    
    @Override
    public int getBoardX() {
        return xVal;
    }

    @Override
    public int getBoardY() {
        return yVal;
    }

    @Override
    public double getCoefficientOfReflection() {
        return COEFFICIENT_OF_REFLECTION;
    }

    @Override
    public void collideWithBall(Ball ball) {
        ball.setVelocity(reflectCircle(this.point.getCenter(), ball.getCircle().getCenter(),  ball.getVelocity(), getCoefficientOfReflection()));
    }

    @Override
    public void updateToTime(double timeToCollision) {
        // immutable. Nothing to update.
    }

    @Override
    public double collisionTime(Ball ball) {
        return timeUntilCircleCollision(this.point, ball.getCircle(), ball.getVelocity());
    }

    @Override
    public char[][] getSymbol() {
        char[][] symbol = {{'P'}};
        return symbol;
    }

    @Override
    public void action() {
        // This gadget has no action.
    }

    public void drawCanvas(Graphics2D g2d) {
    }

}
