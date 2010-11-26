package com.crazymath.algebra.set;

import java.util.Iterator;

public interface AlgebraSet<T extends Number> extends Iterable<T> {

    void add(T element);

    void add(T... elements);

    boolean isEmpty();

    boolean has(T element);

    boolean contains(AlgebraSet<T> other);

    Iterator<T> iterator();

    T lower();

}
