package com.crazymath.algebra.set;

import static com.crazymath.algebra.set.InnerNumericSet.newInnerNumericSet;

import java.util.Iterator;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * TODO Idea 1: to separate algebra operations (e.g. union) from the Set itself,
 * and thus permit the same operations in different implementations of Set's
 * (immutable, mutable thread-safe, etc.)
 * 
 * <p>
 * Não é thread-safe.
 * 
 * @author Rafael Fiume
 */
public class IntegerNumericSet implements NumericSet<Integer>, NumericSetOperation<Integer> {

	private InnerNumericSet<Integer> elements = newInnerNumericSet();

	IntegerNumericSet() {
	}

	IntegerNumericSet(Integer... elements) {
		addImpl(elements);
	}
	
	IntegerNumericSet(InnerNumericSet<Integer> elements) {
        addImpl(elements);
    }

    @Override
	public void add(Integer element) {
		elements.add(element);
	}

	@Override
	public void add(Integer... elements) {
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
		Validate.notNull(other);

		if (other.isEmpty()) {
			return true;
		}

		for (final Integer each : other) {
			if (!elements.has(each)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @return an unmodifiable (read-only) view of the union of the
	 *         {@code NumericSet}.
	 */
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

		return new IntegerNumericSet(unionOfElements);
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
		
		return new IntegerNumericSet(intersectionOfElements);
	}

	@Override
	public NumericSet<Integer> subtract(NumericSet<Integer> other) {
		Validate.notNull(other);

		if (other.isEmpty()) {
			// TODO Criar UnmodifiableNumericSet
			return new IntegerNumericSet(elements);
		}

		final NumericSet<Integer> complement = new IntegerNumericSet();
		for (final Integer each : this.elements) {
			if (!other.has(each)) {
				complement.add(each);
			}
		}

		return complement;
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
		return new EqualsBuilder().append(elements,
				((IntegerNumericSet) other).elements).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(elements).toHashCode();
	}

	@Override
	public String toString() {
		return elements.toString();
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
