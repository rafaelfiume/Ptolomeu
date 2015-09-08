package org.ptolomeu.algebra.expression.parser;

import static java.lang.Integer.parseInt;

import org.ptolomeu.algebra.expression.tree.AbstractNode;
import org.ptolomeu.algebra.expression.tree.Constant;
import org.ptolomeu.algebra.expression.tree.MathExp;

public class ExpressionTreeBuilder {
    
    private AbstractNode root;

    public ExpressionTreeBuilder add(Symbol symbol) {      
        if(symbol.isOperator()) {
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
        AbstractNode tree = symbol.operator();
        tree.addNode(root);
        return tree;
    }

}
