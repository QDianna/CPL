package cool.parser;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class ExplicitDispatchNode extends ExpressionNode {
    ExpressionNode caller;
    ObjectNode methodName;
    LinkedList<ExpressionNode> arguments;

    ExplicitDispatchNode(ExpressionNode caller, ObjectNode methodName, LinkedList<ExpressionNode> arguments, Token token) {
        super(token);
        this.caller = caller;
        this.methodName = methodName;
        this.arguments = arguments;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
