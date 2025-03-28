package cool.parser;

import org.antlr.v4.runtime.Token;

public class IsVoidNode extends ExpressionNode {
    ExpressionNode expression;

    IsVoidNode(ExpressionNode expression, Token token) {
        super(token);
        this.expression = expression;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
