package cool.parser;

import org.antlr.v4.runtime.Token;


public class FormalNode extends ASTNode {
    ObjectNode name;
    TypeNode type;

    FormalNode(ObjectNode name, TypeNode type, Token token) {
        super(token);
        this.name = name;
        this.type = type;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
