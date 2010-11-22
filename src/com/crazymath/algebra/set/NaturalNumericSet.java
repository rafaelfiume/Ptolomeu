package com.crazymath.algebra.set;

import static com.crazymath.algebra.set.InnerNumericSet.newInnerNumericSet;

import java.util.Iterator;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This class is not thread-safe.
 * 
 * @author Rafael Fiume
 */
public class NaturalNumericSet implements NumericSet<Integer>, NumericSetOperation<Integer> {

    private InnerNumericSet<Integer> elements = newInnerNumericSet();

    NaturalNumericSet() {
    }

    NaturalNumericSet(Integer... elements) {
        checkNotNegative(elements);
        addImpl(elements);
    }
    
    private NaturalNumericSet(InnerNumericSet<Integer> elements) {
        addImpl(elements);
    }

    @Override
    public void add(Integer element) {
        checkNotNegative(element);
        elements.add(element);
    }

    @Override
    public void add(Integer... elements) {
        checkNotNegative(elements);
        addImpl(elements);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean has(Integer element) {
        return elements.has(element);
    }

    @Override
    public boolean contains(NumericSet<Integer> other) {
        return elements.contains(other);
    }
    
    @Override
    public NumericSet<Integer> union(NumericSet<Integer> other) {
        Validate.notNull(other);

        final InnerNumericSet<Integer> unionOfElements = newInnerNumericSet();
        for (final Integer each : this) {
            unionOfElements.add(each);
        }
        for (final Integer eachOther : other) {
            unionOfElements.add(eachOther);
        }

        if (unionOfElements.lower() < 0) {
            return new IntegerNumericSet(unionOfElements);
        } 
        return new NaturalNumericSet(unionOfElements);
    }

    @Override
    public NumericSet<Integer> intersection(NumericSet<Integer> other) {
        Validate.notNull(other);

        if (this.isEmpty() || other.isEmpty()) {
            // TODO retornar newEmptyNumericSet
            return new IntegerNumericSet();
        }

        final InnerNumericSet<Integer> intersectionOfElements = newInnerNumericSet();
        for (int each : elements) {
            if (other.has(each)) {
                intersectionOfElements.add(each);
            }
        }
        
        if (intersectionOfElements.lower() < 0) {
            return new IntegerNumericSet(intersectionOfElements);
        } 
        return new NaturalNumericSet(intersectionOfElements);
    }

    @Override
    public NumericSet<Integer> subtract(NumericSet<Integer> other) {
        Validate.notNull(other);

        if (other.isEmpty()) {
            // TODO Criar UnmodifiableNumericSet
            return new NaturalNumericSet(elements);
        }

        final InnerNumericSet<Integer> complementOfElements = newInnerNumericSet();
        for (final Integer each : this.elements) {
            if (!other.has(each)) {
                complementOfElements.add(each);
            }
        }

        if (complementOfElements.lower() < 0) {
            return new IntegerNumericSet(complementOfElements);
        } 
        return new NaturalNumericSet(complementOfElements);
    }

    @Override
    public Iterator<Integer> iterator() {
        return elements.iterator();
    }
    
    @Override
    public Integer lower() {
        return elements.lower();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        return new EqualsBuilder().append(elements, ((NaturalNumericSet) other).elements).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(elements).toHashCode();
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    private void checkNotNegative(Integer element) {
        if (element < 0) {
            // TODO Replace this by a more appropriate exception
            // since this's not really a arithmetic error, but a algebra one
            throw new ArithmeticException();
        }
    }

    private void checkNotNegative(Integer... elements) {
        for (Integer each : elements) {
            checkNotNegative(each);
        }
    }
    
    // TODO Eliminar duplicidade
    private void addImpl(Integer... elements) {
        Validate.noNullElements(elements);
        for (final Integer each : elements) {
            this.elements.add(each);
        }
    }
    
 // TODO Eliminar duplicidade
    private void addImpl(InnerNumericSet<Integer> otherElements) {
        Validate.notNull(otherElements, "elements não pode ser null");
        for (final Integer each : otherElements) {
            elements.add(each);
        }
    }

}
