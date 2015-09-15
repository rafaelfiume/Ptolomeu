package org.ptolomeu.algebra.expression.parser;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.ptolomeu.algebra.expression.parser.Derivation.EXP_BY_INT_AND_OPER;
import static org.ptolomeu.algebra.expression.parser.Symbol.NTS_EXP;
import static org.ptolomeu.algebra.expression.parser.Symbol.NTS_INT;
import static org.ptolomeu.algebra.expression.parser.Symbol.NTS_OPER;

import org.junit.Test;
import org.ptolomeu.algebra.expression.parser.LogDerivationTableBuilder.Table;
import org.ptolomeu.util.Stack;

public class LogDerivationTableBuilderTest {
    
    private LogDerivationTableBuilder tableBuilder = new LogDerivationTableBuilder();
    
    private Stack<Symbol> stack = Stack.newInstance();   
    
    @Test
    public void buildInnerStructure() throws Exception {
        // 1st line of the table: 1 + 9 + 4  ||  EXP  ||  EXP -> INT OPER
        // given
        Scanner tokens = Sentence.newInstance("1 + 9 + 4").tokenizer();
        stack.push(NTS_EXP);
        Derivation derivation = EXP_BY_INT_AND_OPER;
        
        // when
        Table table = tableBuilder.addLine(tokens, stack, derivation).table();
        
        // then lines...
        assertThat(table.numOfLines(), is(1));
        
        // and columns...
        assertThat(table.line(1).column(1), is("1+9+4"));
        assertThat(table.line(1).column(2), is("EXP"));
        assertThat(table.line(1).column(3), is("EXP -> INT OPER"));
        
        assertThat(table.maxLengthOfColumn(1), is(5));
        assertThat(table.maxLengthOfColumn(2), is(3));
        assertThat(table.maxLengthOfColumn(3), is(15));
        
        // 2st line of the table: 1 + 9 + 4 ||  OPER INT  ||  INT -> 1
        // given
        stack.pop();
        stack.push(NTS_OPER);
        stack.push(NTS_INT);
        derivation = Derivation.INT_BY_1;
        
        // when
        table = tableBuilder.addLine(tokens, stack, derivation).table();
        
        // and columns...
        assertThat(table.line(2).column(1), is("1+9+4"));
        assertThat(table.line(2).column(2), is("OPER INT"));
        assertThat(table.line(2).column(3), is("INT -> 1"));
        
        assertThat(table.maxLengthOfColumn(1), is(5));
        assertThat(table.maxLengthOfColumn(2), is(8));  // will increase columns maxLength
        assertThat(table.maxLengthOfColumn(3), is(15));
    }

}
