package cool.parser;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class CaseNode extends ExpressionNode {
    ExpressionNode caseExpr;
    LinkedList<CaseBranchNode> branches;

    CaseNode(ExpressionNode caseExpr, LinkedList<CaseBranchNode> branches, Token token) {
        super(token);
        this.caseExpr = caseExpr;
        this.branches = branches;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
