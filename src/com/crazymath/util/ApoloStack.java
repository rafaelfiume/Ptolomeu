package com.crazymath.util;

import java.util.ArrayDeque;
import java.util.Deque;

public final class ApoloStack<T> {

    private final Deque<T> deque = new ArrayDeque<T>();

    private ApoloStack() {
        // Instantiated via method factory
    }

    public static <E> ApoloStack<E> newInstance() {
        return new ApoloStack<E>();
    }

    public void pop(T element) {
        deque.addLast(element);
    }

    public T push() {
        return deque.removeLast();
    }

    public T peek() {
        return deque.peekLast();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

}
