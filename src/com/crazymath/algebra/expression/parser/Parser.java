package com.crazymath.algebra.expression.parser;

import java.util.ArrayDeque;
import java.util.Deque;

public class Parser {

    final ParserTable parserTable = ParserTable.newInstance();

    final Deque<Symbol> stack = new ArrayDeque<Symbol>();

    String sentence;

    /**
     * Construct the abstract parse tree.
     */
    public void parse(String input) {
        stack.addLast(Symbol.TS_EOF);// equivalent to push
        stack.addLast(Symbol.NTS_ADD);// equivalent to push

        final Sentence sentence = Sentence.newInstance(input);
        final Tokens tokens = sentence.tokenizer();

        while (tokens.hasNext()) {
            if (stack.peekLast() == Symbol.TS_EOF) {
                stack.removeLast();
                System.out.println("Yesss!! Bananas!");
                break;
            }

            final Symbol inputToken = tokens.get();

            if (stack.peekLast() == inputToken) {
                stack.removeLast();
                tokens.moveCursor();
                continue;
            }

            switch (parserTable.actionToTake(inputToken, stack.peekLast())) {
                case 1:
                    stack.removeLast();
                    stack.addLast(Symbol.NTS_ADD_2);
                    stack.addLast(Symbol.NTS_INT);
                    break;

                case 2:
                    stack.removeLast();
                    stack.addLast(Symbol.NTS_ADD_2);
                    stack.addLast(Symbol.NTS_INT);
                    stack.addLast(Symbol.TS_PLUS);
                    break;

                case 3:
                    stack.removeLast();
                    break;

                case 4:
                    stack.removeLast();
                    stack.addLast(Symbol.TS_0);
                    break;

                case 5:
                    stack.removeLast();
                    stack.addLast(Symbol.TS_1);
                    break;

                case 6:
                    stack.removeLast();
                    stack.addLast(Symbol.TS_3);
                    break;

                case 7:
                    stack.removeLast();
                    stack.addLast(Symbol.TS_3);
                    break;

                case 8:
                    stack.removeLast();
                    stack.addLast(Symbol.TS_4);
                    break;

                case 9:
                    stack.removeLast();
                    stack.addLast(Symbol.TS_5);
                    break;

                case 10:
                    stack.removeLast();
                    stack.addLast(Symbol.TS_6);
                    break;

                case 11:
                    stack.removeLast();
                    stack.addLast(Symbol.TS_7);
                    break;

                case 12:
                    stack.removeLast();
                    stack.addLast(Symbol.TS_8);
                    break;

                case 13:
                    stack.removeLast();
                    stack.addLast(Symbol.TS_9);
                    break;

                default:
                    throw new IllegalStateException("unknown action: "
                            + parserTable.actionToTake(inputToken, stack.peekLast()));

            }
        }

        if (!stack.isEmpty()) {
            throw new IllegalStateException("error while parsing the sencence: " + sentence);
        }
    }

    public static void main(String... args) {
        new Parser().parse("1 + 9 + 0 + 7 + 3 + 5 ");
    }

}
