package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.example.dto.AccumulateFiltersDto;
import org.example.dto.FilterDto;
import org.example.service.FilterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.example.domain.predicates.UserPredicates.IS_GREATER_THAN;
import static org.example.domain.predicates.UserPredicates.STRING_IS_EQUAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserFilterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private FilterService filterService;

    @Test
    void shouldReceiveReplyForSingleFilter() throws Exception {
        FilterDto dto = new FilterDto("firstname", "Joe", STRING_IS_EQUAL.getKey());
        MockHttpServletResponse response = mockMvc.perform(
            post("/api/filter/user/single")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

        assertThat(response.getContentAsString(), is("true"));
    }

    @Test
    void shouldFilterByMultipleCriteria() throws Exception {
        AccumulateFiltersDto dto = new AccumulateFiltersDto(
            Lists.newArrayList(
                new FilterDto("firstname", "Joe", STRING_IS_EQUAL.getKey()),
                new FilterDto("age", "33", IS_GREATER_THAN.getKey())),
            "all");

        MockHttpServletResponse response = mockMvc.perform(
            post("/api/filter/user/accumulate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

        assertThat(response.getContentAsString(), is("true"));
    }
}