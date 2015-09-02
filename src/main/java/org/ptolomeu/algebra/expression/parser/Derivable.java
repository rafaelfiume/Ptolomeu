package org.ptolomeu.algebra.expression.parser;

import org.ptolomeu.util.Stack;

public interface Derivable {

    void derive(Stack<Symbol> parserStack);

}
