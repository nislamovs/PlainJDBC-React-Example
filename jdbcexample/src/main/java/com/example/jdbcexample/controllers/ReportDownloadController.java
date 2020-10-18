package com.example.jdbcexample.controllers;


import com.example.jdbcexample.domain.dto.ReportTemplateDTO;
import com.example.jdbcexample.services.ReportingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
@Api(name = "Report services", description = "Methods for managing statistic reports", group = "Reports", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class ReportDownloadController {

    private final String REPORT_FILE_EXTENTION = ".pdf";

    private final ReportingService reportingService;

    @RequestMapping(value = "/report/emailProviderReport", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    @ApiMethod(
            path = "/report/emailProviderReport",
            verb = ApiVerb.GET,
            description = "Retrieve info about email provider usage among particular person group.",
            summary =  "Retrieve info about email provider usage among particular person group.",
            produces = { MediaType.APPLICATION_PDF_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> getEmailProviderReport(
            @ApiQueryParam(name="personGroup", description="Person group")
            @RequestParam(name = "personGroup", required = true) String personGroup) {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=" + "emailProviderReport_" + personGroup + REPORT_FILE_EXTENTION);

        return new ResponseEntity<byte[]>(reportingService.getEmailProvidersListReport(personGroup), responseHeaders, HttpStatus.OK);
    }
}
