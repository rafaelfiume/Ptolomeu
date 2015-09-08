package org.ptolomeu.algebra.expression.tree;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class MathExpressionTest {
    
    // Multiplication
    
    @Test
    public void testMultiplicationExpressionWithConstant() {
        final MathExp exp = new MultiplicationExp(new Constant(10), new Constant(3));
        assertSame(30, exp.evaluate());
    }

    @Test
    public void testCompositeMultiplicationExpression() {
        final MathExp exp = new MultiplicationExp(new Constant(5), new AdditionExp(new Constant(2), new Constant(-5)));
        assertSame(-15, exp.evaluate());
    }
    
    // Division
    
    @Test
    public void testDivisionExpressionWithConstant() {
        final MathExp exp = new DivisionExp(new Constant(10), new Constant(2));
        assertSame(5, exp.evaluate());
    }

    @Test
    public void testCompositeDivisionExpression() {
        final MathExp exp = new DivisionExp(new Constant(3), new AdditionExp(new Constant(2), new Constant(-5)));
        assertSame(-1, exp.evaluate());
    }
    
    // Addition
    
    @Test
    public void testAddExpressionWithConstant() {
        final MathExp exp = new AdditionExp(new Constant(2), new Constant(-5));
        assertSame(-3, exp.evaluate());
    }

    @Test
    public void testCompositeAddExpression() {
        final MathExp exp = new AdditionExp(new Constant(19), new AdditionExp(new Constant(2), new Constant(-5)));
        assertSame(16, exp.evaluate());
    }
    
    // Subtraction

    @Test
    public void testSubtractionExpressionWithConstant() {
        final MathExp exp = new SubtractionExp(new Constant(10), new Constant(3));
        assertSame(7, exp.evaluate());
    }

    @Test
    public void testCompositeSubtractionExpression() {
        final MathExp exp = new SubtractionExp(new Constant(5), new AdditionExp(new Constant(2), new Constant(-5)));
        assertSame(8, exp.evaluate());
    }

}
