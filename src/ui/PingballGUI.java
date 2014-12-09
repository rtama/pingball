package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import pingball.Pingball;

   /**
    * PingballGUI describes the GUI for our Pingball game. It creates its own layout and 
    * adds listeners to each component of the menu and drawing area.
    */
public class PingballGUI extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L; // required by Serializable

   // private final CopyOnWriteArrayList<String> wallNames;
    private Pingball client;
    private String initalStateString;
    private Timer boardTimer;
    private boolean isPaused;
    
    private BoardDrawing canvas;
   
    // JMenu objects 
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenu menuGame = new JMenu("Game");
    private final JMenu menuHelp = new JMenu("Help");
    private final JMenu menuConnection = new JMenu("Connection");
    private final JMenuItem openFile = new JMenuItem("Open board");
    private final JMenuItem restartBoard = new JMenuItem("Restart board");
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
        this.canvas = new BoardDrawing(client);
        // set menu bar
        this.client = client;
        setMenuBar();
        createLayout();
        addListeners();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.gameLoop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }            
        });
        thread.start();
        this.addKeyListener(this);
    }
    
    /**
     * Creates a GUI with a blank board, not connected to any server. The user can choose to
     * load a board from a file and connect to a server.
     */
    public PingballGUI() {
        this.canvas = new BoardDrawing();
        //set menu bar
        setMenuBar();
        createLayout();
        addListeners();
        this.addKeyListener(this);
    }
    
    /**
     * Sets the menuBar layout, including menus: File, Game, Connection
     */
    private void setMenuBar() {
        
        // Add all menus to the MenuBar
        menuBar.add(menuFile);
        menuBar.add(menuGame);
        menuBar.add(menuConnection);

        // Add menuItems to menuFile
        menuFile.add(openFile);
        
        // Add menuItems to MenuGame
        menuGame.add(restartBoard);
        menuGame.add(pause);
        
        // Add menuItems to menuConnection
        menuConnection.add(connect);
        menuConnection.add(disconnect);
        
        // set the MenuBar
        setJMenuBar(menuBar);

    }
    
    /**
     * Creates the layout of the GUI content pane. Menu bar with JPanel containing canvas for game drawing.
     */
    private void createLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(canvas))
                );
        
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)                            
                            .addComponent(canvas))
                );
        
        setTitle("Pingball Game");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    
    /**
     * Adds Action Listeners to all of the components of the GUI:
     *      openFile: opens a new board file
     *      connect: connects the client to a server provided the hostname and port
     *      disconnect: discconects the client from the server.
     *      pause: pause the game
     *      restart: restart the Board with the initial startstate
     */
    public void addListeners() {
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread fileThread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        int returnVal = fc.showOpenDialog(PingballGUI.this);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File boardFile = fc.getSelectedFile();
                            String fileName = boardFile.getPath();
                            System.out.println("I found the board file: " + fileName);
                            try {
                                client = new Pingball(fileName);
                                canvas.setClient(client);
                                client.gameLoop();
                            } catch (IOException | InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            System.out.println("Aww");
                        }                        
                    }
                    
                });
                fileThread.start();

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
                if (pause.getText().equals("Pause")) {
                    pauseGame();
                } else {
                    resumeGame();
                }

            }
        });

        restartBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartInitialBoard();
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
        // boardTimer.stop();
        this.client.pause();
        pause.setText("Resume");
    }

    /**
     * Resume the game
     */
    public void resumeGame() {
        // boardTimer.start();
        this.client.resume();
        pause.setText("Pause");
    }

    /**
     * Restart the game from the initial state of the board. Disconnects the game if
     * it was originally connected.
     */
    public void restartInitialBoard() {
        this.client.restartBoard();
        this.resumeGame();
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

