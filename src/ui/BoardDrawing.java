package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import pingball.Pingball;

/**
 *The list of components on the board (wall, shapes) are drawn
 * The list of 
 * of components is passed to the View, we simply paint them and display to the
 * user.
 */
public class BoardDrawing extends JPanel{

    private static final long serialVersionUID = 1L;
    private static final Color backgroundColor = Color.LIGHT_GRAY;
    private static final int WIDTH = 440, HEIGHT = 440;
    
    
    private Pingball pingball;

    public BoardDrawing() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    
    public BoardDrawing(Pingball client) {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.pingball = client;
    }
    
    /**
     * Updates this pingball game to a new one
     * @param newGame new pingball game
     */
    public void setClient(Pingball newGame) {
        this.pingball = newGame;
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // Set background to the background color.
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Paints the components
        g2d.setColor(Color.blue);

        if (pingball != null) {
            pingball.draw(g2d);            
        }
        
        this.repaint();
    }      
    

    

}
