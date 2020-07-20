package com.example.jdbcexample.services;


import com.example.jdbcexample.repository.FakeDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FakeDataService {

    private final FakeDataRepository fakeDataRepository;

    public String genNewClass(String pupilCount) {

        return fakeDataRepository.generateNewClass(pupilCount);
    }
}
