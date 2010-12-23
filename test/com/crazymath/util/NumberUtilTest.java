package com.crazymath.util; // NOPMD by rafaelfiume on 03/01/11 12:25

import static com.crazymath.util.NumberUtil.greater;
import static com.crazymath.util.NumberUtil.greaterOrEquals;
import static com.crazymath.util.NumberUtil.lesser;
import static com.crazymath.util.NumberUtil.lesserOrEquals;
import static com.crazymath.util.NumberUtil.max;
import static com.crazymath.util.NumberUtil.min;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NumberUtilTest {

    @Test
    public void testEqualsRelationalComparisonBetweenDoublesAsNumbers() {
        assertTrue(NumberUtil.equals(new GenericNumber(-4.23).aNumber,
                new GenericNumber(-4.23).aNumber));
        assertFalse(NumberUtil.equals(new GenericNumber(2d).aNumber,
                new GenericNumber(-4.23).aNumber));
    }

    @Test
    public void testEqualsRelationalComparisonBetweenIntegersAsNumbers() {
        assertTrue(NumberUtil.equals(new GenericNumber(8).aNumber, new GenericNumber(8).aNumber));
        assertFalse(NumberUtil.equals(new GenericNumber(8).aNumber, new GenericNumber(6).aNumber));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void equalsRelationalComparisonBetweenDifferentTypesOfNumbersShouldThrowAnException() {
        assertTrue(NumberUtil.equals(new GenericNumber(-1).aNumber, new GenericNumber(-1d).aNumber));
    }

    @Test
    public void testGreaterRelationalComparisonBetweenDoublesAsNumbers() {
        assertTrue(greater(new GenericNumber(2d).aNumber, new GenericNumber(-4.23).aNumber));
        assertFalse(greater(new GenericNumber(-4.23).aNumber, new GenericNumber(2d).aNumber));
    }

    @Test
    public void testGreaterRelationalComparisonBetweenIntegersAsNumbers() {
        assertTrue(greater(new GenericNumber(8).aNumber, new GenericNumber(6).aNumber));
        assertFalse(greater(new GenericNumber(6).aNumber, new GenericNumber(8).aNumber));
    }

    @Test
    public void testGreaterOrEqualsRelationalComparisonBetweenDoublesAsNumbers() {
        assertTrue(greaterOrEquals(new GenericNumber(2d).aNumber, new GenericNumber(-4.23).aNumber));
        assertFalse(greaterOrEquals(new GenericNumber(-4.23).aNumber, new GenericNumber(2d).aNumber));
        assertTrue(greaterOrEquals(new GenericNumber(-4.23).aNumber,
                new GenericNumber(-4.23).aNumber));
    }

    @Test
    public void testGreaterOrEqualsRelationalComparisonBetweenIntegersAsNumbers() {
        assertTrue(greaterOrEquals(new GenericNumber(8).aNumber, new GenericNumber(6).aNumber));
        assertFalse(greaterOrEquals(new GenericNumber(6).aNumber, new GenericNumber(8).aNumber));
        assertTrue(greaterOrEquals(new GenericNumber(8).aNumber, new GenericNumber(8).aNumber));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void greaterOrEqualsRelationalComparisonBetweenDifferentTypesOfNumbersShouldThrowAnException() {
        assertTrue(greaterOrEquals(new GenericNumber(-1).aNumber, new GenericNumber(-6d).aNumber));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void greaterRelationalComparisonBetweenDifferentTypesOfNumbersShouldThrowAnException() {
        assertTrue(greater(new GenericNumber(-1).aNumber, new GenericNumber(-6d).aNumber));
    }

    @Test
    public void testLesserRelationalComparisonBetweenDoublesAsNumbers() {
        assertTrue(lesser(new GenericNumber(0.1).aNumber, new GenericNumber(21.0).aNumber));
        assertFalse(lesser(new GenericNumber(21.0).aNumber, new GenericNumber(0.1).aNumber));
    }

    @Test
    public void testLesserRelationalComparisonBetweenIntegersAsNumbers() {
        assertTrue(lesser(new GenericNumber(-11).aNumber, new GenericNumber(6).aNumber));
        assertFalse(lesser(new GenericNumber(6).aNumber, new GenericNumber(-11).aNumber));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void lesserRelationalComparisonBetweenDifferentTypesOfNumbersShouldThrowAnException() {
        assertTrue(lesser(new GenericNumber(-1).aNumber, new GenericNumber(6d).aNumber));
    }

    @Test
    public void testLesserOrEqualsRelationalComparisonBetweenDoublesAsNumbers() {
        assertTrue(lesserOrEquals(new GenericNumber(0.1).aNumber, new GenericNumber(21.0).aNumber));
        assertFalse(lesserOrEquals(new GenericNumber(21.0).aNumber, new GenericNumber(0.1).aNumber));
        assertTrue(lesserOrEquals(new GenericNumber(-4.23).aNumber,
                new GenericNumber(-4.23).aNumber));
    }

    @Test
    public void testLesserOrEqualsRelationalComparisonBetweenIntegersAsNumbers() {
        assertTrue(lesserOrEquals(new GenericNumber(-11).aNumber, new GenericNumber(6).aNumber));
        assertFalse(lesserOrEquals(new GenericNumber(6).aNumber, new GenericNumber(-11).aNumber));
        assertTrue(lesserOrEquals(new GenericNumber(8).aNumber, new GenericNumber(8).aNumber));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void lesserOrEqualsRelationalComparisonBetweenDifferentTypesOfNumbersShouldThrowAnException() {
        assertTrue(lesserOrEquals(new GenericNumber(-1).aNumber, new GenericNumber(6d).aNumber));
    }

    @Test
    public void testMax() {
        assertTrue(6 == max(-1, 6));
        assertTrue(6d == max(-1.1, 6d));
        assertTrue(6d == max(null, 6d));
        assertTrue(-1.1 == max(-1.1, null));
        assertTrue(0 == max(0, 0));
    }

    @Test
    public void testMin() {
        assertTrue(-1 == min(-1, 6));
        assertTrue(-1.1 == min(-1.1, 6d));
        assertTrue(6d == min(null, 6d));
        assertTrue(-1.1 == min(-1.1, null));
        assertTrue(0 == min(0, 0));
    }

    private static class GenericNumber<T extends Number> {

        T aNumber;

        GenericNumber(T aNumber) {
            this.aNumber = aNumber;
        }

    }
}
