package org.ptolomeu.algebra.expression.parser;

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
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_DIV;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_EOF;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_MINUS;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_MULT;
import static org.ptolomeu.algebra.expression.parser.Symbol.TS_PLUS;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

final class ParserTable {

    private final Map<Key, Derivation> table = new HashMap<>();

    private ParserTable() {
        // /////////// First line of the table ///////////////////
        table.put(new Key(NTS_EXP, TS_0), EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_1), EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_2), EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_3), EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_4), EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_5), EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_6), EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_7), EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_8), EXP_BY_INT_AND_OPER);
        table.put(new Key(NTS_EXP, TS_9), EXP_BY_INT_AND_OPER);
        
        // /////////// Second line of the table //////////////////
        table.put(new Key(NTS_OPER, TS_MULT), OPER_BY_MULT_AND_EXP);
        table.put(new Key(NTS_OPER, TS_DIV), OPER_BY_DIV_AND_EXP);
        table.put(new Key(NTS_OPER, TS_PLUS), OPER_BY_PLUS_AND_EXP);
        table.put(new Key(NTS_OPER, TS_MINUS), OPER_BY_MINUS_AND_EXP);
        table.put(new Key(NTS_OPER, TS_EOF), OPER_BY_EOF);
        
        // /////////// Third line...            //////////////////
        table.put(new Key(NTS_INT, TS_0), INT_BY_0);
        table.put(new Key(NTS_INT, TS_1), INT_BY_1);
        table.put(new Key(NTS_INT, TS_2), INT_BY_2);
        table.put(new Key(NTS_INT, TS_3), INT_BY_3);
        table.put(new Key(NTS_INT, TS_4), INT_BY_4);
        table.put(new Key(NTS_INT, TS_5), INT_BY_5);
        table.put(new Key(NTS_INT, TS_6), INT_BY_6);
        table.put(new Key(NTS_INT, TS_7), INT_BY_7);
        table.put(new Key(NTS_INT, TS_8), INT_BY_8);
        table.put(new Key(NTS_INT, TS_9), INT_BY_9);
    }

    static ParserTable newInstance() {
        return new ParserTable();
    }

    Derivation actionToTake(Symbol nonterminal, Symbol lookahead) {
        final Derivation action = table.get(new Key(nonterminal, lookahead));

        if (action == null) {
            throw new IllegalStateException("unknown operation for: (" + nonterminal + ", " + lookahead + ")");
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
