package org.ptolomeu.algebra.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.ptolomeu.algebra.AlgebraException;

public class IntervalTest {

    private final Interval<Integer> first = Interval.newInstance(-76, -51);

    private final Interval<Integer> second = Interval.newInstance(-4, -2);

    private final Interval<Double> third = Interval.newInstance(1.18, 6.23);

    private final Interval<Double> fourth = Interval.newInstance(3.89, 8d);

    private final Interval<Integer> selfish = Interval.newInstance(-324, 7387363);

    private final Interval<Integer> anotherFirst = Interval.newInstance(-76, -51);

    private final Interval<Double> firstWithDouble = Interval.newInstance(-76d, -51d);

    @Test(expected = AlgebraException.class)
    public void intervalWithLowerEndpointGreaterThanUpperEndpointShoudThrowAnException() {
        Interval.newInstance(111, 2);
    }

    @Test
    public void testLowerEndpoint() {
        assertTrue(-4 == second.lowerEndpoint());
        assertTrue(1.18 == third.lowerEndpoint());
    }

    @Test
    public void testUpperEndpoint() {
        assertTrue(-2 == second.upperEndpoint());
        assertTrue(6.23 == third.upperEndpoint());
    }

    @Test
    public void testEnclose() {
        assertTrue(selfish.encloses(first));
        assertFalse(first.encloses(second));
    }

    @Test
    public void testIsEnclosedBy() {
        assertTrue(first.isEnclosedBy(selfish));
        assertFalse(first.isEnclosedBy(second));
    }

    @Test
    public void testInterleaves() {
        assertTrue(third.interleavesWith(fourth));
        assertTrue(fourth.interleavesWith(third));
        assertFalse(first.interleavesWith(second));
        assertFalse(second.interleavesWith(first));
        assertFalse(selfish.interleavesWith(first));
        assertFalse(first.interleavesWith(selfish));
    }

    @Test
    public void testCompriseElementsOf() {
        assertTrue(selfish.comprises(first));
        assertTrue(first.comprises(selfish));
        assertTrue(third.comprises(fourth));
        assertTrue(fourth.comprises(third));
        assertFalse(first.comprises(second));
        assertFalse(second.comprises(first));
    }

    @Test
    public void testComparator() {
        assertSame(-1, first.compareTo(second));
        assertSame(1, fourth.compareTo(firstWithDouble));
    }

    @Test(expected = AlgebraException.class)
    public void compareToShouldThrowExceptionForContainedEndpoints() {
        first.compareTo(selfish);
    }

    @Test(expected = AlgebraException.class)
    public void compareToShouldThrowExceptionForContainedEndpoints2() {
        selfish.compareTo(first);
    }

    @Test(expected = AlgebraException.class)
    public void compareToShouldThrowExceptionForInterleavingEndpoints() {
        third.compareTo(fourth);
    }

    @Test(expected = AlgebraException.class)
    public void compareToShouldThrowExceptionForInterleavingEndpoints2() {
        fourth.compareTo(third);
    }

    @Test
    public void testUnion() {
        assertEquals(Interval.newInstance(1.18, 8.0), third.union(fourth));
        assertEquals(Interval.newInstance(1.18, 8.0), fourth.union(third));
        assertEquals(selfish, selfish.union(first));
        assertEquals(selfish, second.union(selfish));
    }

    @Test(expected = AlgebraException.class)
    public void unionShouldFailWhenTwoEndpointsDoesntCompriseElementsInCommom() {
        first.union(second);
    }

    @Test
    public void testIntersection() {
        assertEquals(Interval.newInstance(3.89, 6.23), third.intersect(fourth));
    }

    @Test(expected = AlgebraException.class)
    public void intersectionShouldFailWhenTwoEndpointsDoesntCompriseElementsInCommom() {
        first.intersect(second);
    }

    @Test
    public void testEqualsSimetry() {
        assertTrue(first.equals(anotherFirst));
        assertTrue(anotherFirst.equals(first));
    }

    @Test
    public void testEqualsWithIntegerAndDoubleIntervals() {
        assertFalse(first.equals(firstWithDouble));
    }

}
