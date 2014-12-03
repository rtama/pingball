package pingball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Manages a graphical representation of our pingball board, for debugging purposes.
 * Tests smooth functioning of the board under high-framerate conditions.
 * 
 * @author geronm, hlzhou, dmayo2
 */
public class GameScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int MILLISECS_PER_FRAME = 5;
    private Board board;
    private Flipper theLeftFlipper;
    private Timer gameTimer;

    /**
     *  Initializes the board and starts the game.
     */
    public GameScreen() {
     
        //Initialize Game Board
        
        //For now, hacking the solution. 
        board = new Board();
       // board.addBall(new Ball(1.0,1.0,5,0.002));
       // board.addBall(new Ball(10.0,6.0,-0.5,-0.002));
        board.addBall(new Ball(12.0,3.0,-0.2,0.002));
        board.addBall(new Ball(15.0,3.0,-0.4,0.002));
        board.addGadget(new SquareBumper(10, 16));
        board.addGadget(new CircleBumper(14, 16));
        board.addGadget(new TriangleBumper(5, 16, 0));
        board.addGadget(new TriangleBumper(17, 16, 90));
        theLeftFlipper = new LeftFlipper(12,2,90);
        board.addGadget(theLeftFlipper);
        board.addGadget(new TriangleBumper(19, 0, 90));
        Gadget abs = new Absorber(0, 18, 20, 2);
        board.addGadget(abs);
        abs.addTriggeredGadget(abs);
        
        //Start timer
        setFocusable(true);
     
        gameTimer = new javax.swing.Timer(MILLISECS_PER_FRAME, this);
        gameTimer.start();
     
    }
  
    
    @Override
    public void actionPerformed(ActionEvent e) {

        //MAIN LOOP
        
        //Toggle Flipper, for testing.
        if (!theLeftFlipper.isMoving()) {
            theLeftFlipper.action();
        }
        
        //update gadgets
        board.update(MILLISECS_PER_FRAME);        
        
        repaint();
        
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
     
        g2.setColor(Color.BLACK);
     
        RenderingHints rh  = new   RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        board.paintBoardOnGraphics2D(g2);
    }
}
