package cool.parser;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;


public class ProgramNode extends ASTNode {
    LinkedList<ClassDefNode> classes;

    ProgramNode(LinkedList<ClassDefNode> classes, Token token) {
        super(token);
        this.classes = classes;
    }

    public LinkedList<ClassDefNode> getClasses() {
        return classes;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
