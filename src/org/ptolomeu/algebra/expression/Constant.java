package org.ptolomeu.algebra.expression;

public class Constant implements MathExp {

    private final Integer value;

    public Constant(Integer value) {
        this.value = value;
    }

    @Override
    public Integer evaluate(Context context) {
        return value;
    }

}
