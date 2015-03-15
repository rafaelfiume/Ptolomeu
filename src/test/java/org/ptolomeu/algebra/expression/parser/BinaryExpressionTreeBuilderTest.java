package org.ptolomeu.algebra.expression.parser;

import static org.junit.Assert.*;
import static org.ptolomeu.algebra.expression.parser.Symbol.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.ptolomeu.algebra.expression.MathExp;

public class BinaryExpressionTreeBuilderTest {
    
    //          1 + 9 + 4
    //
    //   root ->   +
    //            / \
    // left ->   +   4   <-right
    //          / \
    //         1   9

    @Test
    public void buildBinaryExpressionTree() throws Exception {
        MathExp exp = new BinaryExpressionTreeBuilder()
            .add(TS_1)
            .add(TS_PLUS)
            .add(TS_4)
            .add(TS_PLUS)
            .add(TS_9)
            .build();
        
        assertThat(exp.evaluate(), is(14));
    }
}
