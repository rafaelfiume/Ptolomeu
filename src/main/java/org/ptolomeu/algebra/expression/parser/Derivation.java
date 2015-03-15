package org.ptolomeu.algebra.expression.parser;

import org.ptolomeu.util.ApoloStack;

public interface Derivation {

    void derive(ApoloStack<Symbol> parserStack);

}
