package ui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
    */
public class PingballGUI extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L; // required by Serializable

   // private final CopyOnWriteArrayList<String> wallNames;
    private Pingball client;
    private String initalStateString;
    private Timer boardTimer;
    private boolean isPaused;
    
    private final JPanel boardDisplay = new JPanel();
   
    // JMenu objects 
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenu menuGame = new JMenu("Game");
    private final JMenu menuHelp = new JMenu("Help");
    private final JMenu menuConnection = new JMenu("Connection");
    private final JMenuItem openFile = new JMenuItem("Open board");
    private final JMenuItem restartBoard = new JMenuItem("Restart board");
    private final JMenuItem quitGame = new JMenuItem("Quit game");
    private final JMenuItem controls = new JMenuItem("Controls");
    private final JMenuItem pause = new JMenuItem("Pause");
    private final JMenuItem connect = new JMenuItem("Connect");
    private final JMenuItem disconnect = new JMenuItem("Disconnect");
    private final JFileChooser fc = new JFileChooser();
    
    /**
     * Creates a GUI with the specified board. May or may not be connected to a server.
     * @param client: a pingball game to display in the GUI
     */
    public PingballGUI(Pingball client) {
        // set menu bar
        setMenuBar();
        createLayout();
        addListeners();
    }
    
    /**
     * Creates a GUI with a blank board, not connected to any server. The user can choose to
     * load a board from a file and connect to a server.
     */
    public PingballGUI() {
        //set menu bar
        setMenuBar();
        createLayout();
        addListeners();
    }
    
    /**
     * Sets the menuBar layout.
     */
    private void setMenuBar() {
        
        // Add all menus to the MenuBar
        menuBar.add(menuFile);
        menuBar.add(menuGame);
        menuBar.add(menuConnection);
        menuBar.add(menuHelp);

        // Add menuItems to menuFile
        menuFile.add(openFile);
        
        // Add menuItems to MenuGame
        menuGame.add(restartBoard);
        menuGame.add(quitGame);
        menuGame.add(pause);
        
        // Add menuItems to menuConnection
        menuConnection.add(connect);
        menuConnection.add(disconnect);
        
        // Add menuItems to MenuHelp
        menuHelp.add(controls);
        
        
        // set the MenuBar
        setJMenuBar(menuBar);

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
                layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(boardDisplay))
                );
        
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)                            
                            .addComponent(boardDisplay))
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
                int returnVal = fc.showOpenDialog(PingballGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File boardFile = fc.getSelectedFile();
                    String fileName = boardFile.getPath();
                    System.out.println("I found the board file: " + fileName);
                    // TODO: Connect this with the parser to read the board and then 
                    //      draw it in the boardDrawing panel. 
                } else {
                    System.out.println("Aww");
                }
            }
        });

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField serverName = new JTextField();
                JTextField portNumber = new JTextField();
                
                //create server name for x,y values
                Object[] message = { "Server name:", serverName, "Port number:", portNumber };
                int result = JOptionPane.showConfirmDialog(null, message,
                        "Hostname and port", JOptionPane.OK_CANCEL_OPTION);
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

        quitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: quit game
              
            }
        });
        
        controls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: add actual controls!
                JOptionPane.showMessageDialog(PingballGUI.this,"Here are the controls",
                        "Controls", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
    


    /**
     * Connect to a server by specifying a host and port
     * @param host The host to connect to
     * @param port The port to connect to
     * @throws IOException
     * @throws UnknownHostException
     */
    public void connectServer(String host, int port) throws UnknownHostException, IOException {
        Socket newSocket = new Socket(host, port);
        if (this.client != null) {
            this.client.connect(newSocket);   
        }
    }

    /**
     * Disconnect from the server yo
     */
    public void disconnectServer() {
        if (this.client != null) {
            this.client.disconnect();            
        }
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

