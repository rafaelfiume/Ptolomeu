package org.ptolomeu.algebra.expression.parser;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.ptolomeu.algebra.expression.parser.Derivations.*;
import static org.ptolomeu.algebra.expression.parser.Symbol.*;

public class ParserTableTest {

    private ParserTable parserTable = ParserTable.newInstance();

    @Test
    public void deriveToken() throws Exception {
        // ADD
        assertTrue(REPLACE_ADD_BY_INT_AND_ADD_2 == parserTable.actionToTake(TS_0, NTS_ADD));
        // till ...
        assertTrue(REPLACE_ADD_BY_INT_AND_ADD_2 == parserTable.actionToTake(TS_9, NTS_ADD));

        // ADD'
        assertTrue(REPLACE_ADD_2_BY_PLUS_AND_INT_AND_ADD_2 == parserTable.actionToTake(TS_PLUS, NTS_ADD_2));
        assertTrue(REPLACE_ADD_2_BY_EOF == parserTable.actionToTake(TS_EOF, NTS_ADD_2));

        assertTrue(REPLACE_INT_BY_0 == parserTable.actionToTake(TS_0, NTS_INT));
        assertTrue(REPLACE_INT_BY_1 == parserTable.actionToTake(TS_1, NTS_INT));
        assertTrue(REPLACE_INT_BY_2 == parserTable.actionToTake(TS_2, NTS_INT));
        assertTrue(REPLACE_INT_BY_3 == parserTable.actionToTake(TS_3, NTS_INT));
        assertTrue(REPLACE_INT_BY_4 == parserTable.actionToTake(TS_4, NTS_INT));
        assertTrue(REPLACE_INT_BY_5 == parserTable.actionToTake(TS_5, NTS_INT));
        assertTrue(REPLACE_INT_BY_6 == parserTable.actionToTake(TS_6, NTS_INT));
        assertTrue(REPLACE_INT_BY_7 == parserTable.actionToTake(TS_7, NTS_INT));
        assertTrue(REPLACE_INT_BY_8 == parserTable.actionToTake(TS_8, NTS_INT));
        assertTrue(REPLACE_INT_BY_9 == parserTable.actionToTake(TS_9, NTS_INT));
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