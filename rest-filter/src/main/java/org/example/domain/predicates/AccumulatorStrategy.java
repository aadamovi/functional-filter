package org.example.domain.predicates;

import java.util.function.BinaryOperator;

public enum AccumulatorStrategy implements BinaryOperator<Boolean> {
    ANY((one, two) -> one || two),
    ALL((one, two) -> one && two);

    private BinaryOperator<Boolean> accumulator;

    AccumulatorStrategy(BinaryOperator<Boolean> accumulator) {
        this.accumulator = accumulator;
    }

    @Override
    public Boolean apply(Boolean one, Boolean two) {
        return accumulator.apply(one, two);
    }
}
