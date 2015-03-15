package org.ptolomeu.algebra.expression.tree;

public interface MathExp {

    Integer evaluate(Context context);
    
    Integer evaluate();

}
