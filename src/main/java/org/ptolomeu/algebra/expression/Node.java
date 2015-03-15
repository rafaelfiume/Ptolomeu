package org.ptolomeu.algebra.expression;

public interface Node<T extends Node<T>> {

    void addNode(T constant);

}