package org.ptolomeu.algebra.expression.parser;

import org.ptolomeu.algebra.expression.tree.AbstractNode;
import org.ptolomeu.algebra.expression.tree.AdditionExp;
import org.ptolomeu.algebra.expression.tree.DivisionExp;
import org.ptolomeu.algebra.expression.tree.MultiplicationExp;
import org.ptolomeu.algebra.expression.tree.SubtractionExp;

enum Symbol implements LexicalElement {

    TS_MULT("*") {
        @Override public boolean isOperator() { return true; }
        @Override public AbstractNode operator() { return new MultiplicationExp(); }
    },
    
    TS_DIV("/") {
        @Override public boolean isOperator() { return true; }
        @Override public AbstractNode operator() { return new DivisionExp(); }
    },
    
    TS_PLUS("+") {
        @Override public boolean isOperator() { return true; }       
        @Override public AbstractNode operator() { return new AdditionExp(); }
    },
    
    TS_MINUS("-") {
        @Override public boolean isOperator() { return true; }
        @Override public AbstractNode operator() { return new SubtractionExp(); }
    },
    
    TS_0("0"),
    TS_1("1"),
    TS_2("2"),
    TS_3("3"),
    TS_4("4"),
    TS_5("5"),
    TS_6("6"),
    TS_7("7"),
    TS_8("8"),
    TS_9("9"),
    TS_EOF("\0"),

    NTS_EXP("EXP"),
    NTS_OPER("OPER"),
    NTS_INT("INT");

    private final String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    final static Symbol lexer(char character) {

        for (final Symbol s : values()) {
            if (s.symbol.equals(Character.toString(character))) {
                return s;
            }
        }

        throw new IllegalStateException("Cannot parse '" + character + "'");
    }
    
    @Override
    public boolean isOperator() { return false; }

    String symbol() {
        return symbol;
    }

    @Override
    public AbstractNode operator() {
        throw new UnsupportedOperationException("not an operator");
    }

}
