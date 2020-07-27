package com.example.jdbcexample.controllers;

import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.SchoolClassDTO;
import com.example.jdbcexample.services.ClassesService;
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
@Api(name = "School class services", description = "Methods for managing school classes", group = "School", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class SchoolClassController {

    private final ClassesService classesService;

    @RequestMapping(value = "/class/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/class/all",
            verb = ApiVerb.GET,
            description = "Get all school classes.",
            summary =  "Get all school classes.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<SchoolClassDTO> getClasses() {
        return classesService.getAllClasses();
    }

    @RequestMapping(value = "/class", method = RequestMethod.GET)
    @ApiMethod(
            path = "/class",
            verb = ApiVerb.GET,
            description = "Get page of school classes.",
            summary =  "Get page of school classes.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<SchoolClassDTO> getClassesPage(@ApiQueryParam(name="pagenum", description="Page number") @RequestParam(name = "pagenum", required = true) String pagenum,
                                               @ApiQueryParam(name="pagesize", description="Page size (number of records)")  @RequestParam(name = "pagesize", required = true) String pagesize,
                                               @ApiQueryParam(name="sort", description="Sorting (asc, desc)") @RequestParam(name = "sort", required = true) String sort,
                                               @ApiQueryParam(name="group", description="Grouping by (fieldname)") @RequestParam(name = "group", required = true) String group) {

        return classesService.getClassesPage(pagenum, pagesize, sort, group);
    }

    @RequestMapping(value = "/class/{id}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/class/{id}",
            verb = ApiVerb.GET,
            description = "Get school class by Id.",
            summary =  "Get school class by Id.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public SchoolClassDTO getSchoolClassById(@ApiPathParam(name="id", description="School class id")
                                             @PathVariable("id") String id) {
        return classesService.getSchoolClassById(id);
    }

    @RequestMapping(value = "/class", method = RequestMethod.POST)
    @ApiMethod(
            path = "/class",
            verb = ApiVerb.POST,
            description = "Create new school class.",
            summary =  "Create new school class.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO newSchoolClass(@ApiBodyObject @Valid @RequestBody SchoolClassDTO newSchoolClass) {
        return classesService.addNewSchoolClass(newSchoolClass);
    }

    @RequestMapping(value = "/class", method = RequestMethod.PUT)
    @ApiMethod(
            path = "/class",
            verb = ApiVerb.PUT,
            description = "Edit school class data.",
            summary =  "Edit school class data.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO editSchoolClass(@ApiBodyObject @Valid @RequestBody SchoolClassDTO schoolClass) {
        return classesService.editSchoolClassData(schoolClass);
    }

    @RequestMapping(value = "/class/{id}", method = RequestMethod.DELETE)
    @ApiMethod(
            path = "/class/{id}",
            verb = ApiVerb.DELETE,
            description = "Delete school class by Id.",
            summary =  "Delete school class by Id.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO deleteSchoolClass(@ApiPathParam(name="id", description="School class id")
                                         @PathVariable("id") String id) {
        return classesService.deleteSchoolClass(id);
    }
}
