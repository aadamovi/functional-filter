package org.example.service;

import org.assertj.core.util.Lists;
import org.example.domain.predicates.AccumulatorStrategy;
import org.example.domain.predicates.UserPredicates;
import org.example.dto.FilterDto;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserFilterServiceTest {
    @Mock
    private UserRepository userRepository;

    private FilterService defaultFilterService;

    private final Map<String, String> user = Map.ofEntries(
        new AbstractMap.SimpleEntry<>("firstname", "Mic"),
        new AbstractMap.SimpleEntry<>("surname", "Test"),
        new AbstractMap.SimpleEntry<>("role", "tester"),
        new AbstractMap.SimpleEntry<>("age", "35"),
        new AbstractMap.SimpleEntry<>("isMarried", "true")
    );

    @BeforeEach
    void setUp() {
        defaultFilterService = new UserFilterService(userRepository);
        when(userRepository.getUser()).thenReturn(user);
    }

    @Test
    void shouldFilterBoolean() {
        FilterDto isMarriedFilter = new FilterDto("isMarried", "true", UserPredicates.BOOLEAN_IS_EQUAL.getKey());
        boolean result = defaultFilterService.filterBy(isMarriedFilter);

        assertThat(result, is(true));
    }

    @Test
    void shouldFilterByGreaterThan() {
        FilterDto isPresentFilter = new FilterDto("age", "33", UserPredicates.IS_GREATER_THAN.getKey());
        boolean result = defaultFilterService.filterBy(isPresentFilter);

        assertThat(result, is(true));
    }

    @Test
    void shouldReturnTrueWhenUsingAllAcc() {
        List<FilterDto> listOfFilters = Lists.newArrayList(
            new FilterDto("age", "33", UserPredicates.IS_GREATER_THAN.getKey()),
            new FilterDto("surname", "Test", UserPredicates.STRING_IS_EQUAL.getKey()),
            new FilterDto("isMarried", "true", UserPredicates.BOOLEAN_IS_EQUAL.getKey()));
        boolean result = defaultFilterService.filterByMultiple(listOfFilters, AccumulatorStrategy.ALL);

        assertThat(result, is(true));
    }

    @Test
    void shouldReturnFalseWhenUsingAllAcc() {
        List<FilterDto> listOfFilters = Lists.newArrayList(
            new FilterDto("age", "33", UserPredicates.IS_GREATER_THAN.getKey()),
            new FilterDto("surname", "Test", UserPredicates.STRING_IS_EQUAL.getKey()),
            new FilterDto("isMarried", "false", UserPredicates.BOOLEAN_IS_EQUAL.getKey()));
        boolean result = defaultFilterService.filterByMultiple(listOfFilters, AccumulatorStrategy.ALL);

        assertThat(result, is(false));
    }

    @Test
    void shouldReturnTrueWhenUsingAnyAcc() {
        List<FilterDto> listOfFilters = Lists.newArrayList(
            new FilterDto("age", "33", UserPredicates.IS_GREATER_THAN.getKey()),
            new FilterDto("surname", "Test1", UserPredicates.STRING_IS_EQUAL.getKey()),
            new FilterDto("isMarried", "false", UserPredicates.BOOLEAN_IS_EQUAL.getKey()));
        boolean result = defaultFilterService.filterByMultiple(listOfFilters, AccumulatorStrategy.ANY);

        assertThat(result, is(true));
    }

    @Test
    void shouldReturnFalseWhenUsingAnyAcc() {
        List<FilterDto> listOfFilters = Lists.newArrayList(
            new FilterDto("age", "33", UserPredicates.IS_GREATER_THAN.getKey()),
            new FilterDto("surname", "Test", UserPredicates.STRING_IS_EQUAL.getKey()),
            new FilterDto("isMarried", "true", UserPredicates.BOOLEAN_IS_EQUAL.getKey()));
        boolean result = defaultFilterService.filterByMultiple(listOfFilters, AccumulatorStrategy.ANY);

        assertThat(result, is(true));
    }
}