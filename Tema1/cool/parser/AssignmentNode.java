package cool.parser;

import org.antlr.v4.runtime.Token;

public class AssignmentNode extends ExpressionNode {
    ObjectNode name;
    ExpressionNode value;

    AssignmentNode(ObjectNode name, ExpressionNode value, Token token) {
        super(token);
        this.name = name;
        this.value = value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
