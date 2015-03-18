package org.ptolomeu.algebra.expression.parser;

import org.junit.Test;

public class ParseErrorTest {

    private final Parser parser = new Parser();

    @Test(expected = IllegalStateException.class)
    public void failToParseInvalidAdditionExpression() {
        parser.parse("2 + ");
    }

    @Test(expected = IllegalStateException.class)
    public void failToParseInvalidAdditionExpression2() {
        parser.parse("+ 2");
    }

    @Test(expected = IllegalStateException.class)
    public void failToParseInvalidAdditionExpression3() {
        parser.parse("+");
    }

    @Test(expected = IllegalStateException.class)
    public void failToParseInvalidSubstractionExpression() {
        parser.parse("2 - ");
    }

    @Test(expected = IllegalStateException.class)
    public void failToParseInvalidSubstractionExpression2() {
        parser.parse("- 2");
    }

    @Test(expected = IllegalStateException.class)
    public void failToParseInvalidSubstractionExpression3() {
        parser.parse("-");
    }
}