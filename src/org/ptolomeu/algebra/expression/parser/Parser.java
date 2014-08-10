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
            }

            try {
                parserTable.actionToTake(tokens.current(), stack.peek()).derive(stack);
            } catch (IllegalStateException e) {
                break; // horrible... bleeeeerrrgh. It will disappear in a few commits
            }

        }

        LOG.info("error while parsing the sencence: " + sentence);
        return false;
    }

    public static void main(String... args) {
        new Parser().checkGrammar("1 + 9 + 0 + 7 + 3 + 5");
    }

}
