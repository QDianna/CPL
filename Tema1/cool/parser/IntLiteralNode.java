package cool.parser;

import org.antlr.v4.runtime.Token;

public class IntLiteralNode extends ExpressionNode {
    int value;

    IntLiteralNode(int value, Token token) {
        super(token);
        this.value = value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
