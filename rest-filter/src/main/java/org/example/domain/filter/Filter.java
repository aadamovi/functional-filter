package org.example.domain.filter;

import java.util.Map;

public interface Filter {
    boolean matches(Map<String, String> user);
}
