package org.ptolomeu.algebra.expression;

public abstract class AbstractNode implements MathExp, Node<AbstractNode> {
    
    private AbstractNode left;

    private AbstractNode right;
    
    public AbstractNode() {
    }
    
    public AbstractNode(AbstractNode left, AbstractNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void addNode(AbstractNode node) {
        if (left == null) {
            this.left = node;
        
        } else if (right == null) {
            this.right = node;
        
        } else {
            throw new IllegalStateException("attempt to replace left or right node");
        }      
    }
    
    protected AbstractNode left() {
        return left;
    }
    
    protected AbstractNode right() {
        return right;
    }

}