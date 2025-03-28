package cool.parser;

import org.antlr.v4.runtime.Token;

public class ObjectReferenceNode extends ExpressionNode {
    ObjectNode object;

    ObjectReferenceNode(ObjectNode object, Token token) {
        super(token);
        this.object = object;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
