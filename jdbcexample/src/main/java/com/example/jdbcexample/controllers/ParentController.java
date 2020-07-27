package com.example.jdbcexample.controllers;

import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.ParentDTO;
import com.example.jdbcexample.services.ParentsService;
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
@Api(name = "Parents services", description = "Methods for managing parents", group = "Persons", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class ParentController {

    private final ParentsService parentsService;

    @RequestMapping(value = "/parent/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/parent/all",
            verb = ApiVerb.GET,
            description = "Get all parents.",
            summary =  "Get all parents.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<ParentDTO> getParents() {
        return parentsService.getAllParents();
    }

    @RequestMapping(value = "/parent", method = RequestMethod.GET)
    @ApiMethod(
            path = "/parent",
            verb = ApiVerb.GET,
            description = "Get page of parents.",
            summary =  "Get page of parents.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<ParentDTO> getParentsPage(@ApiQueryParam(name="pagenum", description="Page number") @RequestParam(name = "pagenum", required = true) String pagenum,
                                          @ApiQueryParam(name="pagesize", description="Page size (number of records)")  @RequestParam(name = "pagesize", required = true) String pagesize,
                                          @ApiQueryParam(name="sort", description="Sorting (asc, desc)") @RequestParam(name = "sort", required = true) String sort,
                                          @ApiQueryParam(name="group", description="Grouping by (fieldname)") @RequestParam(name = "group", required = true) String group) {

        return parentsService.getParentsPage(pagenum, pagesize, sort, group);
    }

    @RequestMapping(value = "/parents", method = RequestMethod.GET)
    @ApiMethod(
            path = "/parents",
            verb = ApiVerb.GET,
            description = "Get parent by name and surname.",
            summary =  "Get parent by name and surname.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<ParentDTO> getParentByNameSurname(@ApiQueryParam(name="name", description="Parent's name") @RequestParam(name = "name", required = true) String name,
                                                  @ApiQueryParam(name="surname", description="Parent's surname") @RequestParam(name = "surname", required = true) String surname) {

        return parentsService.getParentsByFirstnameLastname(name, surname);
    }

    @RequestMapping(value = "/parent/{id}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/parent/{id}",
            verb = ApiVerb.GET,
            description = "Get parent by id.",
            summary =  "Get parent by id.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public ParentDTO getParentById(@ApiPathParam(name="id", description="Parent id")
                                   @PathVariable("id") String id) {
        return parentsService.getParentById(id);
    }

    @RequestMapping(value = "/parent", method = RequestMethod.POST)
    @ApiMethod(
            path = "/parent",
            verb = ApiVerb.POST,
            description = "Create new parent.",
            summary =  "Create new parent.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO newParent(@ApiBodyObject @Valid @RequestBody ParentDTO newParent) {

        return parentsService.addNewParent(newParent);
    }

    @RequestMapping(value = "/parent", method = RequestMethod.PUT)
    @ApiMethod(
            path = "/parent",
            verb = ApiVerb.PUT,
            description = "Edit parent data by Id.",
            summary =  "Edit parent data by Id.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO editParent(@ApiBodyObject @Valid @RequestBody ParentDTO parent) {

        return parentsService.editParentData(parent);
    }

    @RequestMapping(value = "/parent/{id}", method = RequestMethod.DELETE)
    @ApiMethod(
            path = "/parent/{id}",
            verb = ApiVerb.DELETE,
            description = "Delete parent by Id.",
            summary =  "Delete parent by Id.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO deleteParent(@ApiPathParam(name="id", description="Parent id")
                                    @PathVariable("id") String id) {

        return parentsService.deleteParent(id);
    }
}
