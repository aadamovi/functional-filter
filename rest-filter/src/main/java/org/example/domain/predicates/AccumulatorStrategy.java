package org.example.domain.predicates;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public enum AccumulatorStrategy implements BinaryOperator<Boolean> {
    ANY((one, two) -> one || two),
    ALL((one, two) -> one && two);

    private final BinaryOperator<Boolean> accumulator;

    AccumulatorStrategy(BinaryOperator<Boolean> accumulator) {
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


}
