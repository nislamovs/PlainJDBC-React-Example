package com.example.jdbcexample.controllers;


import com.example.jdbcexample.domain.dto.statistics.*;
import com.example.jdbcexample.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
@Api(name = "Statistics services", description = "Methods for managing statistics", group = "Statistics", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class StatisticsController {

    private final StatisticsService statisticsService;

    @RequestMapping(value = "/stats/pupilsRelativesList", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/pupilsRelativesList",
            verb = ApiVerb.GET,
            description = "Get pupils with relatives among another pupils.",
            summary = "Get pupils with relatives among another pupils.",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilsRelativesDTO> retrievePupilsRelativesList() {
        return statisticsService.getPupilsRelativesList();
    }

    @RequestMapping(value = "/stats/emailProvidersList", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/emailProvidersList",
            verb = ApiVerb.GET,
            description = "Get statistics over email provider usage over people group [pupils, teachers, parents, all].",
            summary = "Get statistics over email provider usage over people group [pupils, teachers, parents, all].",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public Map<Integer, Map<String, Integer>> retrieveEmailProvidersList(
            @ApiQueryParam(name = "group", description = "Person group")
            @RequestParam(name = "group", defaultValue = "persons") String personsGroup) {
        return statisticsService.getEmailProvidersList(personsGroup);
    }

    @RequestMapping(value = "/stats/pupilsMarksPerClass", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/pupilsMarksPerClass",
            verb = ApiVerb.GET,
            description = " Get all marks for the pupils of definite class.",
            summary = " Get all marks for the pupils of definite class.",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilMarkPerClassDTO> retrievePupilsMarksPerClass(
            @ApiQueryParam(name = "className", description = "Class name")
            @RequestParam(name = "className", defaultValue = "5A") String className,

            @ApiQueryParam(name = "pageSize", description = "Page size")
            @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,

            @ApiQueryParam(name = "pageNum", description = "Page number")
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        return statisticsService.getPupilsMarksPerClass(className, pageSize, pageNum);
    }

    @RequestMapping(value = "/stats/pupilsMarksPerClass/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/pupilsMarksPerClass/all",
            verb = ApiVerb.GET,
            description = " Get all marks for the pupils of definite class.",
            summary = " Get all marks for the pupils of definite class.",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilMarkPerClassDTO> retrievePupilsMarksPerClass(
            @ApiQueryParam(name = "className", description = "Persons email address")
            @RequestParam(name = "className", defaultValue = "5A") String className) {
        return statisticsService.getPupilsMarksPerClass(className);
    }

    @RequestMapping(value = "/stats/avgPupilsMarksByPupilIdInterval", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/avgPupilsMarksByPupilIdInterval",
            verb = ApiVerb.GET,
            description = "Get average marks for pupils by pupil id - all school [page].",
            summary = "Get average marks for pupils by pupil id - all school [page].",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilAvgMarkDTO> retrieveAvgPupilsMarksByPupilIdInterval(
            @ApiQueryParam(name = "idStart", description = "Min id value of interval.")
            @RequestParam(name = "idStart", defaultValue = "1") int idStart,

            @ApiQueryParam(name = "idEnd", description = "Max id value of interval.")
            @RequestParam(name = "idEnd", defaultValue = "30") int idEnd,

            @ApiQueryParam(name = "pageSize", description = "Page size.")
            @RequestParam(name = "pageSize", defaultValue = "30") int pageSize,

            @ApiQueryParam(name = "pageNum", description = "Page number.")
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {

        return statisticsService.getAvgPupilsMarksByPupilIdInterval(idStart, idEnd, pageSize, pageNum);
    }

    @RequestMapping(value = "/stats/avgPupilsMarksByPupilIdInterval/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/avgPupilsMarksByPupilIdInterval/all",
            verb = ApiVerb.GET,
            description = "Get average marks for pupils by pupil id - all school.",
            summary = "Get average marks for pupils by pupil id - all school.",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilAvgMarkDTO> retrieveAvgPupilsMarksByPupilIdInterval(
            @ApiQueryParam(name = "idStart", description = "Min id value of interval.")
            @RequestParam(name = "idStart", defaultValue = "1") int idStart,

            @ApiQueryParam(name = "idEnd", description = "Max id value of interval.")
            @RequestParam(name = "idEnd", defaultValue = "30") int idEnd) {
        return statisticsService.getAvgPupilsMarksByPupilIdInterval(idStart, idEnd);
    }

    @RequestMapping(value = "/stats/avgPupilsMarksTop5PerSchool", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/avgPupilsMarksTop5PerSchool",
            verb = ApiVerb.GET,
            description = "Get average marks for TOP5 pupils - all school.",
            summary = "Get average marks for  TOP5 pupils - all school.",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilAvgMarkRatedDTO> retrieveAvgPupilsMarksTop5PerSchool() {
        return statisticsService.getAvgPupilsMarksTop5PerSchool();
    }

    @RequestMapping(value = "/stats/avgPupilsMarksAbove7PerSchool", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/avgPupilsMarksAbove7PerSchool",
            verb = ApiVerb.GET,
            description = "Get average marks for pupils, whose avg mark above 7 - all school [page].",
            summary = "Get average marks for pupils, whose avg mark above 7 - all school [page].",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilAvgMarkRatedDTO> retrieveAvgPupilsMarksAbove7PerSchool(
            @ApiQueryParam(name = "pageSize", description = "Page size.")
            @RequestParam(name = "pageSize", defaultValue = "30") int pageSize,

            @ApiQueryParam(name = "pageNum", description = "Page number.")
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {

        return statisticsService.getAvgPupilsMarksAbove7PerSchool(pageSize, pageNum);
    }

    @RequestMapping(value = "/stats/avgPupilsMarksAbove7PerSchool/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/avgPupilsMarksAbove7PerSchool/all",
            verb = ApiVerb.GET,
            description = "Get average marks for pupils, whose avg mark above 7 - all school.",
            summary = "Get average marks for pupils, whose avg mark above 7 - all school.",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilAvgMarkRatedDTO> retrieveAvgPupilsMarksAbove7PerSchool() {
        return statisticsService.getAvgPupilsMarksAbove7PerSchool();
    }

    @RequestMapping(value = "/stats/avgPupilsMarksBySubject", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/avgPupilsMarksBySubject",
            verb = ApiVerb.GET,
            description = "Get average marks for pupils per subject - all school, TOP 5.",
            summary = "Get average marks for pupils per subject - all school, TOP 5.",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilAvgMarkRatedDTO> retrieveAvgPupilsMarksBySubject(
            @ApiQueryParam(name = "subject", description = "Subject name.")
            @RequestParam(name = "subject", defaultValue = "Physics") String subject) {
        return statisticsService.getAvgPupilsMarksBySubject(subject);
    }

    @RequestMapping(value = "/stats/avgPupilsMarksByClass/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/avgPupilsMarksByClass/all",
            verb = ApiVerb.GET,
            description = "Get average marks for pupils - by class.",
            summary = "Get average marks for pupils - by class.",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilAvgMarkRatedDTO> retrieveAvgPupilsMarksByClass(
            @ApiQueryParam(name = "className", description = "Class name.")
            @RequestParam(name = "className", defaultValue = "5A") String className) {
        return statisticsService.getAvgPupilsMarksByClass(className);
    }

    @RequestMapping(value = "/stats/avgPupilsMarksByClass", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/avgPupilsMarksByClass",
            verb = ApiVerb.GET,
            description = "Get average marks for pupils - by class [page].",
            summary = "Get average marks for pupils - by class [page].",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilAvgMarkRatedDTO> retrieveAvgPupilsMarksByClass(
            @ApiQueryParam(name = "className", description = "Class name.")
            @RequestParam(name = "className", defaultValue = "5A") String className,

            @ApiQueryParam(name = "pageSize", description = "Page size.")
            @RequestParam(name = "pageSize", defaultValue = "30") int pageSize,

            @ApiQueryParam(name = "pageNum", description = "Page number.")
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {

        return statisticsService.getAvgPupilsMarksByClass(className, pageSize, pageNum);
    }

    @RequestMapping(value = "/stats/parentsAndKidsList", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/avgPupilsMarksByClass",
            verb = ApiVerb.GET,
            description = "Get parents and kids list - [page].",
            summary = "Get parents and kids list - [page].",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsKidsParentsDTO> retrieveParentsAndKidsList(
            @ApiQueryParam(name = "pageSize", description = "Page size.")
            @RequestParam(name = "pageSize", defaultValue = "30") int pageSize,

            @ApiQueryParam(name = "pageNum", description = "Page number.")
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {

        return statisticsService.getParentsAndKidsList(pageSize, pageNum);
    }

    @RequestMapping(value = "/stats/parentsAndKidsList/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/avgPupilsMarksByClass/all",
            verb = ApiVerb.GET,
            description = "Get parents and kids list.",
            summary = "Get parents and kids list.",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsKidsParentsDTO> retrieveParentsAndKidsList() {
        return statisticsService.getParentsAndKidsList();
    }

    @RequestMapping(value = "/stats/pupilsReview", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/pupilsReview",
            verb = ApiVerb.GET,
            description = "Get pupil's review - [page].",
            summary = "Get pupil's review - [page].",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilReviewDTO> retrievePupilsReview(
            @ApiQueryParam(name = "pageSize", description = "Page size.")
            @RequestParam(name = "pageSize", defaultValue = "30") int pageSize,

            @ApiQueryParam(name = "pageNum", description = "Page number.")
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {

        return statisticsService.getPupilsReview(pageSize, pageNum);
    }

    @RequestMapping(value = "/stats/pupilsReview/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/pupilsReview/all",
            verb = ApiVerb.GET,
            description = "Get pupil's review.",
            summary = "Get pupil's review.",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilReviewDTO> retrievePupilsReview() {
        return statisticsService.getPupilsReview();
    }


    @RequestMapping(value = "/stats/pupilsFullReview", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/pupilsFullReview",
            verb = ApiVerb.GET,
            description = "Get pupils full review - [page].",
            summary = "Get pupils full review - [page].",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilFullReviewDTO> retrievePupilsFullReview(
            @ApiQueryParam(name = "pageSize", description = "Page size.")
            @RequestParam(name = "pageSize", defaultValue = "30") int pageSize,

            @ApiQueryParam(name = "pageNum", description = "Page number.")
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {

        return statisticsService.getPupilsFullReview(pageSize, pageNum);
    }

    @RequestMapping(value = "/stats/pupilsFullReview/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/pupilsFullReview/all",
            verb = ApiVerb.GET,
            description = "Get pupil's full review.",
            summary = "Get pupil's full review.",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilFullReviewDTO> retrievePupilsFullReview() {
        return statisticsService.getPupilsFullReview();
    }

    @RequestMapping(value = "/stats/pupilsFullReviewByEmail", method = RequestMethod.GET)
    @ApiMethod(
            path = "/stats/pupilsFullReviewByEmail",
            verb = ApiVerb.GET,
            description = "Get pupil's full review by email.",
            summary = "Get pupil's full review by email.",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<StatsPupilFullReviewDTO> retrievePupilsFullReviewByEmail(
            @ApiQueryParam(name = "email", description = "Pupil's email.")
            @RequestParam(name = "email", defaultValue = "afaulds2g@pinterest.com") String email) {
        return statisticsService.getPupilsFullReviewByEmail(email);
    }
}


