package org.ptolomeu.algebra.expression.parser;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.ptolomeu.algebra.expression.parser.Symbol.*;

public class ParserTableTest {

    private ParserTable parserTable = ParserTable.newInstance();

    @Test
    public void deriveToken() throws Exception {
        // ADD
        assertTrue(1 == parserTable.actionToTake(TS_0, NTS_ADD));
        // till ...
        assertTrue(1 == parserTable.actionToTake(TS_9, NTS_ADD));

        // ADD'
        assertTrue(2 == parserTable.actionToTake(TS_PLUS, NTS_ADD_2));
        assertTrue(3 == parserTable.actionToTake(TS_EOF, NTS_ADD_2));

        assertTrue(4 == parserTable.actionToTake(TS_0, NTS_INT));
        assertTrue(5 == parserTable.actionToTake(TS_1, NTS_INT));
        assertTrue(6 == parserTable.actionToTake(TS_2, NTS_INT));
        assertTrue(7 == parserTable.actionToTake(TS_3, NTS_INT));
        assertTrue(8 == parserTable.actionToTake(TS_4, NTS_INT));
        assertTrue(9 == parserTable.actionToTake(TS_5, NTS_INT));
        assertTrue(10 == parserTable.actionToTake(TS_6, NTS_INT));
        assertTrue(11 == parserTable.actionToTake(TS_7, NTS_INT));
        assertTrue(12 == parserTable.actionToTake(TS_8, NTS_INT));
        assertTrue(13 == parserTable.actionToTake(TS_9, NTS_INT));
    }

    @Test
    public void invalidDerivation() {
        // ADD
        try {
            parserTable.actionToTake(TS_0, NTS_ADD);
            // fail
        } catch (IllegalStateException e) {
            assertEquals("unknown operation for: " + TS_0, e.getMessage());
        }
    }
}