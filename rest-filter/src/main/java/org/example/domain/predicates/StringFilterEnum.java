package org.example.domain.predicates;

import java.util.function.BiPredicate;

public class StringFilterEnum implements BiPredicate<String, String> {
    @Override
    public boolean test(String s, String s2) {
        return false;
    }
}
