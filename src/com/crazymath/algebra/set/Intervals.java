package com.crazymath.algebra.set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Manage the intervals that an {@code AlgebraSet} can possibly have.
 * 
 * <p>
 * 
 * References:
 * <p>
 * 
 * <a href="http://en.wikipedia.org/wiki/Interval_%28mathematics%29">Interval on WIkipedia</a>
 */
final class Intervals<T extends Number> implements Iterable<Interval<T>> {

    private final SortedSet<Interval<T>> intervals; // NOPMD by rafaelfiume on 28/12/10 16:13

    private Intervals() {
        this.intervals = new TreeSet<Interval<T>>();
    }

    private Intervals(SortedSet<Interval<T>> intervals) {
        this.intervals = new TreeSet<Interval<T>>(intervals);
    }

    static <E extends Number> Intervals<E> newInstance() {
        return new Intervals<E>();
    }

    private static <E extends Number> Intervals<E> newInstance(SortedSet<Interval<E>> intervals) {
        return new Intervals<E>(intervals);
    }

    @Override
    public Iterator<Interval<T>> iterator() {
        return intervals.iterator();
    }

    boolean isEmpty() {
        return intervals.isEmpty();
    }

    boolean isNotEmpty() {
        return !isEmpty();
    }

    T firstLowerEndpoint() {
        return intervals.first().lowerEndpoint();
    }

    T lastUpperEndpoint() {
        return intervals.last().upperEndpoint();
    }

    boolean enclose(T element) {
        for (Interval<T> e : intervals) {
            if (e.encloses(element)) {
                return true;
            }
        }
        return false;
    }

    boolean enclose(Interval<T> other) {
        for (Interval<T> in : intervals) {
            if (in.encloses(other)) {
                return true;
            }
        }
        return false;
    }

    boolean enclose(Intervals<T> other) {
        if (other.isEmpty()) {
            return false;
        }
        if (isEmpty()) {
            return false;
        }

        for (Interval<T> otherInterval : other) {
            if (!enclose(otherInterval)) {
                return false;
            }
        }

        return true;
    }

    boolean comprise(Intervals<T> other) {
        if (other.isEmpty()) {
            return false;
        }
        if (isEmpty()) {
            return false;
        }

        for (Interval<T> otherInterval : other) {
            if (!comprise(otherInterval)) {
                return false;
            }
        }

        return true;
    }

    boolean comprise(Interval<T> other) {
        for (Interval<T> in : intervals) {
            if (in.comprises(other)) {
                return true;
            }
        }
        return false;
    }

    // TODO Substitute mutable method add by a builder method.
    Intervals<T> add(Intervals<T> others) {
        for (Interval<T> otherInterval : others.intervals) {
            add(otherInterval);
        }
        return this;
    }

    // TODO Substitute mutable method add by a builder method.
    Intervals<T> add(T lowerEndpoint, T upperEndpoint) {
        final Interval<T> other = Interval.newInstance(lowerEndpoint, upperEndpoint);
        return add(other);
    }

    // TODO Substitute mutable method add by a builder method.
    Intervals<T> add(Interval<T> other) {
        if (intervals.isEmpty()) {
            intervals.add(other);
            return this;
        }
        // 1. Iterate through the endpoinstsSet
        // 2. Verify if endpoints a) contains, b) is contained or c) interleaves another
        // endpoinsts.
        // 2a. endpoint is included and the other (the contained one) is removed
        // 2b. endpoint doesn't need to be included
        // 2c. endpoint is merged
        for (final Iterator<Interval<T>> it = intervals.iterator(); it.hasNext();) {
            final Interval<T> thisInterval = it.next();

            if (thisInterval.encloses(other)) {
                return this;
            }

            if (thisInterval.isEnclosedBy(other)) {
                it.remove();
                intervals.add(other);
                return this;
            }

            if (thisInterval.interleavesWith(other)) {
                it.remove();
                final Interval<T> mergedInterval = thisInterval.union(other);
                add(mergedInterval);
                return this;
            }
        }

        intervals.add(other);
        return this;
    }

    // TODO Substitute mutable method intersect by a builder method.
    Intervals<T> intersect(Intervals<T> other) {
        final Intervals<T> newIntersectedIntervals = newInstance();

        for (Interval<T> thisInterval : intervals) {
            for (Interval<T> otherInterval : other.intervals) {
                if (thisInterval.comprises(otherInterval)) {
                    newIntersectedIntervals.add(thisInterval.intersect(otherInterval));
                }
            }
        }

        return newIntersectedIntervals;
    }

    // TODO Substitute mutable method subtract by a builder method.
    Intervals<T> subtract(T element) {
        final Intervals<T> newIntervals = newInstance(intervals);

        if (newIntervals.enclose(element)) {
            for (Interval<T> interval : newIntervals) {
                if (interval.encloses(element)) {
                    newIntervals.add(interval.subtract(element));
                }
            }
        }
        return newIntervals;
    }

    // TODO Substitute mutable method subtract by a builder method.
    Intervals<T> subtract(Intervals<T> other) {
        Intervals<T> newIntervals = newInstance(intervals);
        for (Iterator<Interval<T>> it = other.iterator(); it.hasNext();) {
            final Interval<T> otherInterval = it.next();
            if (newIntervals.comprise(otherInterval)) {
                newIntervals.subtract(otherInterval);
            }
        }
        return newIntervals;
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

        // The parameterized type doesn't make difference here. To see why, take a look at
        // Interval#equals(Object).
        @SuppressWarnings("unchecked")
        final SortedSet<Interval<T>> otherIntervals = ((Intervals<T>) other).intervals;
        if (intervals.size() != otherIntervals.size()) {
            return false;
        }

        final Iterator<Interval<T>> otherIt = otherIntervals.iterator();
        for (final Iterator<Interval<T>> myIt = intervals.iterator(); myIt.hasNext();) {
            if (!myIt.next().equals(otherIt.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(intervals).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder<T>(this).toString();
    }

    // TODO Substitute mutable subtract method by a builder
    private void subtract(Interval<T> otherInterval) {
        // PENDING This's a workaround to avoid ConcurrentModificationException
        final List<Intervals<T>> intervalsToBeAdded = new ArrayList<Intervals<T>>();

        for (final Iterator<Interval<T>> it = iterator(); it.hasNext();) {
            final Interval<T> actualInterval = it.next();
            if (actualInterval.comprises(otherInterval)) {
                it.remove();
                final Intervals<T> subtractedIntervals = actualInterval.subtract(otherInterval);
                if (subtractedIntervals.isNotEmpty()) {
                    intervalsToBeAdded.add(subtractedIntervals);
                }
            }
        }

        for (final Intervals<T> toBeAdded : intervalsToBeAdded) {
            add(toBeAdded);
        }

    }

    private static final class ToStringBuilder<S extends Number> {

        private final StringBuilder builder = new StringBuilder();

        ToStringBuilder(Intervals<S> intervals) {
            final Iterator<Interval<S>> it = intervals.intervals.iterator();

            if (it.hasNext()) {
                builder.append(it.next());
            }
            while (it.hasNext()) {
                append(it.next());
            }
        }

        ToStringBuilder<S> append(Interval<S> endpoints) {
            builder.append(" & ").append(endpoints);
            return this;
        }

        @Override
        public String toString() {
            return builder.toString();
        }

    }

}
