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
		TRIGGER=24, ACTION=25, COMMENT=26, FLOAT=27, NAME=28, NEWLINE=29, SPACE=30, 
		EQUALS=31;
	public static final String[] tokenNames = {
		"<INVALID>", "'board'", "'gravity'", "'friction1'", "'friction2'", "'ball'", 
		"'name'", "'xVelocity'", "'yVelocity'", "'squareBumper'", "'circleBumper'", 
		"'triangleBumper'", "'leftFlipper'", "'rightFlipper'", "'absorber'", "'portal'", 
		"'otherBoard'", "'otherPortal'", "'x'", "'y'", "'orientation'", "'width'", 
		"'height'", "'fire'", "'trigger'", "'action'", "COMMENT", "FLOAT", "NAME", 
		"NEWLINE", "SPACE", "'='"
	};
	public static final int
		RULE_boardFile = 0, RULE_boardDef = 1, RULE_ballDef = 2, RULE_gadgetDef = 3, 
		RULE_fireDef = 4, RULE_squareBumperDef = 5, RULE_circleBumperDef = 6, 
		RULE_triangleBumperDef = 7, RULE_flipperDef = 8, RULE_absorberDef = 9, 
		RULE_portalDef = 10;
	public static final String[] ruleNames = {
		"boardFile", "boardDef", "ballDef", "gadgetDef", "fireDef", "squareBumperDef", 
		"circleBumperDef", "triangleBumperDef", "flipperDef", "absorberDef", "portalDef"
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
		public BallDefContext ballDef(int i) {
			return getRuleContext(BallDefContext.class,i);
		}
		public List<GadgetDefContext> gadgetDef() {
			return getRuleContexts(GadgetDefContext.class);
		}
		public GadgetDefContext gadgetDef(int i) {
			return getRuleContext(GadgetDefContext.class,i);
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
			setState(22); boardDef();
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BALL) | (1L << SQUARE_BUMPER) | (1L << CIRCLE_BUMPER) | (1L << TRIANGLE_BUMPER) | (1L << LEFT_FLIPPER) | (1L << RIGHT_FLIPPER) | (1L << ABSORBER) | (1L << PORTAL) | (1L << FIRE))) != 0)) {
				{
				setState(26);
				switch (_input.LA(1)) {
				case BALL:
					{
					setState(23); ballDef();
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
					setState(24); gadgetDef();
					}
					break;
				case FIRE:
					{
					setState(25); fireDef();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(30);
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
			setState(31); match(BOARD);
			setState(35);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(32); match(NAME_LITERAL);
				setState(33); match(EQUALS);
				setState(34); match(NAME);
				}
			}

			setState(40);
			_la = _input.LA(1);
			if (_la==GRAVITY) {
				{
				setState(37); match(GRAVITY);
				setState(38); match(EQUALS);
				setState(39); match(FLOAT);
				}
			}

			setState(45);
			_la = _input.LA(1);
			if (_la==FRICTION1) {
				{
				setState(42); match(FRICTION1);
				setState(43); match(EQUALS);
				setState(44); match(FLOAT);
				}
			}

			setState(50);
			_la = _input.LA(1);
			if (_la==FRICTION2) {
				{
				setState(47); match(FRICTION2);
				setState(48); match(EQUALS);
				setState(49); match(FLOAT);
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
			setState(52); match(BALL);
			setState(56);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(53); match(NAME_LITERAL);
				setState(54); match(EQUALS);
				setState(55); match(NAME);
				}
			}

			setState(58); match(X);
			setState(59); match(EQUALS);
			setState(60); match(FLOAT);
			setState(61); match(Y);
			setState(62); match(EQUALS);
			setState(63); match(FLOAT);
			setState(64); match(X_VELOCITY);
			setState(65); match(EQUALS);
			setState(66); match(FLOAT);
			setState(67); match(Y_VELOCITY);
			setState(68); match(EQUALS);
			setState(69); match(FLOAT);
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
			setState(77);
			switch (_input.LA(1)) {
			case SQUARE_BUMPER:
				enterOuterAlt(_localctx, 1);
				{
				setState(71); squareBumperDef();
				}
				break;
			case CIRCLE_BUMPER:
				enterOuterAlt(_localctx, 2);
				{
				setState(72); circleBumperDef();
				}
				break;
			case TRIANGLE_BUMPER:
				enterOuterAlt(_localctx, 3);
				{
				setState(73); triangleBumperDef();
				}
				break;
			case LEFT_FLIPPER:
			case RIGHT_FLIPPER:
				enterOuterAlt(_localctx, 4);
				{
				setState(74); flipperDef();
				}
				break;
			case ABSORBER:
				enterOuterAlt(_localctx, 5);
				{
				setState(75); absorberDef();
				}
				break;
			case PORTAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(76); portalDef();
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
			setState(79); match(FIRE);
			setState(80); match(TRIGGER);
			setState(81); match(EQUALS);
			setState(82); match(NAME);
			setState(83); match(ACTION);
			setState(84); match(EQUALS);
			setState(85); match(NAME);
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
		enterRule(_localctx, 10, RULE_squareBumperDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87); match(SQUARE_BUMPER);
			setState(91);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(88); match(NAME_LITERAL);
				setState(89); match(EQUALS);
				setState(90); match(NAME);
				}
			}

			setState(93); match(X);
			setState(94); match(EQUALS);
			setState(95); match(FLOAT);
			setState(96); match(Y);
			setState(97); match(EQUALS);
			setState(98); match(FLOAT);
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
		enterRule(_localctx, 12, RULE_circleBumperDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); match(CIRCLE_BUMPER);
			setState(104);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(101); match(NAME_LITERAL);
				setState(102); match(EQUALS);
				setState(103); match(NAME);
				}
			}

			setState(106); match(X);
			setState(107); match(EQUALS);
			setState(108); match(FLOAT);
			setState(109); match(Y);
			setState(110); match(EQUALS);
			setState(111); match(FLOAT);
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
		enterRule(_localctx, 14, RULE_triangleBumperDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113); match(TRIANGLE_BUMPER);
			setState(117);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(114); match(NAME_LITERAL);
				setState(115); match(EQUALS);
				setState(116); match(NAME);
				}
			}

			setState(119); match(X);
			setState(120); match(EQUALS);
			setState(121); match(FLOAT);
			setState(122); match(Y);
			setState(123); match(EQUALS);
			setState(124); match(FLOAT);
			setState(125); match(ORIENTATION);
			setState(126); match(EQUALS);
			setState(127); match(FLOAT);
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
		enterRule(_localctx, 16, RULE_flipperDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_la = _input.LA(1);
			if ( !(_la==LEFT_FLIPPER || _la==RIGHT_FLIPPER) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(133);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(130); match(NAME_LITERAL);
				setState(131); match(EQUALS);
				setState(132); match(NAME);
				}
			}

			setState(135); match(X);
			setState(136); match(EQUALS);
			setState(137); match(FLOAT);
			setState(138); match(Y);
			setState(139); match(EQUALS);
			setState(140); match(FLOAT);
			setState(141); match(ORIENTATION);
			setState(142); match(EQUALS);
			setState(143); match(FLOAT);
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
		enterRule(_localctx, 18, RULE_absorberDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145); match(ABSORBER);
			setState(149);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(146); match(NAME_LITERAL);
				setState(147); match(EQUALS);
				setState(148); match(NAME);
				}
			}

			setState(151); match(X);
			setState(152); match(EQUALS);
			setState(153); match(FLOAT);
			setState(154); match(Y);
			setState(155); match(EQUALS);
			setState(156); match(FLOAT);
			setState(157); match(WIDTH);
			setState(158); match(EQUALS);
			setState(159); match(FLOAT);
			setState(160); match(HEIGHT);
			setState(161); match(EQUALS);
			setState(162); match(FLOAT);
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
		enterRule(_localctx, 20, RULE_portalDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); match(PORTAL);
			setState(168);
			_la = _input.LA(1);
			if (_la==NAME_LITERAL) {
				{
				setState(165); match(NAME_LITERAL);
				setState(166); match(EQUALS);
				setState(167); match(NAME);
				}
			}

			setState(170); match(X);
			setState(171); match(EQUALS);
			setState(172); match(FLOAT);
			setState(173); match(Y);
			setState(174); match(EQUALS);
			setState(175); match(FLOAT);
			setState(179);
			_la = _input.LA(1);
			if (_la==OTHER_BOARD) {
				{
				setState(176); match(OTHER_BOARD);
				setState(177); match(EQUALS);
				setState(178); match(NAME);
				}
			}

			setState(181); match(OTHER_PORTAL);
			setState(182); match(EQUALS);
			setState(183); match(NAME);
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
		"\2\3!\u00bc\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\3\2\3\2\3\2\3\2\7\2\35\n\2\f\2\16\2 "+
		"\13\2\3\3\3\3\3\3\3\3\5\3&\n\3\3\3\3\3\3\3\5\3+\n\3\3\3\3\3\3\3\5\3\60"+
		"\n\3\3\3\3\3\3\3\5\3\65\n\3\3\4\3\4\3\4\3\4\5\4;\n\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5P\n\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7^\n\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\5\bk\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\5\tx\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\5\n\u0088\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\5\13\u0098\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\5\f\u00ab\n\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\5\f\u00b6\n\f\3\f\3\f\3\f\3\f\3\f\2\r\2\4\6\b\n"+
		"\f\16\20\22\24\26\2\3\3\16\17\u00c4\2\30\3\2\2\2\4!\3\2\2\2\6\66\3\2\2"+
		"\2\bO\3\2\2\2\nQ\3\2\2\2\fY\3\2\2\2\16f\3\2\2\2\20s\3\2\2\2\22\u0083\3"+
		"\2\2\2\24\u0093\3\2\2\2\26\u00a6\3\2\2\2\30\36\5\4\3\2\31\35\5\6\4\2\32"+
		"\35\5\b\5\2\33\35\5\n\6\2\34\31\3\2\2\2\34\32\3\2\2\2\34\33\3\2\2\2\35"+
		" \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37\3\3\2\2\2 \36\3\2\2\2!%\7\3\2"+
		"\2\"#\7\b\2\2#$\7!\2\2$&\7\36\2\2%\"\3\2\2\2%&\3\2\2\2&*\3\2\2\2\'(\7"+
		"\4\2\2()\7!\2\2)+\7\35\2\2*\'\3\2\2\2*+\3\2\2\2+/\3\2\2\2,-\7\5\2\2-."+
		"\7!\2\2.\60\7\35\2\2/,\3\2\2\2/\60\3\2\2\2\60\64\3\2\2\2\61\62\7\6\2\2"+
		"\62\63\7!\2\2\63\65\7\35\2\2\64\61\3\2\2\2\64\65\3\2\2\2\65\5\3\2\2\2"+
		"\66:\7\7\2\2\678\7\b\2\289\7!\2\29;\7\36\2\2:\67\3\2\2\2:;\3\2\2\2;<\3"+
		"\2\2\2<=\7\24\2\2=>\7!\2\2>?\7\35\2\2?@\7\25\2\2@A\7!\2\2AB\7\35\2\2B"+
		"C\7\t\2\2CD\7!\2\2DE\7\35\2\2EF\7\n\2\2FG\7!\2\2GH\7\35\2\2H\7\3\2\2\2"+
		"IP\5\f\7\2JP\5\16\b\2KP\5\20\t\2LP\5\22\n\2MP\5\24\13\2NP\5\26\f\2OI\3"+
		"\2\2\2OJ\3\2\2\2OK\3\2\2\2OL\3\2\2\2OM\3\2\2\2ON\3\2\2\2P\t\3\2\2\2QR"+
		"\7\31\2\2RS\7\32\2\2ST\7!\2\2TU\7\36\2\2UV\7\33\2\2VW\7!\2\2WX\7\36\2"+
		"\2X\13\3\2\2\2Y]\7\13\2\2Z[\7\b\2\2[\\\7!\2\2\\^\7\36\2\2]Z\3\2\2\2]^"+
		"\3\2\2\2^_\3\2\2\2_`\7\24\2\2`a\7!\2\2ab\7\35\2\2bc\7\25\2\2cd\7!\2\2"+
		"de\7\35\2\2e\r\3\2\2\2fj\7\f\2\2gh\7\b\2\2hi\7!\2\2ik\7\36\2\2jg\3\2\2"+
		"\2jk\3\2\2\2kl\3\2\2\2lm\7\24\2\2mn\7!\2\2no\7\35\2\2op\7\25\2\2pq\7!"+
		"\2\2qr\7\35\2\2r\17\3\2\2\2sw\7\r\2\2tu\7\b\2\2uv\7!\2\2vx\7\36\2\2wt"+
		"\3\2\2\2wx\3\2\2\2xy\3\2\2\2yz\7\24\2\2z{\7!\2\2{|\7\35\2\2|}\7\25\2\2"+
		"}~\7!\2\2~\177\7\35\2\2\177\u0080\7\26\2\2\u0080\u0081\7!\2\2\u0081\u0082"+
		"\7\35\2\2\u0082\21\3\2\2\2\u0083\u0087\t\2\2\2\u0084\u0085\7\b\2\2\u0085"+
		"\u0086\7!\2\2\u0086\u0088\7\36\2\2\u0087\u0084\3\2\2\2\u0087\u0088\3\2"+
		"\2\2\u0088\u0089\3\2\2\2\u0089\u008a\7\24\2\2\u008a\u008b\7!\2\2\u008b"+
		"\u008c\7\35\2\2\u008c\u008d\7\25\2\2\u008d\u008e\7!\2\2\u008e\u008f\7"+
		"\35\2\2\u008f\u0090\7\26\2\2\u0090\u0091\7!\2\2\u0091\u0092\7\35\2\2\u0092"+
		"\23\3\2\2\2\u0093\u0097\7\20\2\2\u0094\u0095\7\b\2\2\u0095\u0096\7!\2"+
		"\2\u0096\u0098\7\36\2\2\u0097\u0094\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\u009a\7\24\2\2\u009a\u009b\7!\2\2\u009b\u009c\7\35"+
		"\2\2\u009c\u009d\7\25\2\2\u009d\u009e\7!\2\2\u009e\u009f\7\35\2\2\u009f"+
		"\u00a0\7\27\2\2\u00a0\u00a1\7!\2\2\u00a1\u00a2\7\35\2\2\u00a2\u00a3\7"+
		"\30\2\2\u00a3\u00a4\7!\2\2\u00a4\u00a5\7\35\2\2\u00a5\25\3\2\2\2\u00a6"+
		"\u00aa\7\21\2\2\u00a7\u00a8\7\b\2\2\u00a8\u00a9\7!\2\2\u00a9\u00ab\7\36"+
		"\2\2\u00aa\u00a7\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac"+
		"\u00ad\7\24\2\2\u00ad\u00ae\7!\2\2\u00ae\u00af\7\35\2\2\u00af\u00b0\7"+
		"\25\2\2\u00b0\u00b1\7!\2\2\u00b1\u00b5\7\35\2\2\u00b2\u00b3\7\22\2\2\u00b3"+
		"\u00b4\7!\2\2\u00b4\u00b6\7\36\2\2\u00b5\u00b2\3\2\2\2\u00b5\u00b6\3\2"+
		"\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\7\23\2\2\u00b8\u00b9\7!\2\2\u00b9"+
		"\u00ba\7\36\2\2\u00ba\27\3\2\2\2\21\34\36%*/\64:O]jw\u0087\u0097\u00aa"+
		"\u00b5";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}