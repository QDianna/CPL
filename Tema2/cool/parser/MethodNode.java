package cool.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

import cool.structures.*;

public class MethodNode extends FeatureNode {
    ObjectNode name;
    TypeNode returnType;
    LinkedList<FormalNode> formals;
    ExpressionNode body;
    ParserRuleContext ctx;
    Scope scope;

    public ObjectNode getName() {
        return name;
    }
    public TypeNode getReturnType() {
        return returnType;
    }
    public ParserRuleContext getContext() {
        return ctx;
    }
    public LinkedList<FormalNode> getFormals() {
        return formals;
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

    MethodNode(ObjectNode name, TypeNode returnType, LinkedList<FormalNode> formals, ExpressionNode body, ParserRuleContext ctx, Token token) {
        super(token);
        this.name = name;
        this.returnType = returnType;
        this.formals = formals;
        this.body = body;
        this.ctx = ctx;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
