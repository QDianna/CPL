package cool.parser;

import org.antlr.v4.runtime.Token;

public class LocalVariableNode extends ASTNode {
    ObjectNode name;
    TypeNode type;
    ExpressionNode init;

    LocalVariableNode(ObjectNode name, TypeNode type, ExpressionNode init, Token token) {
        super(token);
        this.name = name;
        this.type = type;
        this.init = init;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
