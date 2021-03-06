package com.example.jdbcexample.controllers;

import com.example.jdbcexample.domain.dao.ReportTemplateDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.PupilDTO;
import com.example.jdbcexample.domain.dto.ReportTemplateDTO;
import com.example.jdbcexample.services.PupilsService;
import com.example.jdbcexample.services.ReportTemplateService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
@Api(name = "Report template services", description = "Methods for managing report templates", group = "Reports", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class ReportTemplateController {

    private final ReportTemplateService reportTemplateService;

    @RequestMapping(value = "/report/template/all", method = RequestMethod.GET)
    @ApiMethod(
            path = "/report/template/all",
            verb = ApiVerb.GET,
            description = "Retrieve info about all report templates.",
            summary =  "Retrieve info about all report templates.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public List<ReportTemplateDTO> getTemplatesInfo() {
        return reportTemplateService.getAllTemplates();
    }

    @RequestMapping(value = "/report/template/{id}", method = RequestMethod.GET)
    @ApiMethod(
            path = "/report/template/{id}}",
            verb = ApiVerb.GET,
            description = "Download template by id.",
            summary =  "Download template by id.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> getTemplateById(
            @ApiPathParam(name="id", description="Report template id")
            @PathVariable("id") String id) {

        ReportTemplateDTO template = reportTemplateService.getTemplateById(id);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=" + template.getTemplateName());

        return new ResponseEntity<byte[]>(template.getTemplate(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/template", method = RequestMethod.GET)
    @ApiMethod(
            path = "/report/template}",
            verb = ApiVerb.GET,
            description = "Download template by name.",
            summary =  "Download template by name.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    public @ApiResponseObject ResponseEntity<byte[]> getTemplateByName(
            @ApiQueryParam(name="name", description="Report template's name")
            @RequestParam(name = "name", required = true) String templateName) {

        ReportTemplateDTO template = reportTemplateService.getTemplateByName(templateName);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=" + template.getTemplateName());

        return new ResponseEntity<byte[]>(template.getTemplate(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/template", method = RequestMethod.POST)
    @ApiMethod(
            path = "/report/template",
            verb = ApiVerb.POST,
            description = "Upload new report template.",
            summary =  "Upload new report template.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO uploadNewTemplate(@ApiBodyObject @Valid @RequestBody ReportTemplateDTO template) {
        return reportTemplateService.addNewTemplate(template);
    }

    @RequestMapping(value = "/report/template", method = RequestMethod.PUT)
    @ApiMethod(
            path = "/report/template",
            verb = ApiVerb.PUT,
            description = "Upload new report template.",
            summary =  "Upload new report template.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO editNewTemplate(@ApiBodyObject @Valid @RequestBody ReportTemplateDTO template) {
        return reportTemplateService.editReportTemplateData(template);
    }


    @RequestMapping(value = "/report/template/{id}", method = RequestMethod.DELETE)
    @ApiMethod(
            path = "/report/template/{id}",
            verb = ApiVerb.DELETE,
            description = "Delete report template.",
            summary =  "Delete report template.",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            visibility = ApiVisibility.PUBLIC,
            stage = ApiStage.GA,
            responsestatuscode = "200 - OK"
    )
    @ApiResponseObject
    public AbstractDTO deleteTemplate(@ApiPathParam(name="id", description="Report template id")
                                      @PathVariable("id") String templateId) {
        return reportTemplateService.deleteReportTemplate(templateId);
    }
}
