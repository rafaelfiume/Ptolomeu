package com.crazymath.algebra.set;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NumericSetTest {

    @Test
    public void builderShouldReturnNaturalSet() {
        assertTrue(new NumericSet.Builder().add(2).add(7).add(3456778).build() instanceof NaturalSet);
    }

    @Test
    public void builderShouldReturnIntegerSet() {
        assertTrue(new NumericSet.Builder().add(-8).add(-4).add(2).build() instanceof IntegerSet);
    }

    @Test
    public void builderShouldReturnRealSet() {
        assertTrue(new NumericSet.Builder().add(-52.453).add(90).add(89789.987).build() instanceof RealSet);
    }

}
