package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pingball.Board; //TODO: check
import physics.Vect;

/**
 * parser class for Board building
 */
public class BoardBuilder {

    private enum LineDeclaration {
        BOARD, BALL, GADGET, FIRE, KEYUP, KEYDOWN, IGNORE, INVALID
    }

    // acceptable gadget names
    private static String[] gadgetTypes = { "absorber", "circleBumper", "triangleBumper",
            "squareBumper", "rightFlipper", "leftFlipper", "portal" };
    
    // default values
    //TODO: check the values with board
    private static final double DEFAULT_G = 25;
    private static final double DEFAULT_MU = 0.025;
    private static final double DEFAULT_MU2 = 0.025;
    
    // for identifying field definitions //TODO: fix
    private static Pattern fieldPattern = Pattern.compile("\\b[A-Za-z_]\\w*\\s*=\\s*[\\w.-]+\\b");
    // for assessing legal names     //TODOL fix 
    private static Pattern namePattern = Pattern.compile("[A-Za-z_]\\w*");

    /**
     * Creates a board from reader
     * @param rin
     * @return the board generated from the reader
     * @throws IOException
     */
    public static Board boardFromReader(Reader rin) throws IOException {
        Board board = null;
        BufferedReader reader = new BufferedReader(rin);

        try {
            Map<String, Vect> memoryMap = new HashMap<String, Vect>();
            
            // first parse out name and params for the board
            board = findAndParseBoardLine(reader);
            
            // next get board contents
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                line = line.trim();
                switch (parseLineDeclaration(line)) {
                case BOARD:
                    // we already parsed the board parameters
                    throw new RuntimeException("file format error: duplicate board line: " + line);
                case BALL:
                    parseBallDeclaration(line, board, memoryMap);
                    break;
                case GADGET:
                    parseGadgetDeclaration(line, board, memoryMap);
                    break;
                case FIRE:
                    parseTriggerDeclaration(line, board, memoryMap);
                    break;
                case KEYUP:
                    parseKeyupDeclaration(line, board, memoryMap);
                    break;
                case KEYDOWN:
                    parseKeydownDeclaration(line, board, memoryMap);
                    break;
                case IGNORE:
                    // comment or empty
                    break;
                case INVALID:
                    // unreadable line
                    throw new RuntimeException("file format exception: " + line);
                default:
                    throw new RuntimeException("file format exception: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("file failed to load");
        } finally {
            reader.close();
        }

        if (board != null) {
            return board;
        } else {
            throw new RuntimeException("file could not be read");
        }
    }

    /**
     * 
     * @return the board encoded in the argument file
     * @param filename file that is to be parsed
     */
    public static Board readFile(File filename) throws IOException, FileNotFoundException {
        return boardFromReader(new FileReader(filename));
    }


    /**
     * Search for the field with name [fileName] in [line]
     * return the value as a string without whitespace.
     * @param fieldName
     * @param line
     * @return
     */
    private static String parseField(String fieldName, String line) {
        Matcher fieldMatcher = fieldPattern.matcher(line);
        while (fieldMatcher.find()) {
            String field = fieldMatcher.group();
            if (field.split("=")[0].trim().equals(fieldName)) {
                return field.replaceFirst(fieldName + "\\s*=", "").trim();
            }
        }
        return "";
    }

    private static void parseKeyupDeclaration(String line, Board board, Map<String, Vect> memMap) {
        String key = parseField("key", line);
        String triggeredGadget = parseField("action", line);
        //TODO: add the key up to the board -- key, triggeredGadget
    }

    private static void parseKeydownDeclaration(String line, Board board, Map<String, Vect> memMap) {
        String key = parseField("key", line);
        String triggeredGadget = parseField("action", line);
        //TODO: add the key down to the board -- key, triggeredGadget
    }


    /**
     * parses Ball declaration //TODO: add more
     * line declarations should be checked before function called
     * @param line
     * @param board
     * @param memMap
     */
    private static void parseBallDeclaration(String line, Board board, Map<String, Vect> memMap) {
        String name = parseField("name", line);
        // verify that name is legal
        if (name.isEmpty()) {
            throw new RuntimeException("missing element name: " + line);
        }
        if (!namePattern.matcher(name).matches()) {
            throw new RuntimeException("invalid name: " + name);
        }
        if (memMap.containsKey(name)) {
            throw new RuntimeException("duplicate name: " + name);
        }
        // get state parameters
        double x = Double.parseDouble(parseField("x", line));
        double y = Double.parseDouble(parseField("y", line));
        double xVel = Double.parseDouble(parseField("xVelocity", line));
        double yVel = Double.parseDouble(parseField("yVelocity", line));
        // place on board
        //TODO: board.addBall(name, x, y, xVel, yVel);
        
        // keep in memory for later access, if necessary
        memMap.put(name, new Vect(x, y));
    }


    /**
     * add a gadget to the board based on arguments in the line. 
     * Line should be trimmed before function is called.
     * @param line
     * @param board
     * @param memMap
     */
    private static void parseGadgetDeclaration(String line, Board board, Map<String, Vect> memMap) {
        String name = parseField("name", line);
        // verify that name is legal
        if (name.isEmpty()) {
            throw new RuntimeException("missing element name: " + line);
        }
        if (!namePattern.matcher(name).matches()) {
            throw new RuntimeException("invalid name: " + name);
        }
        if (memMap.containsKey(name)) {
            throw new RuntimeException("duplicate name: " + line);
        }
        int x = Integer.parseInt(parseField("x", line));
        int y = Integer.parseInt(parseField("y", line));
        memMap.put(name, new Vect(x, y));

        
        //determine what gadget is is, and get the needed arguemnts
        //TODO: complete
        
        if (line.startsWith("squareBumper ")) {
            //TODO: add square bumper.
        	//board.addSquareBumper(name, x, y);
        } else if (line.startsWith("circleBumper ")) {
            //TODO: add circle bumper
        	//board.addCircleBumper(name, x, y);
        } else if (line.startsWith("absorber ")) {
            int width = Integer.parseInt(parseField("width", line));
            int height = Integer.parseInt(parseField("height", line));
            if (height <= 0 || width <= 0) {
                throw new IllegalArgumentException("illegal width/height: " + line);
            }
            //TODO: add absorber
            //board.addAbsorber(name, x, y, width, height);
        } else if(line.startsWith("portal")){
            String otherBoard = parseField("otherBoard", line);
            String otherPortal = parseField("otherPortal", line);
            //TODO: add portal
            //board.addPortal(name, x, y, otherBoard, otherPortal);
        }
        else {
            int orientation = Integer.parseInt(parseField("orientation", line));
            if (line.startsWith("triangleBumper ")) {
            	//TODO: add triangle bumper
                //board.addTriangleBumper(name, x, y, orientation);
            } else if (line.startsWith("leftFlipper")) {
            	//TODO: add flipper
                //board.addFlipper(name, x, y, FlipperType.LEFT, orientation);
            } else if (line.startsWith("rightFlipper")) {
                //TODO: add flipper
            	//board.addFlipper(name, x, y, FlipperType.RIGHT, orientation);
            }
        }
    }

    /**
     * Make triggering link on the board from line
     * @param line
     * @param board
     * @param memMap
     */
    private static void parseTriggerDeclaration(String line, Board board, Map<String, Vect> memMap) {
        String trigger = parseField("trigger", line);
        String action = parseField("action", line);
        
        //TODO: if trigger/action is empty return exception
        //TODO: if not null, then create triggering link
    }

    /**
     * Find the declaration of the line.
     * Assume line has been trimmed.
     * @param line
     * @return INVALID if not acceptable line
     */
    private static LineDeclaration parseLineDeclaration(String line) {
        for (String gadgetType : gadgetTypes) {
            if (line.startsWith(gadgetType + " ")) {
                return LineDeclaration.GADGET;
            }
        }
        if (line.startsWith("keyup "))
            return LineDeclaration.KEYUP;
        if (line.startsWith("keydown "))
            return LineDeclaration.KEYDOWN;
        if (line.startsWith("ball "))
            return LineDeclaration.BALL;
        if (line.startsWith("board "))
            return LineDeclaration.BOARD;
        if (line.startsWith("fire "))
            return LineDeclaration.FIRE;
        if (line.startsWith("#") || line.isEmpty())
            return LineDeclaration.IGNORE;
        return LineDeclaration.INVALID;
    }


    /**
     * Construct the board
     * @param reader
     * @return
     * @throws IOException
     */
    private static Board findAndParseBoardLine(BufferedReader reader) throws IOException {
        String name = "";
        double g = DEFAULT_G;
        double mu = DEFAULT_MU;
        double mu2 = DEFAULT_MU2;
        boolean specialCase = false;
        // first skip all the lines that precede the board line
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            if (parseLineDeclaration(line) != LineDeclaration.BOARD) {
                if (!line.startsWith("\\s*#") && !line.isEmpty()) {
                	//starts with commment or empty line 
                    throw new IllegalArgumentException("format error: " + line);
                }
            } else {
            	//board line found. parse board params and get names
                name = parseField("name", line);
                // verify that name is legal
                if (!namePattern.matcher(name).matches()) {
                    throw new RuntimeException("invalid name: " + name);
                }
                // parse physics params
                String param = parseField("gravity", line);
                if (param.equals("cyclic")) {
                    specialCase = true;
                } else {
                    g = param.isEmpty() ? g : Double.parseDouble(param);
                }
                param = parseField("friction", line);
                mu = param.isEmpty() ? mu : Double.parseDouble(param);
                param = parseField("friction2", line);
                mu2 = param.isEmpty() ? mu2 : Double.parseDouble(param);
                // break loop
                break;
            }
        }
        // construct a new board with the parsed parameters and return it
        if (specialCase) {
            //TODO: return new Board
        }
        return new Board(name, g, mu, mu2);
    }
}