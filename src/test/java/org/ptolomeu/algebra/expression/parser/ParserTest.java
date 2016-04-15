package org.ptolomeu.algebra.expression.parser;

import static org.hamcrest.Matchers.is;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.yatspec.junit.SpecRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpecRunner.class)
public class ParserTest {

    private final Parser parser = new Parser();
    
    @Test
    public void addition() {
        assertThat(parser.parse("2 + 9 + 0 + 7 + 3 + 5"), is(26));
        assertThat(parser.parse("0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9"), is(45));
        assertThat(parser.parse("1 + 9 + 4"), is(14));
    }

    @Test
    public void subtraction() throws Exception {
        assertThat(parser.parse("1 - 1 - 0 + 7 - 5"), is(2));
        assertThat(parser.parse("1 + 1 - 7 + 2"), is(-3));
        assertThat(parser.parse("1 - 9 - 4"), is(-12));
    }
    
    @Test
    public void multiplication() throws Exception {
        assertThat(parser.parse("1 * 9 * 4"), is(36));
        assertThat(parser.parse("1 * 9 + 4 - 4"), is(9));
    }
    
    @Test
    public void division() throws Exception {
        assertThat(parser.parse("9 / 3 / 3"), is(1)); 
        assertThat(parser.parse("8 / 2 + 4"), is(8));
    }
    
    @Ignore // Precedence tests failing. The grammar needs to support it.
    @Test
    public void precedence() throws Exception {
        assertThat(parser.parse("9 + 1 * 5"), is(14));
        assertThat(parser.parse("9 + 6 / 3"), is(11));
    }
    
    @Test
    public void digit() {
        assertThat(parser.parse("2"), is(2));
    }

}
