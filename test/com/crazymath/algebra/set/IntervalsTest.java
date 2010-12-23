package com.crazymath.algebra.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IntervalsTest {

    private final Intervals<Double> aLargeInterval = Intervals.<Double> newInstance()
            .add(-0.345, 1001.234555).add(-987654.8768, -11d).add(-34.5, 1001d).add(2001d, 2002.2);

    private final Intervals<Double> twoIntervals = Intervals.<Double> newInstance().add(-210d, -7d)
            .add(800d, 900d);

    private final Intervals<Double> oneInterval = Intervals.<Double> newInstance().add(-76d, 39d);

    @Test
    public void testIsEmpty() {
        final Intervals<Double> intervals = Intervals.newInstance();
        assertTrue(intervals.isEmpty());
        intervals.add(-1.0, 1.0);
        assertTrue(intervals.isNotEmpty());
    }

    @Test
    public void testLowerElement() {
        assertTrue(-987654.8768 == aLargeInterval.firstLowerEndpoint());
    }

    @Test
    public void testUpperElement() {
        assertTrue(2002.2 == aLargeInterval.lastUpperEndpoint());
    }

    @Test
    public void testIfEnclosesAnElement() {
        assertTrue(oneInterval.enclose(-3d));
        assertFalse(oneInterval.enclose(-77d));
        assertTrue(oneInterval.enclose(2d));
        assertFalse(oneInterval.enclose(100d));
    }

    @Test
    public void testIfEncloseAnotherInterval() {
        assertTrue(aLargeInterval.enclose(Interval.newInstance(-100.2, 9.2)));
        assertFalse(aLargeInterval.enclose(Interval.newInstance(-1003d, 1501d)));
        assertTrue(twoIntervals.enclose(Interval.newInstance(-100.4, -8.67)));
        assertFalse(twoIntervals.enclose(Interval.newInstance(-101d, 14d)));
        assertTrue(oneInterval.enclose(Interval.newInstance(-76d, 39d)));
        assertFalse(oneInterval.enclose(Interval.newInstance(-82d, -80d)));
    }

    @Test
    public void testIfEncloseOtherIntervals() {
        assertTrue(aLargeInterval.enclose(twoIntervals));
        assertFalse(twoIntervals.enclose(aLargeInterval));
    }

    @Test
    public void testAdd() {
        final Intervals<Integer> intervals = Intervals.newInstance();
        assertTrue(intervals.isEmpty());
        intervals.add(1, 1);
        assertFalse(intervals.isEmpty());
        assertEquals("[1, 1]", intervals.toString());
        intervals.add(0, 6);
        assertEquals("[0, 6]", intervals.toString());
        intervals.add(1, 5);
        assertEquals("[0, 6]", intervals.toString());
        intervals.add(-15, -7);
        assertEquals("[-15, -7] & [0, 6]", intervals.toString());
        intervals.add(3, 11);
        assertEquals("[-15, -7] & [0, 11]", intervals.toString());
        intervals.add(-18, -10);
        assertEquals("[-18, -7] & [0, 11]", intervals.toString());
        intervals.add(-22, -21).add(29, 30);
        assertEquals("[-22, -21] & [-18, -7] & [0, 11] & [29, 30]", intervals.toString());
    }

    @Test
    public void testAMoreComplexAddOperationInIntervals() {
        assertEquals(
                "[-987654.0, 2001.5]",
                Intervals.<Double> newInstance().add(-0.1, 1001.0).add(-987654.0, -11.6)
                        .add(-34.5, 2001.5).toString());
    }

    @Test
    public void addOperationsShouldEncloseElementsOfBothIntervals() {
        final Intervals<Double> intervals = Intervals.<Double> newInstance().add(-4.11112, -2.65)
                .add(1d, 6.775);
        assertFalse(intervals.enclose(-4.11113));
        assertFalse(intervals.enclose(-4.1111201));
        assertTrue(intervals.enclose(-4.11112));
        assertTrue(intervals.enclose(-4.111119998));
        assertFalse(intervals.enclose(-1d));
        assertFalse(intervals.enclose(0d));
        assertTrue(intervals.enclose(1d));
        assertTrue(intervals.enclose(2d));
        assertTrue(intervals.enclose(3d));
        assertTrue(intervals.enclose(6d));
        assertTrue(intervals.enclose(6.775));
        assertFalse(intervals.enclose(6.7751));
        assertFalse(intervals.enclose(7d));
    }

    @Test
    public void testIntersect() {
        assertEquals(Intervals.<Double> newInstance().add(-76d, -7d),
                twoIntervals.intersect(oneInterval));

        assertEquals(Intervals.<Double> newInstance().add(-76d, -7d),
                twoIntervals.intersect(oneInterval).intersect(aLargeInterval));
    }

    @Test
    public void testSubtract() {
        final Intervals<Integer> first = Intervals.<Integer> newInstance().add(1, 4).add(6, 20);

        final Intervals<Integer> second = Intervals.<Integer> newInstance().add(13, 16).add(23, 41);

        final Intervals<Integer> firstMinusSecondInterval = Intervals.<Integer> newInstance()
                .add(1, 4).add(6, 13).add(16, 20);

        assertEquals(firstMinusSecondInterval, first.subtract(second));

        final Intervals<Integer> secondMinusFirstInterval = Intervals.<Integer> newInstance().add(
                23, 41);

        assertEquals(secondMinusFirstInterval, second.subtract(first));
    }

    @Test
    public void testSubtractElement() {
        assertEquals(oneInterval, oneInterval.subtract(1000d));
        assertEquals(Intervals.<Double> newInstance().add(-76d, 1d).add(1d, 39d),
                oneInterval.subtract(1d));
    }

    @Test
    public void testEqualsSimetry() {
        // TODO Test a more complex Intervals
        final Intervals<Integer> r = Intervals.<Integer> newInstance().add(0, 21);
        final Intervals<Integer> s = Intervals.<Integer> newInstance().add(0, 21);
        final Intervals<Integer> t = Intervals.<Integer> newInstance().add(-2, 5);
        assertTrue(r.equals(s)); // NOPMD by rafaelfiume on 26/12/10 22:47
        assertTrue(s.equals(r)); // NOPMD by rafaelfiume on 26/12/10 22:47
        assertFalse(r.equals(t));
        assertFalse(t.equals(r));
    }

}
