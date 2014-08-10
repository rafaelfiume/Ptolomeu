package org.ptolomeu.algebra.expression;

public class VariableExp implements MathExp {

    private final String name;

    public VariableExp(String name) {
        this.name = name;
    }

    @Override
    public Integer evaluate(Context context) {
        return context.lookup(name);
    }

    String getName() {
        return name;
    }

}
