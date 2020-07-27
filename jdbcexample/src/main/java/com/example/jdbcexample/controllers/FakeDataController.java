package com.example.jdbcexample.controllers;


import com.example.jdbcexample.services.FakeDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.annotation.global.ApiChangelog;
import org.jsondoc.core.annotation.global.ApiChangelogSet;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
@Api(name = "Fake data generation services", description = "Fake data controller", group = "Fake data", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class FakeDataController {

    private final FakeDataService fakeDataService;

    @RequestMapping(value = "/generateNewClass", method = RequestMethod.POST)
    @ApiMethod(
            path = "/api/v1/generateNewClass",
            verb = ApiVerb.POST,
            description = "Launches new class generation process in database.",
            summary = "Launches new class generation process in database.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public String generateNewClass(@ApiQueryParam(name = "Pupil count", description = "Pupil count in class")
                                   @RequestParam(value = "pupilCount", required = true, defaultValue = "30") String pupilCount) {

        return fakeDataService.genNewClass(pupilCount);
    }
}
