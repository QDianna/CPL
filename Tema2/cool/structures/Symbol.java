package cool.structures;

import javax.print.attribute.Attribute;
import java.util.*;

public class Symbol {
    protected String name;
    protected Boolean hasError = false;
    
    public Symbol(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    public Boolean getHasError() {
        return hasError;
    }
    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }
    @Override
    public String toString() {
        return getName();
    }

}

