package cool.parser;

import org.antlr.v4.runtime.Token;


public class TypeNode extends ASTNode {
    String typeName;

    TypeNode(String typeName, Token token) {
        super(token);
        this.typeName = typeName;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
