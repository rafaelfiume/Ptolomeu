package com.crazymath.algebra.set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * TODO Idea 1: to separate algebra operations (e.g. union) from the Set itself, and thus permit the
 * same operations in different implementations of Set's (immutable, mutable thread-safe, etc.)
 * 
 * <p>
 * Não é thread-safe.
 * 
 * @author Rafael Fiume
 */
public final class IntegerSet extends NumericSet<Integer> {

    IntegerSet(AlgebraSet<Integer> elements) {
        addElements(elements);
    }
    
    private IntegerSet(Integer... elements) {
        addElements(elements);
    }
    
    public static IntegerSet newInstance(Integer... elements) {
        return new IntegerSet(elements);
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
        return new EqualsBuilder().append(getElements(), ((IntegerSet) other).getElements())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getElements()).toHashCode();
    }

}
