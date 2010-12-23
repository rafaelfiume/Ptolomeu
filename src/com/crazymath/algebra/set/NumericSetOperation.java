package com.crazymath.algebra.set;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import org.apache.commons.lang.Validate;

import com.crazymath.algebra.set.relation.BinaryRelation;

// PENDING Change this name to SetOperation?
public final class NumericSetOperation {

    // PENDING Put this in the NumericSet class?

    private NumericSetOperation() {
        // Utility class
    }

    // TODO Re-implement cartesianProduct
    // public static <E extends Number> CartesianProduct cartesianProduct(AlgebraSet<E> one,
    // AlgebraSet<E> another) {
    //
    // algebraSetsCannotBeNull(one, another);
    //
    // final Builder builder = new CartesianProduct.Builder();
    // for (E aElement : one) {
    // for (E bElement : another) {
    // builder.append(OrderedPair.newInstance(aElement, bElement));
    // }
    //
    // }
    // return builder.build();
    // }

    public static <E extends Number> BinaryRelation binaryRelation(AlgebraSet<E> one,
            AlgebraSet<E> another, String relation) {

        algebraSetsCannotBeNull(one, another);
        Validate.isTrue(isNotBlank(relation));

        return null;
    }

    private static <E extends Number> void algebraSetsCannotBeNull(
            AlgebraSet<? extends Number> one, AlgebraSet<? extends Number> another) {

        Validate.notNull(one, "one can't be null");
        Validate.notNull(another, "another can't be null");
    }

}
