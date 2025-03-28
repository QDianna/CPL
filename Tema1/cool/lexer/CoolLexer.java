// Generated from C:/Users/elena/Desktop/tema1_CPL/Tema1/src/cool/lexer/CoolLexer.g4 by ANTLR 4.13.1
package cool.lexer;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, CLASS=2, ELSE=3, FALSE=4, FI=5, IF=6, IN=7, INHERITS=8, ISVOID=9, 
		LET=10, LOOP=11, POOL=12, THEN=13, WHILE=14, CASE=15, ESAC=16, NEW=17, 
		OF=18, NOT=19, TRUE=20, OBJECT=21, TYPE=22, INT=23, LPAREN=24, RPAREN=25, 
		LBRACE=26, RBRACE=27, COMMA=28, SEMI=29, COLON=30, DOT=31, ARROW=32, AT=33, 
		ASSIGN=34, NEGATION=35, MULT=36, DIV=37, PLUS=38, MINUS=39, LT=40, LE=41, 
		EQUAL=42, STRING=43, UNTERMINATED_STRING=44, EOF_IN_STRING=45, LINE_COMMENT=46, 
		UNMATCHED_COMMENT=47, COMMENT=48, WS=49, INVALID_CHAR=50;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CLASS", "ELSE", "FALSE", "FI", "IF", "IN", "INHERITS", "ISVOID", "LET", 
			"LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", "NOT", 
			"TRUE", "SELF", "SELF_TYPE", "OBJECT", "TYPE", "DIGIT", "INT", "LPAREN", 
			"RPAREN", "LBRACE", "RBRACE", "COMMA", "SEMI", "COLON", "DOT", "ARROW", 
			"AT", "ASSIGN", "NEGATION", "MULT", "DIV", "PLUS", "MINUS", "LT", "LE", 
			"EQUAL", "NEW_LINE", "ESCAPED", "NON_ESCAPED", "STRING", "UNTERMINATED_STRING", 
			"EOF_IN_STRING", "LINE_COMMENT", "UNMATCHED_COMMENT", "COMMENT", "WS", 
			"INVALID_CHAR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'class'", "'else'", "'false'", "'fi'", "'if'", "'in'", "'inherits'", 
			"'isvoid'", "'let'", "'loop'", "'pool'", "'then'", "'while'", "'case'", 
			"'esac'", "'new'", "'of'", "'not'", "'true'", null, null, null, "'('", 
			"')'", "'{'", "'}'", "','", "';'", "':'", "'.'", "'=>'", "'@'", "'<-'", 
			"'~'", "'*'", "'/'", "'+'", "'-'", "'<'", "'<='", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "CLASS", "ELSE", "FALSE", "FI", "IF", "IN", "INHERITS", 
			"ISVOID", "LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", 
			"OF", "NOT", "TRUE", "OBJECT", "TYPE", "INT", "LPAREN", "RPAREN", "LBRACE", 
			"RBRACE", "COMMA", "SEMI", "COLON", "DOT", "ARROW", "AT", "ASSIGN", "NEGATION", 
			"MULT", "DIV", "PLUS", "MINUS", "LT", "LE", "EQUAL", "STRING", "UNTERMINATED_STRING", 
			"EOF_IN_STRING", "LINE_COMMENT", "UNMATCHED_COMMENT", "COMMENT", "WS", 
			"INVALID_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	    private void raiseError(String msg) {
	        setText(msg);
	        setType(ERROR);
	    }


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 47:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 48:
			UNTERMINATED_STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 49:
			EOF_IN_STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 51:
			UNMATCHED_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 52:
			COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 54:
			INVALID_CHAR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			          String text = getText();

			          if (text.length() > 1024) {
			            raiseError("String constant too long");
			            return;
			          }

			          if (text.contains("\u0000")) {
			            raiseError("String contains null character");
			            return;
			          }

			          // Eliminate quotes
			          text = text.substring(1, text.length() - 1);

			          // Process any escaped sequences
			          StringBuilder processed = new StringBuilder();

			          for (int i = 0; i < text.length(); i++) {
			              char current = text.charAt(i);

			              if (current == '\\') {
			                  // Check the next char after backslash (if it exists)
			                  if (i != text.length() - 1) {
			                      char next = text.charAt(i + 1);
			                      switch (next) {
			                          case 'n': processed.append('\n'); break;  // process as one (special char \n)
			                          case 't': processed.append('\t'); break;  // process as one (special char \t)
			                          case 'b': processed.append('\b'); break;  // process as one (special char \b)
			                          case 'f': processed.append('\f'); break;  // process as one (special char \f)
			                          // case 'r': processed.append('\r'); break;  // process as one (special char \r)
			                          default: processed.append(next);  break;  // skip the '\', add the char (normal char)
			                      }
			                      i++;

			                  } else {
			                      processed.append(current);
			                  }

			              } else {
			                  processed.append(current);
			              }
			          }

			          setText(processed.toString());
			      
			break;
		}
	}
	private void UNTERMINATED_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

			            raiseError("Unterminated string constant");
			        
			break;
		}
	}
	private void EOF_IN_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:

			            raiseError("EOF in string constant");
			        
			break;
		}
	}
	private void UNMATCHED_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:

			            raiseError("Unmatched *)");
			        
			break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			 skip(); 
			break;
		case 5:
			 raiseError("EOF in comment"); 
			break;
		}
	}
	private void INVALID_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:

			            raiseError("Invalid character: " + getText());
			        
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u00002\u018a\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0005\u0015\u00de\b\u0015\n\u0015\f\u0015\u00e1\t\u0015"+
		"\u0001\u0015\u0003\u0015\u00e4\b\u0015\u0001\u0016\u0001\u0016\u0005\u0016"+
		"\u00e8\b\u0016\n\u0016\f\u0016\u00eb\t\u0016\u0001\u0016\u0003\u0016\u00ee"+
		"\b\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0004\u0018\u00f3\b\u0018"+
		"\u000b\u0018\f\u0018\u00f4\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001!"+
		"\u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001$\u0001$\u0001"+
		"%\u0001%\u0001&\u0001&\u0001\'\u0001\'\u0001(\u0001(\u0001)\u0001)\u0001"+
		"*\u0001*\u0001*\u0001+\u0001+\u0001,\u0003,\u0121\b,\u0001,\u0001,\u0001"+
		"-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001"+
		"-\u0001-\u0003-\u0131\b-\u0001.\u0001.\u0001.\u0001/\u0001/\u0001/\u0001"+
		"/\u0005/\u013a\b/\n/\f/\u013d\t/\u0001/\u0001/\u0001/\u00010\u00010\u0005"+
		"0\u0144\b0\n0\f0\u0147\t0\u00010\u00010\u00010\u00011\u00011\u00051\u014e"+
		"\b1\n1\f1\u0151\t1\u00011\u00011\u00011\u00012\u00012\u00012\u00012\u0005"+
		"2\u015a\b2\n2\f2\u015d\t2\u00012\u00012\u00032\u0161\b2\u00012\u00012"+
		"\u00013\u00013\u00013\u00013\u00013\u00013\u00033\u016b\b3\u00013\u0001"+
		"3\u00014\u00014\u00014\u00014\u00014\u00054\u0174\b4\n4\f4\u0177\t4\u0001"+
		"4\u00014\u00014\u00014\u00014\u00014\u00034\u017f\b4\u00015\u00045\u0182"+
		"\b5\u000b5\f5\u0183\u00015\u00015\u00016\u00016\u00016\u0002\u015b\u0175"+
		"\u00007\u0001\u0002\u0003\u0003\u0005\u0004\u0007\u0005\t\u0006\u000b"+
		"\u0007\r\b\u000f\t\u0011\n\u0013\u000b\u0015\f\u0017\r\u0019\u000e\u001b"+
		"\u000f\u001d\u0010\u001f\u0011!\u0012#\u0013%\u0014\'\u0000)\u0000+\u0015"+
		"-\u0016/\u00001\u00173\u00185\u00197\u001a9\u001b;\u001c=\u001d?\u001e"+
		"A\u001fC E!G\"I#K$M%O&Q\'S(U)W*Y\u0000[\u0000]\u0000_+a,c-e.g/i0k1m2\u0001"+
		"\u0000\u0007\u0001\u0000az\u0004\u000009AZ__az\u0001\u0000AZ\u0001\u0000"+
		"09\u0006\u0000\\\\bbffnnrrtt\u0004\u0000\n\n\r\r\"\"\\\\\u0003\u0000\t"+
		"\n\f\r  \u019a\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%"+
		"\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000"+
		"\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000"+
		"\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009"+
		"\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000"+
		"\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000"+
		"\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G"+
		"\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000\u0000\u0000K\u0001\u0000"+
		"\u0000\u0000\u0000M\u0001\u0000\u0000\u0000\u0000O\u0001\u0000\u0000\u0000"+
		"\u0000Q\u0001\u0000\u0000\u0000\u0000S\u0001\u0000\u0000\u0000\u0000U"+
		"\u0001\u0000\u0000\u0000\u0000W\u0001\u0000\u0000\u0000\u0000_\u0001\u0000"+
		"\u0000\u0000\u0000a\u0001\u0000\u0000\u0000\u0000c\u0001\u0000\u0000\u0000"+
		"\u0000e\u0001\u0000\u0000\u0000\u0000g\u0001\u0000\u0000\u0000\u0000i"+
		"\u0001\u0000\u0000\u0000\u0000k\u0001\u0000\u0000\u0000\u0000m\u0001\u0000"+
		"\u0000\u0000\u0001o\u0001\u0000\u0000\u0000\u0003u\u0001\u0000\u0000\u0000"+
		"\u0005z\u0001\u0000\u0000\u0000\u0007\u0080\u0001\u0000\u0000\u0000\t"+
		"\u0083\u0001\u0000\u0000\u0000\u000b\u0086\u0001\u0000\u0000\u0000\r\u0089"+
		"\u0001\u0000\u0000\u0000\u000f\u0092\u0001\u0000\u0000\u0000\u0011\u0099"+
		"\u0001\u0000\u0000\u0000\u0013\u009d\u0001\u0000\u0000\u0000\u0015\u00a2"+
		"\u0001\u0000\u0000\u0000\u0017\u00a7\u0001\u0000\u0000\u0000\u0019\u00ac"+
		"\u0001\u0000\u0000\u0000\u001b\u00b2\u0001\u0000\u0000\u0000\u001d\u00b7"+
		"\u0001\u0000\u0000\u0000\u001f\u00bc\u0001\u0000\u0000\u0000!\u00c0\u0001"+
		"\u0000\u0000\u0000#\u00c3\u0001\u0000\u0000\u0000%\u00c7\u0001\u0000\u0000"+
		"\u0000\'\u00cc\u0001\u0000\u0000\u0000)\u00d1\u0001\u0000\u0000\u0000"+
		"+\u00e3\u0001\u0000\u0000\u0000-\u00ed\u0001\u0000\u0000\u0000/\u00ef"+
		"\u0001\u0000\u0000\u00001\u00f2\u0001\u0000\u0000\u00003\u00f6\u0001\u0000"+
		"\u0000\u00005\u00f8\u0001\u0000\u0000\u00007\u00fa\u0001\u0000\u0000\u0000"+
		"9\u00fc\u0001\u0000\u0000\u0000;\u00fe\u0001\u0000\u0000\u0000=\u0100"+
		"\u0001\u0000\u0000\u0000?\u0102\u0001\u0000\u0000\u0000A\u0104\u0001\u0000"+
		"\u0000\u0000C\u0106\u0001\u0000\u0000\u0000E\u0109\u0001\u0000\u0000\u0000"+
		"G\u010b\u0001\u0000\u0000\u0000I\u010e\u0001\u0000\u0000\u0000K\u0110"+
		"\u0001\u0000\u0000\u0000M\u0112\u0001\u0000\u0000\u0000O\u0114\u0001\u0000"+
		"\u0000\u0000Q\u0116\u0001\u0000\u0000\u0000S\u0118\u0001\u0000\u0000\u0000"+
		"U\u011a\u0001\u0000\u0000\u0000W\u011d\u0001\u0000\u0000\u0000Y\u0120"+
		"\u0001\u0000\u0000\u0000[\u0130\u0001\u0000\u0000\u0000]\u0132\u0001\u0000"+
		"\u0000\u0000_\u0135\u0001\u0000\u0000\u0000a\u0141\u0001\u0000\u0000\u0000"+
		"c\u014b\u0001\u0000\u0000\u0000e\u0155\u0001\u0000\u0000\u0000g\u016a"+
		"\u0001\u0000\u0000\u0000i\u016e\u0001\u0000\u0000\u0000k\u0181\u0001\u0000"+
		"\u0000\u0000m\u0187\u0001\u0000\u0000\u0000op\u0005c\u0000\u0000pq\u0005"+
		"l\u0000\u0000qr\u0005a\u0000\u0000rs\u0005s\u0000\u0000st\u0005s\u0000"+
		"\u0000t\u0002\u0001\u0000\u0000\u0000uv\u0005e\u0000\u0000vw\u0005l\u0000"+
		"\u0000wx\u0005s\u0000\u0000xy\u0005e\u0000\u0000y\u0004\u0001\u0000\u0000"+
		"\u0000z{\u0005f\u0000\u0000{|\u0005a\u0000\u0000|}\u0005l\u0000\u0000"+
		"}~\u0005s\u0000\u0000~\u007f\u0005e\u0000\u0000\u007f\u0006\u0001\u0000"+
		"\u0000\u0000\u0080\u0081\u0005f\u0000\u0000\u0081\u0082\u0005i\u0000\u0000"+
		"\u0082\b\u0001\u0000\u0000\u0000\u0083\u0084\u0005i\u0000\u0000\u0084"+
		"\u0085\u0005f\u0000\u0000\u0085\n\u0001\u0000\u0000\u0000\u0086\u0087"+
		"\u0005i\u0000\u0000\u0087\u0088\u0005n\u0000\u0000\u0088\f\u0001\u0000"+
		"\u0000\u0000\u0089\u008a\u0005i\u0000\u0000\u008a\u008b\u0005n\u0000\u0000"+
		"\u008b\u008c\u0005h\u0000\u0000\u008c\u008d\u0005e\u0000\u0000\u008d\u008e"+
		"\u0005r\u0000\u0000\u008e\u008f\u0005i\u0000\u0000\u008f\u0090\u0005t"+
		"\u0000\u0000\u0090\u0091\u0005s\u0000\u0000\u0091\u000e\u0001\u0000\u0000"+
		"\u0000\u0092\u0093\u0005i\u0000\u0000\u0093\u0094\u0005s\u0000\u0000\u0094"+
		"\u0095\u0005v\u0000\u0000\u0095\u0096\u0005o\u0000\u0000\u0096\u0097\u0005"+
		"i\u0000\u0000\u0097\u0098\u0005d\u0000\u0000\u0098\u0010\u0001\u0000\u0000"+
		"\u0000\u0099\u009a\u0005l\u0000\u0000\u009a\u009b\u0005e\u0000\u0000\u009b"+
		"\u009c\u0005t\u0000\u0000\u009c\u0012\u0001\u0000\u0000\u0000\u009d\u009e"+
		"\u0005l\u0000\u0000\u009e\u009f\u0005o\u0000\u0000\u009f\u00a0\u0005o"+
		"\u0000\u0000\u00a0\u00a1\u0005p\u0000\u0000\u00a1\u0014\u0001\u0000\u0000"+
		"\u0000\u00a2\u00a3\u0005p\u0000\u0000\u00a3\u00a4\u0005o\u0000\u0000\u00a4"+
		"\u00a5\u0005o\u0000\u0000\u00a5\u00a6\u0005l\u0000\u0000\u00a6\u0016\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a8\u0005t\u0000\u0000\u00a8\u00a9\u0005h\u0000"+
		"\u0000\u00a9\u00aa\u0005e\u0000\u0000\u00aa\u00ab\u0005n\u0000\u0000\u00ab"+
		"\u0018\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005w\u0000\u0000\u00ad\u00ae"+
		"\u0005h\u0000\u0000\u00ae\u00af\u0005i\u0000\u0000\u00af\u00b0\u0005l"+
		"\u0000\u0000\u00b0\u00b1\u0005e\u0000\u0000\u00b1\u001a\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b3\u0005c\u0000\u0000\u00b3\u00b4\u0005a\u0000\u0000\u00b4"+
		"\u00b5\u0005s\u0000\u0000\u00b5\u00b6\u0005e\u0000\u0000\u00b6\u001c\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0005e\u0000\u0000\u00b8\u00b9\u0005s\u0000"+
		"\u0000\u00b9\u00ba\u0005a\u0000\u0000\u00ba\u00bb\u0005c\u0000\u0000\u00bb"+
		"\u001e\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005n\u0000\u0000\u00bd\u00be"+
		"\u0005e\u0000\u0000\u00be\u00bf\u0005w\u0000\u0000\u00bf \u0001\u0000"+
		"\u0000\u0000\u00c0\u00c1\u0005o\u0000\u0000\u00c1\u00c2\u0005f\u0000\u0000"+
		"\u00c2\"\u0001\u0000\u0000\u0000\u00c3\u00c4\u0005n\u0000\u0000\u00c4"+
		"\u00c5\u0005o\u0000\u0000\u00c5\u00c6\u0005t\u0000\u0000\u00c6$\u0001"+
		"\u0000\u0000\u0000\u00c7\u00c8\u0005t\u0000\u0000\u00c8\u00c9\u0005r\u0000"+
		"\u0000\u00c9\u00ca\u0005u\u0000\u0000\u00ca\u00cb\u0005e\u0000\u0000\u00cb"+
		"&\u0001\u0000\u0000\u0000\u00cc\u00cd\u0005s\u0000\u0000\u00cd\u00ce\u0005"+
		"e\u0000\u0000\u00ce\u00cf\u0005l\u0000\u0000\u00cf\u00d0\u0005f\u0000"+
		"\u0000\u00d0(\u0001\u0000\u0000\u0000\u00d1\u00d2\u0005S\u0000\u0000\u00d2"+
		"\u00d3\u0005E\u0000\u0000\u00d3\u00d4\u0005L\u0000\u0000\u00d4\u00d5\u0005"+
		"F\u0000\u0000\u00d5\u00d6\u0005_\u0000\u0000\u00d6\u00d7\u0005T\u0000"+
		"\u0000\u00d7\u00d8\u0005Y\u0000\u0000\u00d8\u00d9\u0005P\u0000\u0000\u00d9"+
		"\u00da\u0005E\u0000\u0000\u00da*\u0001\u0000\u0000\u0000\u00db\u00df\u0007"+
		"\u0000\u0000\u0000\u00dc\u00de\u0007\u0001\u0000\u0000\u00dd\u00dc\u0001"+
		"\u0000\u0000\u0000\u00de\u00e1\u0001\u0000\u0000\u0000\u00df\u00dd\u0001"+
		"\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u00e4\u0001"+
		"\u0000\u0000\u0000\u00e1\u00df\u0001\u0000\u0000\u0000\u00e2\u00e4\u0003"+
		"\'\u0013\u0000\u00e3\u00db\u0001\u0000\u0000\u0000\u00e3\u00e2\u0001\u0000"+
		"\u0000\u0000\u00e4,\u0001\u0000\u0000\u0000\u00e5\u00e9\u0007\u0002\u0000"+
		"\u0000\u00e6\u00e8\u0007\u0001\u0000\u0000\u00e7\u00e6\u0001\u0000\u0000"+
		"\u0000\u00e8\u00eb\u0001\u0000\u0000\u0000\u00e9\u00e7\u0001\u0000\u0000"+
		"\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea\u00ee\u0001\u0000\u0000"+
		"\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00ec\u00ee\u0003)\u0014\u0000"+
		"\u00ed\u00e5\u0001\u0000\u0000\u0000\u00ed\u00ec\u0001\u0000\u0000\u0000"+
		"\u00ee.\u0001\u0000\u0000\u0000\u00ef\u00f0\u0007\u0003\u0000\u0000\u00f0"+
		"0\u0001\u0000\u0000\u0000\u00f1\u00f3\u0003/\u0017\u0000\u00f2\u00f1\u0001"+
		"\u0000\u0000\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u00f2\u0001"+
		"\u0000\u0000\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000\u00f52\u0001\u0000"+
		"\u0000\u0000\u00f6\u00f7\u0005(\u0000\u0000\u00f74\u0001\u0000\u0000\u0000"+
		"\u00f8\u00f9\u0005)\u0000\u0000\u00f96\u0001\u0000\u0000\u0000\u00fa\u00fb"+
		"\u0005{\u0000\u0000\u00fb8\u0001\u0000\u0000\u0000\u00fc\u00fd\u0005}"+
		"\u0000\u0000\u00fd:\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005,\u0000\u0000"+
		"\u00ff<\u0001\u0000\u0000\u0000\u0100\u0101\u0005;\u0000\u0000\u0101>"+
		"\u0001\u0000\u0000\u0000\u0102\u0103\u0005:\u0000\u0000\u0103@\u0001\u0000"+
		"\u0000\u0000\u0104\u0105\u0005.\u0000\u0000\u0105B\u0001\u0000\u0000\u0000"+
		"\u0106\u0107\u0005=\u0000\u0000\u0107\u0108\u0005>\u0000\u0000\u0108D"+
		"\u0001\u0000\u0000\u0000\u0109\u010a\u0005@\u0000\u0000\u010aF\u0001\u0000"+
		"\u0000\u0000\u010b\u010c\u0005<\u0000\u0000\u010c\u010d\u0005-\u0000\u0000"+
		"\u010dH\u0001\u0000\u0000\u0000\u010e\u010f\u0005~\u0000\u0000\u010fJ"+
		"\u0001\u0000\u0000\u0000\u0110\u0111\u0005*\u0000\u0000\u0111L\u0001\u0000"+
		"\u0000\u0000\u0112\u0113\u0005/\u0000\u0000\u0113N\u0001\u0000\u0000\u0000"+
		"\u0114\u0115\u0005+\u0000\u0000\u0115P\u0001\u0000\u0000\u0000\u0116\u0117"+
		"\u0005-\u0000\u0000\u0117R\u0001\u0000\u0000\u0000\u0118\u0119\u0005<"+
		"\u0000\u0000\u0119T\u0001\u0000\u0000\u0000\u011a\u011b\u0005<\u0000\u0000"+
		"\u011b\u011c\u0005=\u0000\u0000\u011cV\u0001\u0000\u0000\u0000\u011d\u011e"+
		"\u0005=\u0000\u0000\u011eX\u0001\u0000\u0000\u0000\u011f\u0121\u0005\r"+
		"\u0000\u0000\u0120\u011f\u0001\u0000\u0000\u0000\u0120\u0121\u0001\u0000"+
		"\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0123\u0005\n\u0000"+
		"\u0000\u0123Z\u0001\u0000\u0000\u0000\u0124\u0125\u0005\\\u0000\u0000"+
		"\u0125\u0131\u0005n\u0000\u0000\u0126\u0127\u0005\\\u0000\u0000\u0127"+
		"\u0131\u0005t\u0000\u0000\u0128\u0129\u0005\\\u0000\u0000\u0129\u0131"+
		"\u0005b\u0000\u0000\u012a\u012b\u0005\\\u0000\u0000\u012b\u0131\u0005"+
		"f\u0000\u0000\u012c\u012d\u0005\\\u0000\u0000\u012d\u0131\u0005\\\u0000"+
		"\u0000\u012e\u012f\u0005\\\u0000\u0000\u012f\u0131\u0005\"\u0000\u0000"+
		"\u0130\u0124\u0001\u0000\u0000\u0000\u0130\u0126\u0001\u0000\u0000\u0000"+
		"\u0130\u0128\u0001\u0000\u0000\u0000\u0130\u012a\u0001\u0000\u0000\u0000"+
		"\u0130\u012c\u0001\u0000\u0000\u0000\u0130\u012e\u0001\u0000\u0000\u0000"+
		"\u0131\\\u0001\u0000\u0000\u0000\u0132\u0133\u0005\\\u0000\u0000\u0133"+
		"\u0134\b\u0004\u0000\u0000\u0134^\u0001\u0000\u0000\u0000\u0135\u013b"+
		"\u0005\"\u0000\u0000\u0136\u013a\u0003[-\u0000\u0137\u013a\u0003].\u0000"+
		"\u0138\u013a\b\u0005\u0000\u0000\u0139\u0136\u0001\u0000\u0000\u0000\u0139"+
		"\u0137\u0001\u0000\u0000\u0000\u0139\u0138\u0001\u0000\u0000\u0000\u013a"+
		"\u013d\u0001\u0000\u0000\u0000\u013b\u0139\u0001\u0000\u0000\u0000\u013b"+
		"\u013c\u0001\u0000\u0000\u0000\u013c\u013e\u0001\u0000\u0000\u0000\u013d"+
		"\u013b\u0001\u0000\u0000\u0000\u013e\u013f\u0005\"\u0000\u0000\u013f\u0140"+
		"\u0006/\u0000\u0000\u0140`\u0001\u0000\u0000\u0000\u0141\u0145\u0005\""+
		"\u0000\u0000\u0142\u0144\b\u0005\u0000\u0000\u0143\u0142\u0001\u0000\u0000"+
		"\u0000\u0144\u0147\u0001\u0000\u0000\u0000\u0145\u0143\u0001\u0000\u0000"+
		"\u0000\u0145\u0146\u0001\u0000\u0000\u0000\u0146\u0148\u0001\u0000\u0000"+
		"\u0000\u0147\u0145\u0001\u0000\u0000\u0000\u0148\u0149\u0003Y,\u0000\u0149"+
		"\u014a\u00060\u0001\u0000\u014ab\u0001\u0000\u0000\u0000\u014b\u014f\u0005"+
		"\"\u0000\u0000\u014c\u014e\b\u0005\u0000\u0000\u014d\u014c\u0001\u0000"+
		"\u0000\u0000\u014e\u0151\u0001\u0000\u0000\u0000\u014f\u014d\u0001\u0000"+
		"\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0152\u0001\u0000"+
		"\u0000\u0000\u0151\u014f\u0001\u0000\u0000\u0000\u0152\u0153\u0005\u0000"+
		"\u0000\u0001\u0153\u0154\u00061\u0002\u0000\u0154d\u0001\u0000\u0000\u0000"+
		"\u0155\u0156\u0005-\u0000\u0000\u0156\u0157\u0005-\u0000\u0000\u0157\u015b"+
		"\u0001\u0000\u0000\u0000\u0158\u015a\t\u0000\u0000\u0000\u0159\u0158\u0001"+
		"\u0000\u0000\u0000\u015a\u015d\u0001\u0000\u0000\u0000\u015b\u015c\u0001"+
		"\u0000\u0000\u0000\u015b\u0159\u0001\u0000\u0000\u0000\u015c\u0160\u0001"+
		"\u0000\u0000\u0000\u015d\u015b\u0001\u0000\u0000\u0000\u015e\u0161\u0003"+
		"Y,\u0000\u015f\u0161\u0005\u0000\u0000\u0001\u0160\u015e\u0001\u0000\u0000"+
		"\u0000\u0160\u015f\u0001\u0000\u0000\u0000\u0161\u0162\u0001\u0000\u0000"+
		"\u0000\u0162\u0163\u00062\u0003\u0000\u0163f\u0001\u0000\u0000\u0000\u0164"+
		"\u0165\u0005*\u0000\u0000\u0165\u016b\u0005)\u0000\u0000\u0166\u0167\u0003"+
		"i4\u0000\u0167\u0168\u0005*\u0000\u0000\u0168\u0169\u0005)\u0000\u0000"+
		"\u0169\u016b\u0001\u0000\u0000\u0000\u016a\u0164\u0001\u0000\u0000\u0000"+
		"\u016a\u0166\u0001\u0000\u0000\u0000\u016b\u016c\u0001\u0000\u0000\u0000"+
		"\u016c\u016d\u00063\u0004\u0000\u016dh\u0001\u0000\u0000\u0000\u016e\u016f"+
		"\u0005(\u0000\u0000\u016f\u0170\u0005*\u0000\u0000\u0170\u0175\u0001\u0000"+
		"\u0000\u0000\u0171\u0174\u0003i4\u0000\u0172\u0174\t\u0000\u0000\u0000"+
		"\u0173\u0171\u0001\u0000\u0000\u0000\u0173\u0172\u0001\u0000\u0000\u0000"+
		"\u0174\u0177\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000"+
		"\u0175\u0173\u0001\u0000\u0000\u0000\u0176\u017e\u0001\u0000\u0000\u0000"+
		"\u0177\u0175\u0001\u0000\u0000\u0000\u0178\u0179\u0005*\u0000\u0000\u0179"+
		"\u017a\u0005)\u0000\u0000\u017a\u017b\u0001\u0000\u0000\u0000\u017b\u017f"+
		"\u00064\u0005\u0000\u017c\u017d\u0005\u0000\u0000\u0001\u017d\u017f\u0006"+
		"4\u0006\u0000\u017e\u0178\u0001\u0000\u0000\u0000\u017e\u017c\u0001\u0000"+
		"\u0000\u0000\u017fj\u0001\u0000\u0000\u0000\u0180\u0182\u0007\u0006\u0000"+
		"\u0000\u0181\u0180\u0001\u0000\u0000\u0000\u0182\u0183\u0001\u0000\u0000"+
		"\u0000\u0183\u0181\u0001\u0000\u0000\u0000\u0183\u0184\u0001\u0000\u0000"+
		"\u0000\u0184\u0185\u0001\u0000\u0000\u0000\u0185\u0186\u00065\u0003\u0000"+
		"\u0186l\u0001\u0000\u0000\u0000\u0187\u0188\t\u0000\u0000\u0000\u0188"+
		"\u0189\u00066\u0007\u0000\u0189n\u0001\u0000\u0000\u0000\u0013\u0000\u00df"+
		"\u00e3\u00e9\u00ed\u00f4\u0120\u0130\u0139\u013b\u0145\u014f\u015b\u0160"+
		"\u016a\u0173\u0175\u017e\u0183\b\u0001/\u0000\u00010\u0001\u00011\u0002"+
		"\u0006\u0000\u0000\u00013\u0003\u00014\u0004\u00014\u0005\u00016\u0006";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}