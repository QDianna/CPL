package cool.structures;

public class AttributeSymbol extends Symbol {
    private String type;

    public AttributeSymbol(String name, String type) {
        super(name);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
