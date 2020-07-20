package com.example.jdbcexample.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface FakeDataRepository {

    String generateNewClass(String pupilCount);
}
