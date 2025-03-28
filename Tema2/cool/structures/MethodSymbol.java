package cool.structures;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class MethodSymbol extends Symbol implements Scope {
    private Scope parentScope;
    private String returnType;
    private Map<String, Symbol> formals = new LinkedHashMap<>();

    public MethodSymbol(String name, Scope parentScope) {
        super(name);
        this.parentScope = parentScope;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return this.returnType;
    }

    public void addFormal(VariableSymbol f) {
        formals.put(f.getName(), f);
    }

    public Map<String, Symbol> getFormals() {
        return formals;
    }

    @Override
    public Scope getParent() {
        return this.parentScope;
    }

    @Override
    public boolean add(Symbol s) {
        if (this.formals.containsKey(s.getName())) {
            return false;
        }

        this.formals.put(s.getName(), s);
        return true;
    }

    @Override
    public Symbol lookup(String name) {
        Symbol f = this.formals.get(name);
        if (f != null)
            return f;

        if (this.parentScope != null)
            return this.parentScope.lookup(name);

        return null;
    }

    // lookup a formal - only in current scope
    public Symbol lookupFormal(String name, String type) {
        Symbol f = this.formals.get(name);
        // check for both name and type
        if (f instanceof VariableSymbol && ((VariableSymbol) f).getType().equals(type)) {
            return f;
        }

        return null;
    }
}
