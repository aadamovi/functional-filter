package org.example.dto;

public class FilterDto {
    private final String key;
    private final String value;
    private final String filterType;

    public FilterDto(String key, String value, String filterType) {
        this.key = key;
        this.value = value;
        this.filterType = filterType;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getFilterType() {
        return filterType;
    }
}
