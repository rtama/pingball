// Generated from src/pingball/parser/Pingball.g4 by ANTLR 4.0

package pingball.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PingballLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'board'", "'gravity'", "'friction1'", "'friction2'", "'ball'", "'name'", 
		"'xVelocity'", "'yVelocity'", "'squareBumper'", "'circleBumper'", "'triangleBumper'", 
		"'leftFlipper'", "'rightFlipper'", "'absorber'", "'portal'", "'otherBoard'", 
		"'otherPortal'", "'x'", "'y'", "'orientation'", "'width'", "'height'", 
		"'fire'", "'trigger'", "'action'", "COMMENT", "FLOAT", "NAME", "NEWLINE", 
		"SPACE", "'='"
	};
	public static final String[] ruleNames = {
		"BOARD", "GRAVITY", "FRICTION1", "FRICTION2", "BALL", "NAME_LITERAL", 
		"X_VELOCITY", "Y_VELOCITY", "SQUARE_BUMPER", "CIRCLE_BUMPER", "TRIANGLE_BUMPER", 
		"LEFT_FLIPPER", "RIGHT_FLIPPER", "ABSORBER", "PORTAL", "OTHER_BOARD", 
		"OTHER_PORTAL", "X", "Y", "ORIENTATION", "WIDTH", "HEIGHT", "FIRE", "TRIGGER", 
		"ACTION", "COMMENT", "FLOAT", "NAME", "NEWLINE", "SPACE", "EQUALS"
	};


	public PingballLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Pingball.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 25: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 28: NEWLINE_action((RuleContext)_localctx, actionIndex); break;

		case 29: SPACE_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void SPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: skip();  break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4!\u0156\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23"+
		"\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\7\33\u011e\n\33\f\33\16\33\u0121"+
		"\13\33\3\33\3\33\3\33\3\33\3\34\5\34\u0128\n\34\3\34\6\34\u012b\n\34\r"+
		"\34\16\34\u012c\3\34\3\34\7\34\u0131\n\34\f\34\16\34\u0134\13\34\3\34"+
		"\5\34\u0137\n\34\3\34\6\34\u013a\n\34\r\34\16\34\u013b\5\34\u013e\n\34"+
		"\3\35\3\35\7\35\u0142\n\35\f\35\16\35\u0145\13\35\3\36\6\36\u0148\n\36"+
		"\r\36\16\36\u0149\3\36\3\36\3\37\6\37\u014f\n\37\r\37\16\37\u0150\3\37"+
		"\3\37\3 \3 \3\u011f!\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n"+
		"\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%"+
		"\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34\2\67\35\19"+
		"\36\1;\37\3= \4?!\1\3\2\b\3\62;\3\62;\3\62;\5C\\aac|\6\62;C\\aac|\4\13"+
		"\13\"\"\u015f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\3A\3\2\2\2\5G\3\2\2\2\7O\3\2\2"+
		"\2\tY\3\2\2\2\13c\3\2\2\2\rh\3\2\2\2\17m\3\2\2\2\21w\3\2\2\2\23\u0081"+
		"\3\2\2\2\25\u008e\3\2\2\2\27\u009b\3\2\2\2\31\u00aa\3\2\2\2\33\u00b6\3"+
		"\2\2\2\35\u00c3\3\2\2\2\37\u00cc\3\2\2\2!\u00d3\3\2\2\2#\u00de\3\2\2\2"+
		"%\u00ea\3\2\2\2\'\u00ec\3\2\2\2)\u00ee\3\2\2\2+\u00fa\3\2\2\2-\u0100\3"+
		"\2\2\2/\u0107\3\2\2\2\61\u010c\3\2\2\2\63\u0114\3\2\2\2\65\u011b\3\2\2"+
		"\2\67\u0127\3\2\2\29\u013f\3\2\2\2;\u0147\3\2\2\2=\u014e\3\2\2\2?\u0154"+
		"\3\2\2\2AB\7d\2\2BC\7q\2\2CD\7c\2\2DE\7t\2\2EF\7f\2\2F\4\3\2\2\2GH\7i"+
		"\2\2HI\7t\2\2IJ\7c\2\2JK\7x\2\2KL\7k\2\2LM\7v\2\2MN\7{\2\2N\6\3\2\2\2"+
		"OP\7h\2\2PQ\7t\2\2QR\7k\2\2RS\7e\2\2ST\7v\2\2TU\7k\2\2UV\7q\2\2VW\7p\2"+
		"\2WX\7\63\2\2X\b\3\2\2\2YZ\7h\2\2Z[\7t\2\2[\\\7k\2\2\\]\7e\2\2]^\7v\2"+
		"\2^_\7k\2\2_`\7q\2\2`a\7p\2\2ab\7\64\2\2b\n\3\2\2\2cd\7d\2\2de\7c\2\2"+
		"ef\7n\2\2fg\7n\2\2g\f\3\2\2\2hi\7p\2\2ij\7c\2\2jk\7o\2\2kl\7g\2\2l\16"+
		"\3\2\2\2mn\7z\2\2no\7X\2\2op\7g\2\2pq\7n\2\2qr\7q\2\2rs\7e\2\2st\7k\2"+
		"\2tu\7v\2\2uv\7{\2\2v\20\3\2\2\2wx\7{\2\2xy\7X\2\2yz\7g\2\2z{\7n\2\2{"+
		"|\7q\2\2|}\7e\2\2}~\7k\2\2~\177\7v\2\2\177\u0080\7{\2\2\u0080\22\3\2\2"+
		"\2\u0081\u0082\7u\2\2\u0082\u0083\7s\2\2\u0083\u0084\7w\2\2\u0084\u0085"+
		"\7c\2\2\u0085\u0086\7t\2\2\u0086\u0087\7g\2\2\u0087\u0088\7D\2\2\u0088"+
		"\u0089\7w\2\2\u0089\u008a\7o\2\2\u008a\u008b\7r\2\2\u008b\u008c\7g\2\2"+
		"\u008c\u008d\7t\2\2\u008d\24\3\2\2\2\u008e\u008f\7e\2\2\u008f\u0090\7"+
		"k\2\2\u0090\u0091\7t\2\2\u0091\u0092\7e\2\2\u0092\u0093\7n\2\2\u0093\u0094"+
		"\7g\2\2\u0094\u0095\7D\2\2\u0095\u0096\7w\2\2\u0096\u0097\7o\2\2\u0097"+
		"\u0098\7r\2\2\u0098\u0099\7g\2\2\u0099\u009a\7t\2\2\u009a\26\3\2\2\2\u009b"+
		"\u009c\7v\2\2\u009c\u009d\7t\2\2\u009d\u009e\7k\2\2\u009e\u009f\7c\2\2"+
		"\u009f\u00a0\7p\2\2\u00a0\u00a1\7i\2\2\u00a1\u00a2\7n\2\2\u00a2\u00a3"+
		"\7g\2\2\u00a3\u00a4\7D\2\2\u00a4\u00a5\7w\2\2\u00a5\u00a6\7o\2\2\u00a6"+
		"\u00a7\7r\2\2\u00a7\u00a8\7g\2\2\u00a8\u00a9\7t\2\2\u00a9\30\3\2\2\2\u00aa"+
		"\u00ab\7n\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7h\2\2\u00ad\u00ae\7v\2\2"+
		"\u00ae\u00af\7H\2\2\u00af\u00b0\7n\2\2\u00b0\u00b1\7k\2\2\u00b1\u00b2"+
		"\7r\2\2\u00b2\u00b3\7r\2\2\u00b3\u00b4\7g\2\2\u00b4\u00b5\7t\2\2\u00b5"+
		"\32\3\2\2\2\u00b6\u00b7\7t\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7i\2\2\u00b9"+
		"\u00ba\7j\2\2\u00ba\u00bb\7v\2\2\u00bb\u00bc\7H\2\2\u00bc\u00bd\7n\2\2"+
		"\u00bd\u00be\7k\2\2\u00be\u00bf\7r\2\2\u00bf\u00c0\7r\2\2\u00c0\u00c1"+
		"\7g\2\2\u00c1\u00c2\7t\2\2\u00c2\34\3\2\2\2\u00c3\u00c4\7c\2\2\u00c4\u00c5"+
		"\7d\2\2\u00c5\u00c6\7u\2\2\u00c6\u00c7\7q\2\2\u00c7\u00c8\7t\2\2\u00c8"+
		"\u00c9\7d\2\2\u00c9\u00ca\7g\2\2\u00ca\u00cb\7t\2\2\u00cb\36\3\2\2\2\u00cc"+
		"\u00cd\7r\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7t\2\2\u00cf\u00d0\7v\2\2"+
		"\u00d0\u00d1\7c\2\2\u00d1\u00d2\7n\2\2\u00d2 \3\2\2\2\u00d3\u00d4\7q\2"+
		"\2\u00d4\u00d5\7v\2\2\u00d5\u00d6\7j\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8"+
		"\7t\2\2\u00d8\u00d9\7D\2\2\u00d9\u00da\7q\2\2\u00da\u00db\7c\2\2\u00db"+
		"\u00dc\7t\2\2\u00dc\u00dd\7f\2\2\u00dd\"\3\2\2\2\u00de\u00df\7q\2\2\u00df"+
		"\u00e0\7v\2\2\u00e0\u00e1\7j\2\2\u00e1\u00e2\7g\2\2\u00e2\u00e3\7t\2\2"+
		"\u00e3\u00e4\7R\2\2\u00e4\u00e5\7q\2\2\u00e5\u00e6\7t\2\2\u00e6\u00e7"+
		"\7v\2\2\u00e7\u00e8\7c\2\2\u00e8\u00e9\7n\2\2\u00e9$\3\2\2\2\u00ea\u00eb"+
		"\7z\2\2\u00eb&\3\2\2\2\u00ec\u00ed\7{\2\2\u00ed(\3\2\2\2\u00ee\u00ef\7"+
		"q\2\2\u00ef\u00f0\7t\2\2\u00f0\u00f1\7k\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3"+
		"\7p\2\2\u00f3\u00f4\7v\2\2\u00f4\u00f5\7c\2\2\u00f5\u00f6\7v\2\2\u00f6"+
		"\u00f7\7k\2\2\u00f7\u00f8\7q\2\2\u00f8\u00f9\7p\2\2\u00f9*\3\2\2\2\u00fa"+
		"\u00fb\7y\2\2\u00fb\u00fc\7k\2\2\u00fc\u00fd\7f\2\2\u00fd\u00fe\7v\2\2"+
		"\u00fe\u00ff\7j\2\2\u00ff,\3\2\2\2\u0100\u0101\7j\2\2\u0101\u0102\7g\2"+
		"\2\u0102\u0103\7k\2\2\u0103\u0104\7i\2\2\u0104\u0105\7j\2\2\u0105\u0106"+
		"\7v\2\2\u0106.\3\2\2\2\u0107\u0108\7h\2\2\u0108\u0109\7k\2\2\u0109\u010a"+
		"\7t\2\2\u010a\u010b\7g\2\2\u010b\60\3\2\2\2\u010c\u010d\7v\2\2\u010d\u010e"+
		"\7t\2\2\u010e\u010f\7k\2\2\u010f\u0110\7i\2\2\u0110\u0111\7i\2\2\u0111"+
		"\u0112\7g\2\2\u0112\u0113\7t\2\2\u0113\62\3\2\2\2\u0114\u0115\7c\2\2\u0115"+
		"\u0116\7e\2\2\u0116\u0117\7v\2\2\u0117\u0118\7k\2\2\u0118\u0119\7q\2\2"+
		"\u0119\u011a\7p\2\2\u011a\64\3\2\2\2\u011b\u011f\7%\2\2\u011c\u011e\13"+
		"\2\2\2\u011d\u011c\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u0120\3\2\2\2\u011f"+
		"\u011d\3\2\2\2\u0120\u0122\3\2\2\2\u0121\u011f\3\2\2\2\u0122\u0123\5;"+
		"\36\2\u0123\u0124\3\2\2\2\u0124\u0125\b\33\2\2\u0125\66\3\2\2\2\u0126"+
		"\u0128\7/\2\2\u0127\u0126\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u013d\3\2"+
		"\2\2\u0129\u012b\t\2\2\2\u012a\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c"+
		"\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u0132\7\60"+
		"\2\2\u012f\u0131\t\3\2\2\u0130\u012f\3\2\2\2\u0131\u0134\3\2\2\2\u0132"+
		"\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u013e\3\2\2\2\u0134\u0132\3\2"+
		"\2\2\u0135\u0137\7\60\2\2\u0136\u0135\3\2\2\2\u0136\u0137\3\2\2\2\u0137"+
		"\u0139\3\2\2\2\u0138\u013a\t\4\2\2\u0139\u0138\3\2\2\2\u013a\u013b\3\2"+
		"\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013e\3\2\2\2\u013d"+
		"\u012a\3\2\2\2\u013d\u0136\3\2\2\2\u013e8\3\2\2\2\u013f\u0143\t\5\2\2"+
		"\u0140\u0142\t\6\2\2\u0141\u0140\3\2\2\2\u0142\u0145\3\2\2\2\u0143\u0141"+
		"\3\2\2\2\u0143\u0144\3\2\2\2\u0144:\3\2\2\2\u0145\u0143\3\2\2\2\u0146"+
		"\u0148\7\f\2\2\u0147\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u0147\3\2"+
		"\2\2\u0149\u014a\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014c\b\36\3\2\u014c"+
		"<\3\2\2\2\u014d\u014f\t\7\2\2\u014e\u014d\3\2\2\2\u014f\u0150\3\2\2\2"+
		"\u0150\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0153"+
		"\b\37\4\2\u0153>\3\2\2\2\u0154\u0155\7?\2\2\u0155@\3\2\2\2\r\2\u011f\u0127"+
		"\u012c\u0132\u0136\u013b\u013d\u0143\u0149\u0150";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}