package cool.parser;

import org.antlr.v4.runtime.Token;

public class UnaryOpNode extends ExpressionNode {
    ExpressionNode operand;
    Token op;

    UnaryOpNode(ExpressionNode operand, Token op, Token token) {
        super(token);
        this.operand = operand;
        this.op = op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
