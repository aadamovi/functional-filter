package org.example.service;

import org.example.domain.predicates.AccumulatorStrategy;
import org.example.dto.FilterDto;

import java.util.List;

public interface FilterService {
    boolean filterBy(FilterDto filter);
    boolean filterByMultiple(List<FilterDto> filterDto, AccumulatorStrategy accumulator);
}
