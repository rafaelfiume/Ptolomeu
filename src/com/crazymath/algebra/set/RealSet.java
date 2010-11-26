package com.crazymath.algebra.set;

import static com.crazymath.algebra.set.InnerNumericSet.newInnerNumericSet;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public final class RealSet extends NumericSet<Double> {

    RealSet(AlgebraSet<Double> elements) {
        addElements(elements);
    }

    private RealSet(Builder builder) {
        setElements(builder.elements);
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
        return new EqualsBuilder().append(getElements(), ((RealSet) other).getElements())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getElements()).toHashCode();
    }

    public static class Builder {

        private final AlgebraSet<Double> elements = newInnerNumericSet();

        public Builder add(double element) {
            elements.add(element);
            return this;
        }

        public RealSet build() {
            return new RealSet(this);
        }

    }

}
