package com.crazymath.algebra.set;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import org.apache.commons.lang.Validate;

import com.crazymath.algebra.set.relation.BinaryRelation;
import com.crazymath.algebra.set.relation.CartesianProduct;
import com.crazymath.algebra.set.relation.OrderedPair;
import com.crazymath.algebra.set.relation.CartesianProduct.Builder;

public final class NumericSetOperation {

    private NumericSetOperation() {
        // Utility class
    }

    /**
     * @return an unmodifiable (read-only) view of the union of the {@code NumericSet}.
     */
    public static <E extends Number> AlgebraSet<? extends Number> union(AlgebraSet<E> one,
            AlgebraSet<E> another) {

        algebraSetsCannotBeNull(one, another);

        final NumericSet.Builder builder = new NumericSet.Builder();
        for (final E each : one) {
            builder.add(each);
        }
        for (final E eachOther : another) {
            builder.add(eachOther);
        }
        return builder.build();
    }

    public static <E extends Number> AlgebraSet<? extends Number> intersection(AlgebraSet<E> one,
            AlgebraSet<E> another) {

        algebraSetsCannotBeNull(one, another);

        final NumericSet.Builder builder = new NumericSet.Builder();
        for (E each : one) {
            if (another.has((E) each)) {
                builder.add(each);
            }
        }
        return builder.build();
    }

    public static <E extends Number> AlgebraSet<? extends Number> subtract(AlgebraSet<E> one,
            AlgebraSet<E> another) {

        algebraSetsCannotBeNull(one, another);

        final NumericSet.Builder builder = new NumericSet.Builder();
        for (final E each : one) {
            if (!another.has((E) each)) {
                builder.add(each);
            }
        }
        return builder.build();
    }

    public static <E extends Number> CartesianProduct cartesianProduct(AlgebraSet<E> one,
            AlgebraSet<E> another) {

        algebraSetsCannotBeNull(one, another);

        final Builder builder = new CartesianProduct.Builder();
        for (E aElement : one) {
            for (E bElement : another) {
                builder.append(OrderedPair.newInstance(aElement, bElement));
            }

        }
        return builder.build();
    }

    public static <E extends Number> BinaryRelation binaryRelation(AlgebraSet<E> one,
            AlgebraSet<E> another, String relation) {

        algebraSetsCannotBeNull(one, another);
        Validate.isTrue(isNotBlank(relation));

        return null;
    }

    private static <E extends Number> void algebraSetsCannotBeNull(AlgebraSet<E> one,
            AlgebraSet<E> another) {
        Validate.notNull(one, "one can't be null");
        Validate.notNull(another, "another can't be null");
    }

}
