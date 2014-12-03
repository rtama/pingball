package pingball;

import physics.Circle;
import physics.LineSegment;

/**
 * Immutable square bumper class.
 * 
 * Square bumper object that can be placed on board. Balls
 * colliding with the square bumper bounce off of it.
 * 
 */
public class SquareBumper extends Gadget{
    
    //Rep invariant: Square bumper has edge length 1L
    
    private final int boardX;
    private final int boardY;
    private static final int WIDTH = 1;
    private static final int HEIGHT = 1;
    private static final double COEFFICIENT_OF_REFLECTION = 1.0;
    private final Gadget[] sides = new Gadget[4];
    private final Gadget[] corners = new Gadget[4];
    
    /**
     * Creates a square bumper of edge length 1L.
     * @param boardX x-coordinate of the top left corner of the square bumper
     * @param boardY y-coordinate of the top left corner of the square bumper
     */
    public SquareBumper(int boardX, int boardY){
        this("", boardX, boardY);        
    }
    
    /**
     * Creates a square bumper of edge length 1L with a name.
     * 
     * @param name of this square bumper
     * @param boardX x-coordinate of the top left corner of the square bumper
     * @param boardY y-coordinate of the top left corner of the square bumper
     */
    public SquareBumper(String name, int boardX, int boardY){
        this.name = name;
        int[] topLeftCoord = {boardX, boardY};
        int[] topRightCoord = {boardX + WIDTH, boardY};
        int[] bottomLeftCoord = {boardX, boardY + HEIGHT};
        int[] bottomRightCoord = {boardX + WIDTH, boardY + HEIGHT};
        this.boardX = boardX;
        this.boardY = boardY;
        
        LineSegment topSide = new LineSegment(topLeftCoord[0], topLeftCoord[1], topRightCoord[0], topRightCoord[1]);
        LineSegment bottomSide = new LineSegment(bottomLeftCoord[0], bottomLeftCoord[1], bottomRightCoord[0], bottomRightCoord[1]);
        LineSegment leftSide = new LineSegment(topLeftCoord[0], topLeftCoord[1], bottomLeftCoord[0], bottomLeftCoord[1]);
        LineSegment rightSide = new LineSegment(topRightCoord[0], topRightCoord[1], bottomRightCoord[0], bottomRightCoord[1]);
        
        Circle topLeftCorner = new Circle(topLeftCoord[0], topLeftCoord[1], 0);
        Circle topRightCorner = new Circle(topRightCoord[0], topRightCoord[1], 0);
        Circle bottomLeftCorner = new Circle(bottomLeftCoord[0], bottomLeftCoord[1], 0);
        Circle bottomRightCorner = new Circle(bottomRightCoord[0], bottomRightCoord[1], 0);
        
        sides[0] = new LineBumper(topSide);
        sides[1] = new LineBumper(bottomSide);
        sides[2] = new LineBumper(leftSide);
        sides[3] = new LineBumper(rightSide);
        
        corners[0] = new PointBumper(topLeftCorner);
        corners[1] = new PointBumper(topRightCorner);
        corners[2] = new PointBumper(bottomLeftCorner);
        corners[3] = new PointBumper(bottomRightCorner);
        
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
     * the part of the square bumper that gets hit first.
     * @param equalityBuffer the buffer time value (in milliseconds) for which colliding a 
     * with a corner is equivalent to colliding with a side of the square
     * @return collision event with the closest side/corner of square bumper
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
        char[][] bumperStringArray = {{'#'}}; 
        return bumperStringArray;
    }
    @Override
    public void action() {
        // This gadget has no action.
    }

}
