package pingball;

import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JComponent;

import ui.ColoredShape;

/**
 * Represents a gadget, an active object on the pingball playing board.
 * Examples of such objects include ball, bumper, and outer wall.
 * 
 * The abstraction function for gadgets and balls is based on their actual
 * Java objects in memory. Though two balls may have identical velocity
 * and position, they are still considered not equal if they are represented
 * by different java objects in memory. This rep supports mutations of
 * the objects in memory to modify their abstract value.   
 * 
 * Gadgets' bounding boxes should fit entirely on the board. 
 * 
 */
public abstract class Gadget {
    
    private final LinkedList<Gadget> gadgetsToTrigger = new LinkedList<Gadget>();
    private boolean isGhost = false;
    protected String name;
    
    /*
     * Mutability:
     * Gadget is mutable. Its name can't be changed by anything outside of the package
     * or subclasses because it is protected, and no method returns a reference to the name.
     * Gadget has a mutable list of gadgets that it triggers; these elements can be modified.
     * Gadget's boolean isGhost can be modified.
     * 
     * rep invariant: none
     * 
     * Thread safety argument: see subclasses
     */
    
    /**
     * 
     * @return the name of this gadget
     */
    public String getName() {
        // defensive copying
        String nameCopy = new String(this.name);
        return nameCopy;
    }
    
    /**
     * Get the x position (column) of the top left corner of gadget
     * relative to the top left corner of the board
     * 
     * @return integer value of the top-left x position
     */
    public abstract int getBoardX();
    
    /**
     * Get the y position (row) of the top left corner of gadget
     * relative to the top left corner of the board
     * 
     * @return integer value of the top-left y position
     */
    public abstract int getBoardY();
    
    /**
     * Get the gadget's coefficient of reflection. Determines with 
     * what proportion of original velocity ball is reflected.
     * 
     * @return gadget's coefficient of reflection.
     */
    public abstract double getCoefficientOfReflection();
    
    /**
     * Collide ball against the current gadget as specified by the
     * gadget's behavior. This event may result in the gadget's
     * action being triggered, if it is so specified in the gadget.
     * 
     * @param ball the ball to be collided with. Must not be this.
     * Should be fairly close to the gadget on the board for physics
     * to work properly. Ball is mutated by this method.
     */
    public abstract void collideWithBall(Ball ball);

    /**
     * Linearly advance the gadget in time by the specified number of
     * milliseconds. Non-positive values of timeToCollision will result
     * in no change to the gadget.
     * 
     * @param timeToCollision time, in milliseconds, by which to
     * advance the gadget.
     */
    public abstract void updateToTime(double timeToCollision);
    
    /**
     * Returns the time until this gadget collides with the given
     * ball, assuming the ball follows the linear trajectory specified
     * by its velocity.
     * 
     * @param ball the ball to be collided with
     * @return the time until the ball first collides with this
     * gadget, specified in milliseconds. May be infinite.    
     */
    public abstract double collisionTime(Ball ball);
    
    /**
     * Returns a character array representation of the gadget.
     * 
     * @return char array representing the current gadget, handled at
     * the gadget's top-left corner. Different gadgets' arrays may 
     * vary in dimension, but a given array must be rectangular 
     * (length of all interior arrays equal). Every element char in
     * the array must correspond to a 1L x 1L portion of the gadget's
     * total area. For transparency, see the ignoreChar field of the
     * Board class:
     * @see pingball.Board
     */
    public abstract char[][] getSymbol();
    

        
    /**
     * Execute action. Happens whenever this gadget is triggered.
     * Gadgets may be triggered naturally by events on the board,
     * or may be triggered by other gadgets that were triggered
     * naturally. This method may mutate this gadget.
     */
    public abstract void action();
    
    /**
     * Trigger other gadgets' actions, and potentially this gadget's
     * action as well. It does this by calling gadgets' action()
     * methods.
     */
    public void trigger(){
        for (Gadget gadget: this.gadgetsToTrigger){
            gadget.action();
        }
    }
    
    /**
     * Adds to the list of gadgets a gadget can trigger
     * @param triggeredGadget LinkedList of gadgets that a gadget can trigger
     */
    public void addTriggeredGadget(Gadget triggeredGadget){
        this.gadgetsToTrigger.add(triggeredGadget);
    }
    
    /**
     * Removes a gadget from the list of gadgets that a gadget
     * can trigger (reference equality is used here); if the gadget 
     * is not found, the list is unchanged
     * @param triggeredGadget a Gadget to be removed from the list of 
     * gadgets a gadget can trigger
     */
    public void removeTriggeredGadget(Gadget gadgetToRemove){
        this.gadgetsToTrigger.remove(gadgetToRemove);
    }
    
    /**
     * Whether a gadget is a "ghost" gadget: not moving/ not affected 
     * by gravity or frictional effects, and does not collide/ can
     * overlap with other gadgets.
     * @return isGhost boolean indicating whether a given gadget is a 
     * 'ghost' gadget
     */
    public boolean isGhost(){
        return this.isGhost;
    }

    /**
     * Draw this gadget onto graphics
     * @param g2 graphics
     */
    public abstract void drawCanvas(Graphics2D g2);
}
