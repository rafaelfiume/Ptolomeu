package com.crazymath.algebra.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RealSetTest {

    // TODO Test with intervals mixed with elements

    private final RealSet a = RealSet.newInstance(-465d, -3.15d, Math.PI, 75d, 2349d);

    private final RealSet b = RealSet.newInstance(-465d, -3.15d, Math.PI);

    private final RealSet c = RealSet.newInstance(-4d, Endpoint.CLOSED, 4d, Endpoint.CLOSED);

    private final RealSet notAnotherC = RealSet.newInstance(-500d, Endpoint.CLOSED, 2350d,
            Endpoint.CLOSED);

    @Test
    public void testIsEmpty() {
        final RealSet empty = RealSet.newInstance();
        assertTrue(empty.isEmpty());
        assertFalse(a.isEmpty());
        assertFalse(c.isEmpty());
    }

    @Test
    public void testHas() {
        assertTrue(a.has(-3.15));
        assertFalse(a.has(Math.E));
        assertTrue(c.has(-4d));
        assertFalse(c.has(-7d));
    }

    @Test
    public void testContains() {
        assertTrue(a.contains(b));
        assertFalse(b.contains(a));
        final RealSet d = new RealSet.Builder().add(-31.876).add(65d).add(1987d).build();
        assertFalse(a.contains(d));
        assertFalse(a.contains(c));
        assertFalse(c.contains(a));
        assertTrue(notAnotherC.contains(c));
        assertFalse(c.contains(notAnotherC));
        assertTrue(notAnotherC.contains(a));
        assertFalse(a.contains(notAnotherC));
    }

    @Test
    public void testLower() {
        assertTrue(-465 == a.lower());
        assertTrue(-4 == c.lower());
    }

    @Test
    public void testUpper() {
        assertTrue(Math.PI == b.upper());
        assertTrue(4.0 == c.upper());
    }

    @Test
    public void testEqualsSimetry() {
        final RealSet r = RealSet.newInstance(-78234d, -76.143, -90.7865);
        final RealSet s = RealSet.newInstance(-78234d, -76.143, -90.7865);
        final RealSet t = RealSet.newInstance(-0.17868658);
        assertEquals(r, s);
        assertEquals(s, r);
        assertFalse(r.equals(t));
        assertFalse(t.equals(r));
        assertFalse(s.equals(t));
        assertFalse(t.equals(s));
    }

    @Test
    public void testEqualsSimetryInIntervals() {
        final RealSet anotherC = RealSet.newInstance(-4d, Endpoint.CLOSED, 4d, Endpoint.CLOSED);
        assertEquals(c, anotherC);
        assertEquals(anotherC, c);
        assertFalse(c.equals(notAnotherC));
        assertFalse(notAnotherC.equals(c));
    }

}
