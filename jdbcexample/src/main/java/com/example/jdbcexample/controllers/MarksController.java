package com.example.jdbcexample.controllers;

import com.example.jdbcexample.domain.dao.SubjectMarkDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.SubjectMarkDTO;
import com.example.jdbcexample.services.MarksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
@Api(name = "Marks services", description = "Methods for managing marks", group = "Statistics", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class MarksController {

    private final MarksService marksService;

    @RequestMapping(value = "/mark/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/mark/all",
            verb = ApiVerb.GET,
            description = "Get all subject marks.",
            summary =  "Get all subject marks.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<SubjectMarkDTO> getMarks() {
        return marksService.getAllMarks();
    }

    @RequestMapping(value = "/mark/{pupilId}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/mark/{pupilId}",
            verb = ApiVerb.GET,
            description = "Find subject marks by pupil Id",
            summary = "Find subject marks by pupil Id",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<SubjectMarkDTO> getMarksByPupilId(@ApiPathParam(name="pupilId", description="Pupil id") @PathVariable(value="pupilId") String pupilId) {
        return marksService.retrieveMarksByPupilId(pupilId);
    }

    @RequestMapping(value = "/mark", method = RequestMethod.GET)
    @ApiMethod(
            path = "/mark",
            verb = ApiVerb.GET,
            description = "Get marks page",
            summary = "Get marks page",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public List<SubjectMarkDTO> getMarksPage(@ApiQueryParam(name="pagenum", description="Page number") @RequestParam(name = "pagenum", required = true) String pagenum,
                                             @ApiQueryParam(name="pagesize", description="Page size (number of records)") @RequestParam(name = "pagesize", required = true) String pagesize,
                                             @ApiQueryParam(name="sort", description="Sorting (asc, desc)") @RequestParam(name = "sort", required = true) String sort,
                                             @ApiQueryParam(name="group", description="Grouping by (fieldname)") @RequestParam(name = "group", required = true) String group) {

        return marksService.retrieveMarksPage(pagenum, pagesize, sort, group);
    }

    @RequestMapping(value = "/marksbydate", method = RequestMethod.GET)
    @ApiMethod(
            path = "/marksbydate",
            verb = ApiVerb.GET,
            description = "Find subject mark by Id",
            summary = "Find subject mark by Id",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public List<SubjectMarkDTO> getMarksByDate(@ApiQueryParam(name="startDate", description="StartDate") @RequestParam(name = "startDate", required = true) String startDate,
                                               @ApiQueryParam(name="endDate", description="EndDate") @RequestParam(name = "endDate", required = true) String endDate) {

        return marksService.getMarksByDateInterval(startDate, endDate);
    }

    @RequestMapping(value = "/mark", method = RequestMethod.POST)
    @ApiMethod(
            path = "/mark",
            verb = ApiVerb.POST,
            description = "Add new subject mark",
            summary = "Add new subject mark",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public AbstractDTO addMark(@ApiBodyObject @Valid @RequestBody SubjectMarkDTO mark) {
        return marksService.addNewMark(mark);
    }

    @RequestMapping(value = "/mark/{id}", method = RequestMethod.PUT)
    @ApiMethod(
            path = "/mark/{id}",
            verb = ApiVerb.PUT,
            description = "Update subject mark data by Id",
            summary = "Update subject mark data by Id",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public AbstractDTO editMark(@ApiBodyObject @Valid @RequestBody SubjectMarkDTO mark,
                                @ApiPathParam(name="id", description="Pupil id") @PathVariable String id) {
        mark.setId(id);
        return marksService.editMark(mark);
    }

    @RequestMapping(value = "/mark/{id}", method = RequestMethod.DELETE)
    @ApiMethod(
            path = "/mark/{id}",
            verb = ApiVerb.DELETE,
            description = "Delete subject mark by Id",
            summary = "Delete subject mark by Id",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO removeMark(@ApiPathParam(name="id", description="Pupil id") @PathVariable String id) {
        return marksService.deleteMark(id);
    }
}