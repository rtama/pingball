package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Thread-safety argument //TODO: add more
 * - CopyOnWriteArrayList
 * 
 * @author chaewonlee
 *
 */
public class BoardDrawing extends JPanel{

    private static final long serialVersionUID = 1L;
    private static final Color backgroundColor = Color.white;
    private static final int timeStepMillis = 20;
    private static final int WIDTH = 800, HEIGHT = 800;
    private static final double wallSize = 0.5;
    private static final double boardSize = 20 + wallSize * 2;

    private final CopyOnWriteArrayList<ColoredShape> shapes;
    private final CopyOnWriteArrayList<String> wallNames;
 
    // TODO: just continue doing what you were doing in here. Good luck!
    
    public BoardDrawing() {
    	shapes = new CopyOnWriteArrayList<ColoredShape>();
    	wallNames = new CopyOnWriteArrayList<String>();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // Set background to the background color.
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Set antialiasing hints
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paints the components
        g2d.setColor(Color.blue);
        for (ColoredShape coloredShape: shapes) {
        	g2d.setColor(coloredShape.getColor());;
        	g2d.draw(coloredShape.getShape());
        	g2d.fill(coloredShape.getShape());
        }
        
        // Draws the names of the board this is connected to
        // Draws the names of the board this is connected to
        FontMetrics fm = g2d.getFontMetrics();
        double expectedHeight = getHeight() / 21 / 2 - 1;
        double scale = expectedHeight / fm.getHeight();
        AffineTransform atx = AffineTransform.getScaleInstance(scale, scale);
        Font scaledFont = g2d.getFont().deriveFont(atx);
        g2d.setFont(scaledFont);
        fm = g2d.getFontMetrics();
        
        //height, scale


        
        // Top / bottom
        for (int i = 0; i < Math.min(2, wallNames.size()); i++) {
        	Font originalFont = g2d.getFont();
            String wallName = wallNames.get(i);
            int x = 0, y = 1;
            
            //TODO: figure out how to place the x,y of wallName
            
            g2d.drawString(wallName, x, y);
            g2d.setFont(originalFont);
        }
        // Left / right
        for (int i = 2; i < Math.min(4, wallNames.size()); i++) {
            Font origFont = g2d.getFont();
            String wallName = wallNames.get(i);
            int x = 0, y = 0;
            
            //TODO: figure out how to place the x,y of wallName
 
            g2d.drawString(wallName, x, y);
            g2d.setFont(origFont);
        }
    }      
    
    /**
     * Load a board from a file
     * @param file The file to load the board from
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void loadBoard(File boardFile) throws FileNotFoundException, IOException {

    }
    
    /**
     * redraws the board 
     * @return
     */
    private ActionListener boardRedrawer() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//TODO: repaint the shapes and walls of the board
            }
        };
    }

}
