package org.ptolomeu.algebra.expression.parser;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

import org.ptolomeu.algebra.expression.tree.*;

public class ExpressionTreeBuilder {
    
    private AbstractNode root;

    public ExpressionTreeBuilder add(Symbol symbol) {      
        if(symbol.isOperation()) {
            this.root = addSubTree(symbol);
        
        } else {
            this.root = addLeaf(symbol);
        }
        
        return this;
    }
        
    public MathExp build() {
        return this.root;
    }

    private AbstractNode addLeaf(Symbol symbol) {
        Constant constant = new Constant(parseInt(symbol.symbol()));
        
        if (this.root == null) {    
            return constant;
            
        } else {
            this.root.addNode(constant);
            return this.root;
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
