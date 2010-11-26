package com.crazymath.algebra.set;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

final class InnerNumericSet<T extends Number> implements AlgebraSet<T> {

    private final NavigableSet<T> elements = new TreeSet<T>();

    private InnerNumericSet() {
    }

    static <S extends Number> InnerNumericSet<S> newInnerNumericSet() {
        return new InnerNumericSet<S>();
    }

    @Override
    public void add(T element) {
        elements.add(element);
    }

    @Override
    public void add(T... elements) {
        innerAdd(elements);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean has(T element) {
        return elements.contains(element);
    }

    @Override
    public boolean contains(AlgebraSet<T> other) {
        Validate.notNull(other);

        if (other.isEmpty()) {
            return true;
        }

        for (T each : other) {
            if (!this.elements.contains(each)) {
                return false;
            }
        }

        return true;
    }

    // TODO Criar decorator nesse Iterator para assegurar-se de que não operação remove não pode ser
    // chamada
    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public T lower() {
        return elements.first();
    }

    /**
     * @SuppressWarnings added because there will be no type safe violation in the
     *                   {@code EqualsBuilder} comparison.
     */
    @Override
    @SuppressWarnings("rawtypes")
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
        return new EqualsBuilder().append(elements, ((InnerNumericSet) other).elements).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(elements).toHashCode();
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    private void innerAdd(T... elements) {
        Validate.noNullElements(elements);
        for (T each : elements) {
            this.elements.add(each);
        }
    }

}
