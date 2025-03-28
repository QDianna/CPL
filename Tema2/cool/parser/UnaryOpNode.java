package cool.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class UnaryOpNode extends ExpressionNode {
    ExpressionNode operand;
    Token op;
    ParserRuleContext ctx;

    UnaryOpNode(ExpressionNode operand, Token op, ParserRuleContext ctx, Token token) {
        super(token);
        this.operand = operand;
        this.op = op;
        this.ctx = ctx;
    }

    public ExpressionNode getOperand() {
        return operand;
    }

    public Token getOp() {
        return op;
    }

    public ParserRuleContext getContext() {
        return ctx;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
