package pingball;

import static physics.Geometry.reflectRotatingCircle;
import static physics.Geometry.timeUntilRotatingCircleCollision;

import java.awt.Graphics2D;

import physics.Circle;
import physics.Vect;

/**
 * Immutable rotating point bumper class.
 * 
 * Gadget used to create the corners of more complicated gadgets 
 * involving rotating parts (flipper)
 * 
 * @author hlzhou, geronm, dmayo2
 */
public class RotatingPointBumper extends Gadget{

    private final double pivotXVal;
    private final double pivotYVal;
    private static final double COEFFICIENT_OF_REFLECTION = 1.0;
    private final Vect pivot;
    private final Circle point;
    private final double angularVelocity;
    
    /**
     * Creates a rotating point bumper.
     * @param pivotXVal x-coordinate of pivot
     * @param pivotYVal y-coordinate of pivot
     * @param pivotXVal x-coordinate of center of point, from which a radius-zero
     * circle will be created 
     * @param pivotYVal y-coordinate of center of point, from which a radius-zero
     * circle will be created
     * @param angularVelocityDegrees angular velocity of the point, in Degrees/Second
     */
    public RotatingPointBumper(double pivotXVal, double pivotYVal, double pointXVal, double pointYVal, double angularVelocityDegrees){
        this.pivotXVal = pivotYVal;
        this.pivotYVal = pivotYVal;
        this.pivot = new Vect(pivotXVal, pivotYVal);
        this.point = new Circle(pointXVal, pointYVal, 0);

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
     * @param point a Circle representing the point. This circle's center will be taken
     * for the center of the new Circle object, but the radius of this object will
     * always be 0.
     * @param angularVelocityDegrees the angular velocity of the point in Degrees/Second.
     */
    public RotatingPointBumper(Vect pivot, Circle point, double angularVelocityDegrees){
        this.pivotXVal = (int) pivot.x();
        this.pivotYVal = (int) pivot.y();
        this.pivot = pivot;
        this.point = new Circle(point.getCenter().x(), point.getCenter().y(), 0);
        
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
        ball.setVelocity(reflectRotatingCircle(this.point, this.pivot, this.angularVelocity, ball.getCircle(),  ball.getVelocity(), getCoefficientOfReflection()));
    }

    @Override
    public void updateToTime(double timeToCollision) {
        // immutable. Nothing to update.
    }

    @Override
    public double collisionTime(Ball ball) {
        return timeUntilRotatingCircleCollision(this.point, this.pivot, this.angularVelocity, ball.getCircle(),  ball.getVelocity());                
    }

    @Override
    public char[][] getSymbol() {
        char[][] symbol = {{'R'}};
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
