package com.example.jdbcexample.controllers;

import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.SubjectDTO;
import com.example.jdbcexample.services.SubjectsService;
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
@Api(name = "Subjects services", description = "Methods for managing subjects", group = "School", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class SubjectController {

    private final SubjectsService subjectsService;

    @RequestMapping(value = "/subject/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/subject/all",
            verb = ApiVerb.GET,
            description = "Get all subjects.",
            summary =  "Get all subjects.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<SubjectDTO> getSubjects() {
        return subjectsService.getAllSubjects();
    }

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    @ApiMethod(
            path = "/subject",
            verb = ApiVerb.GET,
            description = "Get page of subjects.",
            summary =  "Get page of subjects.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<SubjectDTO> getSubjectsPage(@ApiQueryParam(name="pagenum", description="Page number") @RequestParam(name = "pagenum", required = true) String pagenum,
                                            @ApiQueryParam(name="pagesize", description="Page size (number of records)") @RequestParam(name = "pagesize", required = true) String pagesize,
                                            @ApiQueryParam(name="sort", description="Sorting (asc, desc)") @RequestParam(name = "sort", required = true) String sort,
                                            @ApiQueryParam(name="group", description="Grouping by (fieldname)") @RequestParam(name = "group", required = true) String group) {

        return subjectsService.getSubjectsPage(pagenum, pagesize, sort, group);
    }

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/subject/{id}",
            verb = ApiVerb.GET,
            description = "Get subject by Id.",
            summary =  "Get subject by Id.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public SubjectDTO getSubjectById(@ApiPathParam(name="id", description="Subject id")
                                    @PathVariable("id") String id) {
        return subjectsService.getSubjectById(id);
    }

    @RequestMapping(value = "/subject", method = RequestMethod.POST)
    @ApiMethod(
            path = "/subject",
            verb = ApiVerb.POST,
            description = "Add new subject.",
            summary =  "Add new subject.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO newSubject(@ApiBodyObject @Valid @RequestBody SubjectDTO newSubject) {
        return subjectsService.addNewSubject(newSubject);
    }

    @RequestMapping(value = "/subject", method = RequestMethod.PUT)
    @ApiMethod(
            path = "/subject",
            verb = ApiVerb.PUT,
            description = "Edit subject data.",
            summary =  "Edit subject data.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO editSubject(@ApiBodyObject @Valid @RequestBody SubjectDTO subject) {
        return subjectsService.editSubjectData(subject);
    }

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.DELETE)
    @ApiMethod(
            path = "/subject/{id}",
            verb = ApiVerb.DELETE,
            description = "Delete subject by Id.",
            summary =  "Delete subject by Id.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO deleteSubject(@ApiPathParam(name="id", description="Subject id")
                                     @PathVariable("id") String id) {
        return subjectsService.deleteSubject(id);
    }

}
