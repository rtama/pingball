package pingball;

import static org.junit.Assert.*;


import org.junit.Test;

import physics.Vect;

public class SquareAndCircleBumperTest {

    /**
     * Square Bumper Testing Strategy:
     *      Ensure that Ball interacts correctly with the square bumper
     *      Partitions:
     *          Hit every wall (top, bot, left, right)
     *          Hit corners
     *          
     * Circle Bumper Testing Strategy:
     *      Ensure that the Ball interacts correctly with the circle bumper
     *      Partitions:
     *          Hit the circle
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
    
    // Test top wall of square bumper
    @Test public void testSquareBumperHitTopWall() {
        Board board = new Board(0,0,0);
        
        Gadget squareBumper = new SquareBumper(5,5);
        board.addGadget(squareBumper);
        
        //Top wall
        Ball ball = new Ball(5,1, 0, 0.001);
        board.addBall(ball);
        stepBoard(board, 25);
        assertEquals(ball.getBoardY(), 3);
        assertEquals(ball.getBoardX(), 5);
    }
    @Test public void testSquareBumperHitBotWall() {
        Ball ball = new Ball(5,1, 0, 0.001);
        Board board = new Board(0,0,0);
        board.addBall(ball);
        
        Gadget squareBumper = new SquareBumper(5,5);
        board.addGadget(squareBumper);
        
        
        //Bottom wall
        System.out.println("Hitting bottom wall");
        updateBall(ball, new Vect(5, 10), new Vect(0, -.001));
        stepBoard(board, 20);
        System.out.println(ball.getBoardY());
        System.out.println(ball.getBoardX());
        assertEquals(ball.getBoardY(), 6);
        assertEquals(ball.getBoardX(), 5);
    }
    
    @Test public void testSquareBumperHitLeftWall() {
        Ball ball = new Ball(5,1, 0, 0.001);
        Board board = new Board(0,0,0);
        board.addBall(ball);
        
        Gadget squareBumper = new SquareBumper(5,5);
        board.addGadget(squareBumper);
        
        
        // Left wall
        System.out.println("Hitting left wall");
        
        updateBall(ball, new Vect(3, 5), new Vect(.001, 0));
        stepBoard(board, 20);
        assertEquals(ball.getBoardY(), 5);
        assertEquals(ball.getBoardX(), 2);
        
        
    }
    
    @Test public void testSquareBumperHitRightWall(){
        Ball ball = new Ball(5,1, 0, 0.001);
        Board board = new Board(0,0,0);
        board.addBall(ball);
        
        Gadget squareBumper = new SquareBumper(5,5);
        board.addGadget(squareBumper);
        
        
        // Right Wall
        System.out.println("Hitting right wall");
        updateBall(ball, new Vect(7, 5), new Vect(-.001, 0));
        stepBoard(board, 20);
        System.out.println(ball.getBoardY());
        System.out.println(ball.getBoardX());
        assertEquals(ball.getBoardY(), 5);
        assertEquals(ball.getBoardX(), 9);
    }
    
    @Test public void testSquareBumperHitCorners() {
        Ball ball = new Ball(1,1,.001,.001);
        Board board = new Board(0,0,0);
        board.addBall(ball);
        
        Gadget sb = new SquareBumper(5,5);
        board.addGadget(sb);
        stepBoard(board, 20);
        
        System.out.println(ball.getBoardY());
        System.out.println(ball.getBoardX());
        assertEquals(ball.getBoardY(), 4);
        assertEquals(ball.getBoardX(), 4);
    }
    
}
    