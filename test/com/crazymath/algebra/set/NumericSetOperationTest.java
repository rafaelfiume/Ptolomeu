package com.crazymath.algebra.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.crazymath.algebra.set.relation.BinaryRelation;
import com.crazymath.algebra.set.relation.CartesianProduct;
import com.crazymath.algebra.set.relation.OrderedPair;

public class NumericSetOperationTest {

    private final NaturalSet nA = NaturalSet.newInstance(15, 16, 17, 301, 302, 303);

    private final NaturalSet nB = NaturalSet.newInstance(15, 16, 17, 7856, 10987);

    private final NaturalSet nC = NaturalSet.newInstance(15, 16, 17);

    private final NaturalSet nD = NaturalSet.newInstance(3, 2);

    private final IntegerSet iA = IntegerSet.newInstance(-1, -2, 3, 4, 5, 10, 15);

    private final IntegerSet iB = IntegerSet.newInstance(-1, -2, 3, 122, 247);

    private final IntegerSet iC = IntegerSet.newInstance(-1, -2, 3);

    private final IntegerSet iD = IntegerSet.newInstance(-9, 0, 89);

    private final RealSet rA = new RealSet.Builder().add(-465).add(-3.15d).add(Math.PI).add(75)
            .add(2349).build();

    private final RealSet rB = new RealSet.Builder().add(-465).add(-3.15d).add(Math.PI).add(533.90)
            .build();

    private final RealSet rC = new RealSet.Builder().add(-465).add(-3.15d).add(Math.PI).build();

    @Test
    public void testUnionOfNaturalSets() {
        assertEquals(NaturalSet.newInstance(15, 16, 17, 301, 302, 303, 7856, 10987),
                NumericSetOperation.union(nA, nB));
        naRemainsTheSame();
        nbRemainsTheSame();
    }

    @Test
    public void testIntersectionOfNaturalSets() {
        assertEquals(nC, NumericSetOperation.intersection(nA, nB));
        naRemainsTheSame();
        nbRemainsTheSame();
    }

    @Test
    public void testSubtractOfNaturalSets() {
        final NaturalSet empty = NaturalSet.newInstance();
        assertEquals(NaturalSet.newInstance(301, 302, 303), NumericSetOperation.subtract(nA, nB));
        assertEquals(NaturalSet.newInstance(7856, 10987), NumericSetOperation.subtract(nB, nA));
        assertEquals(nB, NumericSetOperation.subtract(nB, empty));
        naRemainsTheSame();
        nbRemainsTheSame();
    }

    @Test
    public void testUnionOfIntegerSets() {
        assertEquals(IntegerSet.newInstance(-1, -2, 3, 4, 5, 10, 15, 122, 247),
                NumericSetOperation.union(iA, iB));
        assertTrue(iaRemainsTheSame());
        assertTrue(ibRemainsTheSame());
    }

    @Test
    public void testIntersectionOfIntegerSets() {
        assertEquals(iC, NumericSetOperation.intersection(iA, iB));
        assertTrue(iaRemainsTheSame());
        assertTrue(ibRemainsTheSame());
    }

    @Test
    public void testSubtractOfIntegerSets() {
        final IntegerSet empty = IntegerSet.newInstance();
        assertEquals(NaturalSet.newInstance(4, 5, 10, 15), NumericSetOperation.subtract(iA, iB));
        assertEquals(NaturalSet.newInstance(122, 247), NumericSetOperation.subtract(iB, iA));
        assertEquals(iB, NumericSetOperation.subtract(iB, empty));
        assertTrue(iaRemainsTheSame());
        assertTrue(ibRemainsTheSame());
    }

    @Test
    public void testUnionOfRealSets() {
        final RealSet unionOfAandB = new RealSet.Builder().add(-465).add(-3.15).add(Math.PI)
                .add(75).add(2349).add(533.90).build();
        assertEquals(unionOfAandB, NumericSetOperation.union(rA, rB));
        assertTrue(raRemainsTheSame());
        assertTrue(rbRemainsTheSame());
    }

    @Test
    public void testIntersectionOfRealSets() {
        assertEquals(rC, NumericSetOperation.intersection(rA, rB));
        assertTrue(raRemainsTheSame());
        assertTrue(rbRemainsTheSame());
    }

    @Test
    public void testSubtractOfRealSets() {
        final RealSet empty = new RealSet.Builder().build();
        assertEquals(new RealSet.Builder().add(75).add(2349).build(),
                NumericSetOperation.subtract(rA, rB));
        assertEquals(new RealSet.Builder().add(533.90).build(),
                NumericSetOperation.subtract(rB, rA));
        assertEquals(rB, NumericSetOperation.subtract(rB, empty));
        assertTrue(raRemainsTheSame());
        assertTrue(rbRemainsTheSame());
    }

    @Test
    public void testCartesianProductOfAxB() {
        final CartesianProduct cpAxB = NumericSetOperation.cartesianProduct(nD, iD);
        assertSame(6, cpAxB.numberOfOrderedPairs());
        final List<OrderedPair> orderedPairs = cpAxB.orderedPairAsList();
        assertEquals(OrderedPair.newInstance(2, -9), orderedPairs.get(0));
        assertEquals(OrderedPair.newInstance(2, 0), orderedPairs.get(1));
        assertEquals(OrderedPair.newInstance(2, 89), orderedPairs.get(2));
        assertEquals(OrderedPair.newInstance(3, -9), orderedPairs.get(3));
        assertEquals(OrderedPair.newInstance(3, 0), orderedPairs.get(4));
        assertEquals(OrderedPair.newInstance(3, 89), orderedPairs.get(5));
    }

    @Test
    public void testCartesianProductOfBxA() {
        final CartesianProduct cpBxA = NumericSetOperation.cartesianProduct(iD, nD);
        assertSame(6, cpBxA.numberOfOrderedPairs());
        final Set<OrderedPair> orderedPairs = cpBxA.orderedPair();
        final OrderedPair[] opa = orderedPairs.toArray(new OrderedPair[] {});
        assertEquals(OrderedPair.newInstance(-9, 2), opa[0]);
        assertEquals(OrderedPair.newInstance(-9, 3), opa[1]);
        assertEquals(OrderedPair.newInstance(0, 2), opa[2]);
        assertEquals(OrderedPair.newInstance(0, 3), opa[3]);
        assertEquals(OrderedPair.newInstance(89, 2), opa[4]);
        assertEquals(OrderedPair.newInstance(89, 3), opa[5]);
    }

    @Test()
    public void testBinaryRelation() {
        // A is divisor of B
        final BinaryRelation br = NumericSetOperation.binaryRelation(NaturalSet.newInstance(3, 2),
                NaturalSet.newInstance(1, 2, 3, 4, 5, 10, 12), "A | B");
        assertSame(6, br.numberOfOrderedPairs());
        final List<OrderedPair> orderedPairs = br.orderedPairAsList();
        assertEquals(OrderedPair.newInstance(2, 2), orderedPairs.get(0));
        assertEquals(OrderedPair.newInstance(2, 4), orderedPairs.get(1));
        assertEquals(OrderedPair.newInstance(2, 10), orderedPairs.get(2));
        assertEquals(OrderedPair.newInstance(2, 12), orderedPairs.get(3));
        assertEquals(OrderedPair.newInstance(3, 3), orderedPairs.get(4));
        assertEquals(OrderedPair.newInstance(3, 12), orderedPairs.get(5));
    }

    private <T extends Number> boolean naRemainsTheSame() {
        return nA.equals(NaturalSet.newInstance(15, 16, 17, 301, 302, 303));
    }

    private <T extends Number> boolean nbRemainsTheSame() {
        return nB.equals(NaturalSet.newInstance(15, 16, 17, 7856, 10987));
    }

    private <T extends Number> boolean iaRemainsTheSame() {
        return iA.equals(IntegerSet.newInstance(-1, -2, 3, 4, 5, 10, 15));
    }

    private <T extends Number> boolean ibRemainsTheSame() {
        return iB.equals(IntegerSet.newInstance(-1, -2, 3, 122, 247));
    }

    private <T extends Number> boolean raRemainsTheSame() {
        return rA.equals(new RealSet.Builder().add(-465).add(-3.15).add(Math.PI).add(75).add(2349)
                .build());
    }

    private <T extends Number> boolean rbRemainsTheSame() {
        return rB.equals(new RealSet.Builder().add(-465).add(-3.15).add(Math.PI).add(533.90)
                .build());
    }

}
