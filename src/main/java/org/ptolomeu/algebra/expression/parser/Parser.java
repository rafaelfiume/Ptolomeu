package org.ptolomeu.algebra.expression.parser;

import org.ptolomeu.util.Stack;

import java.util.logging.Logger;

public class Parser {

    private static Logger LOG = Logger.getLogger(Parser.class.getName());

    final ParserTable parserTable = ParserTable.newInstance();

    final Stack<Symbol> parserStack = Stack.newInstance();

    public Integer parse(String input) {
        parserStack.push(Symbol.TS_EOF);
        parserStack.push(Symbol.NTS_EXP);

        final Sentence sentence = Sentence.newInstance(input);
        final Scanner tokens = sentence.tokenizer();

        return doParse(sentence, tokens, new ExpressionTreeBuilder(), new LogDerivationTableBuilder());
    }

    private Integer doParse(Sentence sentence, Scanner tokens, ExpressionTreeBuilder treeBuilder, LogDerivationTableBuilder tableBuilder) {
        if (parserStack.peek() == Symbol.TS_EOF) {
            LOG.info(tableBuilder.build());
            return treeBuilder.build().evaluate(); // Bananas!!
        }

        if (parserStack.peek() == tokens.current()) {
            treeBuilder.add(parserStack.pop());
            tokens.nextToken();
            return doParse(sentence, tokens, treeBuilder, tableBuilder);
        }

        try {
            final Derivation action = parserTable.actionToTake(parserStack.peek(), tokens.current());
            tableBuilder.addLine(tokens, parserStack, action);
            
            action.derive(parserStack);
            return doParse(sentence, tokens, treeBuilder, tableBuilder);

        } catch (IllegalStateException e) {
            LOG.info("error while parsing the sencence " + sentence + ": " + e.getMessage());
            throw e; // Virtually no error handling yet
        }

    }
}
