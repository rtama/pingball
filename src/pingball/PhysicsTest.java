package pingball;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
/**
 * Physics tests
 * 
 * @author hlzhou, geronm, dmayo2
 *
 */
public class PhysicsTest {

    /*
     * Testing Strategy:
     * ( (o)=physical obstacle, (c)=complex behavior )
     * 
     * List of Gadgets:
     * Ball
     * OuterWall (o)
     * SquareBumper (o)
     * TriangleBumber (o)
     * CircleBumber (o)
     * Flipper (o) (c)
     * Absorber (c)
     * 
     * Tests for every Obstacle Gadget:
     *      CollideBallSimple
     *      CollideBall[Complex] (eg. corner)
     * 
     * Tests for Ball:
     *      CollideWithBallStraight
     *      CollideWithBall90
     *      
     */
    
    @Test
    public void testAbsorberCollideHoldBall() {
        
        Ball ourBall = new Ball(8,12.75,0,1);
        Absorber theAbsorber = new Absorber(0, 15,15,3);
                
        testAGadget(ourBall, theAbsorber, 2, new Ball(8, 14.75, 0,0));
        
    }
    
    @Test
    public void testSquareBumperCollideWithBallCorner() {
  
        double xVel = 4;
        double yVel = 4;
        
        Ball ourBall = new Ball(8,8,xVel,yVel);
        SquareBumper theBumper = new SquareBumper(10, 10);
        
        double newX = 10-(Math.sqrt(2)/8.0);
        double newY = 10-(Math.sqrt(2)/8.0);
        
        testAGadget(ourBall, theBumper, (newX-8)/xVel, new Ball(newX, newY, -xVel, -yVel));
        
    }
        
    @Test
    public void testSquareBumperCollideWithBallCloseToCorner() {
        
        double xVel = 3.24;
        
        Ball ourBall = new Ball(13.75,5.15,xVel,0);
        SquareBumper theBumper = new SquareBumper(15, 5);
        
        testAGadget(ourBall, theBumper, 1/xVel, new Ball(14.75,5.15,-xVel,0));
        
    }
    
    @Test
    public void testOuterWallsCollideWithBallManyTimes() {
        //seed the random generator
        Random randGen = new Random();
        randGen.setSeed(45205427);
        
        //try a bunch of collisions. Ensure for all of them that ball no longer will collide with wall after physics step:
        for (int i=0; i<1000; i++) {
            double xVel = 3+5*randGen.nextDouble(); 
            
            Ball ourBall = new Ball(1.25,10,-xVel,xVel+randGen.nextDouble());
            OuterWall theWall = OuterWall.LEFT_WALL(); //left wall
            
            testAGadget(ourBall, theWall, 1/xVel, new Ball(.25,10+(ourBall.getYVel()/xVel),xVel,ourBall.getYVel()));
        }
    }
    
    
    @Test
    public void testOuterWallsCollideWithBallSimple() {
        Ball ourBall = new Ball(18.75,10,1.0,0);
        OuterWall theWall = OuterWall.RIGHT_WALL(); //right wall
        
        testAGadget(ourBall, theWall, 1, new Ball(19.75,10,-1,0));
    }
    
    @Test
    public void testBallCollideWithBallStraight() {
        Ball ourFirstBall = new Ball(2,13,1,0); //position ?,13, velocity 1,0
        Ball ourSecondBall = new Ball(2,13,-1,0); //position /,13, velocity -1,0
        
        double timeTo = 3.0;
        
        ourFirstBall.setXPos(12-(timeTo+ourFirstBall.getCircle().getRadius()));
        ourSecondBall.setXPos(12+(timeTo+ourSecondBall.getCircle().getRadius()));
        
        testAGadget(ourFirstBall, ourSecondBall, timeTo, new Ball(12-ourFirstBall.getCircle().getRadius(),13,-1,0));  //Balls should collide and reverse direction
    }

    @Test
    public void testBallCollideWithBallNinetyDegrees() {
        Ball ourFirstBall = new Ball(14 - .1767766952966369, 13 - .1767766952966369,1.0,0); //position 10,13, velocity 1,0
        Ball ourSecondBall = new Ball(16 + .1767766952966369, 13 + .1767766952966369,-1.0,0); //position 17,13.707, velocity -1,0
        
        testAGadget(ourFirstBall, ourSecondBall, 1, new Ball(15 - .1767766952966369, 13 - .1767766952966369, 0.0, -1.0));
    }

    public void testAGadget(Ball ourBall, Gadget ourGadget, double timeToCollisionIdeal, Ball ballRightAfterCollision) {
  
        //Ball collides after timeToCollisionIdeal milliseconds 
        assertEquals(timeToCollisionIdeal, ourGadget.collisionTime(ourBall), 0.005);
        
        //Advance to collision
        double timeToCollision = ourGadget.collisionTime(ourBall);
        ourBall.updateToTime(timeToCollision);
        ourGadget.updateToTime(timeToCollision);
        
        //Check proximity (manual x,y check)
        assertEquals(ballRightAfterCollision.getXPos(), ourBall.getXPos(), 0.005);
        assertEquals(ballRightAfterCollision.getYPos(), ourBall.getYPos(), 0.005);
        
        //Collide
        ourGadget.collideWithBall(ourBall);
        
        //Confirm that ball will NO LONGER collide (at least, not for a while).
        assertTrue(ourGadget.collisionTime(ourBall) > 400);
        
        //Check reflection
        assertEquals(ballRightAfterCollision.getXVel(), ourBall.getXVel(), 0.005);
        assertEquals(ballRightAfterCollision.getYVel(), ourBall.getYVel(), 0.005);
        
    }
    
}
