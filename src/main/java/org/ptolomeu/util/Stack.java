package org.ptolomeu.util;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Stack<T> {

    private final Deque<T> deque = new ArrayDeque<T>();

    private Stack() {
        // Instantiated via method factory
    }

    public static <E> Stack<E> newInstance() {
        return new Stack<E>();
    }

    public void push(T element) {
        deque.addLast(element);
    }

    public T pop() {
        return deque.removeLast();
    }

    public T peek() {
        return deque.peekLast();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

}
