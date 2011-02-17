package org.ptolomeu.algebra.set;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

final class IndividualElements<T extends Number> implements Iterable<T> {

    private final NavigableSet<T> set;

    private IndividualElements() {
        set = new TreeSet<T>();
    }

    private IndividualElements(NavigableSet<T> set) {
        this.set = new TreeSet<T>(set);
    }

    static <S extends Number> IndividualElements<S> newInstance() {
        return new IndividualElements<S>();
    }

    static <S extends Number> IndividualElements<S> newInstance(IndividualElements<S> elements) {
        return new IndividualElements<S>(elements.set);
    }

    // TODO Wrap iterator
    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    boolean isEmpty() {
        return set.isEmpty();
    }

    boolean isNotEmpty() {
        return !set.isEmpty();
    }

    boolean has(T element) {
        return set.contains(element);
    }

    T upper() {
        return set.last();
    }

    T lower() {
        return set.first();
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
        return new EqualsBuilder().append(set, ((IndividualElements) other).set).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(set).toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(set.toString());
        builder.replace(0, 1, "{");
        builder.replace(builder.length() - 1, builder.length(), "}");
        return builder.toString();
    }

    // TODO Substitute mutable method add by a builder method.
    void add(T otherElement) {
        Validate.notNull(otherElement, "otherElement cannot be null");
        this.set.add(otherElement);
    }

    // TODO Substitute mutable method add by a builder method.
    void add(T... otherElements) {
        Validate.noNullElements(otherElements, "otherElements cannot be null");

        for (T each : otherElements) {
            add(each);
        }
    }

    // TODO Substitute mutable method add by a builder method.
    void add(IndividualElements<T> otherElements) {
        Validate.notNull(otherElements, "otherElements cannot be null");

        for (final Iterator<T> it = otherElements.iterator(); it.hasNext();) {
            set.add(it.next());
        }
    }

    // TODO Substitute mutable method subtract by a builder method.
    void subtract(T otherElement) {
        Validate.notNull(otherElement, "otherElement cannot be null");

        if (has(otherElement)) {
            set.remove(otherElement);
        }
    }

}
