package org.ptolomeu.algebra.expression.tree;

public class DivisionExp extends AbstractNode {

    public DivisionExp() {
    }
    
    // TODO RF Is this constructor going to be used apart from testing code?
    public DivisionExp(AbstractNode left, AbstractNode right) {
        super(left, right);
    }

    @Override
    public Integer evaluate() {
        return left().evaluate() / right().evaluate();
    }

}