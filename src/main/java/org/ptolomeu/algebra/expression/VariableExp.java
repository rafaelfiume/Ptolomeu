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
    
    @Override
    public Integer evaluate() {
        throw new UnsupportedOperationException("it requires a context");
    }

    String getName() {
        return name;
    }

}
