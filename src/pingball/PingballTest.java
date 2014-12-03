package pingball;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


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

}
