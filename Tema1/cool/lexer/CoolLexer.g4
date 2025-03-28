lexer grammar CoolLexer;

tokens { ERROR } 

@header{
    package cool.lexer;
}

@members{
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
}


// 10.4 key words
CLASS : 'class';
ELSE : 'else';
FALSE : 'false';
FI : 'fi';
IF : 'if';
IN : 'in';
INHERITS : 'inherits';
ISVOID : 'isvoid';
LET : 'let';
LOOP : 'loop';
POOL : 'pool';
THEN : 'then';
WHILE : 'while';
CASE : 'case';
ESAC : 'esac';
NEW : 'new';
OF : 'of';
NOT : 'not';
TRUE : 'true';


// 10.1 integers, identifiers and special notations
fragment SELF : 'self';
fragment SELF_TYPE : 'SELF_TYPE';

OBJECT : [a-z][a-zA-Z0-9_]* | SELF;
TYPE: [A-Z][a-zA-Z0-9_]* | SELF_TYPE;

fragment DIGIT : [0-9];
INT : DIGIT+;


// operations
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
COMMA: ',';
SEMI: ';';
COLON: ':';
DOT: '.';
ARROW: '=>';
AT: '@';

ASSIGN: '<-';
NEGATION: '~';
MULT: '*';
DIV: '/';
PLUS: '+';
MINUS: '-';
LT: '<';
LE: '<=';
EQUAL: '=';


// 10.2 strings
fragment NEW_LINE : '\r'?'\n';

fragment ESCAPED
    : '\\n'  // Newline
    | '\\t'  // Tab
    | '\\b'  // Backspace
    | '\\f'  // Formfeed
    | '\\\\' // Backslash
    | '\\"'  // Double quotes
    ;

fragment NON_ESCAPED
    : '\\' ~[\\ntbfr]  // Backslash + any char other than special chars
    ;

STRING
    : '"' (ESCAPED | NON_ESCAPED | ~["\\\n\r])* '"'
      {
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
      }
    ;

UNTERMINATED_STRING
    : '"' (~["\\\n\r])* NEW_LINE
        {
            raiseError("Unterminated string constant");
        }
    ;

EOF_IN_STRING
    : '"'  (~["\\\n\r])* EOF
        {
            raiseError("EOF in string constant");
        }
    ;


// 10.3 comments
LINE_COMMENT: '--' .*? (NEW_LINE | EOF) -> skip;

UNMATCHED_COMMENT
    : ('*)' | COMMENT '*)')
        {
            raiseError("Unmatched *)");
        }
    ;

COMMENT
    : '(*' (COMMENT | .)*? ('*)' { skip(); } | EOF { raiseError("EOF in comment"); });


// 10.5 white space
WS: [ \n\f\r\t]+ -> skip;


// Invalid characters - anything that wasnt matched
INVALID_CHAR
    : .
        {
            raiseError("Invalid character: " + getText());
        }
    ;

