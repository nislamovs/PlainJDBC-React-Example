package com.example.jdbcexample.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FakeDataService {

    public String genNewClass(String pupilCount) {

        int count = Integer.parseInt(pupilCount);

        return "";
    }
}
