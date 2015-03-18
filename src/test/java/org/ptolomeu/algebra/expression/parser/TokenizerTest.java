package org.ptolomeu.algebra.expression.parser;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.ptolomeu.algebra.expression.parser.Symbol.*;

public class TokenizerTest {

    private Tokens tokens;

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
        assertThat(tokens.nextToken(), is(TS_2)); // 2
        assertThat(tokens.nextToken(), is(TS_PLUS)); // +
        assertThat(tokens.nextToken(), is(TS_3)); // 3
        assertTrue(tokens.hasNext());

        assertThat(tokens.nextToken(), is(TS_EOF)); // $
        assertFalse(tokens.hasNext()); // has $

        assertThat(tokens.nextToken(), is(nullValue())); // nothing here
        assertFalse(tokens.hasNext());
    }

}
