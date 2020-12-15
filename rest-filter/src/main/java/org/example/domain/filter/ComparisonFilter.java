package org.example.domain.filter;

import org.apache.commons.lang3.tuple.Pair;
import org.example.domain.predicates.ComparisonFilterBiPredicate;

import java.util.Map;

public class ComparisonFilter implements Filter {
    private final String key;
    private final String value;
    private final ComparisonFilterBiPredicate comparisonType;

    public ComparisonFilter(String key, String value, ComparisonFilterBiPredicate comparisonType) {
        this.key = key;
        this.value = value;
        this.comparisonType = comparisonType;
    }

    @Override
    public boolean matches(Map<String, String> user) {
        return comparisonType.test(user, Pair.of(key, value));
    }
}
