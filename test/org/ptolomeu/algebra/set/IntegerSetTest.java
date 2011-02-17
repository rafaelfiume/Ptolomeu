package org.ptolomeu.algebra.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IntegerSetTest {

    private final IntegerSet a = IntegerSet.newInstance(-1, -2, 3, 4, 5, 10, 15);

    private final IntegerSet b = IntegerSet.newInstance(-1, -2, 3);

    @Test
    public void testIsEmpty() {
        final IntegerSet empty = IntegerSet.newInstance();
        assertTrue(empty.isEmpty());
        assertFalse(a.isEmpty());
    }

    @Test
    public void testHas() {
        assertTrue(a.has(-1));
        assertFalse(a.has(13));
    }

    @Test
    public void testContains() {
        assertTrue(a.contains(b));
        assertFalse(b.contains(a));
        assertFalse(a.contains(IntegerSet.newInstance(1, 2, 1981)));
    }

    @Test
    public void testUpper() {
        assertEquals(Integer.valueOf(15), a.upper());
    }

    @Test
    public void testLower() {
        assertEquals(Integer.valueOf(-2), a.lower());
    }

    @Test
    public void testEqualsSimetry() {
        final IntegerSet r = IntegerSet.newInstance(10, 110, 1010);
        final IntegerSet s = IntegerSet.newInstance(10, 110, 1010);
        final IntegerSet t = IntegerSet.newInstance(-29);
        assertEquals(r, s);
        assertEquals(s, r);
        assertFalse(r.equals(t));
        assertFalse(t.equals(r));
        assertFalse(s.equals(t));
        assertFalse(t.equals(s));
    }

}
