package org.ptolomeu.algebra.expression.parser;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_2;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_3;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_9;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_EOF;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_PLUS;

import org.junit.Before;
import org.junit.Test;

public class ScannerTest {

    private Scanner scanner;

    @Before
    public void setUp() {
        scanner = Sentence.newInstance("9 + 2 + 3").tokenizer();
    }

    @Test
    public void testTokenizer() {
        assertEquals(TS_9, scanner.current());
        scanner.nextToken();
        assertEquals(TS_PLUS, scanner.current());
        scanner.nextToken();
        assertEquals(TS_2, scanner.current());
        scanner.nextToken();
        assertEquals(TS_PLUS, scanner.current());
        scanner.nextToken();
        assertEquals(TS_3, scanner.current());
        scanner.nextToken();
        assertEquals(TS_EOF, scanner.current());
    }

    @Test
    public void testHasNext() {
        assertTrue(scanner.hasNext());

        assertThat(scanner.nextToken(), is(TS_PLUS));
        assertThat(scanner.nextToken(), is(TS_2));
        assertThat(scanner.nextToken(), is(TS_PLUS));
        assertThat(scanner.nextToken(), is(TS_3));
        assertTrue(scanner.hasNext());

        assertThat(scanner.nextToken(), is(TS_EOF));
        assertFalse(scanner.hasNext());

        assertThat(scanner.nextToken(), is(nullValue()));
        assertFalse(scanner.hasNext());
    }
    
    @Test
    public void remainingSentence() throws Exception {
        assertThat(scanner.remainingSentence(), is("9+2+3"));
        
        scanner.nextToken();
        assertThat(scanner.remainingSentence(), is("+2+3"));
        
        scanner.nextToken();
        scanner.nextToken();
        assertThat(scanner.remainingSentence(), is("+3"));
        
        scanner.nextToken();
        scanner.nextToken();
        assertThat(scanner.remainingSentence(), is(""));

    }

}
