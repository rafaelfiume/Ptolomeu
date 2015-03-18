package org.ptolomeu.algebra.expression.parser;

enum Symbol {

    TS_PLUS("+"),
    TS_MINUS("-"),
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

    String symbol() {
        return symbol;
    }
}
