package org.ptolomeu.algebra.set;

public interface AlgebraSet<T extends Number> {

    boolean isEmpty();

    boolean isNotEmpty();

    boolean has(T element);

    T lower();

    T upper();

}
