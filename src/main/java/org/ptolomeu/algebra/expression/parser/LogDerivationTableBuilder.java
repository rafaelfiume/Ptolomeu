package org.ptolomeu.algebra.expression.parser;

import static java.lang.System.lineSeparator;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.ptolomeu.algebra.expression.parser.LogDerivationTableBuilder.Column.columnOf;
import static org.ptolomeu.algebra.expression.parser.LogDerivationTableBuilder.Line.lineWith;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.ptolomeu.util.Stack;

class LogDerivationTableBuilder {

    private final Table table = new Table();
    
    LogDerivationTableBuilder addLine(Scanner scanner, Stack<Symbol> stack, Derivation derivation) {
        table.add(lineWith(
                columnOf(scanner.remainingSentence()), 
                columnOf(stack), 
                columnOf(derivation.toString()))
        );
        return this;
    }
    
    String build() {
        return new TableRenderer(table).render();
    }
    
    Table table() {
        return table;        
    }
    
    static class TableRenderer {
        
        private static final Object MIN_BLANK_SPACE_AT_BEGINNING = repeat(SPACE, 1);
        private static final Object MIN_BLANK_SPACE_AT_END = repeat(SPACE, 2);
        
        private final Table table;
        
        public TableRenderer(Table table) {
            this.table = table;
        }

        String render() {
            final StringBuilder builder = new StringBuilder();
            
            builder.append("Derivation of Sentence ").append(this.table.originalSentence()).append(lineSeparator()).append(lineSeparator());
            
            for (Line line : this.table) {
                builder.append(rightPad(line.column(1), table.maxLengthOfColumn(1))).append(MIN_BLANK_SPACE_AT_END).append("|").append(MIN_BLANK_SPACE_AT_BEGINNING)
                       .append(rightPad(line.column(2), table.maxLengthOfColumn(2))).append(MIN_BLANK_SPACE_AT_END).append("|").append(MIN_BLANK_SPACE_AT_BEGINNING)
                       .append(rightPad(line.column(3), table.maxLengthOfColumn(3))).append(MIN_BLANK_SPACE_AT_END).append(lineSeparator());
            }
            
            return builder.toString();
        }
    }
    
    static class Table implements Iterable<Line>{
        
        private final List<Line> lines = new ArrayList<>();
        
        private final List<Integer> maxLengthOfColumns = new ArrayList<Integer>() {{
            add(0);
            add(0);
            add(0);
        }};
        
        @Override
        public Iterator<Line> iterator() {
            return new ArrayList<Line>(lines).iterator();
        }
        
        String originalSentence() {
            return line(1).column(1);
        }

        Integer numOfLines() {
            return lines.size();
        }

        Object[] toArray() {
            return lines.toArray();
        }

        Line line(int lineNumber) {
            return lines.get(lineNumber - 1);
        }
        
        void add(Line line) {
            lines.add(line);
                        
            updateMaxLengthOfColumn(1, at(line));
            updateMaxLengthOfColumn(2, at(line));
            updateMaxLengthOfColumn(3, at(line));
        }

        int maxLengthOfColumn(int lineNumber) {
            return this.maxLengthOfColumns.get(lineNumber - 1);
        }

        private void updateMaxLengthOfColumn(int column, Line line) {
            int lengthOfColumn = line.column(column).length();
            int maxLengthOfColumn = maxLengthOfColumns.get(column - 1);
            
            if (lengthOfColumn > maxLengthOfColumn) {
                maxLengthOfColumns.set(column - 1, lengthOfColumn);
            }
        }
        
        private Line at(Line line) {
            return line;
        }

    }
    
    static final class Line {
        
        private final Column column1;
        private final Column column2;
        private final Column column3;

        static Line lineWith(Column column1, Column column2, Column column3) {
            return new Line(column1, column2, column3);
        }
        
        Line(Column column1, Column column2, Column column3) {
            this.column1 = column1;
            this.column2 = column2;
            this.column3 = column3;
        }

        String column(int columnNumber) {
            switch(columnNumber) {
                case 1 : return column1.value();
                case 2 : return column2.value();
                case 3 : return column3.value();
                default: throw new IllegalArgumentException("Inexistent columnNumber " + columnNumber); 
            }
        }

    }
    
    static final class Column {
        
        private final String value;
        
        static Column columnOf(String value) {
            return new Column(value);
        }
        
        static Column columnOf(Stack<Symbol> stack) {
            return new Column(StackStringBuilder.string(stack));
        }
        
        Column(String value) {
            this.value = value;
        }

        String value() {
            return value;
        }
        
        @Override
        public String toString() {
            return value;
        }

        static final class StackStringBuilder {
            
            static String string(Stack<Symbol> stack) {
                final StringBuilder builder = new StringBuilder();

                for (Symbol s : stack) {
                    if (StringUtils.isNotBlank(s.symbol())) {
                        builder.append(s.symbol()).append(SPACE);
                    }
                }
                builder.deleteCharAt(builder.length() - 1);
                
                return builder.toString();
            }
        }
    }
    
    
}
