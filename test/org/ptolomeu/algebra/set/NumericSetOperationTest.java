package org.ptolomeu.algebra.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NumericSetOperationTest {

    private final NaturalSet naturalA = NaturalSet.newInstance(15, 16, 17, 301, 302, 303);

    private final NaturalSet naturalB = NaturalSet.newInstance(15, 16, 17, 7856, 10987);

    private final NaturalSet naturalC = NaturalSet.newInstance(15, 16, 17);

    private final NaturalSet naturalD = NaturalSet.newInstance(3, 2);

    private final IntegerSet integerA = IntegerSet.newInstance(-1, -2, 3, 4, 5, 10, 15);

    private final IntegerSet integerB = IntegerSet.newInstance(-1, -2, 3, 122, 247);

    private final IntegerSet integerC = IntegerSet.newInstance(-1, -2, 3);

    private final IntegerSet integerD = IntegerSet.newInstance(-9, 0, 89);

    private final RealSet realA = RealSet.newInstance(-465d, -3.15d, Math.PI, 75d, 2349d);

    private final RealSet realB = RealSet.newInstance(-465d, -3.15d, Math.PI, 533.90);

    private final RealSet realC = RealSet.newInstance(-465d, -3.15d, Math.PI);

    @Test
    public void testUnionOfIndividualElements() {
        assertEquals(NaturalSet.newInstance(15, 16, 17, 301, 302, 303, 7856, 10987),
                NumericSet.union(naturalA, naturalB));
        assertTrue(naRemainsTheSame());
        assertTrue(nbRemainsTheSame());

        assertEquals(IntegerSet.newInstance(-1, -2, 3, 4, 5, 10, 15, 122, 247),
                NumericSet.union(integerA, integerB));

        final RealSet unionOfAandB = RealSet.newInstance(-465d, -3.15, Math.PI, 75d, 2349d, 533.90);
        assertEquals(unionOfAandB, NumericSet.union(realA, realB));
    }

    @Test
    public void testUnion() {
        final NaturalSet nA = new NaturalSet.Builder()
                .addInterval(2, Endpoint.CLOSED, 3, Endpoint.CLOSED)
                .addInterval(6, Endpoint.CLOSED, 8, Endpoint.CLOSED)
                .addInterval(7, Endpoint.CLOSED, 11, Endpoint.CLOSED).add(9).add(25).add(31)
                .build();

        final NaturalSet nB = new NaturalSet.Builder()
                .addInterval(1, Endpoint.CLOSED, 5, Endpoint.CLOSED)
                .addInterval(7, Endpoint.CLOSED, 8, Endpoint.CLOSED)
                .addInterval(27, Endpoint.CLOSED, 37, Endpoint.CLOSED).add(9).add(48).build();

        final NaturalSet AUB = new NaturalSet.Builder()
                .addInterval(1, Endpoint.CLOSED, 5, Endpoint.CLOSED)
                .addInterval(6, Endpoint.CLOSED, 11, Endpoint.CLOSED)
                .addInterval(27, Endpoint.CLOSED, 37, Endpoint.CLOSED).add(25).add(48).build();

        assertEquals(AUB, NumericSet.union(nA, nB));

        assertEquals(nA, NumericSet.union(nA, nA));
        assertEquals(nB, NumericSet.union(nB, nB));

        // PENDING Write more tests
    }

    @Test
    public void testIntersectionOfIndividualElements() {
        assertEquals(naturalC, NumericSet.intersection(naturalA, naturalB));
        assertTrue(naRemainsTheSame());
        assertTrue(nbRemainsTheSame());

        assertEquals(integerC, NumericSet.intersection(integerA, integerB));

        assertEquals(realC, NumericSet.intersection(realA, realB));
    }

    @Test
    public void testIntersection() {
        final NaturalSet nA = new NaturalSet.Builder()
                .addInterval(2, Endpoint.CLOSED, 3, Endpoint.CLOSED)
                .addInterval(6, Endpoint.CLOSED, 8, Endpoint.CLOSED)
                .addInterval(7, Endpoint.CLOSED, 11, Endpoint.CLOSED).add(9).add(25).add(31)
                .build();

        final NaturalSet nB = new NaturalSet.Builder()
                .addInterval(1, Endpoint.CLOSED, 5, Endpoint.CLOSED)
                .addInterval(7, Endpoint.CLOSED, 8, Endpoint.CLOSED)
                .addInterval(27, Endpoint.CLOSED, 37, Endpoint.CLOSED).add(9).add(48).build();

        final NaturalSet AintersectB = new NaturalSet.Builder()
                .addInterval(2, Endpoint.CLOSED, 3, Endpoint.CLOSED)
                .addInterval(7, Endpoint.CLOSED, 8, Endpoint.CLOSED).add(9).add(31).build();

        assertEquals(AintersectB, NumericSet.intersection(nA, nB));
        assertEquals(AintersectB, NumericSet.intersection(nB, nA));

        assertEquals(nA, NumericSet.intersection(nA, nA));
        assertEquals(nB, NumericSet.intersection(nB, nB));

        // PENDING Write more tests
    }

    @Test
    public void testSubtractOfIndividualElements() {
        final NaturalSet emptyNaturalSet = NaturalSet.newInstance();
        assertEquals(NaturalSet.newInstance(301, 302, 303),
                NumericSet.subtraction(naturalA, naturalB));
        assertEquals(NaturalSet.newInstance(7856, 10987),
                NumericSet.subtraction(naturalB, naturalA));
        assertEquals(naturalB, NumericSet.subtraction(naturalB, emptyNaturalSet));
        assertTrue(naRemainsTheSame());
        assertTrue(nbRemainsTheSame());

        final IntegerSet emptyIntegerSet = IntegerSet.newInstance();
        assertEquals(NaturalSet.newInstance(4, 5, 10, 15),
                NumericSet.subtraction(integerA, integerB));
        assertEquals(NaturalSet.newInstance(122, 247), NumericSet.subtraction(integerB, integerA));
        assertEquals(integerB, NumericSet.subtraction(integerB, emptyIntegerSet));

        final RealSet emptyRealSet = RealSet.newInstance();
        assertEquals(RealSet.newInstance(75d, 2349d), NumericSet.subtraction(realA, realB));
        assertEquals(RealSet.newInstance(533.90), NumericSet.subtraction(realB, realA));
        assertEquals(realB, NumericSet.subtraction(realB, emptyRealSet));
    }

    @Test
    public void testSubtraction() {
        final NaturalSet nA = new NaturalSet.Builder()
                .addInterval(2, Endpoint.CLOSED, 3, Endpoint.CLOSED)
                .addInterval(6, Endpoint.CLOSED, 8, Endpoint.CLOSED)
                .addInterval(7, Endpoint.CLOSED, 11, Endpoint.CLOSED).add(9).add(25).add(31)
                .build();

        final NaturalSet nB = new NaturalSet.Builder()
                .addInterval(1, Endpoint.CLOSED, 5, Endpoint.CLOSED)
                .addInterval(7, Endpoint.CLOSED, 8, Endpoint.CLOSED)
                .addInterval(27, Endpoint.CLOSED, 37, Endpoint.CLOSED).add(9).add(48).build();

        final NaturalSet AsubtractB = new NaturalSet.Builder()
                .addInterval(6, Endpoint.CLOSED, 7, Endpoint.CLOSED)
                .addInterval(8, Endpoint.CLOSED, 11, Endpoint.CLOSED).add(25).build();

        final NaturalSet BsubtractA = new NaturalSet.Builder()
                .addInterval(1, Endpoint.CLOSED, 2, Endpoint.CLOSED)
                .addInterval(3, Endpoint.CLOSED, 5, Endpoint.CLOSED)
                .addInterval(27, Endpoint.CLOSED, 31, Endpoint.CLOSED)
                .addInterval(31, Endpoint.CLOSED, 37, Endpoint.CLOSED).add(48).build();

        assertEquals(AsubtractB, NumericSet.subtraction(nA, nB));
        assertEquals(BsubtractA, NumericSet.subtraction(nB, nA));

        final EmptySet empty = EmptySet.newInstance();
        assertEquals(empty, NumericSet.subtraction(nA, nA));
        assertEquals(empty, NumericSet.subtraction(nB, nB));
    }

    // @Test
    // public void testCartesianProductOfAxB() {
    // final CartesianProduct cpAxB = NumericSetOperation.cartesianProduct(naturalD, integerD);
    // assertSame(6, cpAxB.numberOfOrderedPairs());
    // final List<OrderedPair> orderedPairs = cpAxB.orderedPairAsList();
    // assertEquals(OrderedPair.newInstance(2, -9), orderedPairs.get(0));
    // assertEquals(OrderedPair.newInstance(2, 0), orderedPairs.get(1));
    // assertEquals(OrderedPair.newInstance(2, 89), orderedPairs.get(2));
    // assertEquals(OrderedPair.newInstance(3, -9), orderedPairs.get(3));
    // assertEquals(OrderedPair.newInstance(3, 0), orderedPairs.get(4));
    // assertEquals(OrderedPair.newInstance(3, 89), orderedPairs.get(5));
    // }

    // @Test
    // public void testCartesianProductOfBxA() {
    // final CartesianProduct cpBxA = NumericSetOperation.cartesianProduct(integerD, naturalD);
    // assertSame(6, cpBxA.numberOfOrderedPairs());
    // final Set<OrderedPair> orderedPairs = cpBxA.orderedPair();
    // final OrderedPair[] opa = orderedPairs.toArray(new OrderedPair[] {});
    // assertEquals(OrderedPair.newInstance(-9, 2), opa[0]);
    // assertEquals(OrderedPair.newInstance(-9, 3), opa[1]);
    // assertEquals(OrderedPair.newInstance(0, 2), opa[2]);
    // assertEquals(OrderedPair.newInstance(0, 3), opa[3]);
    // assertEquals(OrderedPair.newInstance(89, 2), opa[4]);
    // assertEquals(OrderedPair.newInstance(89, 3), opa[5]);
    // }

    private <T extends Number> boolean naRemainsTheSame() {
        return naturalA.equals(NaturalSet.newInstance(15, 16, 17, 301, 302, 303));
    }

    private <T extends Number> boolean nbRemainsTheSame() {
        return naturalB.equals(NaturalSet.newInstance(15, 16, 17, 7856, 10987));
    }

}
