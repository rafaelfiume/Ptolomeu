package org.ptolomeu.algebra.expression.parser;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckGrammarTest {

    private Parser parser = new Parser();

    @Test
    public void validGrammar() {
        assertTrue(parser.checkGrammar("2 + 9 + 0 + 7 + 3 + 5"));
        assertTrue(parser.checkGrammar("0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9"));
        assertTrue(parser.checkGrammar("1 + 9 + 4"));
    }

    @Test
    public void invalidGrammar() {
        assertFalse(parser.checkGrammar("2 + "));
        assertFalse(parser.checkGrammar("+ 2"));
        assertFalse(parser.checkGrammar("+"));
    }
}
