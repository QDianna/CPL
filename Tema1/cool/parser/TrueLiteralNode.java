package cool.parser;

import org.antlr.v4.runtime.Token;

public class TrueLiteralNode extends ExpressionNode {
    TrueLiteralNode(Token token) {
        super(token);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
