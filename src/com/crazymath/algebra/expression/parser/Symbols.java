package com.crazymath.algebra.expression.parser;

// TODO Separate enums for terminal from nonterminal symbols
enum Symbols {

    TS_PLUS("+"), // TODO Use char instead of Strings for terminals symbols
    TS_0("0"),
    TS_1("1"),
    TS_2("2"),
    TS_3("3"),
    TS_4("4"),
    TS_5("5"),
    TS_6("6"),
    TS_7("7"),
    TS_8("8"),
    TS_9("9"),
    TS_EOF("\0"),
    TS_INVALID("\1"),

    NTS_ADD("ADD"),
    NTS_ADD_("ADD'"),
    NTS_INT("INT");

    private final String token;

    Symbols(String token) {
        this.token = token;
    }

    /*
     * "Converts a valid token to the corresponding terminal symbol" (Taken from Wikipedia)
     */
    final static Symbols of(char token) {

        for (final Symbols s : values()) {
            if (s.token.equals(Character.toString(token))) {
                return s;
            }
        }

        // PENDING See how to deal with error handling
        throw new IllegalArgumentException("Cannot parse token " + token);
    }

    String getToken() {
        return token;
    }
}
