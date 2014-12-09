package pingball;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * Immutable triangle bumper class.
 * 
 * Triangle bumper object that can be placed on board. Balls
 * colliding with the triangle bumper bounce off of it.
 * 
 */
public class TriangleBumper extends Gadget{

    //Triangle bumper has edge length 1L, 1L, and sqrt(2)
    
    /*
     * Mutability:
     * Immutable. None of its fields can change. It has an array of gadgets and gadgets
     * are mutable, but all references are kept to this object.
     * 
     * Thread safety argument:
     * This is immutable, so threadsafe. Only the board that this gadget is on will
     * call one of its methods (and always does so sequentially), so none of its
     * methods will be called at the same time.
     * 
     * Rep invariant: none because it's immutable
     */
    
    private final int boardX;
    private final int boardY;
    private final int orientation;
    private static final int WIDTH = 1;
    private static final int HEIGHT = 1;
    private static final double COEFFICIENT_OF_REFLECTION = 1.0;
    private final Gadget[] sides = new Gadget[3];
    private final Gadget[] corners = new Gadget[3];
    
    /**
     * Creates a triangle bumper without a name. Has edge length 1L, 1L, sqrt(2) L.
     * The default orientation (0 degrees) places one corner in the northeast, 
     * one corner in the northwest, and the last corner in the southwest. 
     * The diagonal goes from the southwest corner to the northeast corner.
     * 
     * Orientation may be 0 degrees (the default orientation), 90 degrees, 
     * 180 degrees, or 270 degrees. When orientation is 90, 180, or 270 degrees, 
     * the value indicates a clockwise rotation in degrees of the whole gadget 
     * from the default orientation, around the center of the bounding box of 
     * the gadget.
     * @param boardX x-coordinate of the top left corner of the triangle bumper
     * @param boardY y-coordinate of the top left corner of the triangle bumper
     * @param degreesOrientation 0 degrees, 90 degrees, 180 degrees, or 270 degrees.
     */
    public TriangleBumper(int boardX, int boardY, int degreesOrientation){
        this("", boardX, boardY, degreesOrientation);
    }
    
    /**
     * Creates a triangle bumper with a name. Has edge length 1L, 1L, sqrt(2) L.
     * The default orientation (0 degrees) places one corner in the northeast, 
     * one corner in the northwest, and the last corner in the southwest. 
     * The diagonal goes from the southwest corner to the northeast corner.
     * 
     * Orientation may be 0 degrees (the default orientation), 90 degrees, 
     * 180 degrees, or 270 degrees. When orientation is 90, 180, or 270 degrees, 
     * the value indicates a clockwise rotation in degrees of the whole gadget 
     * from the default orientation, around the center of the bounding box of 
     * the gadget.
     * @param boardX x-coordinate of the top left corner of the triangle bumper
     * @param boardY y-coordinate of the top left corner of the triangle bumper
     * @param degreesOrientation 0 degrees, 90 degrees, 180 degrees, or 270 degrees.
     */
    public TriangleBumper(String name, int boardX, int boardY, int degreesOrientation){
        this.name = name;
        this.boardX = boardX;
        this.boardY = boardY;
        this.orientation = degreesOrientation;
        
        switch (orientation) {
        case 0:
            this.sides[0] = new LineBumper(boardX, boardY + HEIGHT, boardX + WIDTH, boardY);
            this.sides[1] = new LineBumper(boardX, boardY, boardX + WIDTH, boardY);
            this.sides[2] = new LineBumper(boardX, boardY, boardX, boardY + HEIGHT);
            this.corners[0] = new PointBumper(boardX + WIDTH, boardY);
            this.corners[1] = new PointBumper(boardX, boardY + HEIGHT);
            this.corners[2] = new PointBumper(boardX, boardY);
            break;
        case 90:
            this.sides[0] = new LineBumper(boardX, boardY, boardX + WIDTH, boardY + HEIGHT);
            this.sides[1] = new LineBumper(boardX, boardY, boardX + WIDTH, boardY);
            this.sides[2] = new LineBumper(boardX + WIDTH, boardY, boardX + WIDTH, boardY + HEIGHT);
            this.corners[0] = new PointBumper(boardX + WIDTH, boardY);
            this.corners[1] = new PointBumper(boardX + WIDTH, boardY + HEIGHT);
            this.corners[2] = new PointBumper(boardX, boardY);
            break;
        case 180:
            this.sides[0] = new LineBumper(boardX, boardY + HEIGHT, boardX + WIDTH, boardY);
            this.sides[1] = new LineBumper(boardX, boardY + HEIGHT, boardX + WIDTH, boardY + HEIGHT);
            this.sides[2] = new LineBumper(boardX + HEIGHT, boardY, boardX + HEIGHT, boardY + HEIGHT);
            this.corners[0] = new PointBumper(boardX + WIDTH, boardY);
            this.corners[1] = new PointBumper(boardX, boardY + HEIGHT);
            this.corners[2] = new PointBumper(boardX + WIDTH, boardY + HEIGHT);
            break;
        case 270:
            this.sides[0] = new LineBumper(boardX, boardY, boardX + WIDTH, boardY + HEIGHT);
            this.sides[1] = new LineBumper(boardX, boardY + HEIGHT, boardX + WIDTH, boardY + HEIGHT);
            this.sides[2] = new LineBumper(boardX, boardY, boardX, boardY + HEIGHT);
            this.corners[0] = new PointBumper(boardX, boardY + HEIGHT);
            this.corners[1] = new PointBumper(boardX + WIDTH, boardY + HEIGHT);
            this.corners[2] = new PointBumper(boardX, boardY);
            break;
        default:
            throw new IllegalArgumentException("orientation must be 0, 90, 180, or 270");
        }
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
    
    /**
     * Helper function for collideWithBall and collisionTime that decides 
     * the part of the triangle bumper that gets hit first.
     * @param equalityBuffer the buffer time value (in milliseconds) for which colliding a 
     * with a corner is equivalent to colliding with a side of the triangle
     * @return collision event with the closest side/corner of triangle bumper
     */
    private CollisionEvent collisionHelper(Ball ball, double equalityBuffer){
        double minCornerTimeUntilCollision = Integer.MAX_VALUE;
        double minSideTimeUntilCollision = Integer.MAX_VALUE;
        
        Gadget minCircle = this.corners[0];
        Gadget minSide = this.sides[0];
        
        for (Gadget corner: this.corners){
            double timeUntilCollision = corner.collisionTime(ball);
            if (timeUntilCollision <= minCornerTimeUntilCollision){
                minCornerTimeUntilCollision = timeUntilCollision;
                minCircle = corner;
            }
        }
        for (Gadget segment: this.sides){
            double timeUntilCollision = segment.collisionTime(ball);
            if (timeUntilCollision <= minSideTimeUntilCollision){
                minSideTimeUntilCollision = timeUntilCollision;
                minSide = segment;
            }
        }
        
        boolean cornerIsMinimum = (minCornerTimeUntilCollision < minSideTimeUntilCollision + equalityBuffer);
        
        if (cornerIsMinimum){
            //collide corner
            return new CollisionEvent(ball, minCircle, minCircle.collisionTime(ball));
        } else{
            //collide side
            return new CollisionEvent(ball, minSide, minSide.collisionTime(ball));
        }
    }

    @Override
    public void collideWithBall(Ball ball) {
        double equalityBuffer = 0.0005;
        collisionHelper(ball, equalityBuffer).getGadget().collideWithBall(ball);
        this.trigger();
    }

    @Override
    public void updateToTime(double timeToCollision) {
        // immutable. Nothing to update.
        
    }

    @Override
    public double collisionTime(Ball ball) {
        double equalityBuffer = 0.0005;
        return collisionHelper(ball, equalityBuffer).getTimeToCollision();
    }

    @Override
    public char[][] getSymbol() {
        if (orientation%180 == 0){
            char[][] bumperStringArray = {{'/'}}; 
            return bumperStringArray;
        } else if (orientation%180 ==90){
            char[][] bumperStringArray = {{'\\'}}; 
            return bumperStringArray;
        } else {
            throw new IllegalArgumentException("invalid orientation!");
        }
    }

    @Override
    public void action() {
        // This gadget has no action.
    }
    
    @Override
    public void drawCanvas(Graphics2D g2d) {
    	
        int xpoints[] = {scaleFactor*(1+this.corners[0].getBoardX()), 
        		scaleFactor*(1+this.corners[1].getBoardX()), 
        		scaleFactor*(1+this.corners[2].getBoardX())};
        int ypoints[] = {scaleFactor*(1+this.corners[0].getBoardY()),
        		scaleFactor*(1+this.corners[1].getBoardY()), 
        		scaleFactor*(1+this.corners[2].getBoardY())};
        int npoints = 3;
        
        g2d.setColor(Color.GREEN);
        g2d.drawPolygon(xpoints, ypoints, npoints);
        g2d.fillPolygon(xpoints, ypoints, npoints);;

    }

}
