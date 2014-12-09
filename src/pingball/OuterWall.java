package pingball;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * OuterWall is a variant of the Gadget interface. It represents a border wall
 * surrounding the playfield.
 * 
 * It has no trigger or action. The coefficient of reflection is 1.0. It may be
 * either solid or invisible. A solid wall is reflective, so that a ball bounces
 * off it. An invisible wall allows a ball to pass through it, into another
 * playing area.
 * 
 * @author Erika
 */
public class OuterWall extends Gadget {

    private final LineSegment wall;
    private final double coeffOfRefl = 1.0;
    private boolean isSolid;
    private final boolean isHorizontal;
    private String linkedBoardName; 
    private final wallType type;
    
    private Color color = Color.DARK_GRAY;
    private final Color LIGHT_GRAY = new Color(192, 192, 192);
    
    /*
     * Mutability:
     * OuterWall is mutable. All but two of OuterWall's fields are final and immutable.
     * Its link to another board can be mutable, and it can be changed between solid and transparent (linked state)
     * 
     * OuterWall is contained in a single board so multiple boards (clients) will never
     * access the same OuterWall. 
     * 
     */
    // Rep invariant: none

    /**
     * Creates a wall which can only be horizontal or vertical (must start and
     * end either at the same x coordinate, or the same y coordinate)
     * No name.
     * 
     * @param startX
     *            x-coordinate of the first endpoint of the wall
     * @param startY
     *            y-coordinate of the first endpoint of the wall
     * @param endX
     *            x-coordinate of the second endpoint of the wall
     * @param endY
     *            y-coordinate of the second endpoint of the wall
     */
    public OuterWall(double startX, double startY, double endX, double endY, wallType type) {
        this("", startX, startY, endX, endY, type);
    }
    
    /**
     * Creates a wall which can only be horizontal or vertical (must start and
     * end either at the same x coordinate, or the same y coordinate)
     * Has a name
     * 
     * @param name of this wall
     * @param startX
     *            x-coordinate of the first endpoint of the wall
     * @param startY
     *            y-coordinate of the first endpoint of the wall
     * @param endX
     *            x-coordinate of the second endpoint of the wall
     * @param endY
     *            y-coordinate of the second endpoint of the wall
     */
    public OuterWall(String name, double startX, double startY, double endX, double endY, wallType type) {
        this.name = name;
        this.wall = new LineSegment(startX, startY, endX, endY);
        this.isSolid = true;
        if (startX == endX) {
            this.isHorizontal = false;
        } else {
            this.isHorizontal = true;
        }
        this.type = type;
    }

    /**
     * Creates the top wall of a 20Lx20L board.
     * @return 
     */
    public static OuterWall TOP_WALL() {
        return new OuterWall(0, 0, Board.HEIGHT-2, 0, wallType.TOP_WALL);
    }

    /**
     * Creates the left wall of a 20Lx20L board.
     * @return 
     */
    public static OuterWall LEFT_WALL() {
        return new OuterWall(0, 0, 0, Board.HEIGHT-2, wallType.LEFT_WALL);
    }

    /**
     * Creates the right wall of a 20Lx20L board.
     * @return 
     */
    public static OuterWall RIGHT_WALL() {
        return new OuterWall(Board.HEIGHT-2, 0, Board.HEIGHT-2, Board.HEIGHT-2, wallType.RIGHT_WALL);
    }

    /**
     * Creates the bottom wall of a 20Lx20L board.
     * @return 
     */
    public static OuterWall BOTTOM_WALL() {
        return new OuterWall(0, Board.HEIGHT-2, Board.HEIGHT-2, Board.HEIGHT-2, wallType.BOTTOM_WALL);
    }

    /**
     * @return True if this wall is solid, False otherwise.
     */
    public boolean isSolid() {
        return this.isSolid;
    }

    /**
     * Make this wall solid, effectively unlinking it from another board.
     */
    public void makeSolid() {
        this.isSolid = true;
    }

    @Override
    public void action() {
        // Wall has no action
    }

    @Override
    public void updateToTime(double timeToCollision) {
        // Wall doesn't change with time        
    }

    public Vect getPosition() {
        return this.wall.p1();
    }

    @Override
    public void addTriggeredGadget(Gadget triggeredGadget) {
        // Wall can't trigger any gadgets.
    }
    
    /**
     * Gets the name of the Board that this wall is linked to.
     * @return name of the board that this ball links to if it is linked (otherwise return an empty name)
     */
    public String getLinkedBoardName(){ 
        if (isSolid) {
            return "";
        }
        return this.linkedBoardName; 
    }
    
    /**
     * Sets the name of the Board that this wall is linked to.
     * @param name of the board that this ball links to
     */
    public void setLinkedBoardName(String name){ 
        isSolid = false;
        this.linkedBoardName = name; 
    }
    
    /**
     * 
     * @return whether this wall is a top, left, right, or bottom wall
     */
    public wallType getType() {
        return this.type;
    }

    @Override
    public char[][] getSymbol() {
        char[][] symbol = new char[Board.WIDTH][Board.HEIGHT];
        
        //Fill board with IGNORE_CHARs
        for (int i=0; i<Board.WIDTH; i++) {
            for (int j=0; j<Board.HEIGHT; j++) {
                symbol[i][j] = Board.IGNORE_CHAR;
            }
        }
        
        //Calculate midpoint for LinkedBoardName
        int boardNameLength = 0;
        int dotsBeforeName = 0;
        int dotsAfterName = 0;
        if (!isSolid){
             boardNameLength = linkedBoardName.length(); 
             dotsBeforeName = Math.round(((Board.WIDTH - boardNameLength) / 2)); 
             dotsAfterName = Board.WIDTH - dotsBeforeName - boardNameLength;  
             if (dotsBeforeName < 0) dotsBeforeName =0; 
        }
   
        if (isHorizontal) {
            if (this.wall.p1().y() == 0) {
                for (int i=0; i<Board.WIDTH; i++) {
                    if (!isSolid && i<Board.WIDTH-dotsAfterName && i >= dotsBeforeName) { 
                        symbol[i][0] = linkedBoardName.charAt(i-dotsBeforeName);
                    }
                    else symbol[i][0] = '.';
                }                
            }else {
                for (int i=0; i<Board.WIDTH; i++) {
                    if (!isSolid && i<Board.WIDTH-dotsAfterName && i >= dotsBeforeName) { 
                        symbol[i][(int) (this.wall.p1().y()+1)] = linkedBoardName.charAt(i-dotsBeforeName);
                    }
                    else symbol[i][(int) (this.wall.p1().y()+1)] = '.';
                }                  
            }

        }
        else {
            if (this.wall.p1().x() == 0) {
                for (int i=0; i<Board.HEIGHT; i++) {
                    if (!isSolid && i<Board.WIDTH-dotsAfterName && i >= dotsBeforeName) { 
                        symbol[0][i] = linkedBoardName.charAt(i-dotsBeforeName);
                    }
                    else symbol[0][i] = '.';
                }                
            }else {
                for (int i=0; i<Board.HEIGHT; i++) {
                    if (!isSolid && i<Board.WIDTH-dotsAfterName && i >= dotsBeforeName) { 
                        symbol[(int) this.wall.p1().x()+1][i] = linkedBoardName.charAt(i-dotsBeforeName);
                    }                    
                    else symbol[(int) this.wall.p1().x()+1][i] = '.';
                }
            }

        }
        return symbol;
    }

    @Override
    public void trigger() {
        // Wall has no trigger        
    }

    @Override
    public boolean isGhost() {
        return false;
    }

    @Override
    public int getBoardX() {
        // OuterWall has no meaningful representation of board position, so return -1.
        return -1;
    }

    @Override
    public int getBoardY() {
        // OuterWall has no meaningful representation of board position, so return -1.
        return -1;
    }

    @Override
    public double getCoefficientOfReflection() {
        return this.coeffOfRefl;
    }

    @Override
    public void collideWithBall(Ball ball) {
        if (!isSolid) {
            // Notify the server that the ball will go through this wall //TODO
            
            //ball.remove(); //Remove the ball from the Board. 
            //System.out.println("Wall wants to remove ball");
            ball.sendToBoardMessage(linkedBoardName,this.type);
            ball.remove();
        }else {
            // Update the ball's velocity to that of after the collision
            ball.setVelocity(Geometry.reflectWall(wall, ball.getVelocity(), coeffOfRefl));   
/*            if (color == Color.DARK_GRAY)
            	color = Color.LIGHT_GRAY;
            else
            	color = Color.DARK_GRAY;*/
        }
    }

    @Override
    public double collisionTime(Ball ball) {
        // Check when the ball will collide with the wall (regardless of whether or not it is solid)
        return Geometry.timeUntilWallCollision(wall, ball.getCircle(), ball.getVelocity());
    }

    @Override
    public void drawCanvas(Graphics2D g2d) {
        int height; // of rectangle shape
        int width;
        double x;
        double y;
        
        if (this.type.equals(wallType.BOTTOM_WALL)) {
            height = scaleFactor;
            width = Board.WIDTH*scaleFactor;
            x = 0;
            y = (this.wall.p1().y()+1)*scaleFactor; // Add 1 because wall goes outside of playing area
        }else if (this.type.equals(wallType.TOP_WALL)) {
            height = scaleFactor;
            width = Board.WIDTH*scaleFactor;
            x = 0;
            y = 0;            
        }else if (this.type.equals(wallType.LEFT_WALL)) {
            height = Board.HEIGHT*scaleFactor;
            width = scaleFactor;
            x = 0;
            y = 0;            
        }else {
            height = Board.HEIGHT*scaleFactor;
            width = scaleFactor;
            x = (this.wall.p1().x()+1)*scaleFactor; // Add 1 because wall goes outside of playing area
            y = 0;            
        }
        Shape shape = new Rectangle2D.Double(x, y, width, height);
        //System.out.println(this.wall.p1().x() + ", " + this.wall.p1().y());
        g2d.setColor(color);;
        g2d.draw(shape);
        g2d.fill(shape);
    }

}
