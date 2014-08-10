package org.ptolomeu.algebra.expression.parser;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

final class Tokens {

    private final List<Symbol> tokens = new ArrayList<>();

    private int cursor = 0;

    private Tokens() {
        // Instantiated via method factory
    }

    static Tokens newInstance(String input) {
        Validate.isTrue(isNotBlank(input), "input cannot be blank");

        final Tokens tokens = new Tokens();
        final char[] splited = input.toCharArray();
        for (char aChar : splited) {
            tokens.add(Symbol.lexer(aChar));
        }
        tokens.add(Symbol.TS_EOF);

        return tokens;
    }

    public boolean hasNext() {
        return cursor < tokens.size();
    }

    void moveToNext() {
        cursor++;
    }

    public Symbol current() {
        return tokens.get(cursor);
    }

    private void add(Symbol symbol) {
        tokens.add(symbol);
    }

}
