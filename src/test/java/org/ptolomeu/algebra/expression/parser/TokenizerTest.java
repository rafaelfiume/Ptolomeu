package org.ptolomeu.algebra.expression.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TokenizerTest {

    private Tokens tokens;

    @Before
    public void setUp() {
        tokens = Sentence.newInstance("9 + 2 + 3").tokenizer();
    }

    @Test
    public void testTokenizer() {
        assertEquals(Symbol.TS_9, tokens.current());
        tokens.moveToNext();
        assertEquals(Symbol.TS_PLUS, tokens.current());
        tokens.moveToNext();
        assertEquals(Symbol.TS_2, tokens.current());
        tokens.moveToNext();
        assertEquals(Symbol.TS_PLUS, tokens.current());
        tokens.moveToNext();
        assertEquals(Symbol.TS_3, tokens.current());
        tokens.moveToNext();
        assertEquals(Symbol.TS_EOF, tokens.current());
    }

    @Test
    public void testMoveToNext() {
        assertEquals(Symbol.TS_9, tokens.current());
        tokens.moveToNext();
        assertEquals(Symbol.TS_PLUS, tokens.current());
        tokens.moveToNext();
        tokens.moveToNext();
        tokens.moveToNext();
        assertEquals(Symbol.TS_3, tokens.current());
        tokens.moveToNext();
        assertEquals(Symbol.TS_EOF, tokens.current());
    }

    @Test
    public void testHasNext() {
        assertTrue(tokens.hasNext());
        tokens.moveToNext(); // +
        tokens.moveToNext(); // 2
        tokens.moveToNext(); // +
        tokens.moveToNext(); // 3
        tokens.moveToNext(); // $
        assertTrue(tokens.hasNext()); // has $
        tokens.moveToNext(); // nothing here
        assertFalse(tokens.hasNext());
    }

}
