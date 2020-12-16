package org.example.domain.predicates;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public enum AccumulatorStrategy implements BinaryOperator<Boolean> {
    ANY("any", (one, two) -> one || two),
    ALL("all", (one, two) -> one && two);

    private final String strategy;
    private final BinaryOperator<Boolean> accumulator;

    AccumulatorStrategy(String strategy, BinaryOperator<Boolean> accumulator) {
        this.strategy = strategy;
        this.accumulator = accumulator;
    }

    @Override
    public Boolean apply(Boolean one, Boolean two) {
        return accumulator.apply(one, two);
    }

    @Override
    public <V> BiFunction<Boolean, Boolean, V> andThen(Function<? super Boolean, ? extends V> after) {
        return accumulator.andThen(after);
    }

    public static AccumulatorStrategy getByStrategyName(String strategy) {
        return Arrays.stream(values())
            .filter(val -> val.getStrategy().equals(strategy))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Incorrect strategy supplied"));
    }

    public String getStrategy() {
        return strategy;
    }
}
