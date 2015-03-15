package org.ptolomeu.algebra.expression;

public class Constant extends AbstractNode {

    private final Integer value;

    public Constant(Integer value) {
        this.value = value;
    }

    @Override
    public Integer evaluate(Context context) {
        return value;
    }

    @Override
    public Integer evaluate() {
        return value;
    }

    @Override
    public void addNode(AbstractNode constant) {
        throw new UnsupportedOperationException("constant is a leaf");
    }

}
