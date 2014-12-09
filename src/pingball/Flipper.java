package pingball;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * Represents a Flipper gadget. Flippers are size 2L x 2L elements which come in two varieties of orientation.
 * When triggered, they rotate backwards or forwards within a specific 90-degree arc.
 * 
 * Thread Safety Argument: Flippers are contained in a single board so multiple boards (clients) will never
 *          access the same flipper. 
 * Mutability Comments: FLipper position and other properties are final and immutable. checkRep runs after 
 *          every mutation. 
 *
 */
public abstract class Flipper extends Gadget{

    //Rep invariant: flipperCurrentEdge should accurately reflect the current position of the flipper as
    // specified by timeSinceTrigger and whether movingToInitialPosition is true or false. orientation
    // should be 0, 90, 180, or 270. timeSinceTrigger should be positive. Edges go from pivot to tip.
    // Furthermore, angularVelocityForward and fullySweptAngleDegrees must have the same sign.
    // totalTimeToMove must have value:
    //          totalTimeToMove = fullySweptAngleDegrees / angularVelocityForward;
    
    private LineSegment flipperEdge;
    private Color color = Color.MAGENTA;
    private final Color LIGHT_PINK= new Color(255, 186, 210);
    private boolean left;   // true if left flipper, false if right flipper
    
    protected final int boardX;
    protected final int boardY;
    protected final int width = 2;
    protected final int height = 2;
    protected static final double COEFFICIENT_OF_REFLECTION = 0.95;
    protected static final double FLIPPER_LENGTH = 2.0;
    protected double angularVelocityForward = -1.080; //<--|These should be the same sign for a proper flipper
    protected double fullySweptAngleDegrees = -90; /// <--|
    protected double totalTimeToMove = fullySweptAngleDegrees / angularVelocityForward;
    
    private final double radiusAtTipOfFlipper = 0; 
    
    protected static final int ZERO_DEGREE_ORIENTATION = 0;
    protected static final int NINETY_DEGREE_ORIENTATION = 90;
    protected static final int ONE_EIGHTY_DEGREE_ORIENTATION = 180;
    protected static final int TWO_SEVENTY_DEGREE_ORIENTATION = 270;
    
    protected final int DEFAULT_INITIAL_ANGLE = 90;
    
    protected int orientation;
    protected double initialAngle;
    
    protected double currentTimeSinceMovement;
    protected Vect pivotPoint;
    protected boolean movingToInitialPosition; //Tells the state of the flipper, which direction it's moving in
    
    /** Buffer for comparing close floating point values. Used, eg., so that instead of "a <= b"
     * we have "a <= b + eq_buff" */
    protected final double equalityBuffer = .0005;
    
//    /**
//     * Create a flipper with its top-left corner at the specified coordinates. Subclasses
//     * will handle orientation.
//     * No name.
//     *  
//     * @param boardX top-left corner of the flipper x
//     * @param boardY top-left corner of the flipper y
//     */
//    public Flipper(int boardX, int boardY) {
//        this("", boardX, boardY);
//    }
    
    /**
     * Create a flipper with a name, with its top-left corner at the specified coordinates.
     * 
     * @param name of this flipper
     * @param boardX top-left corner of the flipper x
     * @param boardY top-left corner of the flipper y
     * @param orientation of the flipper, either 0 or 90 degrees
     * @param left true if this is a left flipper, false if it's a right flipper
     */
    public Flipper(String name, int boardX, int boardY, int orientation, boolean left) {
        this.name = name;
        this.boardX = boardX;
        this.boardY = boardY;
        this.left = left;
        
        this.pivotPoint = new Vect(boardX, boardY); 
        
        int dummyOrientation = 0;
        
        //final int DEGREES_IN_COMPLETE_REVOLUTION = 360;
        
        //this.orientation = (DEGREES_IN_COMPLETE_REVOLUTION-dummyOrientation) % DEGREES_IN_COMPLETE_REVOLUTION;
        this.orientation = orientation;
        this.initialAngle = DEFAULT_INITIAL_ANGLE - dummyOrientation; //From definition of Left Flipper
        
        // Initializing default values; these should always be reassigned
        double x1=0;
        double y1=0;
        double x2=0;
        double y2=0;
        if (left) {
            if (this.orientation==ZERO_DEGREE_ORIENTATION) {
                x1 = boardX;
                y1 = boardY;
                x2 = x1;
                y2 = boardY + FLIPPER_LENGTH;
            }else if (this.orientation==NINETY_DEGREE_ORIENTATION) {    // NOT SURE WHICH WAY NINETY DEGREES IS DEFINED, ASSUMING COUNTERCLOCKWISE
                x1 = boardX;
                y1 = boardY;
                x2 = boardX + FLIPPER_LENGTH;
                y2 = y1;
            }            
        }else {
            if (this.orientation==ZERO_DEGREE_ORIENTATION) {
                x1 = boardX + FLIPPER_LENGTH;
                y1 = boardY;
                x2 = x1;
                y2 = boardY + FLIPPER_LENGTH;
            }else if (this.orientation==NINETY_DEGREE_ORIENTATION) {    // NOT SURE WHICH WAY NINETY DEGREES IS DEFINED, ASSUMING COUNTERCLOCKWISE
                x1 = boardX;
                y1 = boardY;
                x2 = boardX + FLIPPER_LENGTH;
                y2 = y1;
            }               
        }
        this.flipperEdge = new LineSegment(x1, y1, x2, y2);


        //Start in initial position
        this.movingToInitialPosition = true;
        int extraMoveTime = 1; 
        this.currentTimeSinceMovement = totalTimeToMove + extraMoveTime;
        checkRep();
    }
    
    public void checkRep(){
        assert(orientation==ZERO_DEGREE_ORIENTATION||orientation==NINETY_DEGREE_ORIENTATION
                ||orientation==ONE_EIGHTY_DEGREE_ORIENTATION||orientation==TWO_SEVENTY_DEGREE_ORIENTATION);
        assert(angularVelocityForward/angularVelocityForward==fullySweptAngleDegrees/fullySweptAngleDegrees);
    }

    @Override
    public int getBoardX() {
        return boardX;
    }

    @Override
    public int getBoardY() {
        return boardY;
    }

    @Override
    public double getCoefficientOfReflection() {
        return COEFFICIENT_OF_REFLECTION;
    }

    @Override
    public void updateToTime(double timeToCollision) {
        currentTimeSinceMovement += timeToCollision;
    }
    
    @Override
    public double collisionTime(Ball ball) {
        double theTime = Math.min(collideStatic(ball, currentTimeSinceMovement, movingToInitialPosition).getTimeToCollision(), collideRotating(ball, currentTimeSinceMovement, movingToInitialPosition).getTimeToCollision());
        
        return theTime;
        
    }
    
    @Override
    public void collideWithBall(Ball ball) {

        CollisionEvent theStaticCollision = collideStatic(ball, currentTimeSinceMovement, movingToInitialPosition);
        CollisionEvent theRotatingCollision = collideRotating(ball, currentTimeSinceMovement, movingToInitialPosition);
        
        if (!(theRotatingCollision.getTimeToCollision() == Double.POSITIVE_INFINITY && theStaticCollision.getTimeToCollision() == Double.POSITIVE_INFINITY)) {
            if (theRotatingCollision.getTimeToCollision() < theStaticCollision.getTimeToCollision()) {
                theRotatingCollision.getGadget().collideWithBall(ball);
            } else {
                theStaticCollision.getGadget().collideWithBall(ball);
            }
        }
        
        if (color == Color.MAGENTA)
        	color = LIGHT_PINK;
        else
        	color = Color.MAGENTA;
        trigger();
        
    }
    
    /**
     * Produce a CollisionEvent corresponding to a rotating collision. Helps collideWithBall
     * and timeUntilCollision. 
     * 
     * @param timeSinceMovementStarted the current position of the flipper as reflected in units
     * of time since the flipper started moving (milliseconds).
     * @return returns a CollisionEvent whose time corresponds to the projected time of collision.
     * If said time is after the time at which the flipper will become static, the collision
     * is considered not to have happened (if any collision happened, it is handled in
     * collideStatic()), and a collision time of infinity is returned.
     */
    private CollisionEvent collideRotating(Ball ball, double timeSinceMovementStarted, boolean isMovingToInitialPosition) {
        
        double angularVelocity;
        if (isMovingToInitialPosition) {
            angularVelocity = -angularVelocityForward;
        } else {
            angularVelocity = angularVelocityForward;
        }
        
        Vect flipperVector = makeFlipperVector(timeSinceMovementStarted, isMovingToInitialPosition); 
        
        Circle flipperTip = new Circle(pivotPoint.plus(flipperVector), radiusAtTipOfFlipper); //Radius-zero circle at tip of flipper
        this.flipperEdge = new LineSegment(pivotPoint,flipperTip.getCenter());
        
        int pointsIndex = 2; 
        int edgeIndex = 1; 
        Gadget[] points = new Gadget[pointsIndex];
        Gadget[] edge = new Gadget[edgeIndex];
        
        points[0] = new RotatingPointBumper(pivotPoint, flipperTip, angularVelocity);
        points[1] = new PointBumper(new Circle(pivotPoint, radiusAtTipOfFlipper));
        edge[0] = new RotatingLineBumper(pivotPoint, flipperEdge, angularVelocity);

        CollisionEvent theCollision = collisionHelper(ball, equalityBuffer, points, edge);
        
        //Make sure the time in question was reached.
        double extraTime = 1.0; 
        if (timeSinceMovementStarted + theCollision.getTimeToCollision() - extraTime <= totalTimeToMove) {
            return theCollision;
        } else {
            return new CollisionEvent(ball, theCollision.getGadget(), Double.POSITIVE_INFINITY);
        }
        
    }
    
    /**
     * Produce a CollisionEvent corresponding to a static collision. Helps collideWithBall
     * and timeUntilCollision operate.
     * 
     * @param timeSinceMovementStarted the current position of the flipper as reflected in units
     * of time since the flipper started moving (milliseconds).
     * @return returns a CollisionEvent with time corresponding to the projected time of collision.
     * If said time is before the time at which the flipper will become static, the collision
     * is considered not to have happened (if any collision happened, it is handled in
     * collideRotating()), and a collision time of infinity is returned.
     */
    private CollisionEvent collideStatic(Ball ball, double timeSinceMovementStarted, boolean isMovingToInitialPosition) {
        
        int extraTime = 1; 
        Vect flipperVector = makeFlipperVector(totalTimeToMove + extraTime, isMovingToInitialPosition); 
                
        Circle flipperTip = new Circle(pivotPoint.plus(flipperVector), radiusAtTipOfFlipper); //Radius-zero circle at tip of flipper
        LineSegment flipperEdge = new LineSegment(pivotPoint,flipperTip.getCenter());
        
        int pointsIndex = 2; 
        int edgeIndex = 1;
        Gadget[] points = new Gadget[pointsIndex];
        Gadget[] edge = new Gadget[edgeIndex];
        
        points[0] = new PointBumper(flipperTip);
        points[1] = new PointBumper(new Circle(pivotPoint, radiusAtTipOfFlipper));
        edge[0] = new LineBumper(flipperEdge);
        
        CollisionEvent theCollision = collisionHelper(ball, equalityBuffer, points, edge);
        
        
        //Make sure the time in question was reached.
        if (timeSinceMovementStarted + theCollision.getTimeToCollision() + extraTime >= totalTimeToMove) {
            return theCollision;
        } else {
            return new CollisionEvent(ball, theCollision.getGadget(), Double.POSITIVE_INFINITY);
        }
        
    }
    
    /**
     * Helper function for collideWithBall and collisionTime that decides 
     * the part of the square bumper that gets hit first.
     * @param ball the ball to be collided.
     * @param equalityBuffer the buffer time value (in milliseconds) for which colliding a 
     * with a corner is equivalent to colliding with a side of the square
     * @param corners array of Gadgets representing the corners of our gadget.
     * @param sides
     * @return collision event with the closest side/corner of square bumper
     */
    private CollisionEvent collisionHelper(Ball ball, double equalityBuffer, Gadget[] corners, Gadget[] sides){
        double minCornerTimeUntilCollision = Double.POSITIVE_INFINITY;
        double minSideTimeUntilCollision = Double.POSITIVE_INFINITY;
        
        Gadget minCircle = corners[0];
        Gadget minSide = sides[0];
        
        for (Gadget corner: corners){
            double timeUntilCollision = corner.collisionTime(ball);
            if (timeUntilCollision <= minCornerTimeUntilCollision){
                minCornerTimeUntilCollision = timeUntilCollision;
                minCircle = corner;
            }
        }
        for (Gadget segment: sides){
            double timeUntilCollision = segment.collisionTime(ball);
            if (timeUntilCollision <= minSideTimeUntilCollision){
                minSideTimeUntilCollision = timeUntilCollision;
                minSide = segment;
            }
        }
        
        boolean cornerIsMinimum = (minCornerTimeUntilCollision < minSideTimeUntilCollision + equalityBuffer);
        
        if (cornerIsMinimum){
            //collide corner
            return new CollisionEvent(ball, minCircle, minCornerTimeUntilCollision);
        } else{
            //collide side
            return new CollisionEvent(ball, minSide, minSideTimeUntilCollision);
        }
    }

    
    /**
     * Returns a vector representing the flipper tip's displacement from the pivot at the specified
     * time and configuration.
     * 
     * @param timeSinceMovementStarted the time (in milliseconds) since the flipper "left" its
     * initial configuration. For example, if movingToInitialPosition is true, this field represents
     * the number of milliseconds ago the flipper was in the Final configuration. The flipper's
     * angle is calculated from this parameter. 
     * @param isMovingToInitialPosition the state of the Flipper; true if moving toward / stopped
     * at the starting position of the flipper, false if moving toward / stopped at final position.
     * @return returns a displacement vector representing the position of the tip of the flipper
     * relative to the pivot, in world coordinates. 
     */
    private Vect makeFlipperVector(double timeSinceMovementStarted, boolean isMovingToInitialPosition) {
        double calculatedAngularDisplacement; //Displacement from initialAngle
        
        if(timeSinceMovementStarted > totalTimeToMove) {
            if (isMovingToInitialPosition) {
                calculatedAngularDisplacement = 0;
            } else {
                calculatedAngularDisplacement = fullySweptAngleDegrees;
            }
        } else {
            if (isMovingToInitialPosition) {
                calculatedAngularDisplacement = fullySweptAngleDegrees - (angularVelocityForward*timeSinceMovementStarted);
            } else {
                calculatedAngularDisplacement = 0 + (angularVelocityForward*timeSinceMovementStarted);
            }
        }
        
        Vect flipperVector = new Vect(FLIPPER_LENGTH*Math.cos(Math.toRadians(calculatedAngularDisplacement + initialAngle)),FLIPPER_LENGTH*Math.sin(Math.toRadians(calculatedAngularDisplacement + initialAngle)));
        
        return flipperVector;
    }

    @Override
    public void action() {
        //The action of the Flipper is to toggle its state.
        movingToInitialPosition = !movingToInitialPosition;
        currentTimeSinceMovement = Math.max(totalTimeToMove - currentTimeSinceMovement, 0);
        checkRep(); 
        
    }
    
    /**
     * Tell the current state of motion of this Flipper.
     * 
     * @return true if the flipper is currently rotating, false otherwise.
     */
    public boolean isMoving() {
        return currentTimeSinceMovement < totalTimeToMove;
    }
    
    @Override
    public void drawCanvas(Graphics2D g2) {
        //System.out.println("printing flipper");

        int x1 = (int) (this.flipperEdge.p1().x()+1) * scaleFactor; // Add 1 to position coordinates to account for walls
        int y1 = (int) (this.flipperEdge.p1().y()+1) * scaleFactor;
        int x2 = (int) (this.flipperEdge.p2().x()+1) * scaleFactor;
        int y2 = (int) (this.flipperEdge.p2().y()+1) * scaleFactor;
        BasicStroke stroke = new BasicStroke(scaleFactor/2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke);
        g2.setColor(color);
        g2.drawLine(x1, y1, x2, y2);
        g2.setStroke(new BasicStroke());

    }

}
