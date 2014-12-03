package pingball;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for triggering:
 *  Bumper (triangle, square, circle): can trigger (can't be triggered since 
 *  bumpers don't have actions)
 *  Flipper (left, right, all orientations): can be triggered, can trigger
 *  Ball: can trigger (no specified action, so don't need to test for can be triggered)
 *  Absorber: can be triggered, can trigger
 */
public class TriggerTest {
    
    //BUMPER TESTS:
    
    @Test
    public void testTriangleBumperCanTrigger(){
        TriangleBumper triangleBumper = new TriangleBumper(10, 5, 90);
        Ball ball1 = new Ball(10,10, 0, -1);
        Ball ball2 = new Ball(10,17, 0, 1);
        Absorber absorber = new Absorber(0, 18, 20, 2);
        triangleBumper.addTriggeredGadget(absorber);
        absorber.collideWithBall(ball2);
        triangleBumper.collideWithBall(ball1);
        assertEquals(absorber.numberOfHeldBalls(), 0);
    }
    
    //Square Bumper:
    @Test
    public void testSquareBumperCanTrigger(){
        SquareBumper squareBumper = new SquareBumper(10, 5);
        Ball ball1 = new Ball(10,10, 0, -1);
        Ball ball2 = new Ball(10,17, 0, 1);
        Absorber absorber = new Absorber(0, 18, 20, 2);
        squareBumper.addTriggeredGadget(absorber);
        absorber.collideWithBall(ball2);
        squareBumper.collideWithBall(ball1);
        assertEquals(absorber.numberOfHeldBalls(), 0);
    }
    

    //Circle Bumper:
    @Test
    public void testCircleBumperCanTrigger(){
        CircleBumper circleBumper = new CircleBumper(10, 5);
        Ball ball1 = new Ball(10,10, 0, -1);
        Ball ball2 = new Ball(10,17, 0, 1);
        Absorber absorber = new Absorber(0, 18, 20, 2);
        circleBumper.addTriggeredGadget(absorber);
        absorber.collideWithBall(ball2);
        circleBumper.collideWithBall(ball1);
        assertEquals(absorber.numberOfHeldBalls(), 0);
    }
    
    //FLIPPER TESTS:
    
    @Test
    public void testFlipperCanTrigger(){
        LeftFlipper leftFlipper = new LeftFlipper(5, 5, 0);
        Ball ball1 = new Ball(10,10, 0, -1);
        Ball ball2 = new Ball(10,17, 0, 1);
        Absorber absorber = new Absorber(0, 18, 20, 2);
        leftFlipper.addTriggeredGadget(absorber);
        absorber.collideWithBall(ball2);
        leftFlipper.collideWithBall(ball1);
        assertEquals(absorber.numberOfHeldBalls(), 0);
    }
    @Test
    public void testFlipperCanBeTriggered(){
        LeftFlipper leftFlipper = new LeftFlipper(5, 5, 0);
        Ball ball = new Ball(10,17, 0, 0);
        Absorber absorber = new Absorber(0, 18, 20, 2);
        absorber.addTriggeredGadget(leftFlipper);
        absorber.collideWithBall(ball);
        Board board = new Board();
        board.addGadget(leftFlipper);
        board.update(10);
        assertTrue(leftFlipper.isMoving());
    }
    
    //BALL TESTS:
    
    @Test
    public void testBallCanTrigger(){
        Ball ball1 = new Ball(10,10, 0, -1);
        Ball ball2 = new Ball(10,17, 0, 1);
        Ball ball3 = new Ball(10,5, 0, 1);
        Absorber absorber = new Absorber(0, 18, 20, 2);
        ball1.addTriggeredGadget(absorber);
        absorber.collideWithBall(ball2);
        ball1.collideWithBall(ball3);
        assertEquals(absorber.numberOfHeldBalls(), 0);
    }
    
    //ABSORBER TESTS:
    
    @Test
    public void testAbsorberCanTrigger(){
        Ball ball1 = new Ball(10,10, 0, -1);
        Ball ball2 = new Ball(10,17, 0, 1);
        Absorber absorber = new Absorber(0, 18, 20, 2);
        absorber.addTriggeredGadget(absorber);
        absorber.collideWithBall(ball2);
        absorber.collideWithBall(ball1);
        assertEquals(absorber.numberOfHeldBalls(), 0);
    }
    
    @Test
    public void testAbsorberCanBeTriggered(){
        TriangleBumper triangleBumper = new TriangleBumper(10, 5, 90);
        Ball ball1 = new Ball(10,10, 0, -1);
        Ball ball2 = new Ball(10,17, 0, 1);
        Absorber absorber = new Absorber(0, 18, 20, 2);
        triangleBumper.addTriggeredGadget(absorber);
        absorber.collideWithBall(ball2);
        triangleBumper.collideWithBall(ball1);
        assertEquals(absorber.numberOfHeldBalls(), 0);
    }
}
