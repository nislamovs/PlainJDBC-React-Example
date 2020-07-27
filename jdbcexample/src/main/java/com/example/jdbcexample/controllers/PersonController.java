package com.example.jdbcexample.controllers;


import com.example.jdbcexample.services.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
@Api(name = "Persons services", description = "Methods for managing all persons", group = "Persons", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class PersonController {

    private final PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    @ApiMethod(
            path = "/person",
            verb = ApiVerb.GET,
            description = "Retrieve info about person by email.",
            summary =  "Retrieve info about person by email.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public ResponseEntity<?> findPerson(@ApiQueryParam(name="email", description="Persons email address")
                                        @RequestParam(value = "email", required = true) @NotBlank @Email String email) {
        return ok(personService.findPersonByEmail(email));
    }
}
