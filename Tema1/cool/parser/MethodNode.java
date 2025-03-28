package cool.parser;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;


public class MethodNode extends FeatureNode {
    ObjectNode name;
    TypeNode returnType;
    LinkedList<FormalNode> formals;
    ExpressionNode body;

    MethodNode(ObjectNode name, TypeNode returnType, LinkedList<FormalNode> formals, ExpressionNode body, Token token) {
        super(token);
        this.name = name;
        this.returnType = returnType;
        this.formals = formals;
        this.body = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
