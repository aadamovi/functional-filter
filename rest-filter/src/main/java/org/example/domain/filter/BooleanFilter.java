package org.example.domain.filter;

import org.apache.commons.lang3.tuple.Pair;
import org.example.domain.predicates.BooleanFilterPredicate;

import java.util.Map;

public class BooleanFilter implements Filter {
    private final String key;
    private final String value;
    private final BooleanFilterPredicate predicate;

    public BooleanFilter(String key, String value, BooleanFilterPredicate predicate) {
        this.key = key;
        this.value = value;
        this.predicate = predicate;
    }

    @Override
    public boolean matches(Map<String, String> user) {
        return predicate.test(Pair.of(Boolean.parseBoolean(value), Boolean.parseBoolean(user.get(key))));
    }
}
