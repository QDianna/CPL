package cool.parser;

import org.antlr.v4.runtime.Token;
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


// feature = method/attribute
abstract class FeatureNode extends ASTNode {
    FeatureNode(Token token) {
        super(token);
    }
}


// expressions
abstract class ExpressionNode extends ASTNode {
    ExpressionNode(Token token) {
        super(token);
    }
}


