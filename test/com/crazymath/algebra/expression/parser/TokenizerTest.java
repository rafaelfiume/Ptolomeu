package com.crazymath.algebra.expression.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

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
        final Iterator<Symbols> it = tokens.iterator();
        assertEquals(Symbols.TS_9, it.next());
        assertEquals(Symbols.TS_PLUS, it.next());
        assertEquals(Symbols.TS_2, it.next());
        assertEquals(Symbols.TS_PLUS, it.next());
        assertEquals(Symbols.TS_3, it.next());
        assertEquals(Symbols.TS_EOF, it.next());
    }

    @Test
    public void testMoveCursor() {
        assertEquals(Symbols.TS_9, tokens.get());
        tokens.moveCursor();
        assertEquals(Symbols.TS_PLUS, tokens.get());
        tokens.moveCursor();
        tokens.moveCursor();
        tokens.moveCursor();
        assertEquals(Symbols.TS_3, tokens.get());
        tokens.moveCursor();
        assertEquals(Symbols.TS_EOF, tokens.get());
    }

    @Test
    public void testHasNext() {
        assertTrue(tokens.hasNext());
        tokens.moveCursor(); // +
        tokens.moveCursor(); // 2
        tokens.moveCursor(); // +
        tokens.moveCursor(); // 3
        tokens.moveCursor(); // $
        assertTrue(tokens.hasNext()); // has $
        tokens.moveCursor(); // nothing here
        assertFalse(tokens.hasNext());
    }

}
