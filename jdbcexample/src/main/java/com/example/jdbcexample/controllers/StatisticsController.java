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

/*


TODO
   1. Get email provider usage stats (pupils, teachers, parents, all_persons) #One group only  //API path ->  /stats/email_provider/persons?person_group=
      returns Map<string, string>

   2. get pupils - relatives


-- Get all marks for the pupils of definite class
-- Get average marks for pupils by pupil id - all school
-- Get average marks for pupils - all school, TOP 5
-- Get average marks for pupils, whose avg mark above 7 - all school
-- Get average marks for pupils per subject - all school, TOP 5
-- Get average marks for pupils - by class
-- Get parents without kids list
-- Get pupils list with class name and class head name and status [passes, fails]
-- Get pupils list with marks per subject (min, max, avg, count, passes or not, class name and class head)

 */



}
