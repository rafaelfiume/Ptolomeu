package org.ptolomeu.algebra.expression.parser;

import org.ptolomeu.util.Stack;

import static org.ptolomeu.algebra.expression.parser.Symbol.*;

public enum Derivation implements Derivable {

    EXP_BY_INT_AND_OPER {
        @Override
        public void derive(Stack<Symbol> parserStack) {
            parserStack.pop();
            parserStack.push(NTS_OPER);
            parserStack.push(NTS_INT);
        }
        
        @Override
        public String toString() {
            return "EXP -> INT OPER";
        }
    },
 
    OPER_BY_MULT_AND_EXP {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(NTS_OPER);
            stack.push(NTS_INT);
            stack.push(TS_MULT);
        }
        
        @Override
        public String toString() {
            return "OPER -> * EXP";
        }
    },
    
    OPER_BY_DIV_AND_EXP {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(NTS_OPER);
            stack.push(NTS_INT);
            stack.push(TS_DIV);
        }
        
        @Override
        public String toString() {
            return "OPER -> / EXP";
        }
    },
    
    OPER_BY_PLUS_AND_EXP {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(NTS_OPER);
            stack.push(NTS_INT);
            stack.push(TS_PLUS);
        }
        
        @Override
        public String toString() {
            return "OPER -> + EXP";
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
        
        @Override
        public String toString() {
            return "OPER -> - EXP";
        }
    },
    
    OPER_BY_EOF {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
        }
        
        @Override
        public String toString() {
            return "OPER -> e";
        }
    },

    // Replacing Int by TS_*. It needs to keep the Symbol there so the parser table knows what comes next
    INT_BY_0 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_0);
        }
        
        @Override
        public String toString() {
            return "INT -> 0";
        }
    },
    
    INT_BY_1 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_1);
        }
        
        @Override
        public String toString() {
            return "INT -> 1";
        }
    },
    
    INT_BY_2 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_2);
        }
        
        @Override
        public String toString() {
            return "INT -> 2";
        }
    },
    
    INT_BY_3 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_3);
        }
        
        @Override
        public String toString() {
            return "INT -> 3";
        }
    },
    
    INT_BY_4 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_4);
        }
        
        @Override
        public String toString() {
            return "INT -> 4";
        }
    },
    
    INT_BY_5 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_5);
        }
        
        @Override
        public String toString() {
            return "INT -> 5";
        }
    },
    
    INT_BY_6 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_6);
        }
        
        @Override
        public String toString() {
            return "INT -> 6";
        }
    },
    
    INT_BY_7 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_7);
        }
        
        @Override
        public String toString() {
            return "INT -> 7";
        }
    },
    
    INT_BY_8 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_8);
        }
        
        @Override
        public String toString() {
            return "INT -> 8";
        }
    },
    
    INT_BY_9 {
        @Override
        public void derive(Stack<Symbol> stack) {
            stack.pop();
            stack.push(TS_9);
        }
        
        @Override
        public String toString() {
            return "INT -> 9";
        }
    };
}
