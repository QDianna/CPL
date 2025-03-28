package cool.semantic;

import cool.parser.*;
import cool.structures.*;

import java.util.*;

public class ResolutionPassVisitor implements ASTVisitor<Void> {
    final private Scope globalScope;

    public ResolutionPassVisitor() {
        this.globalScope = SymbolTable.globals;
    }


    /** this method is used to check for class method overriding differences **/
    private void checkFormals(MethodNode methodNode, MethodSymbol parentMethod, ClassSymbol currentClass) {
        String methodName = methodNode.getName().getName();
        LinkedList<FormalNode> methodFormals = methodNode.getFormals();

        Map<String, Symbol> parentFormalsMap = parentMethod.getFormals();
        LinkedList<Symbol> parentFormals = new LinkedList<>(parentFormalsMap.values());

        // check signature dimension differences
        if (methodFormals.size() != parentFormals.size()) {
            SymbolTable.error(methodNode.getContext(), methodNode.getName().getToken(),
                    "Class " + currentClass.getName() + " overrides method " + methodName +
                            " with different number of formal parameters");
            return;
        }

        for (int i = 0; i < methodFormals.size(); i++) {
            FormalNode formalNode = methodFormals.get(i);
            Symbol parentFormalSymbol = parentFormals.get(i);
            String formalName = formalNode.getName().getName();

            // check formal types differences
            String formalType = formalNode.getType().getTypeName();
            String parentFormalType = ((VariableSymbol) parentFormalSymbol).getType();

            if (!formalType.equals(parentFormalType)) {
                SymbolTable.error(formalNode.getContext(), formalNode.getType().getToken(),
                        "Class " + currentClass.getName() + " overrides method " + methodName +
                                " but changes type of formal parameter " + formalName +
                                " from " + parentFormalType + " to " + formalType);
            }
        }
    }


    /** this method is used to check for declared and actual type compatibility **/
    private boolean checkTypeCompatibility(String declaredType, String assignedType) {
        if (declaredType.equals(assignedType) || declaredType.equals("Object")) {
            return true;
        }

        ClassSymbol declaredClass = (ClassSymbol) globalScope.lookup(declaredType);
        ClassSymbol assignedClass = (ClassSymbol) globalScope.lookup(assignedType);

        if (declaredClass == null || assignedClass == null) {
            return false;
        }

        // check if assigned type is a child of declared type
        while (assignedClass != null) {
            if (assignedClass.getName().equals(declaredType)) {
                return true;
            }

            String parentClassName = assignedClass.getParentClassName();
            if (parentClassName != null) {
                assignedClass = (ClassSymbol) globalScope.lookup(parentClassName);
            } else {
                assignedClass = null;
            }
        }

        return false;
    }


    /** this method is used to find a return type for certain instructions (let, case, if)
     * by finding the first common parent of all types used **/
    private String findReturnType(String type1, String type2) {
        if (type1 == null || type2 == null) {
            return "Object";
        }

        if (type1.equals(type2)) {
            return type1;
        }

        ClassSymbol class1 = (ClassSymbol) globalScope.lookup(type1);
        ClassSymbol class2 = (ClassSymbol) globalScope.lookup(type2);
        if (class1 == null || class2 == null) {
            return null;
        }

        Set<String> ancestors = new HashSet<>();
        while (class1 != null) {
            ancestors.add(class1.getName());
            class1 = (ClassSymbol) globalScope.lookup(class1.getParentClassName());
        }

        while (class2 != null) {
            if (ancestors.contains(class2.getName())) {
                return class2.getName();
            }
            class2 = (ClassSymbol) globalScope.lookup(class2.getParentClassName());
        }

        return "Object";
    }


    /** Program Node **/
    @Override
    public Void visit(ProgramNode programNode) {
        for (ClassDefNode classNode : programNode.getClasses()) {
            classNode.accept(this);
        }
        return null;
    }


    /** Class Node - genereaza erori**/
    @Override
    public Void visit(ClassDefNode classNode) {
        String className = classNode.getName().getTypeName();
        String parentClassName = classNode.getParent() != null ? classNode.getParent().getTypeName() : null;
        ClassSymbol classSymbol = (ClassSymbol)globalScope.lookup(className);

        // undefined parent (type) check
        if (parentClassName != null && globalScope.lookup(parentClassName) == null) {
            SymbolTable.error(classNode.getContext(), classNode.getParent().getToken(),
                    "Class " + className + " has undefined parent " + parentClassName);
        }

        // set parent class
        if (parentClassName != null && globalScope.lookup(parentClassName) != null) {
            classSymbol.setParentClass((ClassSymbol)globalScope.lookup(parentClassName));
        }

        // inheritance cycle check
        Set<String> visitedClasses = new HashSet<>();
        ClassSymbol currentClass = (ClassSymbol)globalScope.lookup(className);

        while (currentClass != null) {
            if (visitedClasses.contains(currentClass.getName())) {
                SymbolTable.error(classNode.getContext(), classNode.getName().getToken(),
                        "Inheritance cycle for class " + currentClass.getName());
                break;
            }

            visitedClasses.add(currentClass.getName());
            currentClass = (ClassSymbol)globalScope.lookup(currentClass.getParentClassName());
        }

        for (FeatureNode feature : classNode.getFeatures()) {
            feature.accept(this);
        }

        return null;
    }


    /** Formal Node **/
    @Override
    public Void visit(FormalNode formalNode) {
        String formalName = formalNode.getName().getName();
        String formalType = formalNode.getType().getTypeName();

        MethodSymbol currentMethod = (MethodSymbol) formalNode.getScope();

        // check if formal is included in scope
        if (currentMethod == null || currentMethod.lookupFormal(formalName, formalType) == null) {
            return null;
        }

        ClassSymbol currentClass = (ClassSymbol) formalNode.getScope().getParent();

        // undefined formal type check
        if (globalScope.lookup(formalType) == null) {
            SymbolTable.error(formalNode.getContext(), formalNode.getType().getToken(),
                    "Method " + currentMethod.getName() + " of class " + currentClass.getName() +
                            " has formal parameter " + formalName + " with undefined type " + formalType);
        }

        return null;
    }


    /** Local Variable Node **/
    @Override
    public Void visit(LocalVariableNode localVar) {
        String varName = localVar.getName().getName();
        String declaredType = localVar.getType().getTypeName();
        String initType = null;

        Scope currentScope = localVar.getScope();

        if (localVar.getInit() != null) {
            localVar.getInit().accept(this);
        }

        // undefined type check
        if (globalScope.lookup(declaredType) == null) {
            SymbolTable.error(localVar.getContext(), localVar.getType().getToken(),
                    "Let variable " + varName + " has undefined type " + declaredType);
            return null;
        }

        // add local variable to scope
        if (!currentScope.add(new VariableSymbol(varName, declaredType))) {
            SymbolTable.error(localVar.getContext(), localVar.getName().getToken(),
                    "Let variable " + varName + " is redefined");
        }

        if (localVar.getInit() != null) {
            initType = localVar.getInit().getExpressionType();
        }

        // check declared and actual type
        if (initType != null && !initType.equals("ErrorType")) {
            if (!checkTypeCompatibility(declaredType, initType)) {
                SymbolTable.error(localVar.getContext(), localVar.getInit().getToken(),
                        "Type " + initType + " of initialization expression of identifier " +
                                varName + " is incompatible with declared type " + declaredType);
            }
        }

        return null;
    }


    /** Attribute Node **/
    @Override
    public Void visit(AttributeNode attributeNode) {
        String attributeName = attributeNode.getName().getName();
        String attributeType = attributeNode.getType().getTypeName();

        ClassSymbol currentClass = (ClassSymbol) attributeNode.getScope();

        // inheritance cycle check
        Set<String> visitedClasses = new HashSet<>();
        visitedClasses.add(currentClass.getName());

        // redefined inherited attribute check
        ClassSymbol parentClass = currentClass.getParentClass();
        while (parentClass != null) {
            // check for inheritance loop
            if (visitedClasses.contains(parentClass.getName())) {
                break;
            }
            visitedClasses.add(parentClass.getName());

            Symbol inheritedAttribute = parentClass.lookup(attributeName);

            if (inheritedAttribute != null) {
                SymbolTable.error(attributeNode.getContext(), attributeNode.getName().getToken(),
                        "Class " + currentClass.getName() + " redefines inherited attribute " + attributeName);
                return null;
            }

            parentClass = parentClass.getParentClass();
        }

        // check if attribute is included in scope
        if (currentClass.lookupAttribute(attributeName, attributeType) == null) {
            return null;
        }

        // undefined attribute type check
        if (globalScope.lookup(attributeType) == null) {
            SymbolTable.error(attributeNode.getContext(), attributeNode.getType().getToken(),
                    "Class " + currentClass.getName() + " has attribute " + attributeName +
                            " with undefined type " + attributeType);
        }

        if (attributeNode.getInit() != null) {
            attributeNode.getInit().accept(this);
            String initType = attributeNode.getInit().getExpressionType();

            // check declared and actual type of init expression
            if (initType != null && !initType.equals("ErrorType") && !checkTypeCompatibility(attributeType, initType)) {
                SymbolTable.error(attributeNode.getContext(), attributeNode.getInit().getToken(),
                        "Type " + initType + " of initialization expression of attribute " + attributeName +
                                " is incompatible with declared type " + attributeType);
            }
        }

        return null;
    }


    /** Method Node **/
    @Override
    public Void visit(MethodNode methodNode) {
        String methodName = methodNode.getName().getName();
        String methodType = methodNode.getReturnType().getTypeName();
        ClassSymbol currentClass = (ClassSymbol) methodNode.getScope();

        // check for overriding method errors
        ClassSymbol parentClass = currentClass.getParentClass();
        while (parentClass != null) {
            MethodSymbol parentMethod = (MethodSymbol) parentClass.lookup(methodName);

            if (parentMethod != null) {
                checkFormals(methodNode, parentMethod, currentClass);

                if (!methodType.equals(parentMethod.getReturnType())) {
                    SymbolTable.error(methodNode.getContext(), methodNode.getReturnType().getToken(),
                            "Class " + currentClass.getName() + " overrides method " + methodName +
                                    " but changes return type from " + parentMethod.getReturnType() +
                                    " to " + methodType);
                }
                break;
            }

            parentClass = parentClass.getParentClass();
        }

        // undefined return type check
        if (globalScope.lookup(methodType) == null) {
            SymbolTable.error(methodNode.getContext(), methodNode.getReturnType().getToken(),
                    "Method " + methodName + " of class " + currentClass.getName() +
                            " has undefined return type " + methodType);
        }

        for (FormalNode formal : methodNode.getFormals()) {
            formal.accept(this);
        }

        methodNode.getBody().accept(this);
        String bodyType = methodNode.getBody().getExpressionType();

        // check declared and actual type of method body
        if (bodyType != null && !bodyType.equals("ErrorType") && !checkTypeCompatibility(methodType, bodyType)) {
            SymbolTable.error(methodNode.getContext(), methodNode.getBody().getToken(),
                    "Type " + bodyType + " of the body of method " + methodName +
                            " is incompatible with declared return type " + methodType);
        }

        return null;
    }


    /** Object Node **/
    @Override
    public Void visit(ObjectNode objNode) {
        return null;
    }


    /** Type Node **/
    @Override
    public Void visit(TypeNode typeNode) {
        return null;
    }


    /** Assignment Node **/
    @Override
    public Void visit(AssignmentNode node) {
        node.getValue().accept(this);
        String identifier = node.getName().getName();
        String assignedType = node.getValue().getExpressionType();
        String declaredType;

        // illegal assigning to self check
        if (identifier.equals("self")) {
            SymbolTable.error(node.getContext(), node.getName().getToken(),
                    "Cannot assign to self");
            node.setExpressionType("ErrorType");
            return null;
        }

        if (assignedType == null || assignedType.equals("ErrorType")) {
            return null;
        }

        Symbol symbol = node.getScope().lookup(identifier);
        if (symbol == null) {
            return null;
        }

        if (symbol instanceof VariableSymbol) {
            declaredType = ((VariableSymbol) symbol).getType();

        } else if (symbol instanceof AttributeSymbol) {
            declaredType = ((AttributeSymbol) symbol).getType();

        } else {
            node.setExpressionType("ErrorType");
            return null;
        }

        if (!checkTypeCompatibility(declaredType, assignedType)) {
            SymbolTable.error(node.getContext(), node.getValue().getToken(),
                    "Type " + assignedType + " of assigned expression is incompatible with declared type " +
                            declaredType + " of identifier " + identifier);
            node.setExpressionType("ErrorType");
            return null;
        }

        node.setExpressionType(declaredType);
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
        String conditionType = node.getCondition().getExpressionType();

        if (conditionType == null || conditionType.equals("ErrorType")) {
            node.setExpressionType("ErrorType");
            return null;
        }

        if (!conditionType.equals("Bool")) {
            SymbolTable.error(node.getContext(), node.getCondition().getToken(),
                    "If condition has type " + conditionType + " instead of Bool");
        }

        node.getThenBranch().accept(this);
        String thenType = node.getThenBranch().getExpressionType();

        node.getElseBranch().accept(this);
        String elseType = node.getElseBranch().getExpressionType();

        String ifType = findReturnType(thenType, elseType);
        node.setExpressionType(Objects.requireNonNullElse(ifType, "ErrorType"));

        return null;
    }

    /** While Node **/
    @Override
    public Void visit(WhileNode node) {
        node.getCondition().accept(this);
        String conditionType = node.getCondition().getExpressionType();

        if (conditionType == null || conditionType.equals("ErrorType")) {
            return null;
        }

        if (!conditionType.equals("Bool")) {
            SymbolTable.error(node.getContext(), node.getCondition().getToken(),
                    "While condition has type " + conditionType + " instead of Bool");
        }

        node.getBody().accept(this);

        node.setExpressionType("Object");

        return null;
    }


    /** Block Node **/
    @Override
    public Void visit(BlockNode node) {
        for (var expr : node.getExpressions()) {
            expr.accept(this);
            // block's type will be the last expression's type
            node.setExpressionType(expr.getExpressionType());
        }

        return null;
    }

    /** Let Node **/
    @Override
    public Void visit(LetNode letNode) {
        for (LocalVariableNode localVar : letNode.getLocalVariables()) {
            localVar.accept(this);
        }

        letNode.getBody().accept(this);

        letNode.setExpressionType(letNode.getBody().getExpressionType());

        return null;
    }

    /** Case Node **/
    @Override
    public Void visit(CaseNode node) {
        node.getCaseExpr().accept(this);

        String caseType = null;

        for (var branch : node.getBranches()) {
            branch.accept(this);
            String branchType = branch.getBody().getExpressionType();


            if (caseType == null) {
                caseType = branchType;
            }
            if (branchType != null && !branchType.equals("ErrorType")) {
                caseType = findReturnType(caseType, branchType);
            } else {
                caseType = "ErrorType";
            }
        }

        node.setExpressionType(Objects.requireNonNullElse(caseType, "ErrorType"));

        return null;
    }


    /** Case Branch Node **/
    @Override
    public Void visit(CaseBranchNode caseBranch) {
        String variableName = caseBranch.getName().getName();
        String variableType = caseBranch.getType().getTypeName();

        // variable undefined type check
        if (globalScope.lookup(variableType) == null && !variableType.equals("SELF_TYPE")) {
            SymbolTable.error(caseBranch.getContext(), caseBranch.getType().getToken(),
                    "Case variable " + variableName + " has undefined type " + variableType);
        }

        caseBranch.getBody().accept(this);

        return null;
    }

    /** New Object Node **/
    @Override
    public Void visit(NewObjectNode node) {
        String typeName = node.getType().getTypeName();
        // new object undefined type check
        if (globalScope.lookup(typeName) == null) {
            SymbolTable.error(node.getContext(), node.getType().getToken(),
                    "new is used with undefined type " + typeName);
            node.setExpressionType("ErrorType");

        } else {
            node.setExpressionType(typeName);
        }

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

        String operandType = node.getOperand().getExpressionType();
        String operator = node.getOp().getText();

        if (operandType == null || operandType.equals("ErrorType")) {
            node.setExpressionType("ErrorType");
            return null;
        }

        switch (operator) {
            case "not":
                if (!operandType.equals("Bool")) {
                    SymbolTable.error(node.getContext(), node.getOperand().getToken(),
                            "Operand of " + operator + " has type " + operandType + " instead of Bool");
                    node.setExpressionType("ErrorType");
                } else {
                    node.setExpressionType("Bool");
                }
                break;

            case "~":
                if (!operandType.equals("Int")) {
                    SymbolTable.error(node.getContext(), node.getOperand().getToken(),
                            "Operand of " + operator + " has type " + operandType + " instead of Int");
                    node.setExpressionType("ErrorType");
                } else {
                    node.setExpressionType("Int");
                }
                break;

            default:
                node.setExpressionType("ErrorType");
        }

        return null;
    }

    /** Binary Op Node **/
    @Override
    public Void visit(BinaryOpNode node) {
        node.getLeft().accept(this);
        String leftType = node.getLeft().getExpressionType();

        node.getRight().accept(this);
        String rightType = node.getRight().getExpressionType();

        String operator = node.getOp().getText();

        if (leftType == null) {
            return null;
        }

        if (rightType == null) {
            return null;
        }

        if (leftType.equals("ErrorType")) {
            // check only right
            if (!"Int".equals(rightType)) {
                SymbolTable.error(node.getContext(), node.getRight().getToken(),
                        "Operand of " + operator + " has type " + rightType + " instead of Int");
            }
            return null;
        }

        if (rightType.equals("ErrorType")) {
            // check only left
            if (!"Int".equals(leftType)) {
                SymbolTable.error(node.getContext(), node.getLeft().getToken(),
                        "Operand of " + operator + " has type " + leftType + " instead of Int");
            }
            return null;
        }

        switch (operator) {
            case "+":
            case "-":
            case "*":
            case "/":
                if (!leftType.equals("Int")) {
                    SymbolTable.error(node.getContext(), node.getLeft().getToken(),
                            "Operand of " + operator + " has type " + leftType + " instead of Int");
                    node.setExpressionType("ErrorType");
                }
                else if (!rightType.equals("Int")) {
                    SymbolTable.error(node.getContext(), node.getRight().getToken(),
                            "Operand of " + operator + " has type " + leftType + " instead of Int");
                    node.setExpressionType("ErrorType");
                } else {
                    node.setExpressionType("Int");
                }
                break;

            case "<":
            case "<=":
                if (!leftType.equals("Int")) {
                    SymbolTable.error(node.getContext(), node.getLeft().getToken(),
                            "Operand of " + operator + " has type " + leftType + " instead of Int");
                    node.setExpressionType("ErrorType");
                }
                else if (!rightType.equals("Int")) {
                    SymbolTable.error(node.getContext(), node.getRight().getToken(),
                            "Operand of " + operator + " has type " + rightType + " instead of Int");
                    node.setExpressionType("ErrorType");
                } else {
                    node.setExpressionType("Bool");
                }
                break;

            case "=":
                if (leftType.equals("Int") || leftType.equals("String") || leftType.equals("Bool")) {
                    if (!leftType.equals(rightType)) {
                        SymbolTable.error(node.getContext(), node.getOp(),
                                "Cannot compare " + leftType + " with " + rightType);
                        node.setExpressionType("ErrorType");
                    } else {
                        node.setExpressionType("Bool");
                    }
                } else {
                    node.setExpressionType("Bool");
                }
                break;

            default:
                SymbolTable.error(node.getContext(), node.getOp(),
                        "Unknown operator " + operator);
                node.setExpressionType("ErrorType");
        }

        return null;
    }

    /** Object Reference Node **/
    @Override
    public Void visit(ObjectReferenceNode node) {
        String varName = node.getObject().getName();
        Scope currentScope = node.getScope();

        if (currentScope == null || currentScope.lookup(varName) == null && !varName.equals("self")) {
            SymbolTable.error(node.getContext(), node.getObject().getToken(), "Undefined identifier " + varName);
            return null;
        }

        Symbol symbol = currentScope.lookup(varName);
        if (symbol instanceof VariableSymbol) {
            node.setExpressionType(((VariableSymbol) symbol).getType());
        }

        else if (symbol instanceof AttributeSymbol) {
            node.setExpressionType(((AttributeSymbol) symbol).getType());
        }

        else {
            node.setExpressionType("ErrorType");
        }

        return null;
    }

    /** Int Literal Node **/
    @Override
    public Void visit(IntLiteralNode node) {
        return null;
    }

    /** String Literal Node **/
    @Override
    public Void visit(StringLiteralNode node) {
        return null;
    }

    /** True Literal Node **/
    @Override
    public Void visit(TrueLiteralNode node) {
        return null;
    }

    /** False Literal Node **/
    @Override
    public Void visit(FalseLiteralNode node) {
        return null;
    }
}


