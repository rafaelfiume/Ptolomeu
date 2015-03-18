package org.ptolomeu.algebra.expression.parser;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    public void addition() {
        assertThat(parser.parse("2 + 9 + 0 + 7 + 3 + 5"), is(26));
        assertThat(parser.parse("0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9"), is(45));
        assertThat(parser.parse("1 + 9 + 4"), is(14));
    }

    @Test
    public void subtraction() throws Exception {
        assertThat(parser.parse("1 - 1 - 0 + 7 - 5"), is(2));
        assertThat(parser.parse("1 + 1 - 7 + 2"), is(-3));
        assertThat(parser.parse("1 - 9 - 4"), is(-12));
    }

}
