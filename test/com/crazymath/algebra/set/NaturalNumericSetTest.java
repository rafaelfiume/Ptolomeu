package com.crazymath.algebra.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NaturalNumericSetTest {

    private final NaturalNumericSet a = new NaturalNumericSet(15, 16, 17, 301, 302, 303);

    private final NaturalNumericSet b = new NaturalNumericSet(15, 16, 17, 7856, 10987);

    private final NaturalNumericSet c = new NaturalNumericSet(15, 16, 17);

    @Test(expected = ArithmeticException.class)
    public void naturalNumericSetShouldNotHaveNegativeNumbers() {
        new NaturalNumericSet(-1, -2, 3, 4, 5, 6, 7);
    }

    @Test
    public void testIsEmptyOperation() {
        final NaturalNumericSet empty = new NaturalNumericSet();
        assertTrue(empty.isEmpty());
        empty.add(143, 2);
        assertFalse(empty.isEmpty());
    }

    @Test(expected = ArithmeticException.class)
    public void addShouldThrowExceptionWhenAddingNegativeNumber() {
        final NaturalNumericSet r = new NaturalNumericSet();
        r.add(-14);
    }
    
    @Test
    public void testHasOperation() {
        assertTrue(a.has(15));
        assertFalse(a.has(55));
    }

    @Test
    public void testContainsSetOperation() {
        assertTrue(a.contains(c));
        assertFalse(c.contains(a));
        assertFalse(a.contains(new NaturalNumericSet(1, 2, 1981)));
    }

    @Test
    public void testUnionOperation() {
        assertEquals(new NaturalNumericSet(15, 16, 17, 301, 302, 303, 7856, 10987), a.union(b));
        aRemainsTheSame();
        bRemainsTheSame();
    }

    @Test
    public void testIntersectionOperation() {
        assertEquals(c, a.intersection(b));
        aRemainsTheSame();
        bRemainsTheSame();
    }

    @Test
    public void testSubtractOperation() {
        final NaturalNumericSet empty = new NaturalNumericSet();
        assertEquals(new NaturalNumericSet(301, 302, 303), a.subtract(b));
        assertEquals(new NaturalNumericSet(7856, 10987), b.subtract(a));
        assertEquals(b, b.subtract(empty));
        aRemainsTheSame();
        bRemainsTheSame();
    }

    @Test
    public void testEqualsOperation() {
        final NaturalNumericSet a = new NaturalNumericSet(10, 110, 1010);
        final NaturalNumericSet b = new NaturalNumericSet(10, 110, 1010);
        final NaturalNumericSet c = new NaturalNumericSet(82);
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertFalse(a.equals(c));
        assertFalse(b.equals(c));
        assertFalse(c.equals(b));
    }

    private <T extends Number> boolean aRemainsTheSame() {
        return a.equals(new IntegerNumericSet(-1, -2, 3, 4, 5, 10, 15));
    }
    
    private <T extends Number> boolean bRemainsTheSame() {
        return b.equals(new IntegerNumericSet(-1, -2, 3, 122, 247));
    }
    
}
