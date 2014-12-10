package pingball;

import static physics.Geometry.reflectRotatingWall;
import static physics.Geometry.timeUntilRotatingWallCollision;
import java.awt.Graphics2D;
import physics.LineSegment;
import physics.Vect;

/**
 * Immutable rotating point bumper class.
 * 
 * Gadget used to create more complicated gadgets involving
 * rotating parts. A line bumper does not include the endpoints
 * of its line.
 * 
 * Gadget used to create the corners of more complicated gadgets 
 * involving rotating parts (flipper)
 * 
 * @author hlzhou, geronm, dmayo2
 */
public class RotatingLineBumper extends Gadget{

    private final double pivotXVal;
    private final double pivotYVal;
    private static final double COEFFICIENT_OF_REFLECTION = 1.0;
    private final Vect pivot;
    private final LineSegment edge;
    private final double angularVelocity; //stored in Radians / Sec
    
    /**
     * Creates a rotating line bumper.
     * @param pivotXVal x-coordinate of pivot
     * @param pivotYVal y-coordinate of pivot
     * @param x1 x1 of line
     * @param y1 y1 of line
     * @param x2 x2 of line
     * @param y2 y2 of line
     * @param angularVelocityDegrees angular velocity of the edge, in Degrees/Second
     */
    public RotatingLineBumper(double pivotXVal, double pivotYVal, double x1, double y1, double x2, double y2, double angularVelocityDegrees){
        this.pivotXVal = pivotYVal;
        this.pivotYVal = pivotYVal;
        this.pivot = new Vect(pivotXVal, pivotYVal);
        this.edge = new LineSegment(x1,y1,x2,y2);
        
        //Convert the angular velocity from degrees/sec to radians/sec. Can't
        //trust Math.toRadians to do this, because angular Velocity isn't
        //just an angle but a time-scaled, directed angle, and the identities
        //aren't the same (eg. Math.toRadians could, in-spec, wrap values to
        // 0 <= angle < 2pi, which wouldn't work for us. 
        this.angularVelocity = Math.PI*angularVelocityDegrees / 180.0;
    }
    
    /**
     * Creates a rotating line bumper from components. 
     * 
     * @param pivot the x,y location of the pivot
     * @param edge a segment representing the edge
     * @param angularVelocityDegrees the angular velocity of the line segment in Degrees/Second.
     */
    public RotatingLineBumper(Vect pivot, LineSegment edge, double angularVelocityDegrees){
        this.pivotXVal = (int) pivot.x();
        this.pivotYVal = (int) pivot.y();
        this.pivot = pivot;
        this.edge = edge;

        //Convert the angular velocity from degrees/sec to radians/sec. Can't
        //trust Math.toRadians to do this, because angular Velocity isn't
        //just an angle but a time-scaled, directed angle, and the identities
        //aren't the same (eg. Math.toRadians could, in-spec, wrap values to
        // 0 <= angle < 2pi, which wouldn't work for us. 
        this.angularVelocity = Math.PI*angularVelocityDegrees / 180.0;
    }
    
    @Override
    public int getBoardX() {
        return (int)Math.floor(pivotXVal);
    }

    @Override
    public int getBoardY() {
        return (int)Math.floor(pivotYVal);
    }

    @Override
    public double getCoefficientOfReflection() {
        return COEFFICIENT_OF_REFLECTION;
    }

    @Override
    public void collideWithBall(Ball ball) {
        ball.setVelocity(reflectRotatingWall(this.edge, this.pivot, this.angularVelocity, ball.getCircle(),  ball.getVelocity(), getCoefficientOfReflection()));
    }

    @Override
    public void updateToTime(double timeToCollision) {
        // immutable. Nothing to update.
    }

    @Override
    public double collisionTime(Ball ball) {
        return timeUntilRotatingWallCollision(this.edge, this.pivot, this.angularVelocity, ball.getCircle(),  ball.getVelocity());                
    }

    @Override
    public char[][] getSymbol() {
        char[][] symbol = {{'Q'}};
        return symbol;
    }

    @Override
    public void action() {
        // No action.
    }

    @Override
    public void drawCanvas(Graphics2D g2) {
        // TODO Auto-generated method stub
        
    }

}
