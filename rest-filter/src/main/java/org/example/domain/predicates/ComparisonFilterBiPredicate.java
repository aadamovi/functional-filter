package org.example.domain.predicates;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;

public enum ComparisonFilterBiPredicate implements BiPredicate<Map<String, String>, Pair<String, String>> {
    IS_PRESENT((resource, filter) -> resource.containsKey(filter.getKey())),
    IS_EQUAL((resource, filter) -> IS_PRESENT.test(resource, filter)
        && resource.get(filter.getKey()).equals(filter.getValue())),
    IS_LESS_THAN((resource, filter) -> IS_PRESENT.test(resource, filter)
        && Integer.parseInt(resource.get(filter.getKey())) < Integer.parseInt(filter.getValue())),
    IS_GREATER_THAN((resource, filter) -> IS_PRESENT.test(resource, filter)
        && Integer.parseInt(resource.get(filter.getKey())) > Integer.parseInt(filter.getValue())),
    MATCHES_PATTERN((resource, filter) -> IS_PRESENT.test(resource, filter)
        && Pattern.compile(filter.getValue()).matcher(resource.get(filter.getKey())).find());

    private final BiPredicate<Map<String, String>, Pair<String, String>> biPredicate;

    ComparisonFilterBiPredicate(BiPredicate<Map<String, String>, Pair<String, String>> biPredicate) {
        this.biPredicate = biPredicate;
    }

    @Override
    public boolean test(Map<String, String> resource, Pair<String, String> filter) {
        return biPredicate.test(resource, filter);
    }
}
