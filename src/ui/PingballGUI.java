package ui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.Timer;

import pingball.Pingball;
import ui.KeyNames;

   /**
    * TODO: specs
    *
    */
public class PingballGUI extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L; // required by Serializable

   // private final CopyOnWriteArrayList<String> wallNames;
    private Pingball client;
    private String initalStateString;
    private Timer boardTimer;
    private boolean isPaused;
    
    // GUI Buttons
    private final JButton connect = new JButton("Connect");
    private final JButton disconnect = new JButton("Disconnect");
    private final JButton pause = new JButton("Pause");
    private final JTextField hostText = new JTextField();
    private final JTextField portText = new JTextField();
    private final JLabel hostLabel = new JLabel("Host:");
    private final JLabel portLabel = new JLabel("Label:");
    private final JPanel boardDisplay = new JPanel();
   
    // JMenu objects 
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    private final JMenuItem openFile = new JMenuItem("Open board");
    private final JMenuItem restartBoard = new JMenuItem("Restart board");
    private final JMenuItem quitGame = new JMenuItem("Quit game");
    
    /**
     * Creates a GUI with the specified board. May or may not be connected to a server.
     * @param client: a pingball game to display in the GUI
     */
    public PingballGUI(Pingball client) {
        // set menu bar
        menuBar.add(menu);
        
        //addListeners();
        createLayout();
    }
    
    /**
     * Creates a GUI with a blank board, not connected to any server. The user can choose to
     * load a board from a file and connect to a server.
     */
    public PingballGUI() {
    	
        //set menu bar
        menuBar.add(menu);
        setJMenuBar(menuBar);
        //Set window
        
        createLayout();
        //addListeners();
    }
    
    /**
     * Creates the layout of the GUI content pane
     */
    private void createLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(hostLabel)
                            .addComponent(hostText)
                            .addComponent(portLabel)
                            .addComponent(portText))
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(connect)
                            .addComponent(disconnect))
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(boardDisplay))
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(pause))
                );
        
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(hostLabel)
                            .addComponent(hostText)
                            .addComponent(portLabel)
                            .addComponent(portText))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(connect)
                            .addComponent(disconnect))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)                            
                            .addComponent(boardDisplay))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(pause))
                );
        setTitle("Pingball Game");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    
    /**
     * Adds Action Listeners to all of the components of the GUI.
     * 
     */
    public void addListeners() {
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:
            }
        });
        menu.add(openFile);

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField serverName = new JTextField();
                JTextField portNumber = new JTextField();
                
                //create server name for x,y values
                Object[] message = { "Server name:", serverName, "Port number:", portNumber };
                int result = JOptionPane.showConfirmDialog(null, message,
                        "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        String host = serverName.getText();
                        int portNum = Integer.parseInt(portNumber.getText());
                        connectServer(host, portNum);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot connect to specified host/port.");
                    }
                }
            }
        });

        disconnect.addActionListener(new ActionListener() {
            //TODO: show "Disconnected from Server
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnectServer();
                JOptionPane.showMessageDialog(null, "Disconnected from server.");
            }
        });


        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: "Game resumed"
            }
        });

        restartBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: restart initial Board 
            }
        });
        menu.add(restartBoard);

        quitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TOD: quite game
              
            }
        });
        menu.add(quitGame);
    }
    
    //constructor: loard from String board path



    private ActionListener boardRedrawer() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set wall and board size, wall name, add shape

            }
        };
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
     * Connect to a server by specifying a host and port
     * @param host The host to connect to
     * @param port The port to connect to
     * @throws IOException
     * @throws UnknownHostException
     */
    public void connectServer(String host, int port) throws UnknownHostException, IOException {

    }

    /**
     * Disconnect from the server
     */
    public void disconnectServer() {

    }

    /**
     * Pause the game
     */
    public void pauseGame() {
        boardTimer.stop();
    }

    /**
     * Resume the game
     */
    public void resumeGame() {
        boardTimer.start();
    }

    /**
     * Restart the game from the initial state of the board
     * @throws IOException
     */
    public void restartInitialBoard() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing. keyTyped corresponds to viewable characters so is a
        // subset of keyPressed.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String key = KeyNames.keyToString(e);
        this.client.keyPressed(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String key = KeyNames.keyToString(e);
        this.client.keyReleased(key);
    }

    public static void main(final String[] args) {
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        // default ports
        int p = 4444;
        // if not specified, no server connection will be attempted
        String hn = "";
        String fp = "";
        // parse arguments
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PingballGUI main = new PingballGUI();
                main.setVisible(true);
            }
        });
        
    }


}

