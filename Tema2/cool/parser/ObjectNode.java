package cool.parser;

import org.antlr.v4.runtime.Token;


public class ObjectNode extends ASTNode {
    String name;

    ObjectNode(String name, Token token) {
        super(token);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
