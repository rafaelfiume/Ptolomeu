package com.crazymath.algebra.expression.parser;

import com.crazymath.util.ApoloStack;

public class Parser {

    final ParserTable parserTable = ParserTable.newInstance();

    final ApoloStack<Symbol> stack = ApoloStack.newInstance();

    String sentence;

    public void checkGrammar(String input) {
        stack.pop(Symbol.TS_EOF);// equivalent to push
        stack.pop(Symbol.NTS_ADD);// equivalent to push

        final Sentence sentence = Sentence.newInstance(input);
        final Tokens tokens = sentence.tokenizer();

        while (tokens.hasNext()) {
            if (stack.peek() == Symbol.TS_EOF) {
                stack.push();
                System.out.println("Yesss!! Bananas!");
                break;
            }

            if (stack.peek() == tokens.current()) {
                stack.push();
                tokens.moveToNext();
                continue;
            }

            switch (parserTable.actionToTake(tokens.current(), stack.peek())) {
                case 1:
                    stack.push();
                    stack.pop(Symbol.NTS_ADD_2);
                    stack.pop(Symbol.NTS_INT);
                    break;

                case 2:
                    stack.push();
                    stack.pop(Symbol.NTS_ADD_2);
                    stack.pop(Symbol.NTS_INT);
                    stack.pop(Symbol.TS_PLUS);
                    break;

                case 3:
                    stack.push();
                    break;

                case 4:
                    stack.push();
                    stack.pop(Symbol.TS_0);
                    break;

                case 5:
                    stack.push();
                    stack.pop(Symbol.TS_1);
                    break;

                case 6:
                    stack.push();
                    stack.pop(Symbol.TS_3);
                    break;

                case 7:
                    stack.push();
                    stack.pop(Symbol.TS_3);
                    break;

                case 8:
                    stack.push();
                    stack.pop(Symbol.TS_4);
                    break;

                case 9:
                    stack.push();
                    stack.pop(Symbol.TS_5);
                    break;

                case 10:
                    stack.push();
                    stack.pop(Symbol.TS_6);
                    break;

                case 11:
                    stack.push();
                    stack.pop(Symbol.TS_7);
                    break;

                case 12:
                    stack.push();
                    stack.pop(Symbol.TS_8);
                    break;

                case 13:
                    stack.push();
                    stack.pop(Symbol.TS_9);
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
