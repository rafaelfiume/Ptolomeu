package org.ptolomeu.algebra.expression;

public class AdditionExp implements MathExp {

    private final MathExp operand1;

    private final MathExp operand2;

    public AdditionExp(MathExp operand1, MathExp operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Integer evaluate(Context context) {
        return operand1.evaluate(context) + operand2.evaluate(context);
    }

}
