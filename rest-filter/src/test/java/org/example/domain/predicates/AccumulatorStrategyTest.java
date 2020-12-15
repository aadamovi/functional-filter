package org.example.domain.predicates;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class AccumulatorStrategyTest {

    @Test
    void shouldAccumulateAllToTrue() {
        assertThat(AccumulatorStrategy.ALL.apply(true, true), is(true));
    }

    @Test
    void shouldAccumulateAllToFalse() {
        assertThat(AccumulatorStrategy.ALL.apply(true, false), is(false));
        assertThat(AccumulatorStrategy.ALL.apply(true, false), is(false));
        assertThat(AccumulatorStrategy.ALL.apply(false, true), is(false));
    }

    @Test
    void shouldAccumulateAnyToTrue() {
        assertThat(AccumulatorStrategy.ANY.apply(true, true), is(true));
        assertThat(AccumulatorStrategy.ANY.apply(true, false), is(true));
        assertThat(AccumulatorStrategy.ANY.apply(false, true), is(true));
    }

    @Test
    void shouldAccumulateAnyToFalse() {
        assertThat(AccumulatorStrategy.ANY.apply(false, false), is(false));
    }
}