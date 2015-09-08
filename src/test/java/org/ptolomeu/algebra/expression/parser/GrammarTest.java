package org.ptolomeu.algebra.expression.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.ptolomeu.algebra.expression.parser.Derivation.EXP_BY_INT_AND_OPER;
import static org.ptolomeu.algebra.expression.parser.Derivation.INT_BY_0;
import static org.ptolomeu.algebra.expression.parser.Derivation.INT_BY_1;
import static org.ptolomeu.algebra.expression.parser.Derivation.INT_BY_2;
import static org.ptolomeu.algebra.expression.parser.Derivation.INT_BY_3;
import static org.ptolomeu.algebra.expression.parser.Derivation.INT_BY_4;
import static org.ptolomeu.algebra.expression.parser.Derivation.INT_BY_5;
import static org.ptolomeu.algebra.expression.parser.Derivation.INT_BY_6;
import static org.ptolomeu.algebra.expression.parser.Derivation.INT_BY_7;
import static org.ptolomeu.algebra.expression.parser.Derivation.INT_BY_8;
import static org.ptolomeu.algebra.expression.parser.Derivation.INT_BY_9;
import static org.ptolomeu.algebra.expression.parser.Derivation.OPER_BY_DIV_AND_EXP;
import static org.ptolomeu.algebra.expression.parser.Derivation.OPER_BY_EOF;
import static org.ptolomeu.algebra.expression.parser.Derivation.OPER_BY_MINUS_AND_EXP;
import static org.ptolomeu.algebra.expression.parser.Derivation.OPER_BY_MULT_AND_EXP;
import static org.ptolomeu.algebra.expression.parser.Derivation.OPER_BY_PLUS_AND_EXP;
import static org.ptolomeu.algebra.expression.parser.Symbol.NTS_EXP;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_0;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.yatspec.junit.Notes;
import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.Table;

@Notes("This is the current grammar for expressions that can be parsed:<p/>" 
        + "<ul>" 
        + "<li><code>Exp -> Int Oper</code></li>"
        + "<li><code>Oper -> + Exp</li>"
        + "<li><code>Oper -> - Exp</li>"
        + "<li><code>Oper -> epsilon</li>"
        + "<li><code>Int -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9</code></li>"
        + "</ul>"
        + "</p>"
        + ""
        + "<h3>First and Follow Functions</h3>"
        + "</p>"
        + "<strong>First:</strong>"
        + "<ul>"
        + "<li>First(Exp) -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9</li>"
        + "<li>First(Oper) -> + | - | epsilon</li>"
        + "<li>First(Int) -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9</li>"
        + "</ul>"
        + ""
        + "<strong>Follow:</strong>"
        + "<ul>"
        + "<li>Follow(Exp) -> $</li>"
        + "<li>Follow(Oper) -> $</li>"
        + "<li>Follow(Int) -> + | - | $</li>"
        + "</ul>"
        + ""
        + "<h3>Parser Table</h3>"
        + ""
        + "<table>"
        + "<tr>"
        + "<td style=\"background-color: #d8d8d8; text-align: center;\"></td>"
        + "<td style=\"background-color: #d8d8d8; text-align: center;\"><strong>+</strong></td>"
        + "<td style=\"background-color: #d8d8d8; text-align: center;\"><strong>-</strong></td>"
        + "<td style=\"background-color: #d8d8d8; text-align: center;\"><strong>0</strong></td>"
        + "<td style=\"background-color: #d8d8d8; text-align: center;\"><strong>1</strong></td>"
        + "<td style=\"background-color: #d8d8d8; text-align: center;\"><strong>. . .</strong></td>"
        + "<td style=\"background-color: #d8d8d8; text-align: center;\"><strong>9</strong></td>"
        + "<td style=\"background-color: #d8d8d8; text-align: center;\"><strong>$</strong></td>"
        + "</tr>"

        + "<tr>"
        + "<td style=\"background-color: #d8d8d8;\">Exp</td>"
        + "<td style=\"text-align: center;\">N/A</td>"
        + "<td style=\"text-align: center;\">N/A</td>"
        + "<td style=\"text-align: center;\">Exp -> Int Add'</td>"
        + "<td style=\"text-align: center;\">Exp -> Int Add'</td>"
        + "<td style=\"text-align: center;\">. . .</td>"
        + "<td style=\"text-align: center;\">Exp -> Int Add'</td>"
        + "<td style=\"text-align: center;\">N/A</td>"
        + "</tr>"

        + "<tr>"
        + "<td style=\"background-color: #d8d8d8;\">Oper</td>"
        + "<td style=\"text-align: center;\">Oper -> + Int Oper</td>"
        + "<td style=\"text-align: center;\">Oper -> - Int Oper</td>"
        + "<td style=\"text-align: center;\">N/A</td>"
        + "<td style=\"text-align: center;\">N/A</td>"
        + "<td style=\"text-align: center;\">. . .</td>"
        + "<td style=\"text-align: center;\">N/A</td>"
        + "<td style=\"text-align: center;\">Oper -> epsilon</td>"
        + "</tr>"
        
        + "<tr>"
        + "<td style=\"background-color: #d8d8d8;\">Int</td>"
        + "<td style=\"text-align: center;\">N/A</td>"
        + "<td style=\"text-align: center;\">N/A</td>"
        + "<td style=\"text-align: center;\">Int -> 0</td>"
        + "<td style=\"text-align: center;\">Int -> 2</td>"
        + "<td style=\"text-align: center;\">. . .</td>"
        + "<td style=\"text-align: center;\">Int -> 9</td>"
        + "<td style=\"text-align: center;\">N/A</td>"
        + "</tr>"
        
        + "</tr>"
        + "</table>"
        + "</p>"
        + "Click <a href=\"http://rafaelfiume.wordpress.com/2011/02/16/grammars-and-parsers/\">*here*</a> to see more about parsers and grammars."
        
)
@RunWith(SpecRunner.class)
public class GrammarTest {
    
    private static final String EOF = "EOF";

    private ParserTable parserTable = ParserTable.newInstance();

    @Notes("This is how a current nonterminal symbol found in an expression will be derived based on the next terminal (lookahead).")
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
        @Row({"OPER", "*", "OPER -> * EXP"}),
        @Row({"OPER", "/", "OPER -> / EXP"}),
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
        assertThat(derivation(forNonTerminal(nonTerminal), andLookahead(lookahead)), is(derivation));
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
    
    private Derivation derivation(Symbol forNonTerminal, Symbol andLookahead) {
        return parserTable.actionToTake(forNonTerminal, andLookahead);
    }
    
    private Symbol forNonTerminal(String currentNonTerminal) {
        return Symbol.valueOf("NTS_" + currentNonTerminal);
    }

    private Symbol andLookahead(String token) {
        if (EOF.equals(token)) return Symbol.TS_EOF;
        
        return Symbol.lexer(token.charAt(0));
    }
    
    private Matcher<Derivation> is(String derivation) {
        switch(derivation) {
            case "EXP -> INT OPER": return Matchers.is(EXP_BY_INT_AND_OPER);
            case "OPER -> * EXP": return Matchers.is(OPER_BY_MULT_AND_EXP);
            case "OPER -> / EXP": return Matchers.is(OPER_BY_DIV_AND_EXP);
            case "OPER -> + EXP": return Matchers.is(OPER_BY_PLUS_AND_EXP);
            case "OPER -> - EXP": return Matchers.is(OPER_BY_MINUS_AND_EXP);
            case "OPER -> epsilon": return Matchers.is(OPER_BY_EOF);
            case "INT -> 0": return Matchers.is(INT_BY_0);
            case "INT -> 1": return Matchers.is(INT_BY_1);
            case "INT -> 2": return Matchers.is(INT_BY_2);
            case "INT -> 3": return Matchers.is(INT_BY_3);
            case "INT -> 4": return Matchers.is(INT_BY_4);
            case "INT -> 5": return Matchers.is(INT_BY_5);
            case "INT -> 6": return Matchers.is(INT_BY_6);
            case "INT -> 7": return Matchers.is(INT_BY_7);
            case "INT -> 8": return Matchers.is(INT_BY_8);
            case "INT -> 9": return Matchers.is(INT_BY_9);
            
            default: return null;
        }
    }

}