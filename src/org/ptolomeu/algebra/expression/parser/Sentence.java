package org.ptolomeu.algebra.expression.parser;

import static org.apache.commons.lang.StringUtils.deleteWhitespace;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import org.apache.commons.lang.Validate;

final class Sentence {

    private final String input;

    private Sentence(String input) {
        Validate.isTrue(isNotBlank(input), "input cannot be blank");

        this.input = input;
    }

    static Sentence newInstance(String input) {
        return new Sentence(input);
    }

    Tokens tokenizer() {
        return Tokens.newInstance(deleteWhitespace(input));
    }

    @Override
    public String toString() {
        return "'" + input + "'";
    }

}
