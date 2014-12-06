package ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardDrawing extends JPanel{

    private static final long serialVersionUID = 1L;
    private static final Color backgroundColor = Color.white;
    private static final int timeStepMillis = 20;
    private static final int WIDTH = 800, HEIGHT = 800;
    private static final double wallSize = 0.5;
    private static final double boardSize = 20 + wallSize * 2;

    
    // TODO: just continue doing what you were doing in here. Good luck!
    
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // Set background to the background color.
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Set antialiasing hints
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paints the components
        
        // Draws the names of the board this is connected to
        FontMetrics fm = g2d.getFontMetrics();
        //height, scale


        // Top / bottom

        }
        // Left / right
}
