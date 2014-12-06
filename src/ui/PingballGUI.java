package ui;

import pingball.Pingball;

import java.awt.Canvas;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

/**
 * TODO: spec
 *
 */
public class PingballGUI extends JFrame{
    
    private static final long serialVersionUID = 1L; // required by Serializable
    // Components in the pingball GUI
    private final JButton loadBoard = new JButton("Load Board");
    private final JButton connect = new JButton("Connect");
    private final JButton disconnect = new JButton("Disconnect");
    private final JButton restart = new JButton("Restart");
    private final JButton quit = new JButton("Quit");
    private final JButton pause = new JButton("Pause");
    private final JTextField boardFile = new JTextField();
    private final JTextField hostText = new JTextField();
    private final JTextField portText = new JTextField();
    private final JLabel hostLabel = new JLabel("Host");
    private final JLabel portLabel = new JLabel("Label");
    private final Canvas boardDisplay = new Canvas();
    
   
    
    
    /**
     * Creates a GUI with a blank board, not connected to any server. The user can choose to
     * load a board from a file and connect to a server.
     */
    public PingballGUI() {

    }
    
    /**
     * Creates a GUI with the specified board. May or may not be connected to a server.
     * @param client: a pingball game to display in the GUI
     */
    public PingballGUI(Pingball client) {
 
    }
    
    /**
     * Creates the layout of GUI content pane.
     */
    private void createLayout(){
        
        
    }
    
}

