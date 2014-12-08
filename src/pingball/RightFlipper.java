package pingball;

import javax.swing.JComponent;

import physics.Vect;

public class RightFlipper extends Flipper {
    //Rep invariant: flipper stays within a 2L by 2L bounding box
    
    /**
     * Create a right flipper with its top-left corner at the specified coordinates, using
     * the specified counterclockwise orientation.
     * No name.
     *  
     * @param boardX top-left corner of the flipper x
     * @param boardY top-left corner of the flipper y
     * @param orientation clockwise orientation of the flipper. Must be 0, 90, 180, 270.
     */
    public RightFlipper(int boardX, int boardY, int orientation) {
        this("", boardX, boardY, orientation);
    }
    
    /**
     * Create a right flipper with a name, with its top-left corner at the specified coordinates, using
     * the specified counterclockwise orientation.
     *  
     * @param name of this flipper
     * @param boardX top-left corner of the flipper x
     * @param boardY top-left corner of the flipper y
     * @param orientation clockwise orientation of the flipper. Must be 0, 90, 180, 270.
     */
    public RightFlipper(String name, int boardX, int boardY, int orientation) {
        super(name, boardX, boardY);

        angularVelocityForward = 1.080; //<--|These should be the same sign for a proper flipper
        fullySweptAngleDegrees = 90; /// <--|
        totalTimeToMove = fullySweptAngleDegrees / angularVelocityForward;
        
        switch (orientation) {
        case ZERO_DEGREE_ORIENTATION:
            this.pivotPoint = new Vect(boardX+width, boardY);
            break;
        case NINETY_DEGREE_ORIENTATION:
            this.pivotPoint = new Vect(boardX+width, boardY+height);
            break;
        case ONE_EIGHTY_DEGREE_ORIENTATION:
            this.pivotPoint = new Vect(boardX, boardY+height);
            break;
        case TWO_SEVENTY_DEGREE_ORIENTATION:
            this.pivotPoint = new Vect(boardX, boardY);
            break;
        default:
            throw new IllegalArgumentException("orientation must be 0, 90, 180, or 270");
        }
        
        final int DEGREES_IN_REVOLUTION = 360;
        
        this.orientation = orientation;
        this.initialAngle = (DEFAULT_INITIAL_ANGLE + orientation) % DEGREES_IN_REVOLUTION; //From definition of Right Flipper
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
                leftOrUp = false;
            } else {
                vertical = false;
                leftOrUp = true;
            }
            break;
        case NINETY_DEGREE_ORIENTATION:
            if (closerToInitialPosition) {
                vertical = false;
                leftOrUp = false;
            } else {
                vertical = true;
                leftOrUp = false;
            }
            break;
        case ONE_EIGHTY_DEGREE_ORIENTATION:
            if (closerToInitialPosition) {
                vertical = true;
                leftOrUp = true;
            } else {
                vertical = false;
                leftOrUp = false;
            }
            break;
        case TWO_SEVENTY_DEGREE_ORIENTATION:
            if (closerToInitialPosition) {
                vertical = false;
                leftOrUp = true;
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

    @Override
    public void drawCanvas(JComponent canvas) {
        // TODO Auto-generated method stub
        
    }    
    
}
