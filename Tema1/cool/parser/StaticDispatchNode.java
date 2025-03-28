package cool.parser;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class StaticDispatchNode extends ExpressionNode {
    ExpressionNode caller;
    TypeNode staticType;
    ObjectNode methodName;
    LinkedList<ExpressionNode> arguments;

    StaticDispatchNode(ExpressionNode caller, TypeNode staticType, ObjectNode methodName, LinkedList<ExpressionNode> arguments, Token token) {
        super(token);
        this.caller = caller;
        this.staticType = staticType;
        this.methodName = methodName;
        this.arguments = arguments;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
