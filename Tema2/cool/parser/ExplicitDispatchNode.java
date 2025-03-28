package cool.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class ExplicitDispatchNode extends ExpressionNode {
    ExpressionNode caller;
    ObjectNode methodName;
    ParserRuleContext ctx;
    LinkedList<ExpressionNode> arguments;

    ExplicitDispatchNode(ExpressionNode caller, ObjectNode methodName, LinkedList<ExpressionNode> arguments, ParserRuleContext ctx, Token token) {
        super(token);
        this.caller = caller;
        this.methodName = methodName;
        this.arguments = arguments;
        this.ctx = ctx;
    }

    public ExpressionNode getCaller() {
        return caller;
    }

    public ObjectNode getMethodName() {
        return methodName;
    }

    public LinkedList<ExpressionNode> getArguments() {
        return arguments;
    }

    public ParserRuleContext getContext() {
        return ctx;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
