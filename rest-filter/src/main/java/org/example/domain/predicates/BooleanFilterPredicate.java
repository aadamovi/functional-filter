package org.example.domain.predicates;

import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Predicate;

public enum BooleanFilterPredicate implements Predicate<Pair<Boolean, Boolean>>{
    EQUALS(pair -> pair.getLeft().equals(pair.getRight()));

    private final Predicate<Pair<Boolean, Boolean>> predicate;

    BooleanFilterPredicate(final Predicate<Pair<Boolean, Boolean>> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(Pair<Boolean, Boolean> pair) {
        return predicate.test(pair);
    }
}
