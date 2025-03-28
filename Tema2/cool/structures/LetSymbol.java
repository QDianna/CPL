package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class LetSymbol extends Symbol implements Scope {
    private Scope parentScope;
    private Map<String, Symbol> variables = new LinkedHashMap<>();


    public LetSymbol (String name, Scope parentScope) {
        super(name);
        this.parentScope = parentScope;
    }

    @Override
    public Scope getParent() {
        return this.parentScope;
    }

    @Override
    public boolean add(Symbol s) {
        return false;
    }

    @Override
    public Symbol lookup(String name) {
        return null;
    }


}
