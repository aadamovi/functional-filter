package org.example.service;

import org.example.dto.FilterDto;

import java.util.List;

public interface FilterService {
    boolean filterBy(FilterDto filter);

    boolean filterByMultipleCriteria(List<FilterDto> filterDto, String accumulator);
}
