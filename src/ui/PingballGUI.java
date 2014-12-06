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
    private final JButton loadBoard;
    private final JButton connect;
    private final JButton disconnect;
    private final JButton restart;
    private final JButton quit;
    private final JButton pause;
    private final JTextField boardFile;
    private final JTextField hostText;
    private final JTextField portText;
    private final JLabel hostLabel;
    private final JLabel portLabel;
    private final Canvas boardDisplay;
    
    private Pingball pingball;
    
    /**
     * Creates a GUI with a blank board, not connected to any server. The user can choose to
     * load a board from a file and connect to a server.
     */
    public PingballGUI() {
        loadBoard = new JButton("Load Board");
        connect = new JButton("Connect");
        disconnect = new JButton("Disconnect");
        restart = new JButton("Restart");
        quit = new JButton("Quit");
        pause = new JButton("Pause");
        boardFile = new JTextField();
        hostText = new JTextField();
        portText = new JTextField();
        hostLabel = new JLabel("Host");
        portLabel = new JLabel("Label");
        boardDisplay = new Canvas();
    }
    
    /**
     * Creates a GUI with the specified board. May or may not be connected to a server.
     * @param client: a pingball game to display in the GUI
     */
    public PingballGUI(Pingball client) {
        loadBoard = new JButton("Load Board");
        connect = new JButton("Connect");
        disconnect = new JButton("Disconnect");
        restart = new JButton("Restart");
        quit = new JButton("Quit");
        pause = new JButton("Pause");
        boardFile = new JTextField();
        hostText = new JTextField();
        portText = new JTextField();
        hostLabel = new JLabel("Host");
        portLabel = new JLabel("Label");
        boardDisplay = new Canvas();
    }
    
    /**
     * Creates the layout of GUI content pane.
     */
    private void createLayout(){
        
        
    }
    
}

