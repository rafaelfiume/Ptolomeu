package org.ptolomeu.algebra.expression.parser;

import org.ptolomeu.algebra.expression.tree.AbstractNode;

public interface LexicalElement {
        
    boolean isOperator();
    
    AbstractNode operator();

}
