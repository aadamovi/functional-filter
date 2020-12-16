package org.example.service;

import org.example.domain.filter.UserFilter;
import org.example.dto.FilterDto;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.domain.predicates.AccumulatorStrategy.getByStrategyName;

@Service
public class UserFilterService implements FilterService {

    private final UserRepository userRepository;

    @Autowired
    public UserFilterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean filterBy(FilterDto dto) {
        final UserFilter filter = new UserFilter(dto);
        return filter.getPredicate().test(userRepository.getUser(), filter.getCriteria());
    }

    @Override
    public boolean filterByMultipleCriteria(List<FilterDto> filterDto, String accumulator) {
        return filterDto.stream()
            .map(UserFilter::new)
            .map(filter -> filter.getPredicate().test(userRepository.getUser(), filter.getCriteria()))
            .reduce(getByStrategyName(accumulator))
            .orElse(false);
    }
}
