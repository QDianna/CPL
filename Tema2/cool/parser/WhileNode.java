package cool.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class WhileNode extends ExpressionNode {
    ExpressionNode condition;
    ExpressionNode body;
    ParserRuleContext ctx;

    WhileNode(ExpressionNode condition, ExpressionNode body, ParserRuleContext ctx, Token token) {
        super(token);
        this.condition = condition;
        this.body = body;
        this.ctx = ctx;
    }

    public ExpressionNode getCondition() {
        return condition;
    }

    public ExpressionNode getBody() {
        return body;
    }

    public ParserRuleContext getContext() {
        return ctx;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
