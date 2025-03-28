package cool.parser;

import org.antlr.v4.runtime.Token;

public class FalseLiteralNode extends ExpressionNode {
    FalseLiteralNode(Token token) {
        super(token);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
