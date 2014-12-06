package ui;

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

import pingball.Pingball;

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
   
    private final Pingball pingball;
    
    
    //No parameters. No board or server connection
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
    
    // Constructor using host, port, and file. 
    public PingballGUI(String host, String port, File file) {
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
    
    // Construct using file.
    public PingballGUI(File file) {
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

