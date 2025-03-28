package cool.parser;

import org.antlr.v4.runtime.Token;
import java.util.LinkedList;

public class LetNode extends ExpressionNode {
    LinkedList<LocalVariableNode> localVariables;
    ExpressionNode body;

    LetNode(LinkedList<LocalVariableNode> localVariables, ExpressionNode body, Token token) {
        super(token);
        this.localVariables = localVariables;
        this.body = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
