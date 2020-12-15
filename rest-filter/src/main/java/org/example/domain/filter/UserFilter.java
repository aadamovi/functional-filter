package org.example.domain.filter;

import org.apache.commons.lang3.tuple.Pair;
import org.example.domain.predicates.UserPredicates;

public class UserFilter {
    private final Pair<String, String> criteria;
    private final UserPredicates predicate;

    public UserFilter(Pair<String, String> criteria, UserPredicates predicate) {
        this.criteria = criteria;
        this.predicate = predicate;
    }

    public Pair<String, String> getCriteria() {
        return criteria;
    }

    public UserPredicates getPredicate() {
        return predicate;
    }
}
