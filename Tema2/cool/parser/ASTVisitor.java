package cool.parser;

import org.antlr.v4.runtime.*;

public interface ASTVisitor<T> {

    // Definitions
    T visit(ProgramNode program);
    T visit(ClassDefNode cls);
    T visit(FormalNode formal);
    T visit (LocalVariableNode localVar);
    T visit(AttributeNode attr);
    T visit(MethodNode method);
    T visit(ObjectNode obj);
    T visit(TypeNode type);

    // Expressions
    T visit(AssignmentNode node);
    T visit(StaticDispatchNode node);
    T visit(ExplicitDispatchNode node);
    T visit(ImplicitDispatchNode node);
    T visit(IfNode node);
    T visit(WhileNode node);
    T visit(BlockNode node);
    T visit(LetNode node);
    T visit(CaseNode node);
    T visit(CaseBranchNode node);
    T visit(NewObjectNode node);
    T visit(IsVoidNode node);
    T visit(UnaryOpNode node);
    T visit(BinaryOpNode node);
    T visit(ObjectReferenceNode node);
    T visit(IntLiteralNode node);
    T visit(StringLiteralNode node);
    T visit(TrueLiteralNode node);
    T visit(FalseLiteralNode node);

}

