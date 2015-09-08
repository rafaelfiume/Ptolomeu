package org.ptolomeu.algebra.expression.tree;

public class SubtractionExp extends AbstractNode {

    public SubtractionExp() {
    }

    // TODO RF Is this constructor going to be used apart from testing code?
    public SubtractionExp(AbstractNode left, AbstractNode right) {
        super(left, right);
    }
    
    @Override
    public Integer evaluate() {
        return left().evaluate() - right().evaluate();
    }
    
}
