package cool.parser;

import org.antlr.v4.runtime.Token;

public class IfNode extends ExpressionNode {
    ExpressionNode condition;
    ExpressionNode thenBranch;
    ExpressionNode elseBranch;

    IfNode(ExpressionNode condition, ExpressionNode thenBranch, ExpressionNode elseBranch, Token token) {
        super(token);
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
