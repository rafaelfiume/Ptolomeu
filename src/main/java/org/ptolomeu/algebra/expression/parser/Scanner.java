package org.ptolomeu.algebra.expression.parser;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

final class Scanner {

    private final List<Symbol> tokens = new ArrayList<>();

    private final char[] splited;
    
    private int cursor = 0;

    static Scanner newInstance(String input) {
        Validate.isTrue(isNotBlank(input), "input cannot be blank");

        return new Scanner(input);
    }
    
    private Scanner(String input) {
        this.splited = input.toCharArray();
        for (char aChar : splited) {
            this.add(Symbol.lexer(aChar));
        }
        this.add(Symbol.TS_EOF);

    }

    public boolean hasNext() {
        return cursor < (tokens.size() - 1);
    }

    Symbol nextToken() {
        return hasNext() ? tokens.get(++cursor) : null;

    }

    public Symbol current() {
        return tokens.get(cursor);
    }

    private void add(Symbol symbol) {
        tokens.add(symbol);
    }

    public String remainingSentence() {
        return new StringBuilder().append(splited, cursor, splited.length - cursor).toString();
    }

}
