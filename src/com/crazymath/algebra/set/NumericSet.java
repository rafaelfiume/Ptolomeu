package com.crazymath.algebra.set;

import static com.crazymath.algebra.set.InnerNumericSet.newInnerNumericSet;

import java.util.Iterator;

import org.apache.commons.lang.Validate;

public abstract class NumericSet<T extends Number> implements AlgebraSet<T> {

    private AlgebraSet<T> elements = newInnerNumericSet();

    protected void setElements(AlgebraSet<T> elements) {
        this.elements = elements;
    }

    protected AlgebraSet<T> getElements() {
        return elements;
    }

    @Override
    public void add(T element) {
        elements.add(element);
    }

    @Override
    public void add(T... elements) {
        addElements(elements);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean has(T element) {
        return elements.has(element);
    }

    @Override
    public boolean contains(AlgebraSet<T> other) {
        Validate.notNull(other);

        if (other.isEmpty()) {
            return true;
        }

        for (final T each : other) {
            if (!elements.has(each)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public T lower() {
        return elements.lower();
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    protected void addElements(T... otherElements) {
        Validate.notNull(otherElements, "otherElements não pode ser null");
        for (final T other : otherElements) {
            elements.add(other);
        }
    }

    protected void addElements(AlgebraSet<T> otherElements) {
        Validate.notNull(otherElements, "otherElements não pode ser null");
        for (final T other : otherElements) {
            elements.add(other);
        }
    }

    public static class Builder {

        private final AlgebraSet<Integer> intElements = newInnerNumericSet();

        private final AlgebraSet<Double> decimalElements = newInnerNumericSet();

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

        public AlgebraSet<? extends Number> build() {
            if (decimalElements.isEmpty()) {
                return buildNaturalOrIntegerNumericSet();
            }

            return buildRealNumericSet();
        }

        private AlgebraSet<? extends Number> buildNaturalOrIntegerNumericSet() {
            if (hasNegativeElement) {
                return new IntegerSet(intElements);
            }

            return new NaturalSet(intElements);
        }

        private AlgebraSet<? extends Number> buildRealNumericSet() {
            for (final Integer each : intElements) {
                decimalElements.add(each.doubleValue());
            }

            return new RealSet(decimalElements);
        }

        private void checkIfNegative(Integer element) {
            if (element < 0) {
                hasNegativeElement = true;
            }
        }

    }

}
