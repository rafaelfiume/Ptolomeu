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
        assertThat(parserTable.actionToTake(TS_0, NTS_ADD), is(REPLACE_ADD_BY_INT_AND_ADD_2));
        assertThat(parserTable.actionToTake(TS_1, NTS_ADD), is(REPLACE_ADD_BY_INT_AND_ADD_2));
        assertThat(parserTable.actionToTake(TS_2, NTS_ADD), is(REPLACE_ADD_BY_INT_AND_ADD_2));
        assertThat(parserTable.actionToTake(TS_3, NTS_ADD), is(REPLACE_ADD_BY_INT_AND_ADD_2));
        assertThat(parserTable.actionToTake(TS_4, NTS_ADD), is(REPLACE_ADD_BY_INT_AND_ADD_2));
        assertThat(parserTable.actionToTake(TS_5, NTS_ADD), is(REPLACE_ADD_BY_INT_AND_ADD_2));
        assertThat(parserTable.actionToTake(TS_6, NTS_ADD), is(REPLACE_ADD_BY_INT_AND_ADD_2));
        assertThat(parserTable.actionToTake(TS_7, NTS_ADD), is(REPLACE_ADD_BY_INT_AND_ADD_2));
        assertThat(parserTable.actionToTake(TS_8, NTS_ADD), is(REPLACE_ADD_BY_INT_AND_ADD_2));
        assertThat(parserTable.actionToTake(TS_9, NTS_ADD), is(REPLACE_ADD_BY_INT_AND_ADD_2));

        // ADD'
        assertThat(parserTable.actionToTake(TS_PLUS, NTS_ADD_2), is(REPLACE_ADD_2_BY_PLUS_AND_INT_AND_ADD_2));
        assertThat(parserTable.actionToTake(TS_EOF, NTS_ADD_2), is(REPLACE_ADD_2_BY_EOF));

        // INT
        assertThat(parserTable.actionToTake(TS_0, NTS_INT), is(REPLACE_INT_BY_0));
        assertThat(parserTable.actionToTake(TS_1, NTS_INT), is(REPLACE_INT_BY_1));
        assertThat(parserTable.actionToTake(TS_2, NTS_INT), is(REPLACE_INT_BY_2));
        assertThat(parserTable.actionToTake(TS_3, NTS_INT), is(REPLACE_INT_BY_3));
        assertThat(parserTable.actionToTake(TS_4, NTS_INT), is(REPLACE_INT_BY_4));
        assertThat(parserTable.actionToTake(TS_5, NTS_INT), is(REPLACE_INT_BY_5));
        assertThat(parserTable.actionToTake(TS_6, NTS_INT), is(REPLACE_INT_BY_6));
        assertThat(parserTable.actionToTake(TS_7, NTS_INT), is(REPLACE_INT_BY_7));
        assertThat(parserTable.actionToTake(TS_8, NTS_INT), is(REPLACE_INT_BY_8));
        assertThat(parserTable.actionToTake(TS_9, NTS_INT), is(REPLACE_INT_BY_9));
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