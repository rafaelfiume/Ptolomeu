package org.ptolomeu.algebra.expression.parser;

import org.ptolomeu.util.Stack;

public interface Derivation {

    void derive(Stack<Symbol> parserStack);

}
