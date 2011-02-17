package org.ptolomeu.util;

import org.apache.commons.lang.Validate;

public final class NumberUtil {

    // TODO Eliminate duplicate messages in exceptions

    private NumberUtil() {
        // Utility class
    }

    public static boolean equals(Number aNumber, Number anotherNumber) {
        numbersCannotBeNull(aNumber, anotherNumber);

        if (bothInstanceOfInteger(aNumber, anotherNumber)) {
            return bothIntegersAreEquals(aNumber, anotherNumber);
        }
        if (bothInstanceOfDouble(aNumber, anotherNumber)) {
            return bothDoublesAreEquals(aNumber, anotherNumber);
        }

        throw new UnsupportedOperationException(
                "comparison can only be done between two integers or two doubles");
    }

    public static boolean greater(Number aNumber, Number anotherNumber) {
        numbersCannotBeNull(aNumber, anotherNumber);

        if (bothInstanceOfInteger(aNumber, anotherNumber)) {
            return integerIsGreater(aNumber, anotherNumber);
        }
        if (bothInstanceOfDouble(aNumber, anotherNumber)) {
            return doubleIsGreater(aNumber, anotherNumber);
        }

        throw new UnsupportedOperationException(
                "comparison can only be done between two integers or two doubles");
    }

    public static boolean greaterOrEquals(Number aNumber, Number anotherNumber) {
        numbersCannotBeNull(aNumber, anotherNumber);

        if (bothInstanceOfInteger(aNumber, anotherNumber)) {
            return integerIsGreater(aNumber, anotherNumber)
                    || bothIntegersAreEquals(aNumber, anotherNumber);
        }
        if (bothInstanceOfDouble(aNumber, anotherNumber)) {
            return doubleIsGreater(aNumber, anotherNumber)
                    || bothDoublesAreEquals(aNumber, anotherNumber);
        }

        throw new UnsupportedOperationException(
                "comparison can only be done between two integers or two doubles");
    }

    public static boolean lesser(Number aNumber, Number anotherNumber) {
        numbersCannotBeNull(aNumber, anotherNumber);

        if (bothInstanceOfInteger(aNumber, anotherNumber)) {
            return integerIsLesser(aNumber, anotherNumber);
        }
        if (bothInstanceOfDouble(aNumber, anotherNumber)) {
            return doubleIsLesser(aNumber, anotherNumber);
        }

        throw new UnsupportedOperationException(
                "comparison can only be done between two integers or two doubles");
    }

    public static boolean lesserOrEquals(Number aNumber, Number anotherNumber) {
        numbersCannotBeNull(aNumber, anotherNumber);

        if (bothInstanceOfInteger(aNumber, anotherNumber)) {
            return integerIsLesser(aNumber, anotherNumber)
                    || bothIntegersAreEquals(aNumber, anotherNumber);
        }
        if (bothInstanceOfDouble(aNumber, anotherNumber)) {
            return doubleIsLesser(aNumber, anotherNumber)
                    || bothDoublesAreEquals(aNumber, anotherNumber);
        }

        throw new UnsupportedOperationException(
                "comparison can only be done between two integers or two doubles");
    }

    // Since the process of instantiation is manually controlled, there's no need of parameterized
    // type here.
    @SuppressWarnings("unchecked")
    public static <T extends Number> T max(T aNumber, T anotherNumber) {
        if (bothInstanceOfIntegerOrNull(aNumber, anotherNumber)) {
            return (T) maxInteger(aNumber, anotherNumber);
        }
        if (bothInstanceOfDoubleOrNull(aNumber, anotherNumber)) {
            return (T) maxDouble(aNumber, anotherNumber);
        }

        throw new UnsupportedOperationException(
                "comparison can only be done between two integers or two doubles");
    }

    // Since the process of instantiation is manually controlled, there's no need of parameterized
    // type here.
    @SuppressWarnings("unchecked")
    public static <T extends Number> T min(T aNumber, T anotherNumber) {
        if (bothInstanceOfIntegerOrNull(aNumber, anotherNumber)) {
            return (T) minInteger(aNumber, anotherNumber);
        }
        if (bothInstanceOfDoubleOrNull(aNumber, anotherNumber)) {
            return (T) minDouble(aNumber, anotherNumber);
        }

        throw new UnsupportedOperationException(
                "comparison can only be done between two integers or two doubles");
    }

    public static boolean bothInstanceOfInteger(Number aNumber, Number anotherNumber) {
        return (aNumber instanceof Integer) && (anotherNumber instanceof Integer);
    }

    public static boolean bothInstanceOfDouble(Number aNumber, Number anotherNumber) {
        return (aNumber instanceof Double) && (anotherNumber instanceof Double);
    }

    private static boolean bothInstanceOfIntegerOrNull(Number aNumber, Number anotherNumber) {
        return (aNumber instanceof Integer || aNumber == null)
                && (anotherNumber instanceof Integer || anotherNumber == null);
    }

    private static boolean bothInstanceOfDoubleOrNull(Number aNumber, Number anotherNumber) {
        return (aNumber instanceof Double || aNumber == null)
                && (anotherNumber instanceof Double || anotherNumber == null);
    }

    private static boolean bothIntegersAreEquals(Number aNumber, Number anotherNumber) {
        return ((Integer) aNumber).equals((Integer) anotherNumber);
    }

    private static boolean bothDoublesAreEquals(Number aNumber, Number anotherNumber) {
        return ((Double) aNumber).equals((Double) anotherNumber);
    }

    private static boolean integerIsGreater(Number aNumber, Number anotherNumber) {
        return ((Integer) aNumber) > ((Integer) anotherNumber);
    }

    private static boolean doubleIsGreater(Number aNumber, Number anotherNumber) {
        return ((Double) aNumber) > ((Double) anotherNumber);
    }

    private static boolean integerIsLesser(Number aNumber, Number anotherNumber) {
        return ((Integer) aNumber) < ((Integer) anotherNumber);
    }

    private static boolean doubleIsLesser(Number aNumber, Number anotherNumber) {
        return ((Double) aNumber) < ((Double) anotherNumber);
    }

    private static Integer maxInteger(Number aNumber, Number anotherNumber) {
        if (aNumber == null && anotherNumber == null) {
            throw new UnsupportedOperationException("cannot compare null's");
        }
        if (aNumber == null) {
            return (Integer) anotherNumber;
        }
        if (anotherNumber == null) {
            return (Integer) aNumber;
        }

        final Integer anInteger = (Integer) aNumber;
        final Integer anotherInteger = (Integer) anotherNumber;

        return (anInteger > anotherInteger) ? anInteger : anotherInteger;
    }

    private static Double maxDouble(Number aNumber, Number anotherNumber) {
        if (aNumber == null && anotherNumber == null) {
            throw new UnsupportedOperationException("cannot compare null's");
        }
        if (aNumber == null) {
            return (Double) anotherNumber;
        }
        if (anotherNumber == null) {
            return (Double) aNumber;
        }

        final Double aDouble = (Double) aNumber;
        final Double anotherDouble = (Double) anotherNumber;

        return (aDouble > anotherDouble) ? aDouble : anotherDouble;
    }

    private static Integer minInteger(Number aNumber, Number anotherNumber) {
        if (aNumber == null && anotherNumber == null) {
            throw new UnsupportedOperationException("cannot compare null's");
        }
        if (aNumber == null) {
            return (Integer) anotherNumber;
        }
        if (anotherNumber == null) {
            return (Integer) aNumber;
        }

        final Integer anInteger = (Integer) aNumber;
        final Integer anotherInteger = (Integer) anotherNumber;

        return (anInteger < anotherInteger) ? anInteger : anotherInteger;
    }

    private static Double minDouble(Number aNumber, Number anotherNumber) {
        if (aNumber == null && anotherNumber == null) {
            throw new UnsupportedOperationException("cannot compare null's");
        }
        if (aNumber == null) {
            return (Double) anotherNumber;
        }
        if (anotherNumber == null) {
            return (Double) aNumber;
        }

        final Double aDouble = (Double) aNumber;
        final Double anotherDouble = (Double) anotherNumber;

        return (aDouble < anotherDouble) ? aDouble : anotherDouble;
    }

    private static void numbersCannotBeNull(Number aNumber, Number anotherNumber) {
        Validate.notNull(aNumber, "aNumber cannot be null");
        Validate.notNull(anotherNumber, "anotherNumber cannot be null");
    }

}
