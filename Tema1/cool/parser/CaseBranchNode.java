package cool.parser;

import org.antlr.v4.runtime.Token;

public class CaseBranchNode extends ASTNode {
    ObjectNode name;
    TypeNode type;
    ExpressionNode body;

    CaseBranchNode(ObjectNode name, TypeNode type, ExpressionNode body, Token token) {
        super(token);
        this.name = name;
        this.type = type;
        this.body = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
