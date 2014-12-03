package pingball;

import static org.junit.Assert.*;

import org.junit.Test;

public class PortalTest {
    
    public void stepBoard(Board board, int steps){
        for (int i=1; i<=steps; i++){ 
            board.update(200);
            System.out.println(board.toString()) ;
        }
    }
    /**
     * Portal Testing Strategy
     *      Make sure that the ball interacts with a portal correctly
     *      Partitions:
     *         
     *         Source and Target portals on the same Board
     *         Source and Target portals on a different Board
     *         Ball hits disconnected portal (P(A) -> P(B)) B does not exist. 
     *         Asymmetrically connected portal (P(A)-> P(B) -> P(C)) 
     *         
     */
    
    @Test public void testPortalSourceAndTargetSameBoard(){ 

        //Board hits SourcePortal which teleports to a portal on the same Board. 
            //Assert that velocity of ball is the same after going through the portal
            //Assert that the position of the ball changed (ball was teleported). 
        Board board = new Board(0,0,0); 
        Ball ball = new Ball(5,0,0,.001); 
        board.addBall(ball); 
       
        Portal portalA = new Portal("A", 10, 10, "B",board); 
        Portal portalB = new Portal("B", 5, 5, "A",board); 
        board.addPortal(portalA);
        board.addPortal(portalB);
        
        stepBoard(board, 50); 
        
//        for (int i=0; i<400; i++){ 
//            if (board.toString().charAt(i) == '*'){ 
//                System.out.println(i); 
//            }
//        }
        assertEquals('*' , board.toString().charAt(379) ); 
        //Keep in mind that we don't have access to the newBall

    }
 
   @Test public void testPortalDisconnectedPortal(){ 
       
       //Ball hits disconnected portal
           //Assert that ball passes through portal as if it where not there. 
       
       Board board = new Board(0,0,0); 
       Ball ball = new Ball(5,0,0,.001); 
       board.addBall(ball); 
       Portal portalA = new Portal("A", 5, 5, "B",board); 
       board.addPortal(portalA); 
       
       stepBoard(board,50); 
       
       assertEquals('*' , board.toString().charAt(259) ) ; 
   }
   
   @Test public void testPortalAssymetricallyConneceted(){ 
       //Ball hits Portal A which is linked to Portal B which is linked to Portal C
           //Assert that A teleports to B
           //Assert that B teleports to C
       
       Board board = new Board(0,0,0); 
       Ball ball = new Ball(1,19,0,-.01);
       board.addBall(ball);
       
       Portal portalA = new Portal("A", 1,10, "B", board); 
       Portal portalB = new Portal("B", 5,10, "C", board); 
       Portal portalC = new Portal("C", 10,10, "A", board); 
       
       board.addPortal(portalA); 
       board.addPortal(portalB); 
       board.addPortal(portalC); 

       stepBoard(board,115); 
     
       System.out.println(board.toString()); 


       //assertEquals('*' , board.toString().charAt(140) ); 
   }
    


   @Test public void testPortalSourceAndTargetDifferentBoard(){ 

       //Ball hits sourcePortal which teleports to a Portal on a different Board. 
           //Assert that velocity of ball is the same after going through the portal
           //Assert that the position of the ball changed (ball was teleported). 
           //Assert that ball is not in oldBoard
           //Assert that ball is in newBoard 
       
       //Since this requires multiple boards, we will test it manually using 
       // SimplePortal_A.pb and SimplePortal_B.pb
   }
   
}
