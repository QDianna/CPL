package cool.parser;

import org.antlr.v4.runtime.Token;

public class WhileNode extends ExpressionNode {
    ExpressionNode condition;
    ExpressionNode body;

    WhileNode(ExpressionNode condition, ExpressionNode body, Token token) {
        super(token);
        this.condition = condition;
        this.body = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
