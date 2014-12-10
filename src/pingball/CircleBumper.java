package pingball;

import static physics.Geometry.reflectCircle;
import static physics.Geometry.timeUntilCircleCollision;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import physics.Circle;

/**
 * Immutable circle bumper class.
 * 
 * A circle bumper can be placed on the board, and will have the ball
 * bounce off of it when a collision with the ball occurs.
 * 
 */
public class CircleBumper extends Gadget{

    //Rep invariant: Circle bumper has diameter 1L
    
    private final int boardX;
    private final int boardY;
    private final static double RADIUS = 0.5;
    private final static double COEFFICIENT_OF_REFLECTION = 1.0;
    private final Circle circle;
    private Color color = Color.ORANGE;
    private final Color LIGHT_ORANGE = new Color(255, 102, 0);
    
    /**
     * Creates a circle bumper of diameter 1L from a pair of coordinates 
     * indicating the location of the bounding box of its top left corner
     * No name.
     * 
     * @param boardX x-coordinate of bounding box's top left corner
     * @param boardY y-coordinate of bounding box's top left corner
     */
    public CircleBumper(int boardX, int boardY){
        this("", boardX, boardY);
    }

    public void checkRep(){
        // don't have to check, because using final variables
    }
    
    /**
     * Creates a circle bumper of diameter 1L from a pair of coordinates 
     * indicating the location of the bounding box of its top left corner
     * Has a name
     * @param name of this circle bumper
     * @param boardX x-coordinate of bounding box's top left corner
     * @param boardY y-coordinate of bounding box's top left corner
     */
    public CircleBumper(String name, int boardX, int boardY){
        this.name = name;
        this.boardX = boardX;
        this.boardY = boardY;
        this.circle = new Circle(boardX + RADIUS, boardY + RADIUS, RADIUS);
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
        return COEFFICIENT_OF_REFLECTION;
    }

    @Override
    public void collideWithBall(Ball ball) {
        ball.setVelocity(reflectCircle(this.circle.getCenter(), ball.getCircle().getCenter(),  ball.getVelocity(), getCoefficientOfReflection()));
      //changes the color of the flipper at ball collision
        if (color == Color.ORANGE)
        	color = LIGHT_ORANGE;
        else
        	color = Color.ORANGE;
        this.trigger();
    }

    @Override
    public void updateToTime(double timeToCollision) {
     // immutable. Nothing to update.
        
    }

    @Override
    public double collisionTime(Ball ball) {
        return timeUntilCircleCollision(this.circle, ball.getCircle(), ball.getVelocity());
    }

    @Override
    public char[][] getSymbol() {
        char[][] symbol = {{'O'}};
        return symbol;
    }

    @Override
    public void action() {
        // This gadget has no action.
    }

    
    @Override
    public void drawCanvas(Graphics2D g2d) {
        Shape shape = new Ellipse2D.Double(scaleFactor*(1+this.boardX), scaleFactor*(1+this.boardY), scaleFactor,scaleFactor);
        g2d.setColor(Color.BLACK);
        g2d.draw(shape);
        g2d.setColor(color);;
        g2d.fill(shape);
    }

}
