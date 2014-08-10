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

        return doCheckGrammar(sentence, tokens);
    }

    private boolean doCheckGrammar(Sentence sentence, Tokens tokens) {
        if (stack.peek() == Symbol.TS_EOF) {
            stack.pop();
            LOG.info("Yesss!! Bananas!");
            return true;
        }

        if (stack.peek() == tokens.current()) {
            stack.pop();
            tokens.moveToNext();
            return doCheckGrammar(sentence, tokens);
        }

        try {
            parserTable.actionToTake(tokens.current(), stack.peek()).derive(stack);
            return doCheckGrammar(sentence, tokens);

        } catch (IllegalStateException e) {
            LOG.info("error while parsing the sencence " + sentence + ": " + e.getMessage());
            return false; // horrible... bleeeeerrrgh. It will disappear in a few commits
        }

    }
}
