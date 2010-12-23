package com.crazymath.algebra.set;

public final class EmptySet extends NumericSet<Number> {

    // TODO Implement this class

    private EmptySet() {
        // Instantiated via method factory
    }

    static EmptySet newInstance() {
        return new EmptySet();
    }

}
