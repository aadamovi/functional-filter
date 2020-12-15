package org.example.domain.predicates;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class BooleanFilterPredicateTest {

    @Test
    void shouldReturnFalse() {
        assertThat(BooleanFilterPredicate.EQUALS.test(Pair.of(true, false)), is(false));
    }

    @Test
    void shouldReturnTrue() {
        assertThat(BooleanFilterPredicate.EQUALS.test(Pair.of(false, false)), is(true));
        assertThat(BooleanFilterPredicate.EQUALS.test(Pair.of(true, true)), is(true));
    }
}