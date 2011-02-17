package org.ptolomeu.algebra.set;

import java.util.Iterator;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.ptolomeu.util.NumberUtil;

public abstract class NumericSet<T extends Number> implements AlgebraSet<T> {

    private final ElementsSet<T> elementsSet;

    protected NumericSet() {
        this.elementsSet = ElementsSet.newInstance();
    }

    protected NumericSet(ElementsSet<T> elementsSet) {
        this.elementsSet = elementsSet;
    }

    public static <E extends Number> NumericSet<E> union(NumericSet<E> one, NumericSet<E> another) {
        algebraSetsCannotBeNull(one, another);

        return buildNumericSet(ElementsSet.union(one.elementsSet, another.elementsSet));
    }

    public static <E extends Number> NumericSet<E> intersection(NumericSet<E> one,
            NumericSet<E> another) {

        algebraSetsCannotBeNull(one, another);

        return buildNumericSet(ElementsSet.intersect(one.elementsSet, another.elementsSet));
    }

    public static <E extends Number> NumericSet<E> subtraction(NumericSet<E> one,
            NumericSet<E> another) {

        algebraSetsCannotBeNull(one, another);

        return buildNumericSet(ElementsSet.subtract(one.elementsSet, another.elementsSet));
    }

    // Since the process of instantiation is manually controlled, there's no need of
    // parameterized type here.
    @SuppressWarnings("unchecked")
    private static <E extends Number> NumericSet<E> buildNumericSet(ElementsSet<E> elementsSet) {
        if (elementsSet.isEmpty()) {
            return (NumericSet<E>) EmptySet.newInstance();
        }

        if (elementsSet.lower() instanceof Integer) {
            if (NumberUtil.lesser(elementsSet.lower(), 0)) {
                return (NumericSet<E>) new IntegerSet((ElementsSet<Integer>) elementsSet);
            }
            return (NumericSet<E>) new NaturalSet((ElementsSet<Integer>) elementsSet);
        }

        if (elementsSet.lower() instanceof Double) {
            return (NumericSet<E>) new RealSet((ElementsSet<Double>) elementsSet);
        }

        throw new UnsupportedOperationException(
                "Cannot instantiate an NumericSet with parameter type different from Integer or Double");
    }

    @Override
    public boolean isEmpty() {
        return elementsSet.isEmpty();
    }

    @Override
    public boolean isNotEmpty() {
        return !isEmpty();
    }

    @Override
    public boolean has(T element) {
        return elementsSet.has(element);
    }

    public boolean contains(NumericSet<T> other) {
        return elementsSet.contains(other.elementsSet);
    }

    @Override
    public T upper() {
        return elementsSet.upper();
    }

    @Override
    public T lower() {
        return elementsSet.lower();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() != getClass()) {
            return false;
        }
        return new EqualsBuilder().append(elementsSet, ((NumericSet<?>) other).elementsSet)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(elementsSet).toHashCode();
    }

    @Override
    public String toString() {
        return elementsSet.toString();
    }

    protected final void addElements(T... elements) {
        elementsSet.addElements(elements);
    }

    protected final void addElements(IndividualElements<T> othersElements) {
        elementsSet.addElements(othersElements);
    }

    protected final void addInterval(Interval<T> interval) {
        elementsSet.addInterval(interval);
    }

    protected final void addIntervals(Intervals<T> otherIntervals) {
        elementsSet.addIntervals(otherIntervals);
    }

    private static <E extends Number> void algebraSetsCannotBeNull(
            AlgebraSet<? extends Number> one, AlgebraSet<? extends Number> another) {

        Validate.notNull(one, "one can't be null");
        Validate.notNull(another, "another can't be null");
    }

    public static class Builder {

        private final IndividualElements<Integer> intElements = IndividualElements.newInstance();

        private final IndividualElements<Double> decimalElements = IndividualElements.newInstance();

        private final Intervals<Integer> intIntervals = Intervals.newInstance();

        private final Intervals<Double> decimalIntervals = Intervals.newInstance();

        private boolean hasNegativeElement = false;

        public Builder add(Integer element) {
            Validate.notNull(element, "element não pode ser null");
            checkIfNegative(element);
            intElements.add(element);
            return this;
        }

        public Builder add(Double element) {
            Validate.notNull(element, "element não pode ser null");
            decimalElements.add(element);
            return this;
        }

        public <E extends Number> Builder add(E element) {
            // TODO validateElementType(element);

            if (element instanceof Integer) {
                add((Integer) element);
            }
            if (element instanceof Double) {
                add((Double) element);
            }

            return this;
        }

        public Builder addInterval(Integer lowerEndpoint, Endpoint leftBound,
                Integer upperEndpoint, Endpoint rightBound) {

            checkIfNegative(lowerEndpoint);
            intIntervals.add(lowerEndpoint, upperEndpoint);
            return this;
        }

        public Builder addInterval(Double lowerEndpoint, Endpoint leftBound, Double upperEndpoint,
                Endpoint rightBound) {

            checkIfNegative(lowerEndpoint);
            decimalIntervals.add(lowerEndpoint, upperEndpoint);
            return this;
        }

        public NumericSet<? extends Number> build() {
            if (decimalElements.isEmpty() && decimalIntervals.isEmpty()) {
                return buildNaturalOrIntegerNumericSet();
            }

            return buildRealNumericSet();
        }

        private NumericSet<? extends Number> buildNaturalOrIntegerNumericSet() {
            removeRedundantElements(intElements, intIntervals);

            if (hasNegativeElement) {
                return new IntegerSet(intElements, intIntervals);
            }
            return new NaturalSet(intElements, intIntervals);
        }

        private NumericSet<? extends Number> buildRealNumericSet() {
            extractIntElementsToDecimalElementsIfNecessary();
            extractIntIntervalsToDecimalIntervalsIfNecessary();
            removeRedundantElements(decimalElements, decimalIntervals);

            return new RealSet(decimalElements, decimalIntervals);
        }

        private void checkIfNegative(Number element) {
            if (NumberUtil.lesser(element, 0)) {
                hasNegativeElement = true;
            }
        }

        private <E extends Number> void removeRedundantElements(IndividualElements<E> elements,
                Intervals<E> intervals) {

            for (final Iterator<E> it = elements.iterator(); it.hasNext();) {
                if (intervals.enclose(it.next())) {
                    it.remove();
                }
            }
        }

        private void extractIntIntervalsToDecimalIntervalsIfNecessary() {
            for (final Interval<Integer> anInterval : intIntervals) {
                decimalIntervals.add(anInterval.lowerEndpoint().doubleValue(), anInterval
                        .upperEndpoint().doubleValue());
            }
        }

        private void extractIntElementsToDecimalElementsIfNecessary() {
            for (final Integer anElement : intElements) {
                decimalElements.add(anElement.doubleValue());
            }
        }

    }

}
