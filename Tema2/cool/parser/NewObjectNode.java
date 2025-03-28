package cool.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class NewObjectNode extends ExpressionNode {
    TypeNode type;
    ParserRuleContext ctx;

    NewObjectNode(TypeNode type,  ParserRuleContext ctx, Token token) {
        super(token);
        this.type = type;
        this.ctx = ctx;
    }

    public TypeNode getType() {
        return type;
    }

    public ParserRuleContext getContext() {
        return ctx;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
