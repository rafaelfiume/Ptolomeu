package org.ptolomeu.algebra.expression.tree;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import org.ptolomeu.algebra.expression.tree.Constant;
import org.ptolomeu.algebra.expression.tree.Context;
import org.ptolomeu.algebra.expression.tree.MultiplicationExp;
import org.ptolomeu.algebra.expression.tree.SubtractionExp;
import org.ptolomeu.algebra.expression.tree.VariableExp;

public class VariableExpressionTest {

    private VariableExp a;

    private VariableExp b;

    private VariableExp c;

    private Context context;

    @Before
    public void setUp() {
        a = new VariableExp("a");
        b = new VariableExp("b");
        c = new VariableExp("c");

        context = new Context();
        context.assign(a, 2);
        context.assign(b, 4);
        context.assign(c, 10);
    }
    
    // TODO RF Uncomment these tests when implementing parsing subtraction and multiplication

//    @Test
//    public void testAddExpression() {
//        final AdditionExp add = new AdditionExp(a, b);
//        final Integer result = add.evaluate(context);
//        assertSame(6, result);
//    }

//    @Test
//    public void testAddExpressionWithConstant() {
//        final AdditionExp add = new AdditionExp(a, new Constant(-5));
//        final Integer result = add.evaluate(context);
//        assertSame(-3, result);
//    }

//    @Test
//    public void testCompositeAddExpression() {
//        final AdditionExp add = new AdditionExp(a, new AdditionExp(b, c));
//        final Integer result = add.evaluate(context);
//        assertSame(16, result);
//    }

    @Test
    public void testSubtractionExpression() {
        final SubtractionExp add = new SubtractionExp(a, b);
        final Integer result = add.evaluate(context);
        assertSame(-2, result);
    }

    @Test
    public void testSubtractionExpressionWithConstant() {
        final SubtractionExp add = new SubtractionExp(a, new Constant(-5));
        final Integer result = add.evaluate(context);
        assertSame(7, result);
    }

    @Test
    public void testCompositeSubtractionExpression() {
        final SubtractionExp add = new SubtractionExp(a, new SubtractionExp(b, c));
        final Integer result = add.evaluate(context);
        assertSame(8, result);
    }

    @Test
    public void testMultiplicationExpression() {
        final MultiplicationExp add = new MultiplicationExp(a, b);
        final Integer result = add.evaluate(context);
        assertSame(8, result);
    }

    @Test
    public void testMultiplicationExpressionWithConstant() {
        final MultiplicationExp add = new MultiplicationExp(a, new Constant(-5));
        final Integer result = add.evaluate(context);
        assertSame(-10, result);
    }

    @Test
    public void testCompositeMultiplicationExpression() {
        final MultiplicationExp add = new MultiplicationExp(a, new MultiplicationExp(b, c));
        final Integer result = add.evaluate(context);
        assertSame(80, result);
    }

//    @Test
//    public void testAMoreComplexExpression() {
//        final AdditionExp moreComplexExpression = new AdditionExp(new MultiplicationExp(a, c),
//                new SubtractionExp(b, c));
//        final Integer result = moreComplexExpression.evaluate(context);
//        assertSame(14, result);
//    }

}
