package pingball;

/**
 * Immutable collision event class.
 * 
 * A Collision Event consists of three pieces of information: A Ball 
 * object colliding with a Gadget, the Gadget object being collided
 * with, and the number of milliseconds until the collision occurs.   
 * 
 * @author geronm, dmayo2, hlzhou
 */
public class CollisionEvent implements Comparable<CollisionEvent> {

    /*
     * Mutability:
     * CollisionEvent has three final fields, but two of them are mutable objects.
     * 
     * Thread safety argument:
     * CollisionEvent is threadsafe because it has final fields and its methods only
     * return references to those fields. It has a method that compares one of its fields
     * to that of another CollisionEvent, but this is threadsafe because the field is
     * a double, which is immutable.
     * rep invariant: none; there are no mutators and fields are final.
     */
    
    private final Ball ball;
    private final Gadget gadget;
    private final double timeToCollision;
    
    /**
     * Creates a new CollisionEvent.
     * 
     * @param ball the ball that might collide with gadget.
     * @param gadget the gadget that the ball might collide with.
     * @param timeToCollision the linearly-determined time until collision,
     * in milliseconds.
     */
    public CollisionEvent(Ball ball, Gadget gadget, double timeToCollision) {
        this.ball = ball;
        this.gadget = gadget;
        this.timeToCollision = timeToCollision;
    }
    
    /**
     * Returns the linearly-determined time until the Ball collides with
     * the Gadget.
     * 
     * @return the time to collision, in milliseconds
     */
    public double getTimeToCollision() {
        return timeToCollision;
    }
    
    /**
     * Returns the Ball that this Collision Event concerns.
     * 
     * @return the Ball that might collide with the Gadget
     */
    public Ball getBall() {
        return ball;
    }
    
    /**
     * Returns the Gadget that this Collision Event concerns.
     * 
     * @return the Gadget that the Ball might collide with
     */
    public Gadget getGadget() {
        return gadget;
    }

    /**
     * Compares two Collision Events, and tells which is sooner.
     * 
     * @param other a separate collision event, for comparison
     * @return returns -1 if this event's collision is sooner
     * than other event, 1 if it's later, 0 if it's at the same
     * predicted time.
     */
    @Override public int compareTo(CollisionEvent other) {
        return (new Double(this.timeToCollision)).compareTo(new Double(other.timeToCollision));
    }
    
    
}