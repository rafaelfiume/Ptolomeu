package org.ptolomeu.algebra.expression;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BinaryMathExpressionTest {
    
    //         1 + 9 + 4
    //
    //   root ->   +
    //            / \
    // left ->   +   4   <-right
    //          / \
    //         1   9
     
    @Test
    public void evaluate() {
        AdditionExp left = new AdditionExp(new Constant(1), new Constant(9));
        Constant right = new Constant(4);
        AdditionExp root = new AdditionExp(left, right);
        
        assertThat(root.evaluate(), equalTo(14));
    }

}
