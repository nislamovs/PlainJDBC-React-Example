package com.example.jdbcexample.controllers;

import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.PupilDTO;
import com.example.jdbcexample.services.PupilsService;
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
@Api(name = "Pupils services", description = "Methods for managing pupils", group = "Persons", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class PupilController {

    private final PupilsService pupilsService;

    @RequestMapping(value = "/pupil/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/pupil/all",
            verb = ApiVerb.GET,
            description = "Retrieve info about all pupils.",
            summary =  "Retrieve info about all pupils.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<PupilDTO> getPupils() {
        return pupilsService.getAllPupils();
    }

    @RequestMapping(value = "/pupil", method = RequestMethod.GET)
    @ApiMethod(
            path = "/pupil",
            verb = ApiVerb.GET,
            description = "Get page of pupils.",
            summary =  "Get page of pupils.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<PupilDTO> getPupilsPage(@ApiQueryParam(name="pagenum", description="Page number") @RequestParam(name = "pagenum", required = true) String pagenum,
                                        @ApiQueryParam(name="pagesize", description="Page size (number of records)")  @RequestParam(name = "pagesize", required = true) String pagesize,
                                        @ApiQueryParam(name="sort", description="Sorting (asc, desc)") @RequestParam(name = "sort", required = true) String sort,
                                        @ApiQueryParam(name="group", description="Grouping by (fieldname)") @RequestParam(name = "group", required = true) String group) {

        return pupilsService.getPupilsPage(pagenum, pagesize, sort, group);
    }

    @RequestMapping(value = "/pupils", method = RequestMethod.GET)
    @ApiMethod(
            path = "/pupils",
            verb = ApiVerb.GET,
            description = "Retrieve info about pupil by name and surname.",
            summary =  "Retrieve info about pupil by name and surname.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<PupilDTO> getPupilByNameSurname(@ApiQueryParam(name="name", description="Pupil's name") @RequestParam(name = "name", required = true) String name,
                                                @ApiQueryParam(name="surname", description="Pupil's surname") @RequestParam(name = "surname", required = true) String surname) {

        return pupilsService.getPupilsByFirstnameLastname(name, surname);
    }

    @RequestMapping(value = "/pupil/{id}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/pupil/{id}",
            verb = ApiVerb.GET,
            description = "Retrieve info about pupil by id.",
            summary =  "Retrieve info about pupil by id.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public PupilDTO getPupilById(@ApiPathParam(name="id", description="Pupil id")
                                 @PathVariable("id") String id) {
        return pupilsService.getPupilById(id);
    }

    @RequestMapping(value = "/pupil", method = RequestMethod.POST)
    @ApiMethod(
            path = "/pupil",
            verb = ApiVerb.POST,
            description = "Create new pupil.",
            summary =  "Create new pupil.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO newPupil(@ApiBodyObject @Valid @RequestBody PupilDTO newPupil) {

        return pupilsService.addNewPupil(newPupil);
    }

    @RequestMapping(value = "/pupil", method = RequestMethod.PUT)
    @ApiMethod(
            path = "/pupil",
            verb = ApiVerb.PUT,
            description = "Edit pupil's data.",
            summary =  "Edit pupil's data.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO editPupil(@ApiBodyObject @Valid @RequestBody PupilDTO pupil) {

        return pupilsService.editPupilData(pupil);
    }

    @RequestMapping(value = "/pupil/{id}", method = RequestMethod.DELETE)
    @ApiMethod(
            path = "/person/{id}",
            verb = ApiVerb.DELETE,
            description = "Delete pupil by Id.",
            summary =  "Delete pupil by Id.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO deletePupil(@ApiPathParam(name="id", description="Pupil id")
                                   @PathVariable("id") String id) {

        return pupilsService.deletePupil(id);
    }
}
