package cool.parser;

import org.antlr.v4.runtime.Token;
import java.util.LinkedList;

import cool.structures.*;

public class LetNode extends ExpressionNode {
    LinkedList<LocalVariableNode> localVariables;
    ExpressionNode body;
    Scope scope;

    LetNode(LinkedList<LocalVariableNode> localVariables, ExpressionNode body, Token token) {
        super(token);
        this.localVariables = localVariables;
        this.body = body;
    }

    public LinkedList<LocalVariableNode> getLocalVariables() {
        return localVariables;
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

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
