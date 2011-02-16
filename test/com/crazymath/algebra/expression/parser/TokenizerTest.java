package com.crazymath.algebra.expression.parser;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

public class TokenizerTest {

    @Test
    public void testTokenizer() {
        final Sentence sentence = Sentence.newInstance("9 + 2 + 3");
        final Tokens tokens = sentence.tokenizer();
        final Iterator<Symbols> it = tokens.iterator();
        assertEquals(Symbols.TS_9, it.next());
        assertEquals(Symbols.TS_PLUS, it.next());
        assertEquals(Symbols.TS_2, it.next());
        assertEquals(Symbols.TS_PLUS, it.next());
        assertEquals(Symbols.TS_3, it.next());
    }

}
