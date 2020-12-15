package org.example.domain.predicates;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class UserPredicatesTest {

    private final Map<String, String> user = Map.ofEntries(
        new AbstractMap.SimpleEntry<>("firstname", "Mic"),
        new AbstractMap.SimpleEntry<>("surname", "Test"),
        new AbstractMap.SimpleEntry<>("role", "tester"),
        new AbstractMap.SimpleEntry<>("age", "35"),
        new AbstractMap.SimpleEntry<>("isMarried", "true")
    );

    @Test
    void shouldReturnFalse() {
        assertThat(UserPredicates.BOOLEAN_IS_EQUAL.test(user, Pair.of("isMarried", "false")), is(false));
    }

    @Test
    void shouldReturnTrue() {
        assertThat(UserPredicates.BOOLEAN_IS_EQUAL.test(user, Pair.of("isMarried", "false")), is(true));
        assertThat(UserPredicates.BOOLEAN_IS_EQUAL.test(user, Pair.of("isMarried", "true")), is(true));
    }

    @Test
    void shouldReturnPresentAsTrue() {
        assertThat(UserPredicates.IS_PRESENT.test(user, Pair.of("surname", "Test")), is(true));
    }

    @Test
    void shouldReturnPresentAsFalse() {
        assertThat(UserPredicates.IS_PRESENT.test(user, Pair.of("false_surname", "Test")), is(false));
    }

    @Test
    void shouldReturnEqualAsTrue() {
        assertThat(UserPredicates.STRING_IS_EQUAL.test(user, Pair.of("surname", "Test")), is(true));
    }

    @Test
    void shouldReturnEqualAsFalse() {
        assertThat(UserPredicates.STRING_IS_EQUAL.test(user, Pair.of("surname", "Test1")), is(false));
        assertThat(UserPredicates.STRING_IS_EQUAL.test(user, Pair.of("surname1", "Test")), is(false));
    }

    @Test
    void shouldReturnGreaterThanAsTrue() {
        assertThat(UserPredicates.IS_GREATER_THAN.test(user, Pair.of("age", "33")), is(true));
    }

    @Test
    void shouldReturnGreaterThanAsFalse() {
        assertThat(UserPredicates.IS_GREATER_THAN.test(user, Pair.of("age", "36")), is(false));
        assertThat(UserPredicates.IS_GREATER_THAN.test(user, Pair.of("age1", "33")), is(false));
    }

    @Test
    void shouldReturnIsLessThanAsTrue() {
        assertThat(UserPredicates.IS_LESS_THAN.test(user, Pair.of("age", "37")), is(true));
    }

    @Test
    void shouldReturnIsLessThanAsFalse() {
        assertThat(UserPredicates.IS_LESS_THAN.test(user, Pair.of("age", "33")), is(false));
        assertThat(UserPredicates.IS_LESS_THAN.test(user, Pair.of("age1", "37")), is(false));
    }

    @Test
    void shouldMatchRegexPattern() {
        assertThat(UserPredicates.MATCHES_PATTERN.test(user, Pair.of("surname", "Tes")), is(true));
    }

    @Test
    void shouldNotMatchRegexPattern() {
        assertThat(UserPredicates.MATCHES_PATTERN.test(user, Pair.of("surname", "Dev")), is(false));
    }
}