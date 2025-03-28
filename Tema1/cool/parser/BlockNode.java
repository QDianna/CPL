package cool.parser;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class BlockNode extends ExpressionNode {
    LinkedList<ExpressionNode> expressions;

    BlockNode(LinkedList<ExpressionNode> expressions, Token token) {
        super(token);
        this.expressions = expressions;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
