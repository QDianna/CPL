package cool.parser;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class ImplicitDispatchNode extends ExpressionNode {
    ObjectNode methodName;
    LinkedList<ExpressionNode> arguments;

    ImplicitDispatchNode(ObjectNode methodName, LinkedList<ExpressionNode> arguments, Token token) {
        super(token);
        this.methodName = methodName;
        this.arguments = arguments;
    }

    public ObjectNode getMethodName() {
        return methodName;
    }

    public LinkedList<ExpressionNode> getArguments() {
        return arguments;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
