package cool.parser;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;


public class ClassDefNode extends ASTNode {
    TypeNode name;
    TypeNode parent;
    LinkedList<FeatureNode> features;

    ClassDefNode(TypeNode name, TypeNode parent, LinkedList<FeatureNode> features, Token token) {
        super(token);
        this.name = name;
        this.parent = parent;
        this.features = features;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
