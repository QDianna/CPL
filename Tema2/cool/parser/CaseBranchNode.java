package cool.parser;

import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class CaseBranchNode extends ASTNode {
    ObjectNode name;
    TypeNode type;
    ExpressionNode body;
    ParserRuleContext ctx;
    Scope scope;

    CaseBranchNode(ObjectNode name, TypeNode type, ExpressionNode body, ParserRuleContext ctx, Token token) {
        super(token);
        this.name = name;
        this.type = type;
        this.body = body;
        this.ctx = ctx;
    }

    public ObjectNode getName() {
        return name;
    }

    public TypeNode getType() {
        return type;
    }

    public ExpressionNode getBody() {
        return body;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public ParserRuleContext getContext() {
        return ctx;
    }


    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
