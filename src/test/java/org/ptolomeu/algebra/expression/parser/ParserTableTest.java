package org.ptolomeu.algebra.expression.parser;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.Table;

import static org.junit.Assert.*;
import static org.ptolomeu.algebra.expression.parser.Derivations.*;
import static org.ptolomeu.algebra.expression.parser.Symbol.*;

@RunWith(SpecRunner.class)
public class ParserTableTest {
    
    private static final String EOF = "EOF";

    private ParserTable parserTable = ParserTable.newInstance();

    @Test
    @Table({
        @Row({"0", "EXP", "EXP -> INT OPER"}),
        @Row({"1", "EXP", "EXP -> INT OPER"}),
        @Row({"2", "EXP", "EXP -> INT OPER"}),
        @Row({"3", "EXP", "EXP -> INT OPER"}),
        @Row({"4", "EXP", "EXP -> INT OPER"}),
        @Row({"5", "EXP", "EXP -> INT OPER"}),
        @Row({"6", "EXP", "EXP -> INT OPER"}),
        @Row({"7", "EXP", "EXP -> INT OPER"}),
        @Row({"8", "EXP", "EXP -> INT OPER"}),
        @Row({"9", "EXP", "EXP -> INT OPER"}),        
        @Row({"+", "OPER", "OPER -> + EXP"}),
        @Row({"-", "OPER", "OPER -> - EXP"}),
        @Row({EOF, "OPER", "OPER -> epsilon"}),
        @Row({"0", "INT", "INT -> 0"}),
        @Row({"1", "INT", "INT -> 1"}),
        @Row({"2", "INT", "INT -> 2"}),
        @Row({"3", "INT", "INT -> 3"}),
        @Row({"4", "INT", "INT -> 4"}),
        @Row({"5", "INT", "INT -> 5"}),
        @Row({"6", "INT", "INT -> 6"}),
        @Row({"7", "INT", "INT -> 7"}),
        @Row({"8", "INT", "INT -> 8"}),
        @Row({"9", "INT", "INT -> 9"})
        })
    public void deriveToken(String nextToken, String nonTerminal, String derivation) throws Exception {
        assertThat(parserTable.actionToTake(forNextToken(nextToken), andNonTerminal(nonTerminal)), is(derivation));
    }
    
    @Test
    public void invalidDerivation() {
        // ADD
        try {
            parserTable.actionToTake(TS_0, NTS_EXP);
            // fail
        } catch (IllegalStateException e) {
            assertEquals("unknown operation for: " + TS_0, e.getMessage());
        }
    }
    
    private Symbol andNonTerminal(String currentNonTerminal) {
        return Symbol.valueOf("NTS_" + currentNonTerminal);
    }

    private Symbol forNextToken(String token) {
        if (EOF.equals(token)) return Symbol.TS_EOF;
        
        return Symbol.lexer(token.charAt(0));
    }
    
    private Matcher<Derivations> is(String derivation) {
        switch(derivation) {
            case "EXP -> INT OPER": return Matchers.is(REPLACE_EXP_BY_INT_AND_OPER);
            case "OPER -> + EXP": return Matchers.is(REPLACE_OPER_BY_PLUS_AND_EXP);
            case "OPER -> - EXP": return Matchers.is(REPLACE_OPER_BY_MINUS_AND_EXP);
            case "OPER -> epsilon": return Matchers.is(REPLACE_OPER_BY_EOF);
            case "INT -> 0": return Matchers.is(REPLACE_INT_BY_0);
            case "INT -> 1": return Matchers.is(REPLACE_INT_BY_1);
            case "INT -> 2": return Matchers.is(REPLACE_INT_BY_2);
            case "INT -> 3": return Matchers.is(REPLACE_INT_BY_3);
            case "INT -> 4": return Matchers.is(REPLACE_INT_BY_4);
            case "INT -> 5": return Matchers.is(REPLACE_INT_BY_5);
            case "INT -> 6": return Matchers.is(REPLACE_INT_BY_6);
            case "INT -> 7": return Matchers.is(REPLACE_INT_BY_7);
            case "INT -> 8": return Matchers.is(REPLACE_INT_BY_8);
            case "INT -> 9": return Matchers.is(REPLACE_INT_BY_9);
            
            default: return null;
        }
    }

}