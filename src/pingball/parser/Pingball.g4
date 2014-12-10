grammar Pingball;

@header {
package pingball.parser;
}


BOARD
	: 'board'
	;

GRAVITY
	: 'gravity'
	;

FRICTION1
	: 'friction1'
	;

FRICTION2
	: 'friction2'
	;

BALL
	: 'ball'
	;
	
NAME_LITERAL
	: 'name'
	;

X_VELOCITY
	: 'xVelocity'
	;

Y_VELOCITY
	: 'yVelocity'
	;

SQUARE_BUMPER
	: 'squareBumper'
	;

CIRCLE_BUMPER
	: 'circleBumper'
	;

TRIANGLE_BUMPER
	: 'triangleBumper'
	;

LEFT_FLIPPER
	: 'leftFlipper'
	;

RIGHT_FLIPPER
	: 'rightFlipper'
	;

ABSORBER
	: 'absorber'
	;
	
PORTAL
	: 'portal'
	;
	
OTHER_BOARD
	: 'otherBoard'
	;
	
OTHER_PORTAL
	: 'otherPortal'
	;

X
	: 'x'
	;

Y
	: 'y'
	;

ORIENTATION
	: 'orientation'
	;

WIDTH
	: 'width'
	;
	
HEIGHT
	: 'height'
	;

FIRE
	: 'fire'
	;

TRIGGER
	: 'trigger'
	;

ACTION
	: 'action'
	;

COMMENT
	: '#' .*? NEWLINE -> skip
	; // skip comments

FLOAT
	: '-'?([0-9]+'.'[0-9]*|'.'?[0-9]+)
	;
/*
INTEGER
	: [0-9]+
	;
*/

KEY
	: [a-z] 
    | [0-9]
    | 'shift' | 'ctrl' | 'alt' | 'meta'
    | 'space'
    | 'left' | 'right' | 'up' | 'down'
    | 'minus' | 'equals' | 'backspace'
    | 'openbracket' | 'closebracket' | 'backslash'
    | 'semicolon' | 'quote' | 'enter'
    | 'comma' | 'period' | 'slash'
    ;
    
KEYUP
	: 'keyup'
	;
	
KEYDOWN
	: 'keydown'
	;
	
KEY_LITERAL
	: 'key'
	;

NAME
	: [A-Za-z_][A-Za-z_0-9]*
	;

NEWLINE
	: '\n'+ -> skip
	;

SPACE
	: [ \t]+ -> skip
	;

EQUALS
	: '='
	;

// Parser rules

boardFile
	: boardDef (ballDef | gadgetDef | fireDef | keyDef)*
	;

boardDef
	: BOARD (NAME_LITERAL EQUALS NAME)? (GRAVITY EQUALS FLOAT)? (FRICTION1 EQUALS FLOAT)? (FRICTION2 EQUALS FLOAT)? 
	; // This line must be the first non-comment line in a valid Pingball board, and exactly one board line must appear in the file.

ballDef
	: BALL (NAME_LITERAL EQUALS NAME)? X EQUALS FLOAT Y EQUALS FLOAT X_VELOCITY EQUALS FLOAT Y_VELOCITY EQUALS FLOAT 
	;

gadgetDef
	: squareBumperDef | circleBumperDef | triangleBumperDef | flipperDef | absorberDef | portalDef
	;
	
fireDef
	: FIRE TRIGGER EQUALS NAME ACTION EQUALS NAME
	;

keyDef
	: (KEYUP | KEYDOWN) KEY_LITERAL EQUALS (KEY | FLOAT) ACTION EQUALS NAME
	;

squareBumperDef
	: SQUARE_BUMPER (NAME_LITERAL EQUALS NAME)? X EQUALS FLOAT Y EQUALS FLOAT 
	;

circleBumperDef
	: CIRCLE_BUMPER (NAME_LITERAL EQUALS NAME)? X EQUALS FLOAT Y EQUALS FLOAT 
	;

triangleBumperDef
	: TRIANGLE_BUMPER (NAME_LITERAL EQUALS NAME)? X EQUALS FLOAT Y EQUALS FLOAT ORIENTATION EQUALS FLOAT 
	;

flipperDef
	: (LEFT_FLIPPER | RIGHT_FLIPPER) (NAME_LITERAL EQUALS NAME)? X EQUALS FLOAT Y EQUALS FLOAT ORIENTATION EQUALS FLOAT 
	;

absorberDef
	: ABSORBER (NAME_LITERAL EQUALS NAME)? X EQUALS FLOAT Y EQUALS FLOAT WIDTH EQUALS FLOAT HEIGHT EQUALS FLOAT 
	;

portalDef
	: PORTAL (NAME_LITERAL EQUALS NAME)? X EQUALS FLOAT Y EQUALS FLOAT (OTHER_BOARD EQUALS NAME)? OTHER_PORTAL EQUALS NAME 
	;
