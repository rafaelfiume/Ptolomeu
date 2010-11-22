package com.crazymath.algebra.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.crazymath.algebra.set.IntegerNumericSet;

public class IntegerNumericSetTest {
	
	private final IntegerNumericSet a = new IntegerNumericSet(-1, -2, 3, 4, 5, 10, 15);
	
	private final IntegerNumericSet b = new IntegerNumericSet(-1, -2, 3, 122, 247);
	
	private final IntegerNumericSet c = new IntegerNumericSet(-1, -2, 3);
	
	@Test
	public void testIsEmptyOperation() {
		final IntegerNumericSet empty = new IntegerNumericSet();
		assertTrue(empty.isEmpty());
		empty.add(143, 2);
		assertFalse(a.isEmpty());
	}
	
	@Test
	public void testHasOperation() {
		assertTrue(a.has(-1));
		assertFalse(a.has(13));
	}
	
	@Test
	public void testContainsSetOperation() {
		assertTrue(a.contains(c));
		assertFalse(c.contains(a));
		assertFalse(a.contains(new IntegerNumericSet(1, 2, 1981)));
	}

	@Test
	public void testUnionOperation() {
		assertEquals(new IntegerNumericSet(-1, -2, 3, 4, 5, 10, 15, 122, 247), a.union(b));
		assertTrue(aRemainsTheSame());
		assertTrue(bRemainsTheSame());
	}

	@Test
	public void testIntersectionOperation() {
		assertEquals(c, a.intersection(b));
		assertTrue(aRemainsTheSame());
        assertTrue(bRemainsTheSame());
	}
	
	@Test
	public void testSubtractOperation() {
		final IntegerNumericSet empty = new IntegerNumericSet();
		assertEquals(new IntegerNumericSet(4, 5, 10, 15), a.subtract(b));
		assertEquals(new IntegerNumericSet(122, 247), b.subtract(a));
		assertEquals(b, b.subtract(empty));
		assertEquals(b, b.subtract(empty));
		assertTrue(aRemainsTheSame());
        assertTrue(bRemainsTheSame());
	}
	
	// TODO testIterator
	
	// TODO testLower
	
	@Test
	public void testLowerOperation() {
	    assertEquals(new Integer(-2), a.lower());
	}
	
	@Test
	public void testEqualsOperation() {
		final IntegerNumericSet r = new IntegerNumericSet(10, 110, 1010);
		final IntegerNumericSet s = new IntegerNumericSet(10, 110, 1010);
		final IntegerNumericSet t = new IntegerNumericSet(-29);
		assertTrue(r.equals(s));
		assertTrue(s.equals(r));
		assertFalse(r.equals(t));
		assertFalse(s.equals(t));
		assertFalse(t.equals(s));
	}
	
	private <T extends Number> boolean aRemainsTheSame() {
        return a.equals(new IntegerNumericSet(-1, -2, 3, 4, 5, 10, 15));
    }
	
	private <T extends Number> boolean bRemainsTheSame() {
	    return b.equals(new IntegerNumericSet(-1, -2, 3, 122, 247));
	}

}
