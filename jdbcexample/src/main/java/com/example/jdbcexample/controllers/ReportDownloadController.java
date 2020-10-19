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

    @RequestMapping(value = "/report/pupilRelativesReport", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    @ApiMethod(
            path = "/report/pupilRelativesReport",
            verb = ApiVerb.GET,
            description = "Retrieve info about pupils and its relatives.",
            summary =  "Retrieve info about pupils and its relatives.",
            produces = { MediaType.APPLICATION_PDF_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> getPupilRelativesReport() {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=" + "pupilsRelativesReport" + REPORT_FILE_EXTENTION);

        return new ResponseEntity<byte[]>(reportingService.getPupilsRelativesListReport(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/pupilsMarksPerClassReport", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    @ApiMethod(
            path = "/report/pupilsMarksPerClassReport",
            verb = ApiVerb.GET,
            description = "Retrieve info about pupils per form name and its marks.",
            summary =  "Retrieve info about pupils per form name and its marks.",
            produces = { MediaType.APPLICATION_PDF_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> getPupilsMarksPerClassReport(
            @ApiQueryParam(name="formName", description="Form name")
            @RequestParam(name = "formName", required = true) String formName) {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=" + "pupilsMarksPerClassReport_" + formName + REPORT_FILE_EXTENTION);

        return new ResponseEntity<byte[]>(reportingService.getPupilsMarksPerClassListReport(formName), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/pupilsAvgMarksPerPupilIdIntervalReport", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    @ApiMethod(
            path = "/report/pupilsAvgMarksPerPupilIdIntervalReport",
            verb = ApiVerb.GET,
            description = "Retrieve info about pupils and its avg marks by id interval.",
            summary =  "Retrieve info about pupils and its avg marks by id interval.",
            produces = { MediaType.APPLICATION_PDF_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> getPupilsMarksPerClassReport(
            @ApiQueryParam(name="idStart", description="Pupil id start interval")
            @RequestParam(name = "idStart", required = true) int idStart,

            @ApiQueryParam(name="idEnd", description="Pupil id end interval")
            @RequestParam(name = "idEnd", required = true) int idEnd ) {

        HttpHeaders responseHeaders = new HttpHeaders();
        String contentDispositionHeader = "attachment; filename=" + "pupilAvgMarksPerIdIntervalReport_" + idStart + "_" + idEnd + REPORT_FILE_EXTENTION;
        responseHeaders.set("Content-Disposition", contentDispositionHeader);

        return new ResponseEntity<byte[]>(reportingService.getAvgPupilsMarksByPupilIdIntervalReport(idStart, idEnd), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/pupilsAvgMarksTop5Report", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    @ApiMethod(
            path = "/report/pupilsAvgMarksTop5Report",
            verb = ApiVerb.GET,
            description = "Retrieve info about top 5 pupils and its avg marks.",
            summary =  "Retrieve info about top 5 pupils and its avg marks.",
            produces = { MediaType.APPLICATION_PDF_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> getAvgPupilsMarksTop5Report() {

        HttpHeaders responseHeaders = new HttpHeaders();
        String contentDispositionHeader = "attachment; filename=" + "pupilAvgMarksTop5Report_" + REPORT_FILE_EXTENTION;
        responseHeaders.set("Content-Disposition", contentDispositionHeader);

        return new ResponseEntity<byte[]>(reportingService.getAvgPupilsMarksTop5PerSchoolReport(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/pupilsAvgMarksAbove7PerSchoolReport", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    @ApiMethod(
            path = "/report/pupilsAvgMarksAbove7PerSchoolReport",
            verb = ApiVerb.GET,
            description = "Retrieve info about pupils with avg marks level above 7.",
            summary =  "Retrieve info about pupils with avg marks level above 7.",
            produces = { MediaType.APPLICATION_PDF_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> pupilsAvgMarksAbove7PerSchoolReport() {

        HttpHeaders responseHeaders = new HttpHeaders();
        String contentDispositionHeader = "attachment; filename=" + "pupilAvgMarksAbove7Report_" + REPORT_FILE_EXTENTION;
        responseHeaders.set("Content-Disposition", contentDispositionHeader);

        return new ResponseEntity<byte[]>(reportingService.getAvgPupilsMarksAbove7PerSchoolReport(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/pupilsAvgMarksPerSubjectReport", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    @ApiMethod(
            path = "/report/pupilsAvgMarksPerSubjectReport",
            verb = ApiVerb.GET,
            description = "Retrieve info about pupils marks by subject.",
            summary =  "Retrieve info about pupils marks by subject.",
            produces = { MediaType.APPLICATION_PDF_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> pupilsAvgMarksBySubjectReport(
            @ApiQueryParam(name="subject", description="Subject name")
            @RequestParam(name = "subject", required = true) String subject ) {

        HttpHeaders responseHeaders = new HttpHeaders();
        String contentDispositionHeader = "attachment; filename=" + "pupilsAvgMarksBySubjectReport_" + subject + REPORT_FILE_EXTENTION;
        responseHeaders.set("Content-Disposition", contentDispositionHeader);

        return new ResponseEntity<byte[]>(reportingService.getAvgPupilsMarksBySubjectReport(subject), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/pupilsAvgMarksByClassReport", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    @ApiMethod(
            path = "/report/pupilsAvgMarksByClassReport",
            verb = ApiVerb.GET,
            description = "Retrieve info about pupils avg marks by form.",
            summary =  "Retrieve info about pupils avg marks by form.",
            produces = { MediaType.APPLICATION_PDF_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> pupilsAvgMarksByClassReport(
            @ApiQueryParam(name="class", description="Form name")
            @RequestParam(name = "class", required = true) String className ) {

        HttpHeaders responseHeaders = new HttpHeaders();
        String contentDispositionHeader = "attachment; filename=" + "pupilsAvgMarksByFormReport_" + className + REPORT_FILE_EXTENTION;
        responseHeaders.set("Content-Disposition", contentDispositionHeader);

        return new ResponseEntity<byte[]>(reportingService.getAvgPupilsMarksByClassReport(className), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/pupilsReviewReport", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    @ApiMethod(
            path = "/report/pupilsReviewReport",
            verb = ApiVerb.GET,
            description = "Retrieve info about pupils.",
            summary =  "Retrieve info about pupils.",
            produces = { MediaType.APPLICATION_PDF_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> pupilsReviewReport() {

        HttpHeaders responseHeaders = new HttpHeaders();
        String contentDispositionHeader = "attachment; filename=" + "pupilsReviewReport" + REPORT_FILE_EXTENTION;
        responseHeaders.set("Content-Disposition", contentDispositionHeader);

        return new ResponseEntity<byte[]>(reportingService.getPupilsReviewReport(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/pupilsFullReviewReport", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    @ApiMethod(
            path = "/report/pupilsFullReviewReport",
            verb = ApiVerb.GET,
            description = "Retrieve full review about pupils.",
            summary =  "Retrieve full review about pupils.",
            produces = { MediaType.APPLICATION_PDF_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> pupilsFullReviewReport() {

        HttpHeaders responseHeaders = new HttpHeaders();
        String contentDispositionHeader = "attachment; filename=" + "pupilsFullReviewReport" + REPORT_FILE_EXTENTION;
        responseHeaders.set("Content-Disposition", contentDispositionHeader);

        return new ResponseEntity<byte[]>(reportingService.getPupilsFullReviewReport(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/pupilsFullReviewByEmailReport", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    @ApiMethod(
            path = "/report/pupilsFullReviewByEmailReport",
            verb = ApiVerb.GET,
            description = "Retrieve full review about pupils by email (e.g. acurmiw@yahoo.com).",
            summary =  "Retrieve full review about pupils by email.",
            produces = { MediaType.APPLICATION_PDF_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> pupilsFullReviewByEmailReport(
            @ApiQueryParam(name="email", description="Pupil's email")
            @RequestParam(name = "email", required = true) String email ) {

        HttpHeaders responseHeaders = new HttpHeaders();
        String contentDispositionHeader = "attachment; filename=" + "pupilsFullReviewByEmailReport_" + email + REPORT_FILE_EXTENTION;
        responseHeaders.set("Content-Disposition", contentDispositionHeader);

        return new ResponseEntity<byte[]>(reportingService.getPupilsFullReviewByEmailReport(email), responseHeaders, HttpStatus.OK);
    }
}
