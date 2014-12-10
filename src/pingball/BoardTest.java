package pingball;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    /*
     * Testing Strategy:
     * 
     * ToStringRounding
     * 
     * update():
     *    BounceBallOffWall
     *    BounceBallOffCorner
     *    BounceBallOffBallStraight
     *    CollisionAtZeroMillisecs
     *    testToStringRounding
     *  
     *Bumpers:
     *  Triangle:
     *      orientations: 0, 90, 180, 270
     *      collisions: flat side, diagonal side, corner
     *      toString: prints correctly and in the correct position
     *  Square
     *      collisions: flat side, corner
     *      toString: prints correctly and in the correct position
     *  Circle
     *      collisions
     *      toString: prints correctly and in the correct position
     *Other Gadgets: 
     *  Flipper
     *      collisions
     *      toString
     *  Absorber
     *      collisions
     *      toString
     * 
     */
    
    
    /**
     * Parses the toString output of board to find the char corresponding to
     * a given board location
     * @param board
     * @param x x position on the board relative to the top left corner
     * @param y y position on the board relative to the top left corner
     * @return char representation of the specified board location in the toString output of board
     */
    public char charAtBoardPosition(Board board,int x,int y){
        final int WIDTH_OF_OUTER_WALL = 1;
        final int FIRST_CHAR_IN_NEXT_ROW = 23;
        return board.toString().charAt(FIRST_CHAR_IN_NEXT_ROW*WIDTH_OF_OUTER_WALL+FIRST_CHAR_IN_NEXT_ROW*y+x+WIDTH_OF_OUTER_WALL);
    }
    
    @Test
    public void testToStringRounding() {
        Ball ball = new Ball(10,19.4); //almost 19.5. Should draw as if in the bottom most row, the 21st row of the string rep.
        
        Board theBoard = new Board();
        theBoard.addBall(ball);
        
        String theBoardToString = theBoard.toString();
        
        final int rowNum = 20;
        final int totalRows = 22;
        int stringLength = theBoardToString.length();
        int rowStartRough = (stringLength * rowNum) / totalRows;
        int rowLength = (stringLength / totalRows);
        
        //Extract something in the middle of the row. Like bracketed expression in:
        //   Row 20:   .                   .
        //   Row 21:   .  [      *      ]  .
        //   Row 22:   .                   .
        String middleOfRow = (theBoardToString).substring(rowStartRough+4, rowStartRough+rowLength-4); 
        
        assertTrue(middleOfRow.lastIndexOf((int)(ball.getSymbol()[0][0])) != -1);
        
    }
    
    @Test
    public void testUpdateBounceBallOffWall(){
        Board oneBallBoard = new Board();
        Ball ourTestBall = new Ball(15.5,18,1,-1); //position 15,18, velocity 1,-1
        oneBallBoard.addBall(ourTestBall);
        //check balls initial locations
        assertEquals('*',charAtBoardPosition(oneBallBoard,15,18));
        //advance board linearly by 7 milliseconds 
        oneBallBoard.update(7);
        //After the above described collision at (20,13) after 5 milliseconds, should use new
        //velocity (1,-1) to continue to point (17,11) after remaining 2 milliseconds.
        System.out.println(oneBallBoard.toString());
        assertEquals('*',charAtBoardPosition(oneBallBoard,17,11));
    }
    
    @Test
    public void testUpdateBounceBallOffBallStraight() {
        Board twoBallBoard = new Board();
        Ball ourFirstBall = new Ball(10,13,1.0,0); //position 10,13, velocity 1,0
        Ball ourSecondBall = new Ball(17,13,-1.0,0); //position 17,13, velocity -1,0
        twoBallBoard.addBall(ourFirstBall); 
        twoBallBoard.addBall(ourSecondBall); 

        //Balls should collide with each other after 3 milliseconds at (13,13) -><- (14,13) 
        
        //check balls initial locations
        assertEquals('*',charAtBoardPosition(twoBallBoard,10,13));
        assertEquals('*',charAtBoardPosition(twoBallBoard,17,13));
        
        //advance board linearly by 5 milliseconds 
        twoBallBoard.update(5.0);

        //After the above described collision after 3 milliseconds, should use new
        //velocity (1,-1) to continue to point (11,13) and (15,13) after remaining 2 milliseconds.
        assertEquals('*',charAtBoardPosition(twoBallBoard,11,13));
        assertEquals('*',charAtBoardPosition(twoBallBoard,15,13));
    }
    
    @Test
    public void testUpdateBounceBallOffBallNinetyDegrees() {
        Board twoBallNinetyBoard = new Board();
        Ball ourFirstBall = new Ball(10,13,1.0,0); //position 10,13, velocity 1,0
        Ball ourSecondBall = new Ball(16.7071,13.7071,-1.0,0); //position 17,13.707, velocity -1,0
        twoBallNinetyBoard.addBall(ourFirstBall); 
        twoBallNinetyBoard.addBall(ourSecondBall); 
        
        //check balls initial positions
        assertEquals('*',charAtBoardPosition(twoBallNinetyBoard,10,13));
        assertEquals('*',charAtBoardPosition(twoBallNinetyBoard,16,13));
        
        twoBallNinetyBoard.update(3);
        
        //Balls should collide with each other after 3 milliseconds at (13,13) / (13.7071,13.7071) 
        assertEquals('*',charAtBoardPosition(twoBallNinetyBoard,13,13));
        
        twoBallNinetyBoard.update(5);
        
        //After the above described collision at (20,13) after 5 milliseconds, should use new
        //velocity (1,-1) to continue to point (17,13) and (9,13) after remaining 2 milliseconds.
        assertEquals('*',charAtBoardPosition(twoBallNinetyBoard,9,13));
        assertEquals('*',charAtBoardPosition(twoBallNinetyBoard,17,13));
    }
    
    
    @Test
    public void testTriangleSymbolOrientation() {
        Board theBoard = new Board();
        theBoard.addGadget(new TriangleBumper(0, 0, 0));
        theBoard.addGadget(new TriangleBumper(1, 0, 180));
        theBoard.addGadget(new TriangleBumper(2, 0, 90));
        theBoard.addGadget(new TriangleBumper(3, 0, 270));

        assertEquals('/',charAtBoardPosition(theBoard,0,0)); //0
        assertEquals('/',charAtBoardPosition(theBoard,1,0)); //180
        assertEquals('\\',charAtBoardPosition(theBoard,2,0)); //90
        assertEquals('\\',charAtBoardPosition(theBoard,3,0)); //270
    }
    
    @Test
    public void testTriangleCollideFlatSide() {
        Board theBoard = new Board();
        Ball ourTestBall = new Ball(2,2,-1,-1); //position 15,18, velocity 1,-1
        theBoard.addBall(ourTestBall);
        theBoard.addGadget(new TriangleBumper(0, 0, 0));
        
        //check initial locations
        assertEquals('*',charAtBoardPosition(theBoard,2,2));
        assertEquals('/',charAtBoardPosition(theBoard,0,0));
        
        theBoard.update(5);
        
        assertEquals('*',charAtBoardPosition(theBoard,4,4));
        assertEquals('/',charAtBoardPosition(theBoard,0,0));
    }
    @Test
    public void testTriangleCollideDiagonalSide() {
        Board theBoard = new Board();
        Ball ourTestBall = new Ball(2,2,-1,-1); //position 15,18, velocity 1,-1
        theBoard.addBall(ourTestBall);
        theBoard.addGadget(new TriangleBumper(0, 0, 0));
        
        //check initial locations
        assertEquals('*',charAtBoardPosition(theBoard,2,2));
        assertEquals('/',charAtBoardPosition(theBoard,0,0));
        
        theBoard.update(5);
        
        assertEquals('*',charAtBoardPosition(theBoard,4,4));
        assertEquals('/',charAtBoardPosition(theBoard,0,0));
    }
    @Test
    public void testTriangleCollideCorner() {
        Board theBoard = new Board();
        Ball ourTestBall = new Ball(2,2,-1,-1); //position 15,18, velocity 1,-1
        theBoard.addBall(ourTestBall);
        theBoard.addGadget(new TriangleBumper(0, 0, 90));
        //check initial locations
        assertEquals('*',charAtBoardPosition(theBoard,2,2));
        assertEquals('\\',charAtBoardPosition(theBoard,0,0));
        
        theBoard.update(5);
        assertEquals('*',charAtBoardPosition(theBoard,5,5));
        assertEquals('\\',charAtBoardPosition(theBoard,0,0));
    }
    
    @Test
    public void testSquareSymbol() {
        Board theBoard = new Board();
        theBoard.addGadget(new SquareBumper(0, 0));

        assertEquals('#',charAtBoardPosition(theBoard,0,0));
    }
    @Test
    public void testSquareCollideSide(){
        Board theBoard = new Board();
        Ball ourTestBall = new Ball(0,2,0,-1); //position 15,18, velocity 1,-1
        theBoard.addBall(ourTestBall);
        theBoard.addGadget(new SquareBumper(0, 0));
        //check initial locations
        assertEquals('*',charAtBoardPosition(theBoard,0,2));
        assertEquals('#',charAtBoardPosition(theBoard,0,0));
        
        theBoard.update(5);

        assertEquals('*',charAtBoardPosition(theBoard,0,5));
        assertEquals('#',charAtBoardPosition(theBoard,0,0));
    }
    @Test
    public void testSquareCollideCorner(){
        Board theBoard = new Board();
        Ball ourTestBall = new Ball(2,2,-1,-1); //position 15,18, velocity 1,-1
        theBoard.addBall(ourTestBall);
        theBoard.addGadget(new SquareBumper(0, 0));

        //check initial locations
        assertEquals('*',charAtBoardPosition(theBoard,2,2));
        assertEquals('#',charAtBoardPosition(theBoard,0,0));
        
        theBoard.update(5);
        assertEquals('*',charAtBoardPosition(theBoard,5,5));
        assertEquals('#',charAtBoardPosition(theBoard,0,0));
    }
    
    @Test
    public void testCircleSymbol() {
        Board theBoard = new Board();
        theBoard.addGadget(new CircleBumper(0, 0));

        assertEquals('O',charAtBoardPosition(theBoard,0,0));
    }
    @Test
    public void testCircleCollide() {
        Board theBoard = new Board();
        Ball ourTestBall = new Ball(2,2,-1,-1); //position 15,18, velocity 1,-1
        theBoard.addBall(ourTestBall);
        theBoard.addGadget(new CircleBumper(0, 0));
        
        //check initial locations
        assertEquals('*',charAtBoardPosition(theBoard,2,2));
        assertEquals('O',charAtBoardPosition(theBoard,0,0));
        
        theBoard.update(5);
        assertEquals('*',charAtBoardPosition(theBoard,5,5));
        assertEquals('O',charAtBoardPosition(theBoard,0,0));
    }
    @Test
    public void testFlipperSymbol() {
        Board theBoard = new Board();
        Gadget leftFlipper = new LeftFlipper(2, 2, 0);
        theBoard.addGadget(leftFlipper);

        //vertical
        assertEquals('|',charAtBoardPosition(theBoard,2,2));
        assertEquals('|',charAtBoardPosition(theBoard,2,3));
        
        leftFlipper.action();
        theBoard.update(100);

        //horizontal
        assertEquals('-',charAtBoardPosition(theBoard,2,2));
        assertEquals('-',charAtBoardPosition(theBoard,3,2));
    }
    @Test
    public void testFlipperCollide() {
        Board theBoard = new Board();
        Ball ourTestBall = new Ball(4,2,-2,0); //position 15,18, velocity 1,-1
        theBoard.addBall(ourTestBall);
        Gadget leftFlipper = new LeftFlipper(2, 2, 0);
        theBoard.addGadget(leftFlipper);

        //check initial locations
        assertEquals('*',charAtBoardPosition(theBoard,4,2));
        //vertical
        assertEquals('|',charAtBoardPosition(theBoard,2,2));
        assertEquals('|',charAtBoardPosition(theBoard,2,3));
        
        theBoard.update(3);
        
        assertEquals('*',charAtBoardPosition(theBoard,6,2));
        //vertical
        assertEquals('|',charAtBoardPosition(theBoard,2,2));
        assertEquals('|',charAtBoardPosition(theBoard,2,3));
    }
    @Test
    public void testAbsorberSymbol() {
        Board theBoard = new Board();
        theBoard.addGadget(new Absorber(0, 18, 20, 2));
        
        for(int i=0;i<20;i++){
            assertEquals('=',charAtBoardPosition(theBoard,i,18));
            assertEquals('=',charAtBoardPosition(theBoard,i,19));
        }
    }
    @Test
    public void testAbsorberCollide() {
        Board theBoard = new Board();
        Ball ourTestBall = new Ball(2,2,0,-1); //position 15,18, velocity 1,-1
        theBoard.addBall(ourTestBall);
        theBoard.addGadget(new Absorber(0, 18, 20, 2));
        
        //check initial locations
        assertEquals('*',charAtBoardPosition(theBoard,2,2));
        for(int i=0;i<20;i++){
            assertEquals('=',charAtBoardPosition(theBoard,i,18));
            assertEquals('=',charAtBoardPosition(theBoard,i,19));
        }
        
        theBoard.update(500);
        
        assertEquals('*',charAtBoardPosition(theBoard,19,19));
        for(int i=0;i<19;i++){
            assertEquals('=',charAtBoardPosition(theBoard,i,18));
            assertEquals('=',charAtBoardPosition(theBoard,i,19));
        }
    }
    
}
