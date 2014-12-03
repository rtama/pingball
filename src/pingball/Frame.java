package pingball;

import javax.swing.*;

/**
 * Produces a graphical window version of our pingball table, for debugging purposes.
 * 
 * @author geronm, hlzhou, dmayo2
 */
public class Frame {

    /**
     * Initialize a graphics window, running GameScreen.java
     */
    public static void main(String args[]) {
        JFrame f = new JFrame("2D Game");
        f.add("Center", new GameScreen());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,400);
        f.setVisible(true);
    }
}