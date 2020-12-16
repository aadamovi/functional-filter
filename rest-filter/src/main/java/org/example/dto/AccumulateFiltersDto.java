package org.example.dto;

import java.util.List;

public class AccumulateFiltersDto {
    private final List<FilterDto> filters;
    private final String accumulatorType;

    public AccumulateFiltersDto(List<FilterDto> filters, String accumulatorType) {
        this.filters = filters;
        this.accumulatorType = accumulatorType;
    }

    public List<FilterDto> getFilters() {
        return filters;
    }

    public String getAccumulatorType() {
        return accumulatorType;
    }
}
