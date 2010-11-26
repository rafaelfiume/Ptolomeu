package com.crazymath.algebra.set.relation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class OrderedPairTest {

    final OrderedPair op1 = OrderedPair.newInstance(2.0, 3.0);

    final OrderedPair op11 = OrderedPair.newInstance(2, 3);

    final OrderedPair op2 = OrderedPair.newInstance(-32, 456);

    @Test
    public void testCompare() {
        assertSame(op2.compareTo(op1), -1);
        assertSame(op2.compareTo(op11), -1);
        assertSame(op1.compareTo(op11), 0);
        assertSame(op11.compareTo(op1), 0);
        assertSame(op1.compareTo(op2), 1);
        assertSame(op11.compareTo(op2), 1);
    }

    @Test
    public void testEquals() {
        assertEquals(op1, op11);
        assertFalse(op1.equals(op2));
    }

}
