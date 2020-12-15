package org.example.domain.predicates;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;

public enum UserPredicates implements BiPredicate<Map<String, String>, Pair<String, String>> {
    IS_PRESENT("isPresent", (user, filter) -> user.containsKey(filter.getKey())),
    BOOLEAN_IS_EQUAL("booleanIsEqual", (user, filter) -> IS_PRESENT.test(user, filter)
        && Boolean.parseBoolean(user.get(filter.getKey())) == Boolean.parseBoolean(filter.getValue())),
    STRING_IS_EQUAL("stringIsEqual", (user, filter) -> IS_PRESENT.test(user, filter)
        && user.get(filter.getKey()).equals(filter.getValue())),
    IS_LESS_THAN("isLessThan", (user, filter) -> IS_PRESENT.test(user, filter)
        && Integer.parseInt(user.get(filter.getKey())) < Integer.parseInt(filter.getValue())),
    IS_GREATER_THAN("isGreaterThan", (user, filter) -> IS_PRESENT.test(user, filter)
        && Integer.parseInt(user.get(filter.getKey())) > Integer.parseInt(filter.getValue())),
    MATCHES_PATTERN("matchesPattern", (user, filter) -> IS_PRESENT.test(user, filter)
        && Pattern.compile(filter.getValue()).matcher(user.get(filter.getKey())).find());

    private final String key;
    private final BiPredicate<Map<String, String>, Pair<String, String>> biPredicate;

    UserPredicates(String key, BiPredicate<Map<String, String>, Pair<String, String>> biPredicate) {
        this.key = key;
        this.biPredicate = biPredicate;
        Holder.map.put(key, this);
    }

    @Override
    public boolean test(Map<String, String> user, Pair<String, String> filter) {
        return biPredicate.test(user, filter);
    }

    @Override
    public BiPredicate<Map<String, String>, Pair<String, String>> and(BiPredicate<? super Map<String, String>, ? super Pair<String, String>> other) {
        return biPredicate.and(other);
    }

    @Override
    public BiPredicate<Map<String, String>, Pair<String, String>> negate() {
        return biPredicate.negate();
    }

    @Override
    public BiPredicate<Map<String, String>, Pair<String, String>> or(BiPredicate<? super Map<String, String>, ? super Pair<String, String>> other) {
        return biPredicate.or(other);
    }

    public String getKey() {
        return key;
    }

    public BiPredicate<Map<String, String>, Pair<String, String>> getBiPredicate() {
        return biPredicate;
    }

    private static class Holder {
        static Map<String, UserPredicates> map = new HashMap<>();
    }

    public static UserPredicates getFilterFromString(String value) {
        UserPredicates filter = Holder.map.get(value);
        if (filter == null) {
            throw new IllegalArgumentException("Invalid filter name provided");
        }
        return filter;
    }
}
