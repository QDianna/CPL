package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClassSymbol extends Symbol implements Scope {
    private Scope parentScope;
    private String parentClassName;
    private ClassSymbol parentClass;
    private Map<String, Symbol> components = new LinkedHashMap<>();


    public ClassSymbol(String name, Scope parentScope) {
        super(name);
        this.parentScope = parentScope;
    }

    public String getParentClassName() {
        return parentClassName;
    }

    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    public ClassSymbol getParentClass() {
        return this.parentClass;
    }
    public void setParentClass(ClassSymbol parentClass) {
        this.parentClass = parentClass;
    }

    public Map<String, Symbol> getComponents() {
        return components;
    }

    @Override
    public Scope getParent() {
        return this.parentScope;
    }

    @Override
    public boolean add(Symbol s) {
        Symbol existingSymbol = this.components.get(s.getName());

        if (existingSymbol != null) {
            boolean areDifferentTypes = (s instanceof MethodSymbol && existingSymbol instanceof AttributeSymbol)
                    || (s instanceof AttributeSymbol && existingSymbol instanceof MethodSymbol);

            if (!areDifferentTypes) {
                return false;
            }
        }

        this.components.put(s.getName(), s);
        return true;
    }

    @Override
    public Symbol lookup(String name) {
        Symbol s = this.components.get(name);
        if (s != null && s.getHasError() == false)
            return s;

        if (this.parentScope != null)
            return this.parentScope.lookup(name);

        return null;
    }

    // lookup an attribute - only in current scope
    public Symbol lookupAttribute(String name, String type) {
        Symbol s = this.components.get(name);
        // check for both name and type
        if (s instanceof AttributeSymbol && ((AttributeSymbol) s).getType().equals(type)) {
            return s;
        }

        return null;
    }
}
