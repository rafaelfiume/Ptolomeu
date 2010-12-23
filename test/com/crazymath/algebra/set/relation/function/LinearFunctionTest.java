package com.crazymath.algebra.set.relation.function;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.crazymath.algebra.AlgebraException;
import com.crazymath.algebra.set.Endpoint;
import com.crazymath.algebra.set.RealSet;

public class LinearFunctionTest {

    @Test
    public void testLinearFunction() {
        final double slope = 4;
        final double yIntercept = 3; // linear coefficient
        final LinearFunction f = LinearFunction.newInstance(slope, yIntercept);
        final double result = f.calculate(5); // evaluate is used to resolve expressions
        assertTrue(result == 23d); // NOPMD by rafaelfiume on 23/12/10 20:45
    }

    @Test
    public void testLinearFunctionWithSpecificDomain() {
        final double slope = 4;
        final double yIntercept = 3;
        final RealSet domain = RealSet.newInstance(-2d, Endpoint.CLOSED, 19d, Endpoint.CLOSED);
        final LinearFunction f = LinearFunction.newInstance(slope, yIntercept, domain);
        assertTrue(11.0 == f.calculate(2));
    }

    @Test(expected = AlgebraException.class)
    public void linearFunctionWithSpecificDomainShouldThrowExceptionWhenDomainIsNotRespected() {
        final double slope = 4;
        final double yIntercept = 3;
        // TODO Consider OPEN/CLOSED notation. See
        // http://en.wikipedia.org/wiki/Interval_%28mathematics%29
        final RealSet domain = RealSet.newInstance(-2d, Endpoint.CLOSED, 19d, Endpoint.CLOSED);
        final LinearFunction f = LinearFunction.newInstance(slope, yIntercept, domain);
        f.calculate(-7); // -7 raises an exception because it is not a member of set domain
    }

}
