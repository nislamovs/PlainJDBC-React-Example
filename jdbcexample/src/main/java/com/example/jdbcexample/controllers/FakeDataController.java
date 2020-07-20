package com.example.jdbcexample.controllers;


import com.example.jdbcexample.services.FakeDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class FakeDataController {

    private final FakeDataService fakeDataService;

    @PostMapping("/generateNewClass")
    public ResponseEntity<?> generateNewClass(@RequestParam(value = "pupilCount", required = true, defaultValue = "30") String pupilCount) {

        return ok(fakeDataService.genNewClass(pupilCount));
    }
}
