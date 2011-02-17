package org.ptolomeu.algebra.set;

import org.apache.commons.lang.Validate;

// TODO @Immutable
public final class IntegerSet extends NumericSet<Integer> {

    IntegerSet(ElementsSet<Integer> elementsSet) {
        super(elementsSet);
    }

    IntegerSet(IndividualElements<Integer> elements, Intervals<Integer> intervals) {
        addElements(elements);
        addIntervals(intervals);
    }

    private IntegerSet(Integer... elements) {
        addElements(elements);
    }

    private IntegerSet(Interval<Integer> interval) {
        Validate.notNull(interval, "interval cannot be null");
        addInterval(interval);
    }

    public static IntegerSet newInstance(Integer... elements) {
        return new IntegerSet(elements);
    }

    public static IntegerSet newInstance(Integer lowerEndpoint, Endpoint leftBound,
            Integer upperEndpoint, Endpoint rightBound) {

        return new IntegerSet(Interval.newInstance(lowerEndpoint, upperEndpoint));
    }

}
