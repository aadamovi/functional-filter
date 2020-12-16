package org.example.controller;

import org.example.dto.AccumulateFiltersDto;
import org.example.dto.FilterDto;
import org.example.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filter/user")
public class UserFilterController {

    private final FilterService filterService;

    @Autowired
    public UserFilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @PostMapping(("/single"))
    @ResponseBody
    public String filterBySingleCriteria(@RequestBody FilterDto filter) {
        return String.valueOf(filterService.filterBy(filter));
    }

    @PostMapping("/accumulate")
    @ResponseBody
    public String filterByMultipleCriteria(@RequestBody AccumulateFiltersDto filters) {
        return String.valueOf(filterService.filterByMultipleCriteria(filters.getFilters(), filters.getAccumulatorType()));
    }
}
