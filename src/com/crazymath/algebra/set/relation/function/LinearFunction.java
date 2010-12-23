package com.crazymath.algebra.set.relation.function;

import com.crazymath.algebra.AlgebraException;
import com.crazymath.algebra.set.RealSet;

/**
 * According to <a href="http://en.wikipedia.org/wiki/Linear_function">Wikipedia</a>, a linear
 * function is "a first-degree polinomial function with one variable", that can be described as:
 * f(x) = &alpha;x + &beta;, where &alpha; is the slope and .
 * <p>
 * TODO It's necessary to mention "Fundamentos da Matemática Elementar" in the References section.
 */
public final class LinearFunction {

    private final double slope;

    private final double yIntercept;

    // TODO 1. Every type of NumericSet can be assigned to domain
    private final RealSet domain; // PENDING If domain is null, domain comprise all real numbers

    private LinearFunction(double slope, double yIntercept, RealSet domain) {
        this.slope = slope;
        this.yIntercept = yIntercept;
        this.domain = domain;
    }

    public static LinearFunction newInstance(double slope, double yIntercept) {
        return new LinearFunction(slope, yIntercept, null);
    }

    public double calculate(int x) {
        final double y = (slope * x) + yIntercept;
        if (domain != null && !domain.has(y)) {
            throw new AlgebraException("y is not a member of domain");
        }
        return y;
    }

    public static LinearFunction newInstance(double slope, double yIntercept, RealSet domain) {
        return new LinearFunction(slope, yIntercept, domain);
    }

    // TODO linear coefficient

    // TODO angular coefficient

}
