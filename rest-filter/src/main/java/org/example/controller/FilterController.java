package org.example.controller;

import org.example.dto.FilterDto;
import org.example.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

    private final FilterService filterService;

    @Autowired
    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @PostMapping()
    @ResponseBody
    public String filter(FilterDto filter) {
        return String.valueOf(filterService.filterBy(filter));
    }
}
