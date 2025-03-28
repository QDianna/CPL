package cool.parser;

import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.sql.PseudoColumnUsage;

public class AssignmentNode extends ExpressionNode {
    ObjectNode name;
    ExpressionNode value;
    Scope scope;

    ParserRuleContext ctx;
    AssignmentNode(ObjectNode name, ExpressionNode value, ParserRuleContext ctx, Token token) {
        super(token);
        this.name = name;
        this.value = value;
        this.ctx = ctx;
    }

    public ObjectNode getName() {
        return name;
    }

    public ExpressionNode getValue() {
        return value;
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
