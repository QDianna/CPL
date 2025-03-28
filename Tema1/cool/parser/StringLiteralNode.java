package cool.parser;

import org.antlr.v4.runtime.Token;

public class StringLiteralNode extends ExpressionNode {
    String value;

    StringLiteralNode(String value, Token token) {
        super(token);
        this.value = value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
