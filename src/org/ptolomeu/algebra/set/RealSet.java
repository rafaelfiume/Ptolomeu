package org.ptolomeu.algebra.set;

import org.apache.commons.lang.Validate;

// TODO @Immutable
public final class RealSet extends NumericSet<Double> {

    RealSet(ElementsSet<Double> elementsSet) {
        super(elementsSet);
    }

    RealSet(IndividualElements<Double> elements, Intervals<Double> intervals) {
        addElements(elements);
        addIntervals(intervals);
    }

    private RealSet(Double... elements) {
        addElements(elements);
    }

    private RealSet(Interval<Double> interval) {
        Validate.notNull(interval, "interval cannot be null");
        addInterval(interval);
    }

    public static RealSet newInstance(Double... elements) {
        return new RealSet(elements);
    }

    public static RealSet newInstance(Double lowerEndpoint, Endpoint leftBound,
            Double upperEndpoint, Endpoint rightBound) {

        return new RealSet(Interval.newInstance(lowerEndpoint, upperEndpoint));
    }

    // TODO Forbid empty constructor
    public static class Builder {

        private final NumericSet.Builder builder = new NumericSet.Builder();

        public Builder() {
            // FIXME
            // new RealSet.Builder().build();
            // ... will throw a ClassCastException
        }

        public Builder add(Double element) {
            builder.add(element);
            return this;
        }

        public Builder addInterval(Double lowerEndpoint, Endpoint leftBound, Double upperEndpoint,
                Endpoint rightBound) {

            builder.addInterval(lowerEndpoint, leftBound, upperEndpoint, rightBound);
            return this;
        }

        public RealSet build() {
            return (RealSet) builder.build();
        }

    }

}
