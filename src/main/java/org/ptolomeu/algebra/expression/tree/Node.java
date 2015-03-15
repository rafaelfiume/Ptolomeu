package org.ptolomeu.algebra.expression.tree;

public interface Node<T extends Node<T>> {

    void addNode(T constant);

}