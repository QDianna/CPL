package cool.parser;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.LinkedList;


public abstract class ASTNode {
    protected Token token;

    ASTNode(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public abstract <T> T accept(ASTVisitor<T> visitor);
}


