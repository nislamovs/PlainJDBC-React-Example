package com.example.jdbcexample.controllers;

import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.TeacherDTO;
import com.example.jdbcexample.services.TeachersService;
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
@Api(name = "Teacher services", description = "Methods for managing teachers", group = "Persons", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class TeacherController {

    private final TeachersService teachersService;

    @RequestMapping(value = "/teacher/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/teacher/all",
            verb = ApiVerb.GET,
            description = "Get all teachers.",
            summary =  "Get all teachers.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<TeacherDTO> getTeachers() {
        return teachersService.getAllTeachers();
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    @ApiMethod(
            path = "/teacher",
            verb = ApiVerb.GET,
            description = "Get page of teachers.",
            summary =  "Get page of teachers.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<TeacherDTO> getTeachersPage(@ApiQueryParam(name="pagenum", description="Page number") @RequestParam(name = "pagenum", required = true) String pagenum,
                                            @ApiQueryParam(name="pagesize", description="Page size (number of records)") @RequestParam(name = "pagesize", required = true) String pagesize,
                                            @ApiQueryParam(name="sort", description="Sorting (asc, desc)") @RequestParam(name = "sort", required = true) String sort,
                                            @ApiQueryParam(name="group", description="Grouping by (fieldname)") @RequestParam(name = "group", required = true) String group) {

        return teachersService.getTeachersPage(pagenum, pagesize, sort, group);
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/teacher/{id}",
            verb = ApiVerb.GET,
            description = "Get teacher by id.",
            summary =  "Get teacher by id.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public TeacherDTO getTeacherById(@ApiPathParam(name="id", description="Teacher id")
                                     @PathVariable("id") String id) {
        return teachersService.getTeacherById(id);
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    @ApiMethod(
            path = "/teacher",
            verb = ApiVerb.POST,
            description = "Create new teacher",
            summary =  "Create new teacher",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO newTeacher(@ApiBodyObject @Valid @RequestBody TeacherDTO newTeacher) {
        return teachersService.addNewTeacher(newTeacher);
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.PUT)
    @ApiMethod(
            path = "/teacher",
            verb = ApiVerb.PUT,
            description = "Edit teacher data",
            summary =  "Edit teacher data",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO editTeacher(@ApiBodyObject @Valid @RequestBody TeacherDTO teacher) {
        return teachersService.editTeacherData(teacher);
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE)
    @ApiMethod(
            path = "/teacher/{id}",
            verb = ApiVerb.DELETE,
            description = "Delete teacher",
            summary =  "Delete teacher",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO deleteTeacher(@ApiPathParam(name="id", description="Teacher id")
                                     @PathVariable("id") String id) {
        return teachersService.deleteTeacher(id);
    }
}
