package cool.parser;

import org.antlr.v4.runtime.Token;

public class NewObjectNode extends ExpressionNode {
    TypeNode type;

    NewObjectNode(TypeNode type, Token token) {
        super(token);
        this.type = type;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
