package cool.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class IfNode extends ExpressionNode {
    ExpressionNode condition;
    ExpressionNode thenBranch;
    ExpressionNode elseBranch;
    ParserRuleContext ctx;

    IfNode(ExpressionNode condition, ExpressionNode thenBranch, ExpressionNode elseBranch, ParserRuleContext ctx,Token token) {
        super(token);
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
        this.ctx = ctx;
    }

    public ExpressionNode getCondition() {
        return condition;
    }

    public ExpressionNode getThenBranch() {
        return thenBranch;
    }

    public ExpressionNode getElseBranch() {
        return elseBranch;
    }

    public ParserRuleContext getContext() {
        return ctx;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
