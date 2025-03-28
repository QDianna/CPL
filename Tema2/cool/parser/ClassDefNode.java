package cool.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;
import cool.structures.*;

public class ClassDefNode extends ASTNode {
    TypeNode name;
    TypeNode parent;
    LinkedList<FeatureNode> features;
    ParserRuleContext ctx;
    Scope scope;

    ClassDefNode(TypeNode name, TypeNode parent, LinkedList<FeatureNode> features, ParserRuleContext ctx, Token token) {
        super(token);
        this.name = name;
        this.parent = parent;
        this.features = features;
        this.ctx = ctx;
    }

    public TypeNode getName() {
        return name;
    }

    public LinkedList<FeatureNode> getFeatures() {
        return features;
    }

    public ParserRuleContext getContext() {
        return ctx;
    }

    public TypeNode getParent() {
        return parent;
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
