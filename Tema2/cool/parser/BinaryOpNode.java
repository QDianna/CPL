package cool.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class BinaryOpNode extends ExpressionNode {
    ExpressionNode left;
    ExpressionNode right;
    ParserRuleContext ctx;
    Token op;

    BinaryOpNode(ExpressionNode left, ExpressionNode right, Token op, ParserRuleContext ctx, Token token) {
        super(token);
        this.left = left;
        this.right = right;
        this.ctx = ctx;
        this.op = op;
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public ExpressionNode getRight() {
        return right;
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
