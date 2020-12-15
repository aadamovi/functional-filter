package org.example.domain.predicates;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.AbstractMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class ComparisonFilterPredicateTest {

    private final Map<String, String> user = Map.ofEntries(
        new AbstractMap.SimpleEntry<>("firstname", "Mic"),
        new AbstractMap.SimpleEntry<>("surname", "Test"),
        new AbstractMap.SimpleEntry<>("role", "tester"),
        new AbstractMap.SimpleEntry<>("age", "35"),
        new AbstractMap.SimpleEntry<>("isMarried", "true")
    );

    @Test
    void shouldReturnPresentAsTrue() {
        assertThat(ComparisonFilterBiPredicate.IS_PRESENT.test(user, Pair.of("surname", "Test")), is(true));
    }

    @Test
    void shouldReturnPresentAsFalse() {
        assertThat(ComparisonFilterBiPredicate.IS_PRESENT.test(user, Pair.of("false_surname", "Test")), is(false));
    }

    @Test
    void shouldReturnEqualAsTrue() {
        assertThat(ComparisonFilterBiPredicate.IS_EQUAL.test(user, Pair.of("surname", "Test")), is(true));
    }

    @Test
    void shouldReturnEqualAsFalse() {
        assertThat(ComparisonFilterBiPredicate.IS_EQUAL.test(user, Pair.of("surname", "Test1")), is(false));
        assertThat(ComparisonFilterBiPredicate.IS_EQUAL.test(user, Pair.of("surname1", "Test")), is(false));
    }

    @Test
    void shouldReturnGreaterThanAsTrue() {
        assertThat(ComparisonFilterBiPredicate.IS_GREATER_THAN.test(user, Pair.of("age", "33")), is(true));
    }

    @Test
    void shouldReturnGreaterThanAsFalse() {
        assertThat(ComparisonFilterBiPredicate.IS_GREATER_THAN.test(user, Pair.of("age", "36")), is(false));
        assertThat(ComparisonFilterBiPredicate.IS_GREATER_THAN.test(user, Pair.of("age1", "33")), is(false));
    }

    @Test
    void shouldReturnIsLessThanAsTrue() {
        assertThat(ComparisonFilterBiPredicate.IS_LESS_THAN.test(user, Pair.of("age", "37")), is(true));
    }

    @Test
    void shouldReturnIsLessThanAsFalse() {
        assertThat(ComparisonFilterBiPredicate.IS_LESS_THAN.test(user, Pair.of("age", "33")), is(false));
        assertThat(ComparisonFilterBiPredicate.IS_LESS_THAN.test(user, Pair.of("age1", "37")), is(false));
    }

    @Test
    void shouldMatchRegexPattern() {
        assertThat(ComparisonFilterBiPredicate.MATCHES_PATTERN.test(user, Pair.of("surname", "Tes")), is(true));
    }

    @Test
    void shouldNotMatchRegexPattern() {
        assertThat(ComparisonFilterBiPredicate.MATCHES_PATTERN.test(user, Pair.of("surname", "Dev")), is(false));
    }
}