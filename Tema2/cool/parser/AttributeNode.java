package cool.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import cool.structures.*;

public class AttributeNode extends FeatureNode {
    ObjectNode name;
    TypeNode type;
    ExpressionNode init;
    ParserRuleContext ctx;
    Scope scope;

    AttributeNode(ObjectNode name, TypeNode type, ExpressionNode init, ParserRuleContext ctx, Token token) {
        super(token);
        this.name = name;
        this.type = type;
        this.init = init;
        this.ctx = ctx;
    }

    public ObjectNode getName() {
        return name;
    }

    public TypeNode getType() {
        return type;
    }

    public ExpressionNode getInit() {
        return init;
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
