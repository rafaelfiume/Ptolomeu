package org.ptolomeu.algebra.set.relation;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public final class CartesianProduct { // NOPMD by rafaelfiume on 04/12/10 01:09

    private final Set<OrderedPair> orderedPairs;

    private CartesianProduct(Builder builder) {
        this.orderedPairs = builder.orderedPairs;
    }

    public int numberOfOrderedPairs() {
        return orderedPairs.size();
    }

    public Set<OrderedPair> orderedPair() {
        return unmodifiableSet(orderedPairs);
    }

    public List<OrderedPair> orderedPairAsList() {
        return unmodifiableList(new ArrayList<OrderedPair>(orderedPairs));
    }

    public static class Builder {

        private final Set<OrderedPair> orderedPairs = new TreeSet<OrderedPair>();

        public Builder append(OrderedPair op) {
            orderedPairs.add(op);
            return this;
        }

        public CartesianProduct build() {
            return new CartesianProduct(this);
        }

    }

}
