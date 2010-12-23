package com.crazymath.algebra.set;

import org.apache.commons.lang.Validate;

import com.crazymath.algebra.AlgebraException;

// TODO @Immutable
public final class NaturalSet extends NumericSet<Integer> {

    NaturalSet(ElementsSet<Integer> elementsSet) {
        super(elementsSet);
    }

    NaturalSet(IndividualElements<Integer> elements, Intervals<Integer> intervals) {
        NaturalSet.validateNotNegative(elements);
        addElements(elements);
        NaturalSet.validateNotNegative(intervals);
        addIntervals(intervals);
    }

    private NaturalSet(Integer... elements) {
        NaturalSet.validateNotNegative(elements);
        addElements(elements);
    }

    private NaturalSet(Interval<Integer> interval) {
        Validate.notNull(interval, "interval cannot be null");
        NaturalSet.validateNotNegative(interval.lowerEndpoint());
        addInterval(interval);
    }

    public static NaturalSet newInstance(Integer... elements) {
        return new NaturalSet(elements);
    }

    public static NaturalSet newInstance(Integer lowerEndpoint, Endpoint leftBound,
            Integer upperEndpoint, Endpoint rightBound) {

        return new NaturalSet(Interval.newInstance(lowerEndpoint, upperEndpoint));
    }

    static void validateNotNegative(Integer... elements) {
        for (Integer each : elements) {
            if (each < 0) {
                throw new AlgebraException("A natural set cannot have a negative number");
            }
        }
    }

    static void validateNotNegative(IndividualElements<Integer> elements) {
        for (final Integer anElement : elements) {
            if (anElement < 0) {
                throw new AlgebraException("A natural set cannot have a negative number");
            }
        }
    }

    static void validateNotNegative(Intervals<Integer> intervals) {
        if (intervals.isNotEmpty() && intervals.firstLowerEndpoint() < 0) {
            throw new AlgebraException("A natural set cannot have a negative number");
        }
    }

    // TODO Forbid empty constructor
    public static class Builder {

        private final NumericSet.Builder builder = new NumericSet.Builder();

        public Builder add(Integer element) {
            NaturalSet.validateNotNegative(element);

            builder.add(element);
            return this;
        }

        public Builder addInterval(Integer lowerEndpoint, Endpoint leftBound,
                Integer upperEndpoint, Endpoint rightBound) {

            NaturalSet.validateNotNegative(lowerEndpoint);

            builder.addInterval(lowerEndpoint, leftBound, upperEndpoint, rightBound);
            return this;
        }

        public NaturalSet build() {
            return (NaturalSet) builder.build();
        }

    }

}
