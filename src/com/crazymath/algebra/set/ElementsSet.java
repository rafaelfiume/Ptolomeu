package com.crazymath.algebra.set;

import static com.crazymath.util.NumberUtil.max;
import static com.crazymath.util.NumberUtil.min;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Need to be careful to not expose the inner data to the exterior word.
 * 
 * @param <T>
 */
// TODO Make this class immutable
final class ElementsSet<T extends Number> {

    private final IndividualElements<T> individualElements;

    private final Intervals<T> intervals;

    private ElementsSet(IndividualElements<T> individualElements, Intervals<T> intervals) {
        this.individualElements = individualElements;
        this.intervals = intervals;
    }

    static <E extends Number> ElementsSet<E> newInstance() {
        return new ElementsSet<E>(IndividualElements.<E> newInstance(), Intervals.<E> newInstance());
    }

    static <E extends Number> ElementsSet<E> union(ElementsSet<E> one, ElementsSet<E> another) {
        final IndividualElements<E> newIndividualElements = IndividualElements.newInstance();
        final Intervals<E> newIntervals = Intervals.newInstance();

        newIntervals.add(one.intervals);
        newIntervals.add(another.intervals);

        for (E oneElement : one.individualElements) {
            if (!newIntervals.enclose(oneElement)) {
                newIndividualElements.add(oneElement);
            }
        }
        for (E anotherElement : another.individualElements) {
            if (!newIntervals.enclose(anotherElement)) {
                newIndividualElements.add(anotherElement);
            }
        }

        return new ElementsSet<E>(newIndividualElements, newIntervals);
    }

    static <E extends Number> ElementsSet<E> intersect(ElementsSet<E> one, ElementsSet<E> another) {
        final IndividualElements<E> newIndividualElements = IndividualElements.newInstance();
        final Intervals<E> newIntervals = one.intervals.intersect(another.intervals);

        for (E oneElement : one.individualElements) {
            if (another.has(oneElement) && !newIntervals.enclose(oneElement)) {
                newIndividualElements.add(oneElement);
            }
        }
        for (E anotherElement : another.individualElements) {
            if (one.has(anotherElement) && !newIntervals.enclose(anotherElement)) {
                newIndividualElements.add(anotherElement);
            }
        }

        return new ElementsSet<E>(newIndividualElements, newIntervals);
    }

    static <E extends Number> ElementsSet<E> subtract(ElementsSet<E> one, ElementsSet<E> another) {
        if (one.equals(another)) {
            return ElementsSet.<E> newInstance();
        }

        final IndividualElements<E> newIndividualElements = IndividualElements
                .newInstance(one.individualElements);
        final Intervals<E> newIntervals = one.intervals.subtract(another.intervals);

        for (E elementOfAnother : another.individualElements) {
            if (newIntervals.enclose(elementOfAnother)) {
                // TODO Substitute mutable method subtract by a builder method.
                newIntervals.subtract(elementOfAnother);
            }
            // TODO Substitute mutable method subtract by a builder method.
            newIndividualElements.subtract(elementOfAnother);
        }

        for (Interval<E> anotherInterval : another.intervals) {
            for (E elementOfOne : one.individualElements) {
                if (anotherInterval.encloses(elementOfOne)) {
                    newIndividualElements.subtract(elementOfOne);
                }
            }
        }

        return new ElementsSet<E>(newIndividualElements, newIntervals);
    }

    boolean isEmpty() {
        return individualElements.isEmpty() && intervals.isEmpty();
    }

    boolean isNotEmpty() {
        return !isEmpty();
    }

    boolean has(T element) {
        return individualElements.has(element) || intervals.enclose(element);
    }

    boolean contains(ElementsSet<T> other) {
        Validate.notNull(other, "other cannot be null");

        if (other.isEmpty()) {
            return true;
        }
        if (this.isEmpty()) {
            return false;
        }
        if (individualElements.isEmpty()) {
            return intervalsEncloses(other);
        }
        if (intervals.isEmpty()) {
            return containsInElements(other);
        }

        return intervalsEncloses(other) || containsInElements(other);
    }

    private boolean intervalsEncloses(ElementsSet<T> other) {
        boolean query = true;

        if (!other.isIntervalsEmpty()) {
            query = intervals.enclose(other.intervals);
        }

        if (query) {
            for (T otherElement : other.individualElements) {
                if (!intervals.enclose(otherElement)) {
                    query = false;
                    break;
                }
            }
        }
        return query;
    }

    private boolean containsInElements(ElementsSet<T> other) {
        if (individualElements.isEmpty()) {
            return false;
        }

        if (other.isIndividualElementsEmpty()) {
            return false;
        }

        for (final T each : other.individualElements) {
            if (!individualElements.has(each)) {
                return false;
            }
        }

        return true;
    }

    T upper() {
        T upperElement = null;
        if (individualElements.isNotEmpty()) {
            upperElement = individualElements.upper();
        }

        T lastUpperEndpoint = null;
        if (intervals.isNotEmpty()) {
            lastUpperEndpoint = intervals.lastUpperEndpoint();
        }

        return max(upperElement, lastUpperEndpoint);
    }

    T lower() {
        T lowerElement = null;
        if (individualElements.isNotEmpty()) {
            lowerElement = individualElements.lower();
        }

        T firstLowerEndpoint = null;
        if (intervals.isNotEmpty()) {
            firstLowerEndpoint = intervals.firstLowerEndpoint();
        }

        return min(lowerElement, firstLowerEndpoint);
    }

    void addElements(T... elements) {
        individualElements.add(elements);
    }

    void addElements(IndividualElements<T> othersElements) {
        individualElements.add(othersElements);
    }

    void addInterval(Interval<T> interval) {
        intervals.add(interval);
    }

    void addIntervals(Intervals<T> otherIntervals) {
        intervals.add(otherIntervals);
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
                .append(individualElements, ((ElementsSet<?>) other).individualElements)
                .append(intervals, ((ElementsSet<?>) other).intervals).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(individualElements).append(intervals).hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (individualElements.isNotEmpty()) {
            builder.append(individualElements).append(" & ");
        }
        builder.append(intervals);
        return builder.toString();
    }

    private boolean isIndividualElementsEmpty() {
        return individualElements.isEmpty();
    }

    private boolean isIntervalsEmpty() {
        return intervals.isEmpty();
    }

}
