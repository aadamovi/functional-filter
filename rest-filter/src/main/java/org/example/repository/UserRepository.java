package org.example.repository;

import org.springframework.stereotype.Repository;

import java.util.AbstractMap;
import java.util.Map;

@Repository
public class UserRepository {
    Map<String, String> user = Map.ofEntries(
        new AbstractMap.SimpleEntry<>("firstname", "Joe"),
        new AbstractMap.SimpleEntry<>("surname", "Bloggs"),
        new AbstractMap.SimpleEntry<>("role", "administrator"),
        new AbstractMap.SimpleEntry<>("age", "35")
    );

    public Map<String, String> getUser() {
        return user;
    }
}
