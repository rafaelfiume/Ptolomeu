package org.ptolomeu.algebra.expression.parser;

public enum Derivations implements Derivation {

    // Replacing Add...
    REPLACE_ADD_BY_INT_AND_ADD_2,

    // Replacing Add'
    REPLACE_ADD_2_BY_PLUS_AND_INT_AND_ADD_2,
    REPLACE_ADD_2_BY_EOF,

    // Replacing Int...
    REPLACE_INT_BY_0,
    REPLACE_INT_BY_1,
    REPLACE_INT_BY_2,
    REPLACE_INT_BY_3,
    REPLACE_INT_BY_4,
    REPLACE_INT_BY_5,
    REPLACE_INT_BY_6,
    REPLACE_INT_BY_7,
    REPLACE_INT_BY_8,
    REPLACE_INT_BY_9;
}
