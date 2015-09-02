package org.ptolomeu.algebra.expression.parser;

import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_EXP_BY_INT_AND_OPER;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_INT_BY_0;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_INT_BY_1;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_INT_BY_2;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_INT_BY_3;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_INT_BY_4;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_INT_BY_5;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_INT_BY_6;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_INT_BY_7;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_INT_BY_8;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_INT_BY_9;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_OPER_BY_EOF;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_OPER_BY_MINUS_AND_EXP;
import static org.ptolomeu.algebra.expression.parser.Derivation.REPLACE_OPER_BY_PLUS_AND_EXP;
import static org.ptolomeu.algebra.expression.parser.Symbol.NTS_EXP;
import static org.ptolomeu.algebra.expression.parser.Symbol.NTS_INT;
import static org.ptolomeu.algebra.expression.parser.Symbol.NTS_OPER;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_0;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_1;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_2;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_3;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_4;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_5;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_6;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_7;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_8;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_9;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_EOF;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_MINUS;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_PLUS;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * It decides the next action to take based on the current nonterminal and the next terminal (lookahead) being parsed.</p>
 * Click <a href="http://rafaelfiume.wordpress.com/2011/02/16/grammars-and-parsers/">here</a> for an explanation about
 * the parser table.
 *
 * <p/>
 * 
 * <ol>
 * <li><code>Exp -> Int Oper</code></li>
 * <li><code>Oper -> + Exp</code></li>
 * <li><code>Oper -> - Exp</code></li>
 * <li><code>Oper -> epsilon</code></li>
 * <li><code>Int -> 0 </code></li>
 * <li><code>Int -> 1 </code></li>
 * <li><code>Int -> 2 </code></li>
 * <li><code>Int -> 3 </code></li>
 * <li><code>Int -> 4 </code></li>
 * <li><code>Int -> 5 </code></li>
 * <li><code>Int -> 6 </code></li>
 * <li><code>Int -> 7 </code></li>
 * <li><code>Int -> 8 </code></li>
 * <li><code>Int -> 9 </code></li>
 * </ol>
 */
final class ParserTable {

    private final Map<Key, Derivation> table = new HashMap<>();

    private ParserTable() {
        // /////////// First line of the table ///////////////////
        table.put(new Key(NTS_EXP, TS_0), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_1), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_2), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_3), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_4), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_5), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_6), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_7), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_8), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_9), REPLACE_EXP_BY_INT_AND_OPER);
        
        // /////////// Second line of the table //////////////////
        table.put(new Key(NTS_OPER, TS_PLUS), REPLACE_OPER_BY_PLUS_AND_EXP);
        table.put(new Key(NTS_OPER, TS_MINUS), REPLACE_OPER_BY_MINUS_AND_EXP);
        table.put(new Key(NTS_OPER, TS_EOF), REPLACE_OPER_BY_EOF);
        
        // /////////// Third line...            //////////////////
        table.put(new Key(NTS_INT, TS_0), REPLACE_INT_BY_0);
        table.put(new Key(NTS_INT, TS_1), REPLACE_INT_BY_1);
        table.put(new Key(NTS_INT, TS_2), REPLACE_INT_BY_2);
        table.put(new Key(NTS_INT, TS_3), REPLACE_INT_BY_3);
        table.put(new Key(NTS_INT, TS_4), REPLACE_INT_BY_4);
        table.put(new Key(NTS_INT, TS_5), REPLACE_INT_BY_5);
        table.put(new Key(NTS_INT, TS_6), REPLACE_INT_BY_6);
        table.put(new Key(NTS_INT, TS_7), REPLACE_INT_BY_7);
        table.put(new Key(NTS_INT, TS_8), REPLACE_INT_BY_8);
        table.put(new Key(NTS_INT, TS_9), REPLACE_INT_BY_9);
    }

    static ParserTable newInstance() {
        return new ParserTable();
    }

    Derivation actionToTake(Symbol nonterminal, Symbol lookahead) {
        final Derivation action = table.get(new Key(nonterminal, lookahead));

        if (action == null) {
            throw new IllegalStateException("unknown operation for: " + lookahead);
        }

        return action;
    }

    static final class Key {

        private final Symbol nonterminal;
        
        private final Symbol lookahead;

        public Key(Symbol nonterminal, Symbol lookahead) {
            this.nonterminal = nonterminal;
            this.lookahead = lookahead;

        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (other == this) {
                return true;
            }
            if (other.getClass() != this.getClass()) {
                return false;
            }
            return new EqualsBuilder()
                    .append(nonterminal, ((Key) other).nonterminal)
                    .append(lookahead, ((Key) other).lookahead)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(nonterminal).append(lookahead).hashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append(nonterminal).append(lookahead).toString();
        }

    }

}
