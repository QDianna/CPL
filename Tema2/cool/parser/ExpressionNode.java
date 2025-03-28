package cool.parser;

import org.antlr.v4.runtime.Token;

// expressions
public abstract class ExpressionNode extends ASTNode {
    protected String expressionType;
    ExpressionNode(Token token) {
        super(token);
    }

    public String getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(String type) {
        this.expressionType = type;
    }
}
