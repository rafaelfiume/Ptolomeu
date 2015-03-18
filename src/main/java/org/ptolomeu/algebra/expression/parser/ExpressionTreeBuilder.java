package org.ptolomeu.algebra.expression.parser;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

import org.ptolomeu.algebra.expression.tree.*;

public class ExpressionTreeBuilder {
    
    private AbstractNode root;

    public ExpressionTreeBuilder add(Symbol symbol) {
        switch (symbol) {
            case TS_0:
            case TS_1:
            case TS_2:
            case TS_3:
            case TS_4:
            case TS_5:
            case TS_6:
            case TS_7:
            case TS_8:
            case TS_9:
                this.root = addLeaf(symbol);
                break;
            
            case TS_PLUS:
            case TS_MINUS:
                this.root = addSubTree(symbol);
                break;
                
            default:
                throw new UnsupportedOperationException(format("Symbol %s not supported", symbol));
        }
        return this;
    }
    
    public MathExp build() {
        return root;
    }

    private AbstractNode addLeaf(Symbol symbol) {
        Constant constant = new Constant(parseInt(symbol.symbol()));
        
        if (root == null) {    
            return constant;
            
        } else {
            root.addNode(constant);
            return root;
        }        
    }
    
    private AbstractNode addSubTree(Symbol symbol) {
        AbstractNode exp = nodeFor(symbol);
        exp.addNode(root);
        return exp;
    }

    private AbstractNode nodeFor(Symbol symbol) {
        switch(symbol) {
            case TS_PLUS: return new AdditionExp();
            case TS_MINUS: return new SubtractionExp();

            default: throw new IllegalArgumentException(format("unknown expression for symbol %s", symbol));
        }
    }

}
