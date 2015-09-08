package org.ptolomeu.algebra.expression.parser;

import org.ptolomeu.util.Stack;

import static org.ptolomeu.algebra.expression.parser.Symbol.*;

public enum Derivation implements Derivable {

    // Replacing Add...
    EXP_BY_INT_AND_OPER {
        @Override
        public void derive(Stack<Symbol> parserStack) {
            parserStack.pop();
            parserStack.push(NTS_OPER);
            parserStack.push(NTS_INT);
        }
    },

    /*
     * Replacing Oper by Exp: Exp -> Int Oper
     */
    OPER_BY_PLUS_AND_EXP {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(NTS_OPER);
            stack.push(NTS_INT);
            stack.push(TS_PLUS);
        }
    },
    OPER_BY_MINUS_AND_EXP {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(NTS_OPER);
            stack.push(NTS_INT);
            stack.push(TS_MINUS);
        }
    },
    OPER_BY_EOF {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
        }
    },

    // Replacing Int by TS_*. It needs to keep the Symbol there so the parser table knows what comes next
    INT_BY_0 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_0);
        }
    },
    INT_BY_1 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_1);
        }
    },
    INT_BY_2 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_2);
        }
    },
    INT_BY_3 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_3);
        }
    },
    INT_BY_4 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_4);
        }
    },
    INT_BY_5 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_5);
        }
    },
    INT_BY_6 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_6);
        }
    },
    INT_BY_7 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_7);
        }
    },
    INT_BY_8 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_8);
        }
    },
    INT_BY_9 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_9);
        }
    };
}
