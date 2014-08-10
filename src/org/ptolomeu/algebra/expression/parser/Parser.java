package org.ptolomeu.algebra.expression.parser;

import org.ptolomeu.util.ApoloStack;

import java.util.logging.Logger;

public class Parser {

    private static Logger LOG = Logger.getLogger(Parser.class.getName());

    final ParserTable parserTable = ParserTable.newInstance();

    final ApoloStack<Symbol> stack = ApoloStack.newInstance();

    public boolean checkGrammar(String input) {
        stack.push(Symbol.TS_EOF);
        stack.push(Symbol.NTS_ADD);

        final Sentence sentence = Sentence.newInstance(input);
        final Tokens tokens = sentence.tokenizer();

        while (tokens.hasNext()) {
            if (stack.peek() == Symbol.TS_EOF) {
                stack.pop();
                LOG.info("Yesss!! Bananas!");
                return true;
            }

            if (stack.peek() == tokens.current()) {
                stack.pop();
                tokens.moveToNext();
                continue;
            }

            final Derivations actionToTake;
            try {
                actionToTake = parserTable.actionToTake(tokens.current(), stack.peek());
            } catch (IllegalStateException e) {
                LOG.info("error while parsing the sencence: " + sentence);
                return false;
            }

            switch (actionToTake) {
                case REPLACE_ADD_BY_INT_AND_ADD_2:
                    stack.pop();
                    stack.push(Symbol.NTS_ADD_2);
                    stack.push(Symbol.NTS_INT);
                    break;

                case REPLACE_ADD_2_BY_PLUS_AND_INT_AND_ADD_2:
                    stack.pop();
                    stack.push(Symbol.NTS_ADD_2);
                    stack.push(Symbol.NTS_INT);
                    stack.push(Symbol.TS_PLUS);
                    break;

                case REPLACE_ADD_2_BY_EOF:
                    stack.pop();
                    break;

                case REPLACE_INT_BY_0:
                    stack.pop();
                    stack.push(Symbol.TS_0);
                    break;

                case REPLACE_INT_BY_1:
                    stack.pop();
                    stack.push(Symbol.TS_1);
                    break;

                case REPLACE_INT_BY_2:
                    stack.pop();
                    stack.push(Symbol.TS_2);
                    break;

                case REPLACE_INT_BY_3:
                    stack.pop();
                    stack.push(Symbol.TS_3);
                    break;

                case REPLACE_INT_BY_4:
                    stack.pop();
                    stack.push(Symbol.TS_4);
                    break;

                case REPLACE_INT_BY_5:
                    stack.pop();
                    stack.push(Symbol.TS_5);
                    break;

                case REPLACE_INT_BY_6:
                    stack.pop();
                    stack.push(Symbol.TS_6);
                    break;

                case REPLACE_INT_BY_7:
                    stack.pop();
                    stack.push(Symbol.TS_7);
                    break;

                case REPLACE_INT_BY_8:
                    stack.pop();
                    stack.push(Symbol.TS_8);
                    break;

                case REPLACE_INT_BY_9:
                    stack.pop();
                    stack.push(Symbol.TS_9);
                    break;

                default:
                    throw new IllegalStateException("unknown action: " + actionToTake);
            }
        }

        LOG.info("error while parsing the sencence: " + sentence);
        return false;
    }

    public static void main(String... args) {
        new Parser().checkGrammar("1 + 9 + 0 + 7 + 3 + 5");
    }

}
