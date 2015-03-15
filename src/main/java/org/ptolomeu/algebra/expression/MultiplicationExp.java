package org.ptolomeu.algebra.expression;

public class MultiplicationExp implements MathExp {

    // TODO Create abstract class to eliminate repetition
    private final MathExp operand1;

    private final MathExp operand2;

    public MultiplicationExp(MathExp operand1, MathExp operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Integer evaluate(Context context) {
        return operand1.evaluate(context) * operand2.evaluate(context);
    }
    
    @Override
    public Integer evaluate() {
        return operand1.evaluate() * operand2.evaluate();
    }

}
