package cool.structures;

import java.io.File;

import org.antlr.v4.runtime.*;

import cool.compiler.Compiler;
import cool.parser.CoolParser;

public class SymbolTable {
    public static Scope globals;
    
    private static boolean semanticErrors;
    
    public static void defineBasicClasses() {
        globals = new DefaultScope(null);
        semanticErrors = false;
        
        // TODO: Populate global scope.
        /************************ Clasa Object ************************/
        ClassSymbol objectClass = new ClassSymbol("Object", globals);
        globals.add(objectClass);

        // Metodele clasei Object
        MethodSymbol abortMethod = new MethodSymbol("abort", objectClass);
        abortMethod.setReturnType("Object");
        objectClass.add(abortMethod);

        MethodSymbol typeNameMethod = new MethodSymbol("type_name", objectClass);
        typeNameMethod.setReturnType("String");
        objectClass.add(typeNameMethod);

        MethodSymbol copyMethod = new MethodSymbol("copy", objectClass);
        copyMethod.setReturnType("SELF_TYPE");
        objectClass.add(copyMethod);

        /************************ Clasa IO ************************/
        ClassSymbol ioClass = new ClassSymbol("IO", globals);
        globals.add(ioClass);

        // Metodele clasei IO
        MethodSymbol outStringMethod = new MethodSymbol("out_string", ioClass);
        outStringMethod.setReturnType("SELF_TYPE");
        outStringMethod.addFormal(new VariableSymbol("x", "String"));
        ioClass.add(outStringMethod);

        MethodSymbol outIntMethod = new MethodSymbol("out_int", ioClass);
        outIntMethod.setReturnType("SELF_TYPE");
        outIntMethod.addFormal(new VariableSymbol("x", "Int"));
        ioClass.add(outIntMethod);

        MethodSymbol inStringMethod = new MethodSymbol("in_string", ioClass);
        inStringMethod.setReturnType("String");
        ioClass.add(inStringMethod);

        MethodSymbol inIntMethod = new MethodSymbol("in_int", ioClass);
        inIntMethod.setReturnType("Int");
        ioClass.add(inIntMethod);

        /************************ Clasa Int ************************/
        ClassSymbol intClass = new ClassSymbol("Int", globals);
        globals.add(intClass);

        /************************ Clasa Bool ************************/
        ClassSymbol boolClass = new ClassSymbol("Bool", globals);
        globals.add(boolClass);

        /************************ Clasa String ************************/
        ClassSymbol stringClass = new ClassSymbol("String", globals);
        globals.add(stringClass);

        // Metodele clasei String
        MethodSymbol lengthMethod = new MethodSymbol("length", stringClass);
        lengthMethod.setReturnType("Int");
        stringClass.add(lengthMethod);

        MethodSymbol concatMethod = new MethodSymbol("concat", stringClass);
        concatMethod.setReturnType("String");
        concatMethod.addFormal(new VariableSymbol("s", "String"));
        stringClass.add(concatMethod);

        MethodSymbol substrMethod = new MethodSymbol("substr", stringClass);
        substrMethod.setReturnType("String");
        substrMethod.addFormal(new VariableSymbol("i", "Int"));
        substrMethod.addFormal(new VariableSymbol("l", "Int"));
        stringClass.add(substrMethod);
    }

    
    /**
     * Displays a semantic error message.
     * 
     * @param ctx Used to determine the enclosing class context of this error,
     *            which knows the file name in which the class was defined.
     * @param info Used for line and column information.
     * @param str The error message.
     */
    public static void error(ParserRuleContext ctx, Token info, String str) {
        while (! (ctx.getParent() instanceof CoolParser.ProgramContext))
            ctx = ctx.getParent();
        
        String message = "\"" + new File(Compiler.fileNames.get(ctx)).getName()
                + "\", line " + info.getLine()
                + ":" + (info.getCharPositionInLine() + 1)
                + ", Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static void error(String str) {
        String message = "Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static boolean hasSemanticErrors() {
        return semanticErrors;
    }
}
