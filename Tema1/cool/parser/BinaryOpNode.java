package cool.parser;

import org.antlr.v4.runtime.Token;

public class BinaryOpNode extends ExpressionNode {
    ExpressionNode left;
    ExpressionNode right;
    Token op;

    BinaryOpNode(ExpressionNode left, ExpressionNode right, Token op, Token token) {
        super(token);
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
