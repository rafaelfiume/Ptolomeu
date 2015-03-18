package org.ptolomeu.algebra.expression.parser;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.ptolomeu.algebra.expression.parser.Derivations.*;

/**
 * Click <a href="http://rafaelfiume.wordpress.com/2011/02/16/grammars-and-parsers/">here</a> for an explanation about
 * the parser table.
 *
 * <p/>
 * "LL parser table, maps < non-terminal, terminal> pair to action" (Taken from Wikipedia)
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

    private final Map<Key, Derivations> table = new HashMap<>();

    private ParserTable() {
        // /////////// Filling the first line of the table ///////////////////
        table.put(new Key(Symbol.TS_0, Symbol.NTS_EXP), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(Symbol.TS_1, Symbol.NTS_EXP), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(Symbol.TS_2, Symbol.NTS_EXP), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(Symbol.TS_3, Symbol.NTS_EXP), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(Symbol.TS_4, Symbol.NTS_EXP), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(Symbol.TS_5, Symbol.NTS_EXP), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(Symbol.TS_6, Symbol.NTS_EXP), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(Symbol.TS_7, Symbol.NTS_EXP), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(Symbol.TS_8, Symbol.NTS_EXP), REPLACE_EXP_BY_INT_AND_OPER);
        table.put(new Key(Symbol.TS_9, Symbol.NTS_EXP), REPLACE_EXP_BY_INT_AND_OPER);
        // /////////// Filling the second line of the table //////////////////
        table.put(new Key(Symbol.TS_PLUS, Symbol.NTS_OPER), REPLACE_OPER_BY_PLUS_AND_EXP);
        table.put(new Key(Symbol.TS_MINUS, Symbol.NTS_OPER), REPLACE_OPER_BY_MINUS_AND_EXP);
        table.put(new Key(Symbol.TS_EOF, Symbol.NTS_OPER), REPLACE_OPER_BY_EOF);
        // /////////// Filling the third line of the table //////////////////
        table.put(new Key(Symbol.TS_0, Symbol.NTS_INT), REPLACE_INT_BY_0);
        table.put(new Key(Symbol.TS_1, Symbol.NTS_INT), REPLACE_INT_BY_1);
        table.put(new Key(Symbol.TS_2, Symbol.NTS_INT), REPLACE_INT_BY_2);
        table.put(new Key(Symbol.TS_3, Symbol.NTS_INT), REPLACE_INT_BY_3);
        table.put(new Key(Symbol.TS_4, Symbol.NTS_INT), REPLACE_INT_BY_4);
        table.put(new Key(Symbol.TS_5, Symbol.NTS_INT), REPLACE_INT_BY_5);
        table.put(new Key(Symbol.TS_6, Symbol.NTS_INT), REPLACE_INT_BY_6);
        table.put(new Key(Symbol.TS_7, Symbol.NTS_INT), REPLACE_INT_BY_7);
        table.put(new Key(Symbol.TS_8, Symbol.NTS_INT), REPLACE_INT_BY_8);
        table.put(new Key(Symbol.TS_9, Symbol.NTS_INT), REPLACE_INT_BY_9);
    }

    static ParserTable newInstance() {
        return new ParserTable();
    }

    Derivations actionToTake(Symbol nextToken, Symbol currentNonTerminal) {
        final Derivations action = table.get(new Key(nextToken, currentNonTerminal));

        if (action == null) {
            throw new IllegalStateException("unknown operation for: " + nextToken);
        }

        return action;
    }

    final class Key {

        private final Symbol inputToken;

        private final Symbol stackSymbol;

        public Key(Symbol inputToken, Symbol stackSymbol) {
            this.inputToken = inputToken;
            this.stackSymbol = stackSymbol;
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
            return new EqualsBuilder().append(inputToken, ((Key) other).inputToken)
                    .append(stackSymbol, ((Key) other).stackSymbol).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(inputToken).append(stackSymbol).hashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append(inputToken).append(stackSymbol).toString();
        }

    }

}
