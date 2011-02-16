package com.crazymath.algebra.expression.parser;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;

final class Tokens implements Iterable<Symbols> {

    private final List<Symbols> tokens = new ArrayList<Symbols>();

    private int cursor = 0;

    private Tokens() {
        // Instantiated via method factory
    }

    static Tokens newInstance(String input) {
        Validate.isTrue(isNotBlank(input), "input cannot be blank");

        final Tokens tokens = new Tokens();
        final char[] splited = input.toCharArray();
        for (char aChar : splited) {
            tokens.add(Symbols.of(aChar));
        }
        tokens.add(Symbols.TS_EOF);

        return tokens;
    }

    @Override
    public Iterator<Symbols> iterator() {
        return tokens.iterator(); // TODO Encapsulate iterator
    }

    public boolean hasNext() {
        return cursor < tokens.size();
    }

    void moveCursor() {
        cursor++;
    }

    public Symbols get() {
        return tokens.get(cursor);
    }

    private void add(Symbols symbol) {
        tokens.add(symbol);
    }

}
