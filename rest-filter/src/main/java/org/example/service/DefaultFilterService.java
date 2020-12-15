package org.example.service;

import org.apache.commons.lang3.tuple.Pair;
import org.example.domain.filter.UserFilter;
import org.example.domain.predicates.AccumulatorStrategy;
import org.example.domain.predicates.UserPredicates;
import org.example.dto.FilterDto;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultFilterService implements FilterService {

    private final UserRepository userRepository;

    @Autowired
    public DefaultFilterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean filterBy(FilterDto filter) {
        return UserPredicates.getFilterFromString(filter.getFilterType()).test(
            userRepository.getUser(),
            Pair.of(filter.getKey(), filter.getValue()));
    }

    @Override
    public boolean filterByMultiple(List<FilterDto> filterDto, AccumulatorStrategy accumulator) {
        final Map<String, String> user = userRepository.getUser();
        final List<UserFilter> filters = filterDto.stream()
            .map(dto -> new UserFilter(
                Pair.of(dto.getKey(), dto.getValue()),
                UserPredicates.getFilterFromString(dto.getFilterType())))
            .collect(Collectors.toList());

        return filters.stream()
            .map(filter -> filter.getPredicate().test(user, filter.getCriteria()))
            .reduce(accumulator)
            .orElse(false);
    }
}
