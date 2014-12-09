// Generated from src/pingball/parser/Pingball.g4 by ANTLR 4.0

package pingball.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PingballParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BOARD=1, GRAVITY=2, FRICTION1=3, FRICTION2=4, BALL=5, NAME_LITERAL=6, 
		X_VELOCITY=7, Y_VELOCITY=8, SQUARE_BUMPER=9, CIRCLE_BUMPER=10, TRIANGLE_BUMPER=11, 
		LEFT_FLIPPER=12, RIGHT_FLIPPER=13, ABSORBER=14, PORTAL=15, OTHER_BOARD=16, 
		OTHER_PORTAL=17, X=18, Y=19, ORIENTATION=20, WIDTH=21, HEIGHT=22, FIRE=23, 
		TRIGGER=24, ACTION=25, COMMENT=26, FLOAT=27, KEY=28, KEYUP=29, KEYDOWN=30, 
		KEY_LITERAL=31, NAME=32, NEWLINE=33, SPACE=34, EQUALS=35;
	public static final String[] tokenNames = {
		"<INVALID>", "'board'", "'gravity'", "'friction1'", "'friction2'", "'ball'", 
		"'name'", "'xVelocity'", "'yVelocity'", "'squareBumper'", "'circleBumper'", 
		"'triangleBumper'", "'leftFlipper'", "'rightFlipper'", "'absorber'", "'portal'", 
		"'otherBoard'", "'otherPortal'", "'x'", "'y'", "'orientation'", "'width'", 
		"'height'", "'fire'", "'trigger'", "'action'", "COMMENT", "FLOAT", "KEY", 
		"'keyup'", "'keydown'", "'key'", "NAME", "NEWLINE", "SPACE", "'='"
	};
	public static final int
		RULE_boardFile = 0, RULE_boardDef = 1, RULE_ballDef = 2, RULE_gadgetDef = 3, 
		RULE_fireDef = 4, RULE_keyDef = 5, RULE_squareBumperDef = 6, RULE_circleBumperDef = 7, 
		RULE_triangleBumperDef = 8, RULE_flipperDef = 9, RULE_absorberDef = 10, 
		RULE_portalDef = 11;
	public static final String[] ruleNames = {
		"boardFile", "boardDef", "ballDef", "gadgetDef", "fireDef", "keyDef", 
		"squareBumperDef", "circleBumperDef", "triangleBumperDef", "flipperDef", 
		"absorberDef", "portalDef"
	};

	@Override
	public String getGrammarFileName() { return "Pingball.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public PingballParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class BoardFileContext extends ParserRuleContext {
		public FireDefContext fireDef(int i) {
			return getRuleContext(FireDefContext.class,i);
		}
		public List<FireDefContext> fireDef() {
			return getRuleContexts(FireDefContext.class);
		}
		public List<BallDefContext> ballDef() {
			return getRuleContexts(BallDefContext.class);
		}
		public KeyDefContext keyDef(int i) {
			return getRuleContext(KeyDefContext.class,i);
		}
		public BallDefContext ballDef(int i) {
			return getRuleContext(BallDefContext.class,i);
		}
		public List<GadgetDefContext> gadgetDef() {
			return getRuleContexts(GadgetDefContext.class);
		}
		public GadgetDefContext gadgetDef(int i) {
			return getRuleContext(GadgetDefContext.class,i);
		}
		public List<KeyDefContext> keyDef() {
			return getRuleContexts(KeyDefContext.class);
		}
		public BoardDefContext boardDef() {
			return getRuleContext(BoardDefContext.class,0);
		}
		public BoardFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardFile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).enterBoardFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).exitBoardFile(this);
		}
	}

	public final BoardFileContext boardFile() throws RecognitionException {
		BoardFileContext _localctx = new BoardFileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_boardFile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24); boardDef();
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BALL) | (1L << SQUARE_BUMPER) | (1L << CIRCLE_BUMPER) | (1L << TRIANGLE_BUMPER) | (1L << LEFT_FLIPPER) | (1L << RIGHT_FLIPPER) | (1L << ABSORBER) | (1L << PORTAL) | (1L << FIRE) | (1L << KEYUP) | (1L << KEYDOWN))) != 0)) {
				{
				setState(29);
				switch (_input.LA(1)) {
				case BALL:
					{
					setState(25); ballDef();
					}
					break;
				case SQUARE_BUMPER:
				case CIRCLE_BUMPER:
				case TRIANGLE_BUMPER:
				case LEFT_FLIPPER:
				case RIGHT_FLIPPER:
				case ABSORBER:
				case PORTAL:
					{
					setState(26); gadgetDef();
					}
					break;
				case FIRE:
					{
					setState(27); fireDef();
					}
					break;
				case KEYUP:
				case KEYDOWN:
					{
					setState(28); keyDef();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoardDefContext extends ParserRuleContext {
		public TerminalNode BOARD() { return getToken(PingballParser.BOARD, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(PingballParser.FLOAT); }
		public TerminalNode EQUALS(int i) {
			return getToken(PingballParser.EQUALS, i);
		}
		public TerminalNode FRICTION1() { return getToken(PingballParser.FRICTION1, 0); }
		public List<TerminalNode> EQUALS() { return getTokens(PingballParser.EQUALS); }
		public TerminalNode FLOAT(int i) {
			return getToken(PingballParser.FLOAT, i);
		}
		public TerminalNode FRICTION2() { return getToken(PingballParser.FRICTION2, 0); }
		public TerminalNode NAME_LITERAL() { return getToken(PingballParser.NAME_LITERAL, 0); }
		public TerminalNode GRAVITY() { return getToken(PingballParser.GRAVITY, 0); }
		public TerminalNode NAME() { return getToken(PingballParser.NAME, 0); }
		public BoardDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).enterBoardDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).exitBoardDef(this);
		}
	}

	public final BoardDefContext boardDef() throws RecognitionException {
		BoardDefContext _localctx = new BoardDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_boardDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34); match(BOARD);
			setState(38);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(35); match(NAME_LITERAL);
				setState(36); match(EQUALS);
				setState(37); match(NAME);
				}
			}

			setState(43);
			_la = _input.LA(1);
			if (_la==GRAVITY) {
				{
				setState(40); match(GRAVITY);
				setState(41); match(EQUALS);
				setState(42); match(FLOAT);
				}
			}

			setState(48);
			_la = _input.LA(1);
			if (_la==FRICTION1) {
				{
				setState(45); match(FRICTION1);
				setState(46); match(EQUALS);
				setState(47); match(FLOAT);
				}
			}

			setState(53);
			_la = _input.LA(1);
			if (_la==FRICTION2) {
				{
				setState(50); match(FRICTION2);
				setState(51); match(EQUALS);
				setState(52); match(FLOAT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BallDefContext extends ParserRuleContext {
		public TerminalNode BALL() { return getToken(PingballParser.BALL, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(PingballParser.FLOAT); }
		public TerminalNode EQUALS(int i) {
			return getToken(PingballParser.EQUALS, i);
		}
		public List<TerminalNode> EQUALS() { return getTokens(PingballParser.EQUALS); }
		public TerminalNode FLOAT(int i) {
			return getToken(PingballParser.FLOAT, i);
		}
		public TerminalNode X_VELOCITY() { return getToken(PingballParser.X_VELOCITY, 0); }
		public TerminalNode X() { return getToken(PingballParser.X, 0); }
		public TerminalNode Y() { return getToken(PingballParser.Y, 0); }
		public TerminalNode Y_VELOCITY() { return getToken(PingballParser.Y_VELOCITY, 0); }
		public TerminalNode NAME_LITERAL() { return getToken(PingballParser.NAME_LITERAL, 0); }
		public TerminalNode NAME() { return getToken(PingballParser.NAME, 0); }
		public BallDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ballDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).enterBallDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).exitBallDef(this);
		}
	}

	public final BallDefContext ballDef() throws RecognitionException {
		BallDefContext _localctx = new BallDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ballDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55); match(BALL);
			setState(59);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(56); match(NAME_LITERAL);
				setState(57); match(EQUALS);
				setState(58); match(NAME);
				}
			}

			setState(61); match(X);
			setState(62); match(EQUALS);
			setState(63); match(FLOAT);
			setState(64); match(Y);
			setState(65); match(EQUALS);
			setState(66); match(FLOAT);
			setState(67); match(X_VELOCITY);
			setState(68); match(EQUALS);
			setState(69); match(FLOAT);
			setState(70); match(Y_VELOCITY);
			setState(71); match(EQUALS);
			setState(72); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GadgetDefContext extends ParserRuleContext {
		public AbsorberDefContext absorberDef() {
			return getRuleContext(AbsorberDefContext.class,0);
		}
		public TriangleBumperDefContext triangleBumperDef() {
			return getRuleContext(TriangleBumperDefContext.class,0);
		}
		public SquareBumperDefContext squareBumperDef() {
			return getRuleContext(SquareBumperDefContext.class,0);
		}
		public CircleBumperDefContext circleBumperDef() {
			return getRuleContext(CircleBumperDefContext.class,0);
		}
		public PortalDefContext portalDef() {
			return getRuleContext(PortalDefContext.class,0);
		}
		public FlipperDefContext flipperDef() {
			return getRuleContext(FlipperDefContext.class,0);
		}
		public GadgetDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gadgetDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).enterGadgetDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).exitGadgetDef(this);
		}
	}

	public final GadgetDefContext gadgetDef() throws RecognitionException {
		GadgetDefContext _localctx = new GadgetDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_gadgetDef);
		try {
			setState(80);
			switch (_input.LA(1)) {
			case SQUARE_BUMPER:
				enterOuterAlt(_localctx, 1);
				{
				setState(74); squareBumperDef();
				}
				break;
			case CIRCLE_BUMPER:
				enterOuterAlt(_localctx, 2);
				{
				setState(75); circleBumperDef();
				}
				break;
			case TRIANGLE_BUMPER:
				enterOuterAlt(_localctx, 3);
				{
				setState(76); triangleBumperDef();
				}
				break;
			case LEFT_FLIPPER:
			case RIGHT_FLIPPER:
				enterOuterAlt(_localctx, 4);
				{
				setState(77); flipperDef();
				}
				break;
			case ABSORBER:
				enterOuterAlt(_localctx, 5);
				{
				setState(78); absorberDef();
				}
				break;
			case PORTAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(79); portalDef();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FireDefContext extends ParserRuleContext {
		public TerminalNode EQUALS(int i) {
			return getToken(PingballParser.EQUALS, i);
		}
		public List<TerminalNode> EQUALS() { return getTokens(PingballParser.EQUALS); }
		public TerminalNode ACTION() { return getToken(PingballParser.ACTION, 0); }
		public TerminalNode FIRE() { return getToken(PingballParser.FIRE, 0); }
		public TerminalNode TRIGGER() { return getToken(PingballParser.TRIGGER, 0); }
		public TerminalNode NAME(int i) {
			return getToken(PingballParser.NAME, i);
		}
		public List<TerminalNode> NAME() { return getTokens(PingballParser.NAME); }
		public FireDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fireDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).enterFireDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).exitFireDef(this);
		}
	}

	public final FireDefContext fireDef() throws RecognitionException {
		FireDefContext _localctx = new FireDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fireDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); match(FIRE);
			setState(83); match(TRIGGER);
			setState(84); match(EQUALS);
			setState(85); match(NAME);
			setState(86); match(ACTION);
			setState(87); match(EQUALS);
			setState(88); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyDefContext extends ParserRuleContext {
		public TerminalNode EQUALS(int i) {
			return getToken(PingballParser.EQUALS, i);
		}
		public List<TerminalNode> EQUALS() { return getTokens(PingballParser.EQUALS); }
		public TerminalNode ACTION() { return getToken(PingballParser.ACTION, 0); }
		public TerminalNode KEYDOWN() { return getToken(PingballParser.KEYDOWN, 0); }
		public TerminalNode KEYUP() { return getToken(PingballParser.KEYUP, 0); }
		public TerminalNode KEY_LITERAL() { return getToken(PingballParser.KEY_LITERAL, 0); }
		public TerminalNode KEY() { return getToken(PingballParser.KEY, 0); }
		public TerminalNode NAME() { return getToken(PingballParser.NAME, 0); }
		public KeyDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).enterKeyDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).exitKeyDef(this);
		}
	}

	public final KeyDefContext keyDef() throws RecognitionException {
		KeyDefContext _localctx = new KeyDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_keyDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			_la = _input.LA(1);
			if ( !(_la==KEYUP || _la==KEYDOWN) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(91); match(KEY_LITERAL);
			setState(92); match(EQUALS);
			setState(93); match(KEY);
			setState(94); match(ACTION);
			setState(95); match(EQUALS);
			setState(96); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SquareBumperDefContext extends ParserRuleContext {
		public TerminalNode SQUARE_BUMPER() { return getToken(PingballParser.SQUARE_BUMPER, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(PingballParser.FLOAT); }
		public TerminalNode EQUALS(int i) {
			return getToken(PingballParser.EQUALS, i);
		}
		public List<TerminalNode> EQUALS() { return getTokens(PingballParser.EQUALS); }
		public TerminalNode FLOAT(int i) {
			return getToken(PingballParser.FLOAT, i);
		}
		public TerminalNode X() { return getToken(PingballParser.X, 0); }
		public TerminalNode Y() { return getToken(PingballParser.Y, 0); }
		public TerminalNode NAME_LITERAL() { return getToken(PingballParser.NAME_LITERAL, 0); }
		public TerminalNode NAME() { return getToken(PingballParser.NAME, 0); }
		public SquareBumperDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_squareBumperDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).enterSquareBumperDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).exitSquareBumperDef(this);
		}
	}

	public final SquareBumperDefContext squareBumperDef() throws RecognitionException {
		SquareBumperDefContext _localctx = new SquareBumperDefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_squareBumperDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); match(SQUARE_BUMPER);
			setState(102);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(99); match(NAME_LITERAL);
				setState(100); match(EQUALS);
				setState(101); match(NAME);
				}
			}

			setState(104); match(X);
			setState(105); match(EQUALS);
			setState(106); match(FLOAT);
			setState(107); match(Y);
			setState(108); match(EQUALS);
			setState(109); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CircleBumperDefContext extends ParserRuleContext {
		public TerminalNode CIRCLE_BUMPER() { return getToken(PingballParser.CIRCLE_BUMPER, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(PingballParser.FLOAT); }
		public TerminalNode EQUALS(int i) {
			return getToken(PingballParser.EQUALS, i);
		}
		public List<TerminalNode> EQUALS() { return getTokens(PingballParser.EQUALS); }
		public TerminalNode FLOAT(int i) {
			return getToken(PingballParser.FLOAT, i);
		}
		public TerminalNode X() { return getToken(PingballParser.X, 0); }
		public TerminalNode Y() { return getToken(PingballParser.Y, 0); }
		public TerminalNode NAME_LITERAL() { return getToken(PingballParser.NAME_LITERAL, 0); }
		public TerminalNode NAME() { return getToken(PingballParser.NAME, 0); }
		public CircleBumperDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_circleBumperDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).enterCircleBumperDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).exitCircleBumperDef(this);
		}
	}

	public final CircleBumperDefContext circleBumperDef() throws RecognitionException {
		CircleBumperDefContext _localctx = new CircleBumperDefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_circleBumperDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111); match(CIRCLE_BUMPER);
			setState(115);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(112); match(NAME_LITERAL);
				setState(113); match(EQUALS);
				setState(114); match(NAME);
				}
			}

			setState(117); match(X);
			setState(118); match(EQUALS);
			setState(119); match(FLOAT);
			setState(120); match(Y);
			setState(121); match(EQUALS);
			setState(122); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriangleBumperDefContext extends ParserRuleContext {
		public List<TerminalNode> FLOAT() { return getTokens(PingballParser.FLOAT); }
		public TerminalNode EQUALS(int i) {
			return getToken(PingballParser.EQUALS, i);
		}
		public List<TerminalNode> EQUALS() { return getTokens(PingballParser.EQUALS); }
		public TerminalNode FLOAT(int i) {
			return getToken(PingballParser.FLOAT, i);
		}
		public TerminalNode ORIENTATION() { return getToken(PingballParser.ORIENTATION, 0); }
		public TerminalNode TRIANGLE_BUMPER() { return getToken(PingballParser.TRIANGLE_BUMPER, 0); }
		public TerminalNode X() { return getToken(PingballParser.X, 0); }
		public TerminalNode Y() { return getToken(PingballParser.Y, 0); }
		public TerminalNode NAME_LITERAL() { return getToken(PingballParser.NAME_LITERAL, 0); }
		public TerminalNode NAME() { return getToken(PingballParser.NAME, 0); }
		public TriangleBumperDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triangleBumperDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).enterTriangleBumperDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).exitTriangleBumperDef(this);
		}
	}

	public final TriangleBumperDefContext triangleBumperDef() throws RecognitionException {
		TriangleBumperDefContext _localctx = new TriangleBumperDefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_triangleBumperDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); match(TRIANGLE_BUMPER);
			setState(128);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(125); match(NAME_LITERAL);
				setState(126); match(EQUALS);
				setState(127); match(NAME);
				}
			}

			setState(130); match(X);
			setState(131); match(EQUALS);
			setState(132); match(FLOAT);
			setState(133); match(Y);
			setState(134); match(EQUALS);
			setState(135); match(FLOAT);
			setState(136); match(ORIENTATION);
			setState(137); match(EQUALS);
			setState(138); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlipperDefContext extends ParserRuleContext {
		public TerminalNode LEFT_FLIPPER() { return getToken(PingballParser.LEFT_FLIPPER, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(PingballParser.FLOAT); }
		public TerminalNode EQUALS(int i) {
			return getToken(PingballParser.EQUALS, i);
		}
		public List<TerminalNode> EQUALS() { return getTokens(PingballParser.EQUALS); }
		public TerminalNode FLOAT(int i) {
			return getToken(PingballParser.FLOAT, i);
		}
		public TerminalNode RIGHT_FLIPPER() { return getToken(PingballParser.RIGHT_FLIPPER, 0); }
		public TerminalNode ORIENTATION() { return getToken(PingballParser.ORIENTATION, 0); }
		public TerminalNode X() { return getToken(PingballParser.X, 0); }
		public TerminalNode Y() { return getToken(PingballParser.Y, 0); }
		public TerminalNode NAME_LITERAL() { return getToken(PingballParser.NAME_LITERAL, 0); }
		public TerminalNode NAME() { return getToken(PingballParser.NAME, 0); }
		public FlipperDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flipperDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).enterFlipperDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).exitFlipperDef(this);
		}
	}

	public final FlipperDefContext flipperDef() throws RecognitionException {
		FlipperDefContext _localctx = new FlipperDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_flipperDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			_la = _input.LA(1);
			if ( !(_la==LEFT_FLIPPER || _la==RIGHT_FLIPPER) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(144);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(141); match(NAME_LITERAL);
				setState(142); match(EQUALS);
				setState(143); match(NAME);
				}
			}

			setState(146); match(X);
			setState(147); match(EQUALS);
			setState(148); match(FLOAT);
			setState(149); match(Y);
			setState(150); match(EQUALS);
			setState(151); match(FLOAT);
			setState(152); match(ORIENTATION);
			setState(153); match(EQUALS);
			setState(154); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbsorberDefContext extends ParserRuleContext {
		public List<TerminalNode> FLOAT() { return getTokens(PingballParser.FLOAT); }
		public TerminalNode ABSORBER() { return getToken(PingballParser.ABSORBER, 0); }
		public TerminalNode EQUALS(int i) {
			return getToken(PingballParser.EQUALS, i);
		}
		public List<TerminalNode> EQUALS() { return getTokens(PingballParser.EQUALS); }
		public TerminalNode FLOAT(int i) {
			return getToken(PingballParser.FLOAT, i);
		}
		public TerminalNode X() { return getToken(PingballParser.X, 0); }
		public TerminalNode Y() { return getToken(PingballParser.Y, 0); }
		public TerminalNode WIDTH() { return getToken(PingballParser.WIDTH, 0); }
		public TerminalNode HEIGHT() { return getToken(PingballParser.HEIGHT, 0); }
		public TerminalNode NAME_LITERAL() { return getToken(PingballParser.NAME_LITERAL, 0); }
		public TerminalNode NAME() { return getToken(PingballParser.NAME, 0); }
		public AbsorberDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absorberDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).enterAbsorberDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).exitAbsorberDef(this);
		}
	}

	public final AbsorberDefContext absorberDef() throws RecognitionException {
		AbsorberDefContext _localctx = new AbsorberDefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_absorberDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156); match(ABSORBER);
			setState(160);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(157); match(NAME_LITERAL);
				setState(158); match(EQUALS);
				setState(159); match(NAME);
				}
			}

			setState(162); match(X);
			setState(163); match(EQUALS);
			setState(164); match(FLOAT);
			setState(165); match(Y);
			setState(166); match(EQUALS);
			setState(167); match(FLOAT);
			setState(168); match(WIDTH);
			setState(169); match(EQUALS);
			setState(170); match(FLOAT);
			setState(171); match(HEIGHT);
			setState(172); match(EQUALS);
			setState(173); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PortalDefContext extends ParserRuleContext {
		public TerminalNode OTHER_PORTAL() { return getToken(PingballParser.OTHER_PORTAL, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(PingballParser.FLOAT); }
		public TerminalNode EQUALS(int i) {
			return getToken(PingballParser.EQUALS, i);
		}
		public List<TerminalNode> EQUALS() { return getTokens(PingballParser.EQUALS); }
		public TerminalNode FLOAT(int i) {
			return getToken(PingballParser.FLOAT, i);
		}
		public TerminalNode X() { return getToken(PingballParser.X, 0); }
		public TerminalNode Y() { return getToken(PingballParser.Y, 0); }
		public TerminalNode OTHER_BOARD() { return getToken(PingballParser.OTHER_BOARD, 0); }
		public TerminalNode NAME(int i) {
			return getToken(PingballParser.NAME, i);
		}
		public TerminalNode NAME_LITERAL() { return getToken(PingballParser.NAME_LITERAL, 0); }
		public TerminalNode PORTAL() { return getToken(PingballParser.PORTAL, 0); }
		public List<TerminalNode> NAME() { return getTokens(PingballParser.NAME); }
		public PortalDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portalDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).enterPortalDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingballListener ) ((PingballListener)listener).exitPortalDef(this);
		}
	}

	public final PortalDefContext portalDef() throws RecognitionException {
		PortalDefContext _localctx = new PortalDefContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_portalDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); match(PORTAL);
			setState(179);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(176); match(NAME_LITERAL);
				setState(177); match(EQUALS);
				setState(178); match(NAME);
				}
			}

			setState(181); match(X);
			setState(182); match(EQUALS);
			setState(183); match(FLOAT);
			setState(184); match(Y);
			setState(185); match(EQUALS);
			setState(186); match(FLOAT);
			setState(190);
			_la = _input.LA(1);
			if (_la==OTHER_BOARD) {
				{
				setState(187); match(OTHER_BOARD);
				setState(188); match(EQUALS);
				setState(189); match(NAME);
				}
			}

			setState(192); match(OTHER_PORTAL);
			setState(193); match(EQUALS);
			setState(194); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3%\u00c7\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\7\2 \n\2"+
		"\f\2\16\2#\13\2\3\3\3\3\3\3\3\3\5\3)\n\3\3\3\3\3\3\3\5\3.\n\3\3\3\3\3"+
		"\3\3\5\3\63\n\3\3\3\3\3\3\3\5\38\n\3\3\4\3\4\3\4\3\4\5\4>\n\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5"+
		"\5S\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\5\bi\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\5\tv\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\5\n\u0083\n\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\5\13\u0093"+
		"\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\5\f\u00a3\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\5\r\u00b6\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00c1"+
		"\n\r\3\r\3\r\3\r\3\r\3\r\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\4\3\37 "+
		"\3\16\17\u00cf\2\32\3\2\2\2\4$\3\2\2\2\69\3\2\2\2\bR\3\2\2\2\nT\3\2\2"+
		"\2\f\\\3\2\2\2\16d\3\2\2\2\20q\3\2\2\2\22~\3\2\2\2\24\u008e\3\2\2\2\26"+
		"\u009e\3\2\2\2\30\u00b1\3\2\2\2\32!\5\4\3\2\33 \5\6\4\2\34 \5\b\5\2\35"+
		" \5\n\6\2\36 \5\f\7\2\37\33\3\2\2\2\37\34\3\2\2\2\37\35\3\2\2\2\37\36"+
		"\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\3\3\2\2\2#!\3\2\2\2$(\7\3"+
		"\2\2%&\7\b\2\2&\'\7%\2\2\')\7\"\2\2(%\3\2\2\2()\3\2\2\2)-\3\2\2\2*+\7"+
		"\4\2\2+,\7%\2\2,.\7\35\2\2-*\3\2\2\2-.\3\2\2\2.\62\3\2\2\2/\60\7\5\2\2"+
		"\60\61\7%\2\2\61\63\7\35\2\2\62/\3\2\2\2\62\63\3\2\2\2\63\67\3\2\2\2\64"+
		"\65\7\6\2\2\65\66\7%\2\2\668\7\35\2\2\67\64\3\2\2\2\678\3\2\2\28\5\3\2"+
		"\2\29=\7\7\2\2:;\7\b\2\2;<\7%\2\2<>\7\"\2\2=:\3\2\2\2=>\3\2\2\2>?\3\2"+
		"\2\2?@\7\24\2\2@A\7%\2\2AB\7\35\2\2BC\7\25\2\2CD\7%\2\2DE\7\35\2\2EF\7"+
		"\t\2\2FG\7%\2\2GH\7\35\2\2HI\7\n\2\2IJ\7%\2\2JK\7\35\2\2K\7\3\2\2\2LS"+
		"\5\16\b\2MS\5\20\t\2NS\5\22\n\2OS\5\24\13\2PS\5\26\f\2QS\5\30\r\2RL\3"+
		"\2\2\2RM\3\2\2\2RN\3\2\2\2RO\3\2\2\2RP\3\2\2\2RQ\3\2\2\2S\t\3\2\2\2TU"+
		"\7\31\2\2UV\7\32\2\2VW\7%\2\2WX\7\"\2\2XY\7\33\2\2YZ\7%\2\2Z[\7\"\2\2"+
		"[\13\3\2\2\2\\]\t\2\2\2]^\7!\2\2^_\7%\2\2_`\7\36\2\2`a\7\33\2\2ab\7%\2"+
		"\2bc\7\"\2\2c\r\3\2\2\2dh\7\13\2\2ef\7\b\2\2fg\7%\2\2gi\7\"\2\2he\3\2"+
		"\2\2hi\3\2\2\2ij\3\2\2\2jk\7\24\2\2kl\7%\2\2lm\7\35\2\2mn\7\25\2\2no\7"+
		"%\2\2op\7\35\2\2p\17\3\2\2\2qu\7\f\2\2rs\7\b\2\2st\7%\2\2tv\7\"\2\2ur"+
		"\3\2\2\2uv\3\2\2\2vw\3\2\2\2wx\7\24\2\2xy\7%\2\2yz\7\35\2\2z{\7\25\2\2"+
		"{|\7%\2\2|}\7\35\2\2}\21\3\2\2\2~\u0082\7\r\2\2\177\u0080\7\b\2\2\u0080"+
		"\u0081\7%\2\2\u0081\u0083\7\"\2\2\u0082\177\3\2\2\2\u0082\u0083\3\2\2"+
		"\2\u0083\u0084\3\2\2\2\u0084\u0085\7\24\2\2\u0085\u0086\7%\2\2\u0086\u0087"+
		"\7\35\2\2\u0087\u0088\7\25\2\2\u0088\u0089\7%\2\2\u0089\u008a\7\35\2\2"+
		"\u008a\u008b\7\26\2\2\u008b\u008c\7%\2\2\u008c\u008d\7\35\2\2\u008d\23"+
		"\3\2\2\2\u008e\u0092\t\3\2\2\u008f\u0090\7\b\2\2\u0090\u0091\7%\2\2\u0091"+
		"\u0093\7\"\2\2\u0092\u008f\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3\2"+
		"\2\2\u0094\u0095\7\24\2\2\u0095\u0096\7%\2\2\u0096\u0097\7\35\2\2\u0097"+
		"\u0098\7\25\2\2\u0098\u0099\7%\2\2\u0099\u009a\7\35\2\2\u009a\u009b\7"+
		"\26\2\2\u009b\u009c\7%\2\2\u009c\u009d\7\35\2\2\u009d\25\3\2\2\2\u009e"+
		"\u00a2\7\20\2\2\u009f\u00a0\7\b\2\2\u00a0\u00a1\7%\2\2\u00a1\u00a3\7\""+
		"\2\2\u00a2\u009f\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a5\7\24\2\2\u00a5\u00a6\7%\2\2\u00a6\u00a7\7\35\2\2\u00a7\u00a8\7"+
		"\25\2\2\u00a8\u00a9\7%\2\2\u00a9\u00aa\7\35\2\2\u00aa\u00ab\7\27\2\2\u00ab"+
		"\u00ac\7%\2\2\u00ac\u00ad\7\35\2\2\u00ad\u00ae\7\30\2\2\u00ae\u00af\7"+
		"%\2\2\u00af\u00b0\7\35\2\2\u00b0\27\3\2\2\2\u00b1\u00b5\7\21\2\2\u00b2"+
		"\u00b3\7\b\2\2\u00b3\u00b4\7%\2\2\u00b4\u00b6\7\"\2\2\u00b5\u00b2\3\2"+
		"\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\7\24\2\2\u00b8"+
		"\u00b9\7%\2\2\u00b9\u00ba\7\35\2\2\u00ba\u00bb\7\25\2\2\u00bb\u00bc\7"+
		"%\2\2\u00bc\u00c0\7\35\2\2\u00bd\u00be\7\22\2\2\u00be\u00bf\7%\2\2\u00bf"+
		"\u00c1\7\"\2\2\u00c0\u00bd\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\3\2"+
		"\2\2\u00c2\u00c3\7\23\2\2\u00c3\u00c4\7%\2\2\u00c4\u00c5\7\"\2\2\u00c5"+
		"\31\3\2\2\2\21\37!(-\62\67=Rhu\u0082\u0092\u00a2\u00b5\u00c0";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}