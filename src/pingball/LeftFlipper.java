package pingball;

import physics.Vect;

public class LeftFlipper extends Flipper {
    /*
     * Mutability, thread safety arguments, rep invariant: see Flipper class
     */
    
    /**
     * Create a left flipper with its top-left corner at the specified coordinates, using
     * the specified counterclockwise orientation.
     * No name.
     *  
     * @param boardX top-left corner of the flipper x
     * @param boardY top-left corner of the flipper y
     * @param orientation clockwise orientation of the flipper. Must be 0, 90, 180, 270.
     */
    public LeftFlipper(int boardX, int boardY, int orientation) {
        this("", boardX, boardY, orientation);
    }
    
    /**
     * Create a left flipper with a name, with its top-left corner at the specified coordinates, using
     * the specified counterclockwise orientation.
     *  
     * @param name of this flipper
     * @param boardX top-left corner of the flipper x
     * @param boardY top-left corner of the flipper y
     * @param orientation clockwise orientation of the flipper. Must be 0, 90, 180, 270.
     */
    public LeftFlipper(String name, int boardX, int boardY, int orientation) {
        super(name, boardX, boardY, orientation, true); // true because this is a left flipper
        
        switch (orientation) {
        case ZERO_DEGREE_ORIENTATION:
            this.pivotPoint = new Vect(boardX, boardY);
            break;
        case NINETY_DEGREE_ORIENTATION:
            this.pivotPoint = new Vect(boardX+WIDTH, boardY);
            break;
        case ONE_EIGHTY_DEGREE_ORIENTATION:
            this.pivotPoint = new Vect(boardX+WIDTH, boardY+HEIGHT);
            break;
        case TWO_SEVENTY_DEGREE_ORIENTATION:
            this.pivotPoint = new Vect(boardX, boardY+HEIGHT);
            break;
        default:
            throw new IllegalArgumentException("orientation must be 0, 90, 180, or 270");
        }
        
        final int DEGREES_IN_REVOLUTION = 360;
        
        this.orientation = orientation;
        this.initialAngle = (DEFAULT_INITIAL_ANGLE + orientation) % DEGREES_IN_REVOLUTION; //From definition of Left Flipper
    }
    
    @Override
    public char[][] getSymbol() {
        //If movement is almost finished, then flipper is closest to destination position. Otherwise,
        // flipper is closest to the other position.
        boolean closerToInitialPosition; 
        if (currentTimeSinceMovement > (totalTimeToMove / 2)) {
            closerToInitialPosition = movingToInitialPosition;
        } else {
            closerToInitialPosition = !movingToInitialPosition;
        }
        
        //Use orientation to determine graphics
        boolean vertical;
        boolean leftOrUp; 
        switch (orientation) {
        case ZERO_DEGREE_ORIENTATION:
            if (closerToInitialPosition) {
                vertical = true;
                leftOrUp = true;
            } else {
                vertical = false;
                leftOrUp = true;
            }
            break;
        case NINETY_DEGREE_ORIENTATION:
            if (closerToInitialPosition) {
                vertical = false;
                leftOrUp = true;
            } else {
                vertical = true;
                leftOrUp = false;
            }
            break;
        case ONE_EIGHTY_DEGREE_ORIENTATION:
            if (closerToInitialPosition) {
                vertical = true;
                leftOrUp = false;
            } else {
                vertical = false;
                leftOrUp = false;
            }
            break;
        case TWO_SEVENTY_DEGREE_ORIENTATION:
            if (closerToInitialPosition) {
                vertical = false;
                leftOrUp = false;
            } else {
                vertical = true;
                leftOrUp = true;
            }
            break;
        default:
            throw new RuntimeException("orientation had a value "+orientation+", not 0, 90, 180, 270.");
        }
        
        char[][] symbol = new char[2][2];
        if (vertical) {
            if (leftOrUp) {
                symbol[0][0] = '|';
                symbol[0][1] = '|';
                symbol[1][0] = Board.IGNORE_CHAR;
                symbol[1][1] = Board.IGNORE_CHAR;
            } else {
                symbol[0][0] = Board.IGNORE_CHAR;
                symbol[0][1] = Board.IGNORE_CHAR;
                symbol[1][0] = '|';
                symbol[1][1] = '|';
            }
        } else {
            if (leftOrUp) {
                symbol[0][0] = '-';
                symbol[0][1] = Board.IGNORE_CHAR;
                symbol[1][0] = '-';
                symbol[1][1] = Board.IGNORE_CHAR;                
            } else {
                symbol[0][0] = Board.IGNORE_CHAR;
                symbol[0][1] = '-';
                symbol[1][0] = Board.IGNORE_CHAR;
                symbol[1][1] = '-';
            }
        }
        
        return symbol;
        
    }   
    
}
