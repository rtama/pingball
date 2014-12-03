package pingball;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

//import boardFileParsing.MakeInputStream;

//import boardFileParsing.MakeInputStream;


public class PingballTest {

    /*
     * Testing strategy:
     *  parse method
     *      - simple board with comments, each type of gadget, triggers
     *      - incorrectly formatted board
     */
    
    // This parse test covers: simple board with comments, every type of gadget except for portals, triggers
    @Test
    public void testParseSimpleBoard() {
    	

        Board board = new Board();
        try {
            board = Pingball.parse("boards/basic_board.pb",Optional.empty());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Ball ball = new Ball("Ball", 1.8, 4.5, -3.4, -2.3);
        Gadget squareB = new SquareBumper("Square", 0, 2);
        Gadget circleB = new CircleBumper("Circle", 4, 3);
        Gadget triangleB = new TriangleBumper("Tri", 19, 0, 90);
        Gadget leftF = new LeftFlipper("FlipL", 10, 7, 0);
        Gadget rightF = new RightFlipper("FlipR", 12, 7, 0);
        Gadget abs = new Absorber("Abs", 0, 19, 20, 1);
        squareB.addTriggeredGadget(leftF);
        abs.addTriggeredGadget(abs);
        
        Board expectedBoard = new Board();
        expectedBoard.addBall(ball);
        expectedBoard.addGadget(squareB);
        expectedBoard.addGadget(circleB);
        expectedBoard.addGadget(triangleB);
        expectedBoard.addGadget(leftF);
        expectedBoard.addGadget(rightF);
        expectedBoard.addGadget(abs);

        assertEquals(expectedBoard.toString(), board.toString());
    }
    
    // This parse test covers: simple board with comments, every type of gadget except for portals, triggers
    @Test
    public void testParsePortalBoard() {
        Board boardA = new Board();
//        Board boardB = new Board();
        try {
            boardA = Pingball.parse("boards/portal_board_A.pb",Optional.empty());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Ball ball = new Ball("Ball", 1.8, 4.5, -3.4, -2.3);
        Ball ball2 = new Ball("Ball2", 7.8, 1.5, 3.4, -2.3);
        Ball ball3 = new Ball("Ball3", 12.8, 17.5, 3.4, 2.3);
        Gadget squareB = new SquareBumper("Square", 3, 12);
        Gadget circleB = new CircleBumper("Circle", 14, 3);
        Gadget triangleB = new TriangleBumper("Tri", 1, 1, 270);
        Gadget triangleA = new TriangleBumper("Tri1", 19,0,270 );
        Gadget abs = new Absorber("Abs", 0, 19, 20, 1);
        abs.addTriggeredGadget(abs);
        Portal alpha = new Portal("Alpha", 5, 7, "", boardA);
        
        // portal name=Beta x=15 y=7 otherBoard=Mercury otherPortal=Gamma
        Portal beta = new Portal("Beta", 15, 7, "Mercury", "Gamma",boardA);
        
        Board expectedBoard = new Board();
        expectedBoard.addBall(ball);
        expectedBoard.addBall(ball2);
        expectedBoard.addBall(ball3);
        expectedBoard.addGadget(squareB);
        expectedBoard.addGadget(circleB);
        expectedBoard.addGadget(triangleB);
        expectedBoard.addGadget(abs);
        expectedBoard.addGadget(alpha);
        expectedBoard.addGadget(beta);
        expectedBoard.addGadget(triangleA); 

        assertEquals(expectedBoard.toString(), boardA.toString());
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Test
    public void testParseIncorrectBoard() throws IOException {
        Board board;
        exception.expect(IOException.class);
        board = Pingball.parse("boards/wrong_board.pb",Optional.empty());
    }
    
 
  //Testing strategies for Parser:
  //Test that parser correctly parses all staff board plus our 3 created boards. 
  //Testing partitions:
  //(1) # of players
  //   (a) 1
  //   (b) >1
  //(2) gravity, friction, and drag of board
  //   (a) negative double
  //   (b) 0
  //   (c) very large double, e.g. MAXIMUM_DOUBLE
  //(3) portals in board
  //  (a) 0
  //  (b) 1
  //  (c) >1
  //(4) # of trigger actions between objects
  //  (a) 0
  //  (b) 1
  //  (c) >1
  //(5) # of gadgets in board
  //  (a) 0
  //  (b) 1
  //  (c) >1
  //(6) type of gadget in board
  //  (a) bumpers
  //  (b) balls
  //  (c) absorber
  //  (d) portals
  //  (e) flipper
  //(7) optional parameters excluded in gadget declaration of board file
  //  (a) name missing
  //  (b) otherBoard missing for portal
  //  (c) gravity, friction1, or friction2 missing for board decl.
  //  (d) orientation, position, height, or width missing from gadget decl.
  //(8) extraneous white space in board file
  //  (a) extra whitespace within line of gadget declaration
  //  (b) extra whitespace/new lines between comments 
  //(9) incorrect declaration of gadget
  //  (a) assigned invalid parameters
    
    @Test
    public void testBoardParsing() throws IOException{
       
        Board outputBoard = Pingball.parse("boards/default.pb",Optional.empty());
        String boardName = outputBoard.name;
        String expectedBoardName = "Default";
        assertEquals(expectedBoardName,boardName); 
    }
    
    
    @Test
    public void testDefaultBoardParsing() throws IOException{
       
        Board outputBoard = Pingball.parse("boards/default.pb",Optional.empty());
        String boardName = outputBoard.name;
        String expectedBoardName = "Default";
        Double boardGravity = outputBoard.getBoardGravity();
        Double boardDrag= outputBoard.getBoardDrag(); 
        Double boardFriction = outputBoard.getBoardFriction();
        
        int expectedNumBalls = 1;
        Double expectedGravity = 25.0;
        Double expectedBoardFriction = 0.025;
        Double expectedBoardDrag =  0.025;

        assertEquals(expectedGravity,boardGravity); 
        assertEquals(expectedBoardDrag, boardDrag);
        assertEquals(expectedBoardName,boardName); 
        assertEquals(expectedBoardFriction, boardFriction);   
        assertEquals(expectedBoardName,boardName); 
        assertEquals(expectedBoardName,boardName); 
    }
    
    
    @Test
    public void testAbsorberBoardParsing() throws IOException{
        
        Board outputBoard = Pingball.parse("boards/absorber.pb", Optional.empty());
        String boardName = outputBoard.name;
        
        String expectedBoardName = "Absorber";
        
        Double boardGravity = outputBoard.getBoardGravity();
        Double boardDrag= outputBoard.getBoardDrag(); 
        Double boardFriction = outputBoard.getBoardFriction();
        
        int expectedNumBalls = 3;
        Double expectedGravity = 25.0;
        Double expectedBoardFriction = 0.025;
        Double expectedBoardDrag =  0.025;
        
        assertEquals(expectedGravity,boardGravity); 
        assertEquals(expectedBoardDrag, boardDrag);
        assertEquals(expectedBoardName,boardName); 
        assertEquals(expectedBoardFriction, boardFriction);   
        assertEquals(expectedBoardName,boardName); 
    }

    @Test
    public void testTriggersBoardParsing() throws IOException{
        
        Board outputBoard = Pingball.parse("boards/default.pb",Optional.empty());
        String boardName = outputBoard.name;
        String expectedBoardName = "Triggers";
        
        Double boardGravity = outputBoard.getBoardGravity();
        Double boardDrag= outputBoard.getBoardDrag(); 
        Double boardFriction = outputBoard.getBoardFriction();
        
        int expectedNumBalls = 2;
        Double expectedGravity = 10.0;
        Double expectedBoardFriction = 0.025;
        Double expectedBoardDrag =  0.025;
        

        assertEquals(expectedGravity,boardGravity); 
        assertEquals(expectedBoardDrag, boardDrag);
        assertEquals(expectedBoardName,boardName); 
        assertEquals(expectedBoardFriction, boardFriction);   
        assertEquals(expectedBoardName,boardName); 
    }

    @Test
    public void testFlipperBoardParsing() throws IOException{
        Board outputBoard = Pingball.parse("boards/flippers.pb",Optional.empty());
        String boardName = outputBoard.name;
        String expectedBoardName = "Flippers";
        Double boardDrag= outputBoard.getBoardDrag(); 
        Double boardGravity= outputBoard.getBoardGravity(); 
        Double boardFriction = outputBoard.getBoardFriction();
        
        int expectedNumBalls = 5;
        Double expectedGravity = 25.0;
        Double expectedBoardFriction = 0.025;
        Double expectedBoardDrag =  0.025;
        

        assertEquals(expectedGravity,boardGravity); 
        assertEquals(expectedBoardDrag, boardDrag);
        assertEquals(expectedBoardName,boardName); 
        assertEquals(expectedBoardFriction, boardFriction);   
        assertEquals(expectedBoardName,boardName); 
    }
    
    @Test
    public void testMultiPlayerRightBoardParsing() throws IOException{
        Board outputBoard = Pingball.parse("boards/multiplayer_right.pb",Optional.empty());
        String boardName = outputBoard.name;
        String expectedBoardName = "MultiplayerRight";
        Double boardGravity = outputBoard.getBoardGravity();
        Double boardDrag= outputBoard.getBoardDrag(); 
        Double boardFriction = outputBoard.getBoardFriction();        
        
        Double expGravity = 20.0;
        Double expectedBoardDrag =  0.020;
        Double expectedBoardFriction = 0.020;
        int expectedNumBalls = 0;
        
        assertEquals(expGravity, boardGravity); 
        assertEquals(expectedBoardName,boardName); 
        assertEquals(expectedBoardDrag, boardDrag);
        assertEquals(expectedBoardFriction, boardFriction);           
    }
    @Test
    public void testMultiPlayerLeftBoardParsing()throws IOException{
        Board outputBoard = Pingball.parse("boards/multiplayer_left.pb", Optional.empty());
        String boardName = outputBoard.name;
        Double boardDrag= outputBoard.getBoardDrag(); 
        Double boardFriction = outputBoard.getBoardFriction();
        Double boardGravity = outputBoard.getBoardGravity();
        
        String expectedBoardName = "MultiplayerLeft";
        Double expGravity = 20.0;
        Double expectedBoardDrag =  0.020;
        Double expectedBoardFriction = 0.020;
        int expectedNumBalls = 1;
        
        assertEquals(expGravity, boardGravity); 
        assertEquals(expectedBoardName,boardName); 
        assertEquals(expectedBoardDrag, boardDrag);
        assertEquals(expectedBoardFriction, boardFriction);     
    }
    
    @Test
    public void testSimpleBoardParsing()throws IOException{
        Board outputBoard = Pingball.parse("boards/simple_board.pb", Optional.empty());
        String boardName = outputBoard.name;
        Double boardFriction = outputBoard.getBoardFriction();
        Double boardDrag= outputBoard.getBoardDrag(); 
        Double boardGravity= outputBoard.getBoardGravity(); 
        
        int expectedNumBalls = 1;
        Double expectedGravity = 20.0;
        Double expectedBoardFriction = 0.025;
        Double expectedBoardDrag =  0.025;
        String expectedBoardName = "SimpleBoard";

        assertEquals(expectedGravity,boardGravity); 
        assertEquals(expectedBoardDrag, boardDrag);
        assertEquals(expectedBoardName,boardName); 
        assertEquals(expectedBoardFriction, boardFriction);        
    }
    
    @Test
    public void testLotsOfBallsOnBoardParsing()throws IOException{
        Board outputBoard = Pingball.parse("boards/board_many_balls.pb", Optional.empty());
        String boardName = outputBoard.name;
        Double boardFriction = outputBoard.getBoardFriction();
        Double boardDrag= outputBoard.getBoardDrag(); 
        Double boardGravity= outputBoard.getBoardGravity(); 
        
        int expectedNumBalls = 12;
        Double expectedGravity = 20.0;
        Double expectedBoardFriction = 0.025;
        Double expectedBoardDrag =  0.025;
        String expectedBoardName = "board_lots_of_balls";
        
        assertEquals(expectedGravity,boardGravity); 
        assertEquals(expectedBoardDrag, boardDrag);
        assertEquals(expectedBoardName,boardName); 
        assertEquals(expectedBoardFriction, boardFriction);   
    }
    
    @Test
    public void testFastBallBoardParsing()throws IOException{
        Board outputBoard = Pingball.parse("boards/fastBallBoard.pb", Optional.empty());
        String boardName = outputBoard.name;
        Double boardFriction = outputBoard.getBoardFriction();
        Double boardDrag= outputBoard.getBoardDrag(); 
        Double boardGravity= outputBoard.getBoardGravity(); 
        
        int expectedNumBalls = 4;
        Double expectedGravity = 10.0;
        Double expectedBoardFriction = 0.030;
        Double expectedBoardDrag =  0.030;
        String expectedBoardName = "fastBallBoard";
        
        assertEquals(expectedGravity,boardGravity); 
        assertEquals(expectedBoardDrag, boardDrag);
        assertEquals(expectedBoardName,boardName); 
        assertEquals(expectedBoardFriction, boardFriction); 
    }
    
    @Test
    public void testMultPortalBoardParsing()throws IOException{
        Board outputBoard = Pingball.parse("boards/many_portal_board.pb", Optional.empty());
        String boardName = outputBoard.name;
        Double boardFriction = outputBoard.getBoardFriction();
        Double boardDrag= outputBoard.getBoardDrag(); 
        Double boardGravity= outputBoard.getBoardGravity(); 
        
        int expectedNumBalls = 4;
        Double expectedGravity = 15.0;
        Double expectedBoardFriction = 0.025;
        Double expectedBoardDrag =  0.025;
        String expectedBoardName = "many_portal_board";
        
        assertEquals(expectedGravity,boardGravity); 
        assertEquals(expectedBoardDrag, boardDrag);
        assertEquals(expectedBoardName,boardName); 
        assertEquals(expectedBoardFriction, boardFriction) ;
        
    }   
}
