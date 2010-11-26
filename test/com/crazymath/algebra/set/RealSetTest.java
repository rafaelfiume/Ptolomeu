package com.crazymath.algebra.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RealSetTest {

    private final RealSet a = new RealSet.Builder().add(-465).add(-3.15d).add(Math.PI).add(75)
            .add(2349).build();

    private final RealSet b = new RealSet.Builder().add(-465).add(-3.15d).add(Math.PI).build();

    @Test
    public void testIsEmpty() {
        final RealSet empty = new RealSet.Builder().build();
        assertTrue(empty.isEmpty());
        empty.add(865d);
        empty.add(2.65d);
        assertFalse(empty.isEmpty());
    }

    @Test
    public void testHas() {
        assertTrue(a.has(-3.15));
        assertFalse(a.has(Math.E));
    }

    @Test
    public void testContainsSet() {
        assertTrue(a.contains(b));
        assertFalse(b.contains(a));
        final RealSet d = new RealSet.Builder().add(-31.876).add(65).add(1987).build();
        assertFalse(a.contains(d));
    }

    // TODO testIterator

    @Test
    public void testLower() {
        assertEquals(new Double(-465), a.lower());
    }

    @Test
    public void testEqualsSimetry() {
        final RealSet r = new RealSet.Builder().add(-78234).add(-76.143).add(-90.7865).build();
        final RealSet s = new RealSet.Builder().add(-78234).add(-76.143).add(-90.7865).build();
        final RealSet t = new RealSet.Builder().add(-0.17868658).build();
        assertEquals(r, s);
        assertEquals(s, r);
        assertFalse(r.equals(t));
        assertFalse(t.equals(r));
        assertFalse(s.equals(t));
        assertFalse(t.equals(s));
    }

}
