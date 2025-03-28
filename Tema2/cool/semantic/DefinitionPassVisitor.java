package cool.semantic;

import cool.parser.*;
import cool.structures.*;

public class DefinitionPassVisitor implements ASTVisitor<Void> {
    private Scope currentScope;
    public DefinitionPassVisitor() {
        this.currentScope = SymbolTable.globals;
    }

    /** Program Node **/
    @Override
    public Void visit(ProgramNode programNode) {
        for (ClassDefNode classNode : programNode.getClasses()) {
            classNode.accept(this);
        }

        return null;
    }

    /** Class Node - generates class definition errors **/
    @Override
    public Void visit(ClassDefNode classNode) {
        String className = classNode.getName().getTypeName();
        String parentClassName = classNode.getParent() != null ? classNode.getParent().getTypeName() : null;

        // illegal parent class name check
        if (classNode.getParent() != null) {
            if (parentClassName.equals("Int") ||
                parentClassName.equals("String") ||
                parentClassName.equals("Bool") ||
                parentClassName.equals("SELF_TYPE")) {
                SymbolTable.error(classNode.getContext(), classNode.getParent().getToken(),
                        "Class " + className + " has illegal parent " + parentClassName);
            }
        }

        // illegal class name check
        if (className.equals("SELF_TYPE")) {
            SymbolTable.error(classNode.getContext(), classNode.getName().getToken(),
                    "Class has illegal name SELF_TYPE");
        }

        // add class symbol to global scope
        ClassSymbol classSymbol = new ClassSymbol(className, SymbolTable.globals);
        if (!SymbolTable.globals.add(classSymbol)) {
            SymbolTable.error(classNode.getContext(), classNode.getName().getToken(),
                    "Class " + className + " is redefined");
        }

        // set parent class
        if (parentClassName != null) {
            classSymbol.setParentClassName(parentClassName);
        }

        // set current scope as current class symbol
        currentScope = classSymbol;

        // visit class components - attributes and methods
        for (FeatureNode feature : classNode.getFeatures()) {
            feature.accept(this);
        }

        // return to parent scope
        currentScope = currentScope.getParent();

        return null;
    }

    /** Formal Node - generates formal definition errors **/
    @Override
    public Void visit(FormalNode formalNode) {
        String formalName = formalNode.getName().getName();
        String formalType = formalNode.getType().getTypeName();

        // set formal node's scope to current (method) scope
        formalNode.setScope(currentScope);

        MethodSymbol currentMethod = (MethodSymbol) formalNode.getScope();
        ClassSymbol currentClass = (ClassSymbol) currentMethod.getParent();

        // illegal formal name check
        if (formalName.equals("self")) {
            SymbolTable.error(formalNode.getContext(), formalNode.getName().getToken(),
                    "Method " + currentMethod.getName() + " of class " + currentClass.getName() +
                            " has formal parameter with illegal name self");
            return null;
        }

        // illegal formal type check
        if (formalType.equals("SELF_TYPE")) {
            SymbolTable.error(formalNode.getContext(), formalNode.getType().getToken(),
                    "Method " + currentMethod.getName() + " of class " + currentClass.getName() +
                            " has formal parameter " + formalName + " with illegal type SELF_TYPE");
            return null;
        }


        // add formal symbol to current scope
        VariableSymbol variableSymbol = new VariableSymbol(formalName, formalType);
        if (!currentScope.add(variableSymbol)) {
            SymbolTable.error(formalNode.getContext(), formalNode.getName().getToken(),
                    "Method " + currentMethod.getName() + " of class " + currentClass.getName() +
                            " redefines formal parameter " + formalName);
            return null;
        }

        return null;
    }

    /** Local Variable Node - generates variable definition errors **/
    @Override
    public Void visit(LocalVariableNode localVariableNode) {
        String variableName = localVariableNode.getName().getName();
        String variableType = localVariableNode.getType().getTypeName();
        localVariableNode.setScope(currentScope);

        // illegal variable name check
        if (variableName.equals("self")) {
            SymbolTable.error(localVariableNode.getContext(), localVariableNode.getName().getToken(),
                    "Let variable has illegal name self");
            return null;
        }

        // illegal variable type check
        if (variableType.equals("SELF_TYPE")) {
            SymbolTable.error(localVariableNode.getContext(), localVariableNode.getType().getToken(),
                    "Let variable has illegal type SELF_TYPE");
            return null;
        }

        if (localVariableNode.getInit() != null) {
            localVariableNode.getInit().accept(this);
        }

        return null;
    }

    /** Attribute Node - generates attribute definition errors **/
    @Override
    public Void visit(AttributeNode attributeNode) {
        String attributeName = attributeNode.getName().getName();
        String attributeType = attributeNode.getType().getTypeName();

        // set attribute node's scope to current (class) scope
        attributeNode.setScope(currentScope);
        ClassSymbol currentClass = (ClassSymbol) attributeNode.getScope();

        // illegal attribute name check
        if (attributeName.equals("self")) {
            SymbolTable.error(attributeNode.getContext(), attributeNode.getName().getToken(),
                    "Class " + currentClass.getName() + " has attribute with illegal name self");
            return null;
        }

        // illegal attribute type check
        if (attributeType.equals("SELF_TYPE")) {
            SymbolTable.error(attributeNode.getContext(), attributeNode.getName().getToken(),
                    "Class " + currentClass.getName() + " has attribute with illegal type SELF_TYPE");
            return null;
        }

        // add attribute symbol to current scope
        AttributeSymbol attributeSymbol = new AttributeSymbol(attributeName, attributeType);
        if (!currentScope.add(attributeSymbol)) {
            SymbolTable.error(attributeNode.getContext(), attributeNode.getName().getToken(),
                    "Class " + currentClass.getName() + " redefines attribute " + attributeName);
            return null;
        }

        if (attributeNode.getInit() != null) {
            attributeNode.getInit().accept(this);
        }

        return null;
    }

    /** Method Node - generates method definition errors **/
    @Override
    public Void visit(MethodNode methodNode) {
        String methodName = methodNode.getName().getName();
        String methodReturnType = methodNode.getReturnType().getTypeName();

        // set method's scope to current (class) scope
        methodNode.setScope(currentScope);
        ClassSymbol currentClass = (ClassSymbol) methodNode.getScope();

        // illegal return type check
        if (methodReturnType.equals("SELF_TYPE")) {
            SymbolTable.error(methodNode.getContext(), methodNode.getReturnType().getToken(),
                    "Method " + methodName + " of class " + currentClass.getName() +
                            " has illegal return type SELF_TYPE");
            return null;
        }

        // add method symbol to current scope
        MethodSymbol methodSymbol = new MethodSymbol(methodName, currentScope);
        if (!currentScope.add(methodSymbol)) {
            SymbolTable.error(methodNode.getContext(), methodNode.getName().getToken(),
                    "Class " + currentClass.getName() + " redefines method " + methodName);
            return null;
        }

        methodSymbol.setReturnType(methodReturnType);

        // change current scope as current method symbol
        currentScope = methodSymbol;

        for (FormalNode formal : methodNode.getFormals()) {
            formal.accept(this);
        }

        if (methodNode.getBody() != null) {
            methodNode.getBody().accept(this);
        }

        methodNode.getReturnType().accept(this);

        // return to parent scope
        currentScope = currentScope.getParent();
        return null;
    }

    /** Object Node **/
    @Override
    public Void visit(ObjectNode obj) { return null; }

    /** Type Node **/
    @Override
    public Void visit(TypeNode type) {
        return null;
    }

    /** Assignment Node **/
    @Override
    public Void visit(AssignmentNode node) {

        node.getValue().accept(this);
        node.setScope(currentScope);
        return null;
    }

    /** Static Dispatch Node **/
    @Override
    public Void visit(StaticDispatchNode node) {
        return null;
    }

    /** Explicit Dispatch Node **/
    @Override
    public Void visit(ExplicitDispatchNode node) {
        return null;
    }

    /** Implicit Dispatch Node **/
    @Override
    public Void visit(ImplicitDispatchNode node) {
        return null;
    }

    /** If Node **/
    @Override
    public Void visit(IfNode node) {
        node.getCondition().accept(this);
        node.getThenBranch().accept(this);
        node.getElseBranch().accept(this);
        return null;
    }

    /** While Node **/
    @Override
    public Void visit(WhileNode node) {
        node.getCondition().accept(this);
        node.getBody().accept(this);
        return null;
    }

    /** Block Node **/
    @Override
    public Void visit(BlockNode node) {
        for (var expr : node.getExpressions()) {
            expr.accept(this);
        }
        return null;
    }

    /** Let Node **/
    @Override
    public Void visit(LetNode letNode) {
        letNode.setScope(currentScope);

        // set current scope to current let block
        currentScope = new DefaultScope(currentScope);

        for (LocalVariableNode localVar : letNode.getLocalVariables()) {
            localVar.accept(this);
        }

        letNode.getBody().accept(this);

        // return to parent scope
        currentScope = currentScope.getParent();
        return null;
    }

    /** Case Node **/
    @Override
    public Void visit(CaseNode caseNode) {
        caseNode.getCaseExpr().accept(this);
        for (CaseBranchNode branch : caseNode.getBranches()) {
            branch.accept(this);
        }
        return null;
    }

    /** Case Branch Node **/
    @Override
    public Void visit(CaseBranchNode branchNode) {
        String variableName = branchNode.getName().getName();
        String variableType = branchNode.getType().getTypeName();

        branchNode.setScope(currentScope);

        // set current scope to case branch
        currentScope = new DefaultScope(currentScope);

        // illegal variable name check
        if (variableName.equals("self")) {
            SymbolTable.error(branchNode.getContext(), branchNode.getName().getToken(),
                    "Case variable has illegal name self");
            return null;
        }

        // illegal variable type check
        if (variableType.equals("SELF_TYPE")) {
            SymbolTable.error(branchNode.getContext(), branchNode.getType().getToken(),
                    "Case variable " + variableName + " has illegal type SELF_TYPE");
            return null;

        }

        // add variable to current (case branch) scope
        currentScope.add(new VariableSymbol(variableName, variableType));

        branchNode.getBody().accept(this);

        // return to parent scope
        currentScope = currentScope.getParent();
        return null;
    }

    /** New Object Node **/
    @Override
    public Void visit(NewObjectNode node) {
        return null;
    }

    /** Is Void Node **/
    @Override
    public Void visit(IsVoidNode node) {
        node.getExpression().accept(this);
        return null;
    }

    /** Unary Op Node **/
    @Override
    public Void visit(UnaryOpNode node) {
        node.getOperand().accept(this);
        return null;
    }

    /** Binary Op Node **/
    @Override
    public Void visit(BinaryOpNode node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        return null;
    }

    /** Object Reference Node **/
    @Override
    public Void visit(ObjectReferenceNode node) {
        node.setScope(currentScope);
        return null;
    }

    /** Int Literal Node **/
    @Override
    public Void visit(IntLiteralNode node) {
        node.setExpressionType("Int");
        return null;
    }

    /** String Literal Node **/
    @Override
    public Void visit(StringLiteralNode node) {
        node.setExpressionType("String");
        return null;
    }

    /** True Literal Node **/
    @Override
    public Void visit(TrueLiteralNode node) {
        node.setExpressionType("Bool");
        return null;
    }

    /** False Literal Node **/
    @Override
    public Void visit(FalseLiteralNode node) {
        node.setExpressionType("Bool");
        return null;
    }
}
