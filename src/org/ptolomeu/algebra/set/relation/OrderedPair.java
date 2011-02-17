package org.ptolomeu.algebra.set.relation;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * {@code OrderedPair}s implementation must be effective immutable and thus thread-safe.
 */
public final class OrderedPair implements Comparable<OrderedPair> {

    private final Double x;

    private final Double y;

    private OrderedPair(Double x, Double y) {
        Validate.notNull(x, "x não pode ser null");
        Validate.notNull(y, "y não pode ser null");
        this.x = x;
        this.y = y;
    }

    private OrderedPair(Integer x, Integer y) {
        Validate.notNull(x, "x não pode ser null");
        Validate.notNull(y, "y não pode ser null");
        this.x = x.doubleValue();
        this.y = y.doubleValue();
    }

    public static <E extends Number> OrderedPair newInstance(E aElement, E bElement) {
        return newOrderedPair(aElement, bElement);
    }

    public static OrderedPair newInstance(double aElement, double bElement) {
        return new OrderedPair(aElement, bElement);
    }

    public static OrderedPair newInstance(int aElement, int bElement) {
        return new OrderedPair(aElement, bElement);
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
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

        return new EqualsBuilder().append(x, ((OrderedPair) other).x)
                .append(y, ((OrderedPair) other).y).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(x).append(y).toHashCode();
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    private static <E extends Number> OrderedPair newOrderedPair(E aElement, E bElement) {
        if (atLeastOneElementInstanceODouble(aElement, bElement)) {
            return new OrderedPair((Double) aElement, (Double) bElement);
        }
        if (atLeastOneElementInstanceOfInteger(aElement, bElement)) {
            return new OrderedPair((Integer) aElement, (Integer) bElement);
        }
        throw new IllegalArgumentException();
    }

    private static <E> boolean atLeastOneElementInstanceODouble(E aElement, E bElement) {
        return aElement instanceof Double || bElement instanceof Double;
    }

    private static <E> boolean atLeastOneElementInstanceOfInteger(E aElement, E bElement) {
        return aElement instanceof Integer || bElement instanceof Integer;
    }

    @Override
    public int compareTo(OrderedPair other) {
        if (x < other.x) {
            return -1;
        }
        if (x > other.x) {
            return 1;
        }
        if (y < other.y) {
            return -1;
        }
        if (y > other.y) {
            return 1;
        }
        return 0;
    }

}
