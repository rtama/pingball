
package pingball.parser;


import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;

import pingball.Absorber;
import pingball.Ball;
import pingball.Board;
import pingball.CircleBumper;
import pingball.Gadget;
import pingball.LeftFlipper;
import pingball.Portal;
import pingball.RightFlipper;
import pingball.SquareBumper;
import pingball.TriangleBumper;

/**
 * TODO: spec
 *
 */
public class PingballFactory extends PingballBaseListener {
    
    private final String GRAVITY = "gravity"; 
    private final String FRICTION1 = "friction1"; 
    private final String FRICTION2 = "friction2"; 
    private final String ORIENTATION = "orientation"; 
    private final String WIDTH = "width"; 
    private final String HEIGHT = "height"; 
    private final String LEFT_FLIPPER = "leftFlipper"; 
    private final String X = "x"; 
    private final String Y = "y"; 
    private final String Y_VELOCITY = "yVelocity"; 
    private final String X_VELOCITY = "xVelocity"; 
    private final String NAME_LITERAL = "name"; 
    private final String TRIGGER = "trigger";
    private final String ACTION = "action";
    private final String OTHER_BOARD = "otherBoard"; 
    private final String TARGET_PORTAL = "otherPortal";
    private final String KEYUP = "keyup";
    private final String KEYDOWN = "keydown";
    
    private final int SHIFT = 2;    // This is used when getting the value assigned to a variable.
                                    // For instance, in the definition 'x=1', the value, 1, is 
                                    // 2 indices to the right of the index of 'x'.
    private final Double UNIT_CONVERSION = .000001;
    
    private Board board;
    private boolean badBoard = false; 
    private Socket socket=null;
    private Map<String, ArrayList<String>> keyups = new HashMap<String, ArrayList<String>>();
    private Map<String, ArrayList<String>> keydowns = new HashMap<String, ArrayList<String>>();
        
    /**
     * TODO add detail
     * Constructor 
     */   
    public PingballFactory() {    }
    
    public PingballFactory(Socket socket) {
        this.socket = socket;
    }
    
    
    @Override public void enterKeyDef(PingballParser.KeyDefContext ctx) {
        //System.out.print( ctx.children ); 
        String keyUp = "";
        String keyDown = "";
        String action = "";
        
        List<ParseTree> children = ctx.children; 
        for (int i = 0; i < ctx.getChildCount(); i++){ 
                    
            if (children.get(i).toString().contains(KEYUP)) {
                keyUp = children.get(i+SHIFT).getText(); 
            }
            
            else if (children.get(i).toString().contains(KEYDOWN)) {
                keyDown = children.get(i+SHIFT).getText(); 
            }
            
            else if (children.get(i).toString().contains(ACTION)) {
                action = children.get(i+SHIFT).getText(); 
            }
                                
        }
        if (action.equals("")) {
            return;
        }
        if (!keyUp.equals("")) {
            if (keyups.containsKey(keyUp)) {
                keyups.get(keyUp).add(action);
            }else {
                keyups.put(keyUp, new ArrayList<String>(Arrays.asList(action)));
            }
            
        }else if (!keyDown.equals("")) {
            if (keydowns.containsKey(keyDown)) {
                keydowns.get(keyDown).add(action);
            }else {
                keydowns.put(keyDown, new ArrayList<String>(Arrays.asList(action)));
            }
        }
    }
    
    @Override public void enterAbsorberDef(PingballParser.AbsorberDefContext ctx) {
//      System.out.println("Creating new absorber:");
//      System.out.println(ctx.getText()); 
//      System.out.println(ctx.children); 
        
        int x = 0; 
        int y = 0; 
        int width = 0; 
        int height = 0;
        String name = ""; 
        
        List<ParseTree> children = ctx.children; 
        //Populate x,y,width,height,name
        for (int i = 0; i < ctx.getChildCount(); i++){ 
                    
            if (children.get(i).toString().contains(X)) {
                x = Integer.parseInt(children.get(i+SHIFT).getText()); //Skip equals sign and go to next value
            }
            
            else if (children.get(i).toString().contains(Y)) {
                y = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
            
            else if (children.get(i).toString().contains(WIDTH)) {
                width = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
            
            else if (children.get(i).toString().contains(HEIGHT)) {
                height = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }    
            
            else if (children.get(i).toString().contains(NAME_LITERAL)) {
                name = children.get(i+SHIFT).getText(); 
            } 
            
        }

        Gadget absorber = new Absorber(name,x,y,width,height );
        board.addGadget(absorber);
    }

    @Override public void enterFireDef(PingballParser.FireDefContext ctx) {
       //System.out.print( ctx.children ); 
       String trigger = "";  
       String action = "";
       
       List<ParseTree> children = ctx.children; 
       //Populate x,y, orientation,name
       for (int i = 0; i < ctx.getChildCount(); i++){ 
                   
           if (children.get(i).toString().contains(TRIGGER)) {
               trigger = children.get(i+SHIFT).getText(); 
           }
           
           else if (children.get(i).toString().contains(ACTION)) {
               action = children.get(i+SHIFT).getText(); 
           }
                               
       }
       board.addTrigger(trigger, action);
    }

    @Override public void enterTriangleBumperDef(PingballParser.TriangleBumperDefContext ctx) { 
        int x = 0; 
        int y = 0; 
        int orientation = 0; 
        String name = "";
        
        List<ParseTree> children = ctx.children; 
        //Populate x,y, orientation,name
        for (int i = 0; i < ctx.getChildCount(); i++){ 
                    
            if (children.get(i).toString().contains(X)) {
                x = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
            
            else if (children.get(i).toString().contains(Y)) {
                y = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
            
            else if (children.get(i).toString().contains(ORIENTATION)) {
                orientation = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
            
            else if (children.get(i).toString().contains(NAME_LITERAL)) {
                name = children.get(i+SHIFT).getText(); 
            } 
                       
        }
        
        Gadget triangleBumper = new TriangleBumper(name, x,y,orientation); 
        board.addGadget(triangleBumper); 

    }

    @Override public void enterSquareBumperDef(PingballParser.SquareBumperDefContext ctx) {
        int x = 0; 
        int y = 0; 
        String name = "";  
        
        List<ParseTree> children = ctx.children; 
        //Populate x,y,name
        for (int i = 0; i < ctx.getChildCount(); i++){ 
                    
            if (children.get(i).toString().contains(X)) {
                x = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
            
            else if (children.get(i).toString().contains(Y)) {
                y = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
            
            else if (children.get(i).toString().contains(NAME_LITERAL)) {
                name = children.get(i+SHIFT).getText(); 
            } 
            
        }
        
        Gadget squareBumper = new SquareBumper(name, x, y); 
        board.addGadget(squareBumper);
    }

    @Override public void enterCircleBumperDef(PingballParser.CircleBumperDefContext ctx) { 
        int x = 0; 
        int y = 0; 
        String name = ""; 
        
        List<ParseTree> children = ctx.children; 
        //Populate x,y
        for (int i = 0; i < ctx.getChildCount(); i++){ 
                    
            if (children.get(i).toString().contains(X)) {
                x = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
            
            else if (children.get(i).toString().contains(Y)) {
                y = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
            
            else if (children.get(i).toString().contains(NAME_LITERAL)) {
                name = children.get(i+SHIFT).getText(); 
            } 
             
        }
        
        Gadget circleBumper = new CircleBumper(name, x, y); 
        board.addGadget(circleBumper);
        
    }

    @Override public void enterBallDef(PingballParser.BallDefContext ctx) {
        //System.out.println(ctx.children);
        String name = "";
        double x = 0; 
        double y = 0; 
        double xVelocity = 0; 
        double yVelocity = 0; 
        List<ParseTree> children = ctx.children; 
        
        for (int i = 0; i < ctx.getChildCount(); i++){ 

            if (children.get(i).toString().contains(NAME_LITERAL)) {
                name = children.get(i+SHIFT).getText(); 
                //System.out.println("name:" + name); 
            } 
                     

            
            else if (children.get(i).toString().contains(X_VELOCITY)) {
                xVelocity = Double.parseDouble(children.get(i+SHIFT).getText()); 
                //System.out.println("xVel:" + x); 

            }
            
            else if (children.get(i).toString().contains(Y_VELOCITY)) {
                yVelocity = Double.parseDouble(children.get(i+SHIFT).getText()); 
                //System.out.println("yVel:" + y); 


            }    
            else if (children.get(i).toString().contains(X)) {
                x = Double.parseDouble(children.get(i+SHIFT).getText()); 
                //System.out.println("x:" + x); 

            }
            
            else if (children.get(i).toString().contains(Y)) {
                y = Double.parseDouble(children.get(i+SHIFT).getText());
                //System.out.println("y:" + y); 

            }
        }
        
        Ball ball = new Ball(name,x,y,xVelocity,yVelocity);
        board.addBall(ball); 
        
        
    }

    @Override public void enterBoardDef(PingballParser.BoardDefContext ctx) {

        //System.out.println(ctx.children); 
        
        String name = ""; 
        double gravity = .000025;
        double mu1 = .025;
        double mu2 = .000025;
        List<ParseTree> children = ctx.children;

        for (int i = 0; i < ctx.getChildCount(); i++){ 

            if (children.get(i).toString().contains(NAME_LITERAL)) {
                name = children.get(i+SHIFT).getText(); 
            } 
                     
            else if (children.get(i).toString().contains(GRAVITY)) {
                gravity = Double.parseDouble(children.get(i+SHIFT).getText()) * UNIT_CONVERSION; 
            }
            
            else if (children.get(i).toString().contains(FRICTION1)) {
                mu1 = Double.parseDouble(children.get(i+SHIFT).getText()) * UNIT_CONVERSION; 
            }
            
            else if (children.get(i).toString().contains(FRICTION2)) {
                mu2 = Double.parseDouble(children.get(i+SHIFT).getText()) * UNIT_CONVERSION; 
            }
             
        }
        if (socket!=null) {
            try {
                board = new Board(name,gravity,mu1,mu2, socket);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            board = new Board(name,gravity,mu1,mu2);  
        }

    }

    @Override public void enterFlipperDef(PingballParser.FlipperDefContext ctx) {

        String name = ""; 
        boolean left = false; 
        int orientation = 0;
        int x = 0;
        int y = 0;
        List<ParseTree> children = ctx.children; 
        
        for (int i = 0; i < ctx.getChildCount(); i++){ 
            
            if (children.get(i).toString().contains(NAME_LITERAL)) {
                name = children.get(i+SHIFT).getText(); 
            } 
            
            else if (children.get(i).toString().contains(X)) {
                x = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
            
            else if (children.get(i).toString().contains(Y)) {
                y= Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
            
            else if (children.get(i).toString().contains(LEFT_FLIPPER)) {
                left = true; 
            }
            
            else if (children.get(i).toString().contains(ORIENTATION)) { 
                orientation = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
             
        }
        
        Gadget flipper; 
        if (left) { 
            flipper = new LeftFlipper(name,x,y,orientation); 
        }
        else { 
            flipper = new RightFlipper(name,x,y,orientation); 
        }
        
        board.addGadget(flipper); 
        

    }
    @Override public void enterPortalDef(PingballParser.PortalDefContext ctx) { 
        String name = ""; 
        int x = 0; 
        int y = 0; 
        String targetPortal = ""; 
        String otherBoard = "";
        boolean inOtherBoard = false; 
        
        
        List<ParseTree> children = ctx.children; 
        
        for (int i = 0; i < ctx.getChildCount(); i++){ 
            
            if (children.get(i).toString().contains(NAME_LITERAL)) {
                name = children.get(i+SHIFT).getText(); 
            } 
            
            else if (children.get(i).toString().contains(X)) {
                x = Integer.parseInt(children.get(i+SHIFT).getText()); 
            }
            
            else if (children.get(i).toString().equals(Y)) {
                //System.out.println("Portal parsing "+ children.get(i+SHIFT).getText());
                y= Integer.parseInt(children.get(i+SHIFT).getText()); 
                //System.out.println("Portal parsing complte: "+ children.get(i+SHIFT).getText());

            }
            
            else if (children.get(i).toString().contains(TARGET_PORTAL)) {
                targetPortal = children.get(i+SHIFT).getText(); 
                
            }
         
            else if (children.get(i).toString().contains(OTHER_BOARD)) { 
                otherBoard = children.get(i+SHIFT).getText(); 
                inOtherBoard = true; 
            }
             
        }
        
        Gadget portal;  
        if (inOtherBoard){ 
            portal = new Portal(name,x,y,targetPortal, otherBoard, this.board);
        }
        else { 
            portal = new Portal (name,x,y,targetPortal, this.board); 
        }
        board.addPortal(portal); 
    }

    @Override public void visitErrorNode(ErrorNode node) {
  
         //throw new IOException();
        this.badBoard = true; 
    }
    
    /**
     * 
     * @return Return the constructed board. 
     * @throws IOException 
     */
    public Board getBoard() throws IOException{ 
        if (badBoard) throw new IOException(); 
        //System.out.println(board.toString());
        this.board.assignKeydowns(keydowns);
        this.board.assignKeyups(keyups);
        return this.board; 
    }
    
}
