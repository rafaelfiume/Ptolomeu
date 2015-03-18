package org.ptolomeu.algebra.expression.parser;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_2;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_3;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_9;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_EOF;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_PLUS;

import org.junit.Before;
import org.junit.Test;

public class ScannerTest {

    private Scanner tokens;

    @Before
    public void setUp() {
        tokens = Sentence.newInstance("9 + 2 + 3").tokenizer();
    }

    @Test
    public void testTokenizer() {
        assertEquals(TS_9, tokens.current());
        tokens.nextToken();
        assertEquals(TS_PLUS, tokens.current());
        tokens.nextToken();
        assertEquals(TS_2, tokens.current());
        tokens.nextToken();
        assertEquals(TS_PLUS, tokens.current());
        tokens.nextToken();
        assertEquals(TS_3, tokens.current());
        tokens.nextToken();
        assertEquals(TS_EOF, tokens.current());
    }

    @Test
    public void testHasNext() {
        assertTrue(tokens.hasNext());

        assertThat(tokens.nextToken(), is(TS_PLUS));
        assertThat(tokens.nextToken(), is(TS_2));
        assertThat(tokens.nextToken(), is(TS_PLUS));
        assertThat(tokens.nextToken(), is(TS_3));
        assertTrue(tokens.hasNext());

        assertThat(tokens.nextToken(), is(TS_EOF));
        assertFalse(tokens.hasNext());

        assertThat(tokens.nextToken(), is(nullValue()));
        assertFalse(tokens.hasNext());
    }

}
