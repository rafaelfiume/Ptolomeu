package com.crazymath.algebra.expression;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private final Map<String, Integer> map = new HashMap<String, Integer>();

    public void assign(VariableExp var, int value) {
        map.put(var.getName(), value);
    }

    public Integer lookup(String varName) {
        return map.get(varName);
    }

}
