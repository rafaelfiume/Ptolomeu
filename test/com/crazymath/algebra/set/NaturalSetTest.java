package com.crazymath.algebra.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NaturalSetTest {

    private final NaturalSet a = NaturalSet.newInstance(15, 16, 17, 301, 302, 303);

    private final NaturalSet b = NaturalSet.newInstance(15, 16, 17);

    @Test(expected = ArithmeticException.class)
    public void naturalNumericSetShouldNotHaveNegativeNumbers() {
        NaturalSet.newInstance(-1, -2, 3, 4, 5, 6, 7);
    }

    @Test
    public void testIsEmpty() {
        final NaturalSet empty = NaturalSet.newInstance();
        assertTrue(empty.isEmpty());
        empty.add(143, 2);
        assertFalse(empty.isEmpty());
    }

    @Test(expected = ArithmeticException.class)
    public void addShouldThrowExceptionWhenAddingNegativeNumber() {
        final NaturalSet r = NaturalSet.newInstance();
        r.add(-14);
    }

    @Test
    public void testHas() {
        assertTrue(a.has(15));
        assertFalse(a.has(55));
    }

    @Test
    public void testContainsSet() {
        assertTrue(a.contains(b));
        assertFalse(b.contains(a));
        assertFalse(a.contains(NaturalSet.newInstance(1, 2, 1981)));
    }

    // TODO Test iterator

    @Test
    public void testLower() {
        assertEquals(Integer.valueOf(15), a.lower());
    }

    @Test
    public void testEqualsSimetry() {
        final NaturalSet a = NaturalSet.newInstance(10, 110, 1010);
        final NaturalSet b = NaturalSet.newInstance(10, 110, 1010);
        final NaturalSet c = NaturalSet.newInstance(82);
        assertEquals(a, b);
        assertEquals(b, a);
        assertFalse(a.equals(c));
        assertFalse(c.equals(a));
        assertFalse(b.equals(c));
        assertFalse(c.equals(b));
    }

}
