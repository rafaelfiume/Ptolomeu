package org.ptolomeu.algebra.expression.parser;

import org.ptolomeu.util.ApoloStack;

public class Parser {

    final ParserTable parserTable = ParserTable.newInstance();

    final ApoloStack<Symbol> stack = ApoloStack.newInstance();

    String sentence;

    public void checkGrammar(String input) {
        stack.push(Symbol.TS_EOF);
        stack.push(Symbol.NTS_ADD);

        final Sentence sentence = Sentence.newInstance(input);
        final Tokens tokens = sentence.tokenizer();

        while (tokens.hasNext()) {
            if (stack.peek() == Symbol.TS_EOF) {
                stack.pop();
                System.out.println("Yesss!! Bananas!");
                break;
            }

            if (stack.peek() == tokens.current()) {
                stack.pop();
                tokens.moveToNext();
                continue;
            }

            switch (parserTable.actionToTake(tokens.current(), stack.peek())) {
                case 1:
                    stack.pop();
                    stack.push(Symbol.NTS_ADD_2);
                    stack.push(Symbol.NTS_INT);
                    break;

                case 2:
                    stack.pop();
                    stack.push(Symbol.NTS_ADD_2);
                    stack.push(Symbol.NTS_INT);
                    stack.push(Symbol.TS_PLUS);
                    break;

                case 3:
                    stack.pop();
                    break;

                case 4:
                    stack.pop();
                    stack.push(Symbol.TS_0);
                    break;

                case 5:
                    stack.pop();
                    stack.push(Symbol.TS_1);
                    break;

                case 6:
                    stack.pop();
                    stack.push(Symbol.TS_3);
                    break;

                case 7:
                    stack.pop();
                    stack.push(Symbol.TS_3);
                    break;

                case 8:
                    stack.pop();
                    stack.push(Symbol.TS_4);
                    break;

                case 9:
                    stack.pop();
                    stack.push(Symbol.TS_5);
                    break;

                case 10:
                    stack.pop();
                    stack.push(Symbol.TS_6);
                    break;

                case 11:
                    stack.pop();
                    stack.push(Symbol.TS_7);
                    break;

                case 12:
                    stack.pop();
                    stack.push(Symbol.TS_8);
                    break;

                case 13:
                    stack.pop();
                    stack.push(Symbol.TS_9);
                    break;

                default:
                    throw new IllegalStateException("unknown action: "
                            + parserTable.actionToTake(tokens.current(), stack.peek()));

            }
        }

        if (!stack.isEmpty()) {
            throw new IllegalStateException("error while parsing the sencence: " + sentence);
        }
    }

    public static void main(String... args) {
        new Parser().checkGrammar("1 + 9 + 0 + 7 + 3 + 5 ");
    }

}
