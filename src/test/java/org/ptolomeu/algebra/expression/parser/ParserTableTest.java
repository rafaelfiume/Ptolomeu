package org.ptolomeu.algebra.expression.parser;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.Table;

import static org.junit.Assert.*;
import static org.ptolomeu.algebra.expression.parser.Derivation.*;
import static org.ptolomeu.algebra.expression.parser.Symbol.*;

@RunWith(SpecRunner.class)
public class ParserTableTest {
    
    private static final String EOF = "EOF";

    private ParserTable parserTable = ParserTable.newInstance();

    @Test
    @Table({
        @Row({"EXP", "0", "EXP -> INT OPER"}),
        @Row({"EXP", "1", "EXP -> INT OPER"}),
        @Row({"EXP", "2", "EXP -> INT OPER"}),
        @Row({"EXP", "3", "EXP -> INT OPER"}),
        @Row({"EXP", "4", "EXP -> INT OPER"}),
        @Row({"EXP", "5", "EXP -> INT OPER"}),
        @Row({"EXP", "6", "EXP -> INT OPER"}),
        @Row({"EXP", "7", "EXP -> INT OPER"}),
        @Row({"EXP", "8", "EXP -> INT OPER"}),
        @Row({"EXP", "9", "EXP -> INT OPER"}), 
        @Row({"OPER", "+", "OPER -> + EXP"}),
        @Row({"OPER", "-", "OPER -> - EXP"}),
        @Row({"OPER", EOF, "OPER -> epsilon"}),
        @Row({"INT", "0", "INT -> 0"}),
        @Row({"INT", "1", "INT -> 1"}),
        @Row({"INT", "2", "INT -> 2"}),
        @Row({"INT", "3", "INT -> 3"}),
        @Row({"INT", "4", "INT -> 4"}),
        @Row({"INT", "5", "INT -> 5"}),
        @Row({"INT", "6", "INT -> 6"}),
        @Row({"INT", "7", "INT -> 7"}),
        @Row({"INT", "8", "INT -> 8"}),
        @Row({"INT", "9", "INT -> 9"})
    })
    public void deriveToken(String nonTerminal, String lookahead, String derivation) throws Exception {
        assertThat(parserTable.actionToTake(forNonTerminal(nonTerminal), andNextTerminal(lookahead)), is(derivation));
    }
    
    @Test
    public void invalidDerivation() {
        // ADD
        try {
            parserTable.actionToTake(NTS_EXP, TS_0);
            // fail
        } catch (IllegalStateException e) {
            assertEquals("unknown operation for: " + TS_0, e.getMessage());
        }
    }
    
    private Symbol forNonTerminal(String currentNonTerminal) {
        return Symbol.valueOf("NTS_" + currentNonTerminal);
    }

    private Symbol andNextTerminal(String token) {
        if (EOF.equals(token)) return Symbol.TS_EOF;
        
        return Symbol.lexer(token.charAt(0));
    }
    
    private Matcher<Derivation> is(String derivation) {
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