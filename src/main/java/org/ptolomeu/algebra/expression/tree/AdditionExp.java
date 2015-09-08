package org.ptolomeu.algebra.expression.tree;

public class AdditionExp extends AbstractNode {

    public AdditionExp() {
    }
    
    // TODO RF Is this constructor going to be used apart from testing code?
    public AdditionExp(AbstractNode left, AbstractNode right) {
        super(left, right);
    }

    @Override
    public Integer evaluate() {
        return left().evaluate() + right().evaluate();
    }

}
