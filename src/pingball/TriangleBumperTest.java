package pingball;

import static org.junit.Assert.*;


import org.junit.Test;

import physics.Vect;

public class TriangleBumperTest {

    /**
     * Triangle Bumper Testing Strategy
     *      Make sure that rotateGadget works correctly. 
     *      Make sure that the ball is reflected at the correct angle
     *      Partitions:
     *         Rotate a triangle four times by 90 degrees
     *         Hit both of the straight walls 
     *         Hit the slanted wall (hypotenous)
     *         Try with different triangle orientations. 
     *         
     */
    
    public void stepBoard(Board board, int steps){
        for (int i=1; i<steps; i++){ 
            board.update(200);
            System.out.println(board.toString()) ;
        }
    }
    
    public void updateBall(Ball ball, Vect position, Vect velocity){ 
        ball.setVelocity(velocity);
        ball.setXPos(position.x());
        ball.setYPos(position.y()); 
    }
    //Test the walls of a Degree 0 TriangleBumper
    @Test public void testTriangleBumperHitEachWallDegree0(){ 
        Board board = new Board(0,0,0); 

        
        Gadget triangleBumper = new TriangleBumper(5,5,0); 
        board.addGadget(triangleBumper); 
        //Hit top wall
        System.out.println("Hitting Top Wall"); 
        Ball ball = new Ball(5,1, 0,.001); 
        board.addBall(ball);
        stepBoard(board,24); 
        assertTrue(ball.getBoardY() < 5); 
        //Should bounce back up 
        
        //Hit Bottom Wall
        System.out.println("Hitting Bottom Wall"); 


        updateBall(ball, new Vect(5,10), new Vect(0,-5));
        stepBoard(board,10); 
        assertTrue(ball.getBoardY() > 5); 
        //Should hit the longWall and be deflected to the to the right
        
        //Hit RightWall(long)
        System.out.println("Hitting Right Wall"); 
        updateBall(ball, new Vect(10.5,5.5), new Vect (-.001,0));
        stepBoard(board,50); 
        assertTrue(ball.getBoardY() > 5.5);
        //Should hit the longWall and be deflected to the bottom
        
        //Hit leftWall
        System.out.println("Hitting Left Wall"); 
        updateBall(ball, new Vect(1,5), new Vect (-.005,0));
        stepBoard(board,50); 
        assertTrue(ball.getBoardX() < 5);        
        //should bounce back to the left
    }
    
    @Test public void testTriangleBumperHitEachWallDegree90(){ 
        Board board = new Board(0,0,0); 

        
        Gadget triangleBumper = new TriangleBumper(5,5,90); 
        board.addGadget(triangleBumper); 
        //Hit top wall
        System.out.println("Hitting Top Wall"); 
        Ball ball = new Ball(5,1, 0,.005); 
        board.addBall(ball);
        stepBoard(board,50); 
        assertTrue(ball.getBoardY() < 5);    
        //Should bounce back up 
        
        //Hit Bottom Wall
        System.out.println("Hitting Bottom Wall"); 
        updateBall(ball,new Vect(5,10), new Vect(0,-.005));
        stepBoard(board,50); 
        assertTrue(ball.getBoardX() < 5);
        //Should hit the longWall and be deflected to the to the left
        
        //Hit RightWall(long)
        System.out.println("Hitting Right Wall"); 
        updateBall(ball,new Vect(10,5), new Vect (-.005,0));
        stepBoard(board,50); 
        assertTrue(ball.getBoardX() > 5); 
        //Should bounce back to the right
        
        
        //Hit leftWall
        System.out.println("Hitting Left Wall"); 
        updateBall(ball, new Vect(1,5.5), new Vect (.005,0));
        stepBoard(board,50); 
        assertTrue(ball.getBoardY() > 5);
        //should hit longWall and be deflected to the bottom
    }
    
    @Test public void testTriangleBumperHitEachWallDegree180(){ 
        Board board = new Board(0,0,0); 
 
        
        Gadget triangleBumper = new TriangleBumper(5,5,180); 
        board.addGadget(triangleBumper); 
        //Hit top wall
        System.out.println("Hitting Top Wall"); 
        Ball ball = new Ball(5.5,1, 0,.005); 
        board.addBall(ball);
        stepBoard(board,30); 
        assertTrue(ball.getBoardX() < 5);
        //Should hit longWall and be deflected to the left
        
        //Hit Bottom Wall
        System.out.println("Hitting Bottom Wall"); 
        updateBall(ball,new Vect(5.5,10), new Vect(0,-.005));

        stepBoard(board,50); 
        assertTrue(ball.getBoardY() > 5);
        //Should bounce back to the bottom
        
        //Hit RightWall(long)
        System.out.println("Hitting Right Wall"); 
        updateBall(ball,new Vect(10.5,5), new Vect (-.005,0));
        stepBoard(board,50);  
        assertTrue(ball.getBoardX() > 5); 
        //Should bounce back to the right
        
        
        //Hit leftWall
        System.out.println("Hitting Left Wall"); 
        updateBall(ball, new Vect(1.5,5), new Vect (.005,0));
        stepBoard(board,50); 
        assertTrue(ball.getBoardY() < 5); 
        //should hit longWall and be deflected up.
    }
    
    @Test public void testTriangleBumperHitEachWallDegree270(){ 
        Board board = new Board(0,0,0); 

        
        Gadget triangleBumper = new TriangleBumper(5,5,270); 
        board.addGadget(triangleBumper); 
        //Hit top wall
        System.out.println("Hitting Top Wall"); 
        Ball ball = new Ball(5.5,1, 0,.005); 
        board.addBall(ball);
        stepBoard(board,50); 
        assertTrue(ball.getBoardX() > 5); 
        //Should hit longWall and be deflected to the right
        
        //Hit Bottom Wall
        System.out.println("Hitting Bottom Wall"); 
        updateBall(ball,new Vect(5.5,10), new Vect(0,-.005));
        stepBoard(board,50); 
        assertTrue(ball.getBoardY() > 5);
        //Should bounce back to the bottom
        
        //Hit RightWall(long)
        System.out.println("Hitting Right Wall"); 
        updateBall(ball,new Vect(10.5,5), new Vect (-.005,0));
        stepBoard(board,50); 
        assertTrue(ball.getBoardY() < 5); 
        //Should hit longWall and be deflected up
        
        //Hit leftWall
        System.out.println("Hitting Left Wall"); 
        updateBall(ball,new Vect(1.5,5), new Vect (.005,0));
        stepBoard(board,50); 
        assertTrue(ball.getBoardX() < 5);
        //should bounce back to the left
    }  
}
