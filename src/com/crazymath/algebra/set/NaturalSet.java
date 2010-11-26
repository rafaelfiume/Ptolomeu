package com.crazymath.algebra.set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This class is not thread-safe.
 * 
 * @author Rafael Fiume
 */
public final class NaturalSet extends NumericSet<Integer> {

    NaturalSet(AlgebraSet<Integer> elements) {
        // FIXME Bug aqui permite que sejam inseridos elementos negativos em NaturalSet
        addElements(elements);
    }

    private NaturalSet(Integer... elements) {
        checkNotNegative(elements);
        addElements(elements);
    }

    public static NaturalSet newInstance(Integer... elements) {
        return new NaturalSet(elements);
    }

    @Override
    public void add(Integer element) {
        checkNotNegative(element);
        super.add(element);
    }

    @Override
    public void add(Integer... elements) {
        checkNotNegative(elements);
        addElements(elements);
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
        return new EqualsBuilder().append(getElements(), ((NaturalSet) other).getElements())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getElements()).toHashCode();
    }

    private void checkNotNegative(Integer... elements) {
        for (Integer each : elements) {
            if (each < 0) {
                // TODO Replace this by a more appropriate exception
                // since this's not really an arithmetic error, but an algebra one.
                throw new ArithmeticException();
            }
        }
    }

}
