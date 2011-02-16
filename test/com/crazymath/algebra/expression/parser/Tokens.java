package com.crazymath.algebra.expression.parser;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;

final class Tokens implements Iterable<Symbol> {

    private final List<Symbol> tokens = new ArrayList<Symbol>();

    private int cursor = 0;

    private Tokens() {
        // Instantiated via method factory
    }

    static Tokens newInstance(String input) {
        Validate.isTrue(isNotBlank(input), "input cannot be blank");

        final Tokens tokens = new Tokens();
        final char[] splited = input.toCharArray();
        for (char aChar : splited) {
            tokens.add(Symbol.of(aChar));
        }
        tokens.add(Symbol.TS_EOF);

        return tokens;
    }

    @Override
    public Iterator<Symbol> iterator() {
        return tokens.iterator(); // TODO Encapsulate iterator
    }

    public boolean hasNext() {
        return cursor < tokens.size();
    }

    void moveCursor() {
        cursor++;
    }

    public Symbol get() {
        return tokens.get(cursor);
    }

    private void add(Symbol symbol) {
        tokens.add(symbol);
    }

}
