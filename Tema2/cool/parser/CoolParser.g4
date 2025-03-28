parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

// program rule; program = set of classes
program
    : (class_def SEMI)+ EOF
    ;

// class rule; class = type (name of the class), parents (optional), features (attributes and methodes)
class_def
    : CLASS TYPE (INHERITS TYPE)? LBRACE (feature SEMI)* RBRACE
    ;

// feature rules; feature = either an attribute or a method
feature
    : OBJECT COLON TYPE (ASSIGN expr)?                                                  # attributeFeat
    | OBJECT LPAREN (formal (COMMA formal)*)? RPAREN COLON TYPE LBRACE expr RBRACE      # methodFeat
    ;

// formal rule; formal = parameter for methodes = object (name of the parameter object) and type
formal
    : OBJECT COLON TYPE
    ;

// rules for expressions
expr
    : expr AT TYPE DOT OBJECT LPAREN (expr (COMMA expr)*)? RPAREN   # staticDispatch
    | expr DOT OBJECT LPAREN (expr (COMMA expr)*)? RPAREN           # explicitDispatch
    | OBJECT LPAREN (expr (COMMA expr)*)? RPAREN                    # implicitDispatch
    | IF expr THEN expr ELSE expr FI                                # ifElse
    | WHILE expr LOOP expr POOL                                     # whileLoop
    | LBRACE (expr SEMI)+ RBRACE                                    # block
    | LET localVar (COMMA localVar)* IN expr                        # let
    | CASE expr OF (OBJECT COLON TYPE ARROW expr SEMI)+ ESAC        # case
    | NEW TYPE                                                      # newObject
    | ISVOID expr                                                   # isVoid
    | op=NEGATION expr                                              # unaryOp
    | expr op=(MULT | DIV) expr                                     # binaryOp
    | expr op=(PLUS | MINUS) expr                                   # binaryOp
    | expr op=(LT | LE | EQUAL) expr                                # binaryOp
    | op=NOT expr                                                   # unaryOp
    | OBJECT ASSIGN expr                                            # assignment
    | LPAREN expr RPAREN                                            # parentheses
    | OBJECT                                                        # objectReference
    | INT                                                           # intLiteral
    | STRING                                                        # stringLiteral
    | TRUE                                                          # trueLiteral
    | FALSE                                                         # falseLiteral
    ;


// rule for local variables (the ones defined in 'let')
localVar
    : OBJECT COLON TYPE (ASSIGN expr)?
    ;

