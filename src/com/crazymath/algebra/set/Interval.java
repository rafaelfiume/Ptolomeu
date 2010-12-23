package com.crazymath.algebra.set; // NOPMD by rafaelfiume on 03/01/11 16:14

import static com.crazymath.util.NumberUtil.bothInstanceOfDouble;
import static com.crazymath.util.NumberUtil.bothInstanceOfInteger;
import static com.crazymath.util.NumberUtil.greater;
import static com.crazymath.util.NumberUtil.greaterOrEquals;
import static com.crazymath.util.NumberUtil.lesser;
import static com.crazymath.util.NumberUtil.lesserOrEquals;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.crazymath.algebra.AlgebraException;
import com.crazymath.util.NumberUtil;

/**
 * Represents the lower and upper endpoints in an {@code Interval}.
 * 
 */
final class Interval<T extends Number> implements Comparable<Interval<T>> {

    // TODO Eliminate duplicate messages in exceptions

    private final Endpoints<T> endpoints;

    private Interval(T lowerEndpoint, T upperEndpoint) {
        Validate.notNull(lowerEndpoint, "lowerEndpoint cannot be null");
        Validate.notNull(upperEndpoint, "lowerEndpoint cannot be null");

        endpoints = new GenericEndpoints<T>(lowerEndpoint, upperEndpoint);
    }

    static Interval<Integer> newInstance(Integer lowerEndpoint, Integer upperEndpoint) {
        return new Interval<Integer>(lowerEndpoint, upperEndpoint);
    }

    static Interval<Double> newInstance(Double lowerEndpoint, Double upperEndpoint) {
        return new Interval<Double>(lowerEndpoint, upperEndpoint);
    }

    // Since the process of instantiation is manually controlled, there's no need of parameterized
    // type here.
    @SuppressWarnings("unchecked")
    static <E extends Number> Interval<E> newInstance(E lowerEndpoint, E upperEndpoint) {

        if (bothInstanceOfInteger(lowerEndpoint, upperEndpoint)) {
            return (Interval<E>) new Interval<Integer>((Integer) lowerEndpoint,
                    (Integer) upperEndpoint);
        }
        if (bothInstanceOfDouble(lowerEndpoint, upperEndpoint)) {
            return (Interval<E>) new Interval<Double>((Double) lowerEndpoint,
                    (Double) upperEndpoint);
        }

        throw new UnsupportedOperationException(
                "an Interval can only be instantiated with Integer or Double types for lower and upper endpoints");
    }

    T lowerEndpoint() {
        return endpoints.lower();
    }

    T upperEndpoint() {
        return endpoints.upper();
    }

    boolean encloses(T element) {
        return endpoints.enclose(element);
    }

    boolean encloses(Interval<T> other) {
        return endpoints.enclose(other.endpoints);
    }

    boolean isEnclosedBy(Interval<T> other) {
        return endpoints.isEnclosedBy(other.endpoints);
    }

    boolean interleavesWith(Interval<T> other) {
        return endpoints.interleaveWith(other.endpoints);
    }

    boolean comprises(Interval<T> other) {
        return endpoints.comprise(other.endpoints);
    }

    Interval<T> union(Interval<T> other) {
        return endpoints.union(other.endpoints);
    }

    Interval<T> intersect(Interval<T> other) {
        return endpoints.intersection(other.endpoints);
    }

    Intervals<T> subtract(T element) {
        return endpoints.subtract(element);
    }

    Intervals<T> subtract(Interval<T> other) {
        return endpoints.subtract(other.endpoints);
    }

    /**
     * TODO Put the specification of this method here.
     * <p>
     * ... In this sense, this {@code compareTo} implementation never returns zero.
     */
    @Override
    public int compareTo(Interval<T> other) {
        return endpoints.compareTo(other.endpoints);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        return new EqualsBuilder()
                .append(endpoints.lower(), ((Interval<?>) other).endpoints.lower())
                .append(endpoints.upper(), ((Interval<?>) other).endpoints.upper()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(endpoints.lower()).append(endpoints.upper())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "[" + endpoints.lower() + ", " + endpoints.upper() + "]";
    }

    private interface Endpoints<T extends Number> {

        T lower();

        T upper();

        boolean enclose(T element);

        boolean enclose(Endpoints<T> other);

        boolean isEnclosedBy(Endpoints<T> other);

        boolean interleaveWith(Endpoints<T> other);

        boolean comprise(Endpoints<T> other);

        Interval<T> union(Endpoints<T> other);

        Interval<T> intersection(Endpoints<T> other);

        Intervals<T> subtract(T element);

        Intervals<T> subtract(Endpoints<T> endpoints);

        int compareTo(Endpoints<T> other);

    }

    private static class GenericEndpoints<T extends Number> implements Endpoints<T> {

        private final T lower; // NOPMD by rafaelfiume on 03/01/11 16:13

        private final T upper; // NOPMD by rafaelfiume on 03/01/11 16:13

        GenericEndpoints(T lower, T upper) {
            if (greater(lower, upper)) {
                throw new AlgebraException("lower endpoint cannot be greater than upper endpoint");
            }

            this.lower = lower;
            this.upper = upper;
        }

        @Override
        public T lower() {
            return lower;
        }

        @Override
        public T upper() {
            return upper;
        }

        @Override
        public boolean enclose(T element) {
            return greaterOrEquals(element, lower) && lesserOrEquals(element, upper);
        }

        @Override
        public boolean enclose(Endpoints<T> other) {
            return greaterOrEquals(other.lower(), lower) && lesserOrEquals(other.upper(), upper);
        }

        @Override
        public boolean isEnclosedBy(Endpoints<T> other) {
            return greaterOrEquals(lower, other.lower()) && lesserOrEquals(upper, other.upper());
        }

        @Override
        public boolean interleaveWith(Endpoints<T> other) {
            if (lesserOrEquals(lower, other.lower()) && lesserOrEquals(upper, other.upper())
                    && greaterOrEquals(upper, other.lower())) {

                return true;
            }
            if (greaterOrEquals(lower, other.lower()) && greaterOrEquals(upper, other.upper())
                    && lesserOrEquals(lower, other.upper())) {

                return true;
            }

            return false;
        }

        @Override
        public boolean comprise(Endpoints<T> other) {
            if (enclose(other) || isEnclosedBy(other) || interleaveWith(other)) {
                return true;
            }

            return false;
        }

        @Override
        public Interval<T> union(Endpoints<T> other) {
            if (!comprise(other)) {
                throw new AlgebraException(
                        "union operation only works when two endpoints comprise some or all of its elements in commom");
            }

            final T newLower = (lesserOrEquals(lower, other.lower())) ? lower : other.lower();
            final T newUpper = (greaterOrEquals(upper, other.upper())) ? upper : other.upper();

            return Interval.newInstance(newLower, newUpper);
        }

        @Override
        public Interval<T> intersection(Endpoints<T> other) {
            if (!comprise(other)) {
                throw new AlgebraException(
                        "intersection operation only works when two endpoints comprise some or all of its elements in commom");
            }

            final T newLower = (greaterOrEquals(lower, other.lower())) ? lower : other.lower();
            final T newUpper = (lesserOrEquals(upper, other.upper())) ? upper : other.upper();

            return Interval.newInstance(newLower, newUpper);
        }

        @Override
        public Intervals<T> subtract(T element) {
            if (!enclose(element)) {
                throw new AlgebraException(
                        "subtract operation only works when two endpoints comprise some or all of its elements in commom");
            }

            final T lowerA = lower;
            final T upperA = element;

            final T lowerB = element;
            final T upperB = upper;

            return Intervals.<T> newInstance().add(lowerA, upperA).add(lowerB, upperB);
        }

        @Override
        public Intervals<T> subtract(Endpoints<T> endpoints) {
            if (!comprise(endpoints)) {
                throw new AlgebraException(
                        "subtract operation only works when two endpoints comprise some or all of its elements in commom");
            }

            final Intervals<T> intervals = Intervals.newInstance();
            if (enclose(endpoints)) {
                final Interval<T> leftPortion = Interval.newInstance(lower, endpoints.lower());
                final Interval<T> rightPortion = Interval.newInstance(endpoints.upper(), upper);
                intervals.add(leftPortion);
                intervals.add(rightPortion);

            } else if (isEnclosedBy(endpoints)) {
                return intervals;

            } else if (NumberUtil.lesser(lower, endpoints.lower())) {
                final Interval<T> leftPortion = Interval.newInstance(lower, endpoints.lower());
                intervals.add(leftPortion);

            } else {
                final Interval<T> rightPortion = Interval.newInstance(endpoints.upper(), upper);
                intervals.add(rightPortion);
            }

            return intervals;
        }

        @Override
        public int compareTo(Endpoints<T> other) {
            if (comprise(other)) {
                throw new AlgebraException(
                        "intervals with interleavings endpoints cannot be compared; consider to do a merge operation before a comparison");
            }

            return (lesser(lower, other.lower())) ? -1 : 1;
        }

    }

}
