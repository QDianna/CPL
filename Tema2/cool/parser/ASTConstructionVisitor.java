package cool.parser;

import java.util.*;


public class ASTConstructionVisitor extends CoolParserBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        LinkedList<ClassDefNode> classes = new LinkedList<>();

        // visit each class of the program
        for (CoolParser.Class_defContext classDefCtx : ctx.class_def()) {
            classes.add((ClassDefNode) visit(classDefCtx));
        }

        return new ProgramNode(classes, ctx.getStart());
    }


    @Override
    public ASTNode visitClass_def(CoolParser.Class_defContext ctx) {
        TypeNode name = new TypeNode(ctx.TYPE(0).getText(), ctx.TYPE(0).getSymbol());
        TypeNode parent = ctx.TYPE(1) != null
                ? new TypeNode(ctx.TYPE(1).getText(), ctx.TYPE(1).getSymbol())
                : null;

        // visit each feature of the class
        LinkedList<FeatureNode> features = new LinkedList<>();
        for (CoolParser.FeatureContext featureCtx : ctx.feature()) {
            features.add((FeatureNode) visit(featureCtx));
        }

        return new ClassDefNode(name, parent, features, ctx, ctx.getStart());
    }


    @Override
    public ASTNode visitFormal(CoolParser.FormalContext ctx) {
        ObjectNode name = new ObjectNode(ctx.OBJECT().getText(), ctx.OBJECT().getSymbol());
        TypeNode type = new TypeNode(ctx.TYPE().getText(), ctx.TYPE().getSymbol());

        return new FormalNode(name, type, ctx, ctx.getStart());
    }


    @Override
    public LocalVariableNode visitLocalVar(CoolParser.LocalVarContext ctx) {
        ObjectNode name = new ObjectNode(ctx.OBJECT().getText(), ctx.OBJECT().getSymbol());
        TypeNode type = new TypeNode(ctx.TYPE().getText(), ctx.TYPE().getSymbol());
        // extract init value if it exists
        ExpressionNode init = ctx.expr() != null ? (ExpressionNode)visit(ctx.expr()) : null;

        return new LocalVariableNode(name, type, init, ctx, ctx.getStart());

    }


    @Override
    public ASTNode visitAttributeFeat(CoolParser.AttributeFeatContext ctx) {
        ObjectNode name = new ObjectNode(ctx.OBJECT().getText(), ctx.OBJECT().getSymbol());
        TypeNode type = new TypeNode(ctx.TYPE().getText(), ctx.TYPE().getSymbol());
        // extract init value if it exists
        ExpressionNode init = ctx.expr() != null ? (ExpressionNode) visit(ctx.expr()) : null;

        return new AttributeNode(name, type, init, ctx, ctx.getStart());
    }


    @Override
    public ASTNode visitMethodFeat(CoolParser.MethodFeatContext ctx) {
        ObjectNode name = new ObjectNode(ctx.OBJECT().getText(), ctx.OBJECT().getSymbol());
        TypeNode returnType = new TypeNode(ctx.TYPE().getText(), ctx.TYPE().getSymbol());

        // visit all formal parameters of the method
        LinkedList<FormalNode> formals = new LinkedList<>();
        for (CoolParser.FormalContext formalCtx : ctx.formal()) {
            formals.add((FormalNode) visit(formalCtx));
        }

        ExpressionNode body = (ExpressionNode) visit(ctx.expr());

        return new MethodNode(name, returnType, formals, body, ctx, ctx.getStart());
    }


    @Override
    public AssignmentNode visitAssignment(CoolParser.AssignmentContext ctx) {
        ObjectNode object = new ObjectNode(ctx.OBJECT().getText(), ctx.OBJECT().getSymbol());
        ExpressionNode value = (ExpressionNode) visit(ctx.expr());
        return new AssignmentNode(object, value, ctx, ctx.getStart());
    }


    @Override
    public StaticDispatchNode visitStaticDispatch(CoolParser.StaticDispatchContext ctx) {
        // visit caller expression
        ExpressionNode caller = (ExpressionNode) visit(ctx.expr(0));
        TypeNode staticType = new TypeNode(ctx.TYPE().getText(), ctx.TYPE().getSymbol());
        ObjectNode methodName = new ObjectNode(ctx.OBJECT().getText(), ctx.OBJECT().getSymbol());

        // visit argument expressions, if they exist
        LinkedList<ExpressionNode> arguments = new LinkedList<>();
        for (int i = 1; i < ctx.expr().size(); i++) {
            arguments.add((ExpressionNode) visit(ctx.expr(i)));
        }

        return new StaticDispatchNode(caller, staticType, methodName, arguments, ctx.getStart());
    }


    @Override
    public ExplicitDispatchNode visitExplicitDispatch(CoolParser.ExplicitDispatchContext ctx) {
        // visit caller expression
        ExpressionNode caller = (ExpressionNode) visit(ctx.expr(0));
        ObjectNode methodName = new ObjectNode(ctx.OBJECT().getText(), ctx.OBJECT().getSymbol());

        // visit argument expressions, if they exist
        LinkedList<ExpressionNode> arguments = new LinkedList<>();
        for (int i = 1; i < ctx.expr().size(); i++) {
            arguments.add((ExpressionNode) visit(ctx.expr(i)));
        }

        return new ExplicitDispatchNode(caller, methodName, arguments, ctx, ctx.getStart());
    }


    @Override
    public ImplicitDispatchNode visitImplicitDispatch(CoolParser.ImplicitDispatchContext ctx) {
        ObjectNode methodName = new ObjectNode(ctx.OBJECT().getText(), ctx.OBJECT().getSymbol());

        // visit argument expressions, if they exist
        LinkedList<ExpressionNode> arguments = new LinkedList<>();
        if (ctx.expr() != null && ctx.expr().size() != 0) {
            for (CoolParser.ExprContext exprCtx : ctx.expr()) {
                arguments.add((ExpressionNode) visit(exprCtx));
            }
        }

        return new ImplicitDispatchNode(methodName, arguments, ctx.getStart());
    }


    @Override
    public IfNode visitIfElse(CoolParser.IfElseContext ctx) {
        ExpressionNode condition = (ExpressionNode)visit(ctx.expr(0));
        ExpressionNode thenBranch = (ExpressionNode)visit(ctx.expr(1));
        ExpressionNode elseBranch = (ExpressionNode)visit(ctx.expr(2));
        return new IfNode(condition, thenBranch, elseBranch, ctx, ctx.getStart());
    }


    @Override
    public WhileNode visitWhileLoop(CoolParser.WhileLoopContext ctx) {
        ExpressionNode condition = (ExpressionNode)visit(ctx.expr(0));
        ExpressionNode body = (ExpressionNode)visit(ctx.expr(1));
        return new WhileNode(condition, body, ctx, ctx.getStart());
    }


    @Override
    public BlockNode visitBlock(CoolParser.BlockContext ctx) {
        // visit all the expressions from inside the block
        LinkedList<ExpressionNode> expressions = new LinkedList<>();
        for (CoolParser.ExprContext exprCtx : ctx.expr()) {
            expressions.add((ExpressionNode) visit(exprCtx));
        }

        return new BlockNode(expressions, ctx.getStart());
    }


    @Override
    public LetNode visitLet(CoolParser.LetContext ctx) {
        // visit all the local variables declared inside let
        LinkedList<LocalVariableNode> localVariables = new LinkedList<>();
        for (CoolParser.LocalVarContext varCtx : ctx.localVar())
            localVariables.add(visitLocalVar(varCtx));

        ExpressionNode body = (ExpressionNode)visit(ctx.expr());

        return new LetNode(localVariables, body, ctx.getStart());
    }


    @Override
    public CaseNode visitCase(CoolParser.CaseContext ctx) {
        ExpressionNode caseExpr = (ExpressionNode) visit(ctx.expr(0));

        // process each branch of the case
        LinkedList<CaseBranchNode> branches = new LinkedList<>();
        for (int i = 0; i < ctx.OBJECT().size(); i++) {
            ObjectNode name = new ObjectNode(ctx.OBJECT(i).getText(), ctx.OBJECT(i).getSymbol());
            TypeNode type = new TypeNode(ctx.TYPE(i).getText(), ctx.TYPE(i).getSymbol());

            // visit current branch's (i) expression (i + 1)
            ExpressionNode branchExpr = (ExpressionNode)visit(ctx.expr(i + 1));
            // create and add CaseBranchNode
            branches.add(new CaseBranchNode(name, type, branchExpr, ctx, ctx.getStart()));
        }

        return new CaseNode(caseExpr, branches, ctx.getStart());
    }


    @Override
    public NewObjectNode visitNewObject(CoolParser.NewObjectContext ctx) {
        TypeNode type = new TypeNode(ctx.TYPE().getText(), ctx.TYPE().getSymbol());
        return new NewObjectNode(type, ctx, ctx.getStart());
    }


    @Override
    public IsVoidNode visitIsVoid(CoolParser.IsVoidContext ctx) {
        ExpressionNode expression = (ExpressionNode)visit(ctx.expr());
        return new IsVoidNode(expression, ctx.getStart());
    }


    @Override
    public ExpressionNode visitUnaryOp(CoolParser.UnaryOpContext ctx) {
        ExpressionNode expr = (ExpressionNode) visit(ctx.expr());
        return new UnaryOpNode(expr, ctx.op, ctx, ctx.getStart());
    }


    @Override
    public ExpressionNode visitBinaryOp(CoolParser.BinaryOpContext ctx) {
        ExpressionNode left = (ExpressionNode) visit(ctx.expr(0));
        ExpressionNode right = (ExpressionNode) visit(ctx.expr(1));
        return new BinaryOpNode(left, right, ctx.op, ctx, ctx.getStart());
    }


    @Override
    public ObjectReferenceNode visitObjectReference(CoolParser.ObjectReferenceContext ctx) {
        ObjectNode obj = new ObjectNode(ctx.OBJECT().getText(), ctx.OBJECT().getSymbol());
        return new ObjectReferenceNode(obj, ctx, ctx.OBJECT().getSymbol());
    }


    @Override
    public ASTNode visitIntLiteral(CoolParser.IntLiteralContext ctx) {
        int value = Integer.parseInt(ctx.INT().getText());
        return new IntLiteralNode(value, ctx.INT().getSymbol());
    }


    @Override
    public ASTNode visitStringLiteral(CoolParser.StringLiteralContext ctx) {
        String value = ctx.STRING().getText();
        return new StringLiteralNode(value, ctx.STRING().getSymbol());
    }


    @Override
    public ASTNode visitTrueLiteral(CoolParser.TrueLiteralContext ctx) {
        return new TrueLiteralNode(ctx.TRUE().getSymbol());
    }


    @Override
    public ASTNode visitFalseLiteral(CoolParser.FalseLiteralContext ctx) {
        return new FalseLiteralNode(ctx.FALSE().getSymbol());
    }


    @Override
    public ExpressionNode visitParentheses(CoolParser.ParenthesesContext ctx) {
        // no need for a parentheses node since we don't print them,
        // we can only process the expression inside
        return (ExpressionNode)visit(ctx.expr());
    }

}

