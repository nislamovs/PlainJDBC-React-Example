package com.example.jdbcexample.controllers;


import com.example.jdbcexample.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
@Api(name = "Statistics services", description = "Methods for managing statistics", group = "Statistics", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class StatisticsController {

    private final StatisticsService statisticsService;

    //TODO
    //   1. Get email provider usage stats (pupils, teachers, parents, all_persons) #One group only  //API path ->  /stats/persons/{person_group}
    //      returns Map<string, string>
    //
    //   2. Get email provider usage stats for all groups (pupils, teachers, parents, all_persons)   //API path ->  /stats/persons
    //      returns Map<string, Map<string, string>>




}
