package com.crazymath.algebra.set;


public interface NumericSetOperation<T extends Number> {

    /**
     * @return an unmodifiable (read-only) view of the union of the {@code NumericSet}.
     */
    NumericSet<T> union(NumericSet<T> other);

    NumericSet<T> intersection(NumericSet<T> other);

    NumericSet<T> subtract(NumericSet<T> other);
    
}
