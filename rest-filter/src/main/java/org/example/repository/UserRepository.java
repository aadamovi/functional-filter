package org.example.repository;

import org.springframework.stereotype.Repository;

import java.util.AbstractMap;
import java.util.Map;

@Repository
public interface UserRepository {
    Map<String, String> user = Map.ofEntries(
        new AbstractMap.SimpleEntry<>("firstname", "Joe"),
        new AbstractMap.SimpleEntry<>("surname", "Bloggs"),
        new AbstractMap.SimpleEntry<>("role", "administrator"),
        new AbstractMap.SimpleEntry<>("age", "35")
    );


    default Map<String, String> getUser() {
        return user;
    }
}
