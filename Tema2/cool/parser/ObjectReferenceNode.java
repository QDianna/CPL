package cool.parser;

import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class ObjectReferenceNode extends ExpressionNode {
    ObjectNode object;
    ParserRuleContext ctx;
    Scope scope;


    ObjectReferenceNode(ObjectNode object, ParserRuleContext ctx, Token token) {
        super(token);
        this.object = object;
        this.ctx = ctx;
    }

    public ObjectNode getObject() {
        return object;
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
