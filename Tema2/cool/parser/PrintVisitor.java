package cool.parser;

public class PrintVisitor implements ASTVisitor<Void> {
    private int indent = 0;

    private void printIndent(String str) {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }

        System.out.println(str);
    }


    @Override
    public Void visit(ProgramNode program) {
        printIndent("program");
        indent++;

        for (ClassDefNode cls : program.classes) {
            cls.accept(this);
        }

        indent--;
        return null;
    }


    @Override
    public Void visit(ClassDefNode cls) {
        printIndent("class");
        indent++;

        cls.name.accept(this);
        if (cls.parent != null) {
            cls.parent.accept(this);
        }
        for (FeatureNode feature : cls.features) {
            feature.accept(this);
        }

        indent--;
        return null;
    }


    @Override
    public Void visit(FormalNode formal) {
        printIndent("formal");
        indent++;

        formal.name.accept(this);
        formal.type.accept(this);

        indent--;
        return null;
    }


    @Override
    public Void visit(LocalVariableNode localVar) {
        printIndent("local");
        indent++;

        localVar.name.accept(this);
        localVar.type.accept(this);
        if (localVar.init != null)
            localVar.init.accept(this);

        indent --;
        return null;
    }


    @Override
    public Void visit(AttributeNode attr) {
        printIndent("attribute");
        indent++;

        attr.name.accept(this);
        attr.type.accept(this);
        if (attr.init != null) {
            attr.init.accept(this);
        }

        indent--;
        return null;
    }


    @Override
    public Void visit(MethodNode method) {
        printIndent("method");
        indent++;

        method.name.accept(this);
        for (FormalNode formal : method.formals) {
            formal.accept(this);
        }
        method.returnType.accept(this);
        method.body.accept(this);

        indent--;
        return null;
    }


    @Override
    public Void visit(ObjectNode obj) {
        printIndent(obj.name);
        return null;
    }


    @Override
    public Void visit(TypeNode type) {
        printIndent(type.typeName);
        return null;
    }


    @Override
    public Void visit(AssignmentNode assign) {
        printIndent("<-");
        indent ++;

        assign.name.accept(this);
        assign.value.accept(this);

        indent --;
        return null;
    }


    @Override
    public Void visit(StaticDispatchNode staticDis) {
        printIndent(".");
        indent++;

        staticDis.caller.accept(this);
        staticDis.staticType.accept(this);
        staticDis.methodName.accept(this);
        for (ExpressionNode arg : staticDis.arguments) {
            arg.accept(this);
        }

        indent --;
        return null;
    }


    @Override
    public Void visit(ExplicitDispatchNode explicitDis) {
        printIndent(".");
        indent++;

        explicitDis.caller.accept(this);
        explicitDis.methodName.accept(this);
        for (ExpressionNode arg : explicitDis.arguments){
            arg.accept(this);
        }

        indent--;
        return null;
    }


    @Override
    public Void visit(ImplicitDispatchNode implicitDisp) {
        printIndent("implicit dispatch");
        indent++;

        implicitDisp.methodName.accept(this);
        for (ExpressionNode arg : implicitDisp.arguments){
            arg.accept(this);
        }

        indent--;
        return null;
    }


    @Override
    public Void visit(IfNode iff) {
        printIndent("if");
        indent++;

        iff.condition.accept(this);
        iff.thenBranch.accept(this);
        iff.elseBranch.accept(this);

        indent--;
        return null;
    }


    @Override
    public Void visit(WhileNode whilee) {
        printIndent("while");
        indent++;

        whilee.condition.accept(this);
        whilee.body.accept(this);
        indent--;

        return null;
    }


    @Override
    public Void visit(BlockNode block) {
        printIndent("block");
        indent ++;

        for (ExpressionNode expr : block.expressions)
            expr.accept(this);

        indent--;
        return null;
    }


    @Override
    public Void visit(LetNode lett) {
        printIndent("let");
        indent++;

        for(LocalVariableNode var : lett.localVariables)
            var.accept(this);
        lett.body.accept(this);

        indent --;
        return null;
    }


    @Override
    public Void visit(CaseNode casee) {
        printIndent("case");
        indent ++;

        casee.caseExpr.accept(this);
        for (CaseBranchNode casebr : casee.branches)
            casebr.accept(this);

        indent --;
        return null;
    }


    public Void visit(CaseBranchNode branch) {
        printIndent("case branch");
        indent++;

        printIndent(branch.name.getToken().getText());
        printIndent(branch.type.getToken().getText());
        branch.body.accept(this);

        indent --;
        return null;
    }


    @Override
    public Void visit(NewObjectNode newObj) {
        printIndent("new");
        indent++;

        newObj.type.accept(this);

        indent--;
        return null;
    }


    @Override
    public Void visit(IsVoidNode isVoid) {
        printIndent("isvoid");
        indent++;

        isVoid.expression.accept(this);

        indent--;
        return null;
    }


    @Override
    public Void visit(UnaryOpNode unaryOp) {
        printIndent(unaryOp.op.getText());
        indent ++;

        unaryOp.operand.accept(this);

        indent --;
        return null;
    }


    @Override
    public Void visit(BinaryOpNode binaryOp) {
        printIndent(binaryOp.op.getText());
        indent ++;

        binaryOp.left.accept(this);
        binaryOp.right.accept(this);

        indent --;
        return null;
    }


    @Override
    public Void visit(ObjectReferenceNode obj) {
        printIndent(obj.object.name);
        return null;
    }


    @Override
    public Void visit(IntLiteralNode intLiteral) {
        printIndent("" + intLiteral.value);
        return null;
    }


    @Override
    public Void visit(StringLiteralNode stringLiteral) {
        printIndent(stringLiteral.value);
        return null;
    }


    @Override
    public Void visit(TrueLiteralNode trueLiteral) {
        printIndent(trueLiteral.token.getText());
        return null;
    }


    @Override
    public Void visit(FalseLiteralNode falseLiteral) {
        printIndent(falseLiteral.token.getText());
        return null;
    }

}

