// Generated from src/pingball/parser/Pingball.g4 by ANTLR 4.0

package pingball.parser;

import java.io.IOException;

import org.antlr.v4.runtime.tree.*;

public interface PingballListener extends ParseTreeListener {
	void enterAbsorberDef(PingballParser.AbsorberDefContext ctx);
	void exitAbsorberDef(PingballParser.AbsorberDefContext ctx);

	void enterFireDef(PingballParser.FireDefContext ctx);
	void exitFireDef(PingballParser.FireDefContext ctx);

	void enterTriangleBumperDef(PingballParser.TriangleBumperDefContext ctx);
	void exitTriangleBumperDef(PingballParser.TriangleBumperDefContext ctx);

	void enterSquareBumperDef(PingballParser.SquareBumperDefContext ctx);
	void exitSquareBumperDef(PingballParser.SquareBumperDefContext ctx);

	void enterCircleBumperDef(PingballParser.CircleBumperDefContext ctx);
	void exitCircleBumperDef(PingballParser.CircleBumperDefContext ctx);

	void enterBallDef(PingballParser.BallDefContext ctx);
	void exitBallDef(PingballParser.BallDefContext ctx);

	void enterGadgetDef(PingballParser.GadgetDefContext ctx);
	void exitGadgetDef(PingballParser.GadgetDefContext ctx);

	void enterPortalDef(PingballParser.PortalDefContext ctx);
	void exitPortalDef(PingballParser.PortalDefContext ctx);

	void enterBoardFile(PingballParser.BoardFileContext ctx);
	void exitBoardFile(PingballParser.BoardFileContext ctx);

	void enterBoardDef(PingballParser.BoardDefContext ctx);
	void exitBoardDef(PingballParser.BoardDefContext ctx);

	void enterFlipperDef(PingballParser.FlipperDefContext ctx);
	void exitFlipperDef(PingballParser.FlipperDefContext ctx);
}