package cool.parser;

import org.antlr.v4.runtime.Token;

// feature = method/attribute
public abstract class FeatureNode extends ASTNode {
    FeatureNode(Token token) {
        super(token);
    }
}
