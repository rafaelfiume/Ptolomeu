package com.crazymath.algebra.set;

import java.util.Iterator;

public interface NumericSet<T extends Number> extends Iterable<T> {

	void add(T element);

	void add(T... elements);

	boolean isEmpty();

	boolean has(T element);

	boolean contains(NumericSet<T> other);
	
	Iterator<T> iterator();
	
	T lower();

}