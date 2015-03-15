package org.ptolomeu.algebra.expression.parser;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class ParserTest {

    private Parser parser = new Parser();

    @Test
    public void parseExpression() {
        assertThat(parser.parse("2 + 9 + 0 + 7 + 3 + 5"), is(26));
        assertThat(parser.parse("0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9"), is(45));
        assertThat(parser.parse("1 + 9 + 4"), is(14));
    }

    @Test
    public void failToParseInvalidExpression() {
        assertThat(parser.parse("2 + "), is(-1)); // TODO RF Temporarily returning negative number indicate parse error
        assertThat(parser.parse("+ 2"), is(-1));
        assertThat(parser.parse("+"), is(-1));
    }
}
