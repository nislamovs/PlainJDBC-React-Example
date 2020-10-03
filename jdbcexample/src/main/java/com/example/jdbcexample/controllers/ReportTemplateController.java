package com.example.jdbcexample.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Slf4j
@Api(name = "Report template services", description = "Methods for managing report templates", group = "Reports", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
@ApiVersion(since = "1.0", until = "1.0")
@ApiAuthNone
public class ReportTemplateController {

    // get list of templates
    // get template by id (download)
    // get template by name (download)
    // post new template
    // update existing template
    // delete template by id


}
