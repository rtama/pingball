package ui;

import java.awt.BorderLayout;
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

   
public class PingballGUI extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;
    private static final Color backgroundColor = Color.white;
    private static final int timeStepMillis = 20;
    private static final int WIDTH = 800, HEIGHT = 800;
    private static final double wallSize = 0.5;
    private static final double boardSize = 20 + wallSize * 2;
    private final JFrame frame = new JFrame();

   // private final CopyOnWriteArrayList<String> wallNames;
    private Pingball client;
    private String initalStateString;
    private Timer boardTimer;
    private boolean isPaused;
    
    // JMenu objects
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    final JMenuItem openFile = new JMenuItem("Open board");
    final JMenuItem connectToServer = new JMenuItem("Connect to server");
    final JMenuItem disconnectFromServer = new JMenuItem("Disconnect from server");
    final JMenuItem pauseGame = new JMenuItem("Pause game");
    final JMenuItem resumeGame = new JMenuItem("Resume game");
    final JMenuItem restartBoard = new JMenuItem("Restart board");
    final JMenuItem quitGame = new JMenuItem("Quit game");
    
    /**
     * Creates a GUI with the specified board. May or may not be connected to a server.
     * @param client: a pingball game to display in the GUI
     */
    public PingballGUI(Pingball client) {
        // set menu bar
        menuBar.add(menu);
        
        addListeners();
    }
    
    /**
     * Creates a GUI with a blank board, not connected to any server. The user can choose to
     * load a board from a file and connect to a server.
     */
    public PingballGUI() {
    	//shapes
    	//wallNames
    	//client
    	//initialStateString
    	//etc
    
    	
        //set menu bar
        menuBar.add(menu);
        //Set window
        
        addListeners();
    }
    
    /**
     * Creates the layout of the GUI content pane
     */
    private void createLayout() {
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        
        
        
    }
    
    public void addListeners() {
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:
            }
        });
        menu.add(openFile);

        connectToServer.addActionListener(new ActionListener() {
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
        menu.add(connectToServer);

        disconnectFromServer.addActionListener(new ActionListener() {
            //TODO: show "Disconnected from Server
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnectServer();
                JOptionPane.showMessageDialog(null, "Disconnected from server.");
            }
        });
        menu.add(disconnectFromServer);

        pauseGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: "Game Paused"
            }
        });
        menu.add(pauseGame);

        resumeGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: "Game resumed"
            }
        });
        menu.add(resumeGame);

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
    }


}

