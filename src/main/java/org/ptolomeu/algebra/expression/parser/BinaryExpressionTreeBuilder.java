package org.ptolomeu.algebra.expression.parser;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

import org.ptolomeu.algebra.expression.AbstractNode;
import org.ptolomeu.algebra.expression.AdditionExp;
import org.ptolomeu.algebra.expression.Constant;
import org.ptolomeu.algebra.expression.MathExp;

public class BinaryExpressionTreeBuilder {
    
    private AbstractNode root;

    public BinaryExpressionTreeBuilder add(Symbol symbol) {
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
                this.root = addSubTree();
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
    
    private AdditionExp addSubTree() {
        AdditionExp addExp = new AdditionExp();
        addExp.addNode(root);
        return addExp;
    }

}
