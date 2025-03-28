package cool.parser;

import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;


public class FormalNode extends ASTNode {
    ObjectNode name;
    TypeNode type;
    Scope scope;
    ParserRuleContext ctx;

    FormalNode(ObjectNode name, TypeNode type, ParserRuleContext ctx, Token token) {
        super(token);
        this.name = name;
        this.type = type;
        this.ctx = ctx;
    }

    public ObjectNode getName() {
        return name;
    }

    public TypeNode getType() {
        return type;
    }
    public ParserRuleContext getContext() {
        return ctx;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
