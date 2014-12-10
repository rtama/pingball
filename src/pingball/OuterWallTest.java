package pingball;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * Tests for OuterWall
 * 
 * @author Erika
 */
public class OuterWallTest {

    /*
     * Testing strategy.
     *
     * collideWithBall method: 
     * A) ball going straight at the wall should bounce back with
     * same speed in opposite direction (no friction or gravity) 
     * B) ball hitting wall at an angle should reflect off
     * C) Linked wall correctly displays other Board names. 
     * D) ball hitting a non-solid wall should disappear from board and go to another board. 
     * E) ball hitting a non-solid wall linked to another wall in the same board should
     *      disappear from the first wall and appear on the other side (other wall). 
     */
    
    private static Gadget leftWall;
    private static Gadget rightWall;

    @BeforeClass
    public static void setUpBeforeClass() {
        leftWall = OuterWall.LEFT_WALL();
        rightWall = OuterWall.RIGHT_WALL();
    }
    
    /**
     * Helper method to print a wall
     * @param wall wall's symbols
     */
    public String print(char[][] wall){ 
        String result = "";
        for (int i=0; i< wall.length; i++){ 
            for (int j=0; j< wall[0].length; j++){
                result += wall[j][i]; 
            }
            result += "\n"; 
        }
        return result; 
    }
    
    //A)
    @Test
    public void testBallGoingStraightAtWall() {
        Vect position = new Vect(18,1);
        Vect velocity = new Vect(5,0);
        Ball ball = new Ball(position.x(), position.y(), velocity.x(), velocity.y());
        
        LineSegment fakeWall = new LineSegment(20,0,20,20);
        Vect expectedNewVelocity = Geometry.reflectWall(fakeWall, velocity);
        
        rightWall.collideWithBall(ball);
        
        assertEquals(expectedNewVelocity, ball.getVelocity());
    }

    //B)
    @Test
    public void testBallHitWallAtAngle() {
        Vect position = new Vect(19,19);
        Vect velocity = new Vect(-4,-2);
        Ball ball = new Ball(position.x(), position.y(), velocity.x(), velocity.y());
        
        LineSegment fakeWall = new LineSegment(0,0,0,20);
        Vect expectedNewVelocity = Geometry.reflectWall(fakeWall, velocity);
        
        leftWall.collideWithBall(ball);
        
        // Resulting velocities should be (4,2)
        assertEquals((int)expectedNewVelocity.x(), (int)ball.getVelocity().x());
        assertEquals((int)expectedNewVelocity.y(), (int)ball.getVelocity().y());
    }
    
    //C) 
    @Test
    public void testLinkedWallDisplay(){
        Gadget linkedTopWall = OuterWall.TOP_WALL();  
        ((OuterWall) linkedTopWall).setLinkedBoardName("ErikaLu"); 
        //System.out.println(print(linkedTopWall.getSymbol()).split("\n")[0]); 
        assertEquals( ".......ErikaLu........", print(linkedTopWall.getSymbol()).split("\n")[0]); 
        
        Gadget linkedLeftWall = OuterWall.LEFT_WALL(); 
        ((OuterWall) linkedLeftWall).setLinkedBoardName("*******************************bbbb"); 
        //System.out.println(print(linkedLeftWall.getSymbol())); 
        
        }
    

    
    //D)
    @Test
    public void testWallLinksToOtherBoardWall(){ 
        //Test a horizontal link within two balls
        //This needs to be manually tested by creating two boards and linking them
    }
    
    //E)
    @Test
    public void testWallLinksToOtherWall(){ 
        //This needs to be manually tested by creating two boards and linking them
    }

}
