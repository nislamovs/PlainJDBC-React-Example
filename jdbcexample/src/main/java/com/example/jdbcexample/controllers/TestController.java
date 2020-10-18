package com.example.jdbcexample.controllers;

import com.example.jdbcexample.domain.dao.ReportTemplateDAO;
import com.example.jdbcexample.domain.dao.statistics.StatsEmailProvidersDAO;
import com.example.jdbcexample.domain.dao.statistics.StatsPupilsRelativesDAO;
import com.example.jdbcexample.mappers.sqlMappers.ResultSetMapper;
import com.example.jdbcexample.repository.ReportTemplateRepository;
import com.example.jdbcexample.repository.StatisticsRepository;
import com.example.jdbcexample.services.ReportTemplateService;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final DataSource dataSource;

    private final Environment environment;

    private final StatisticsRepository statisticsRepository;
    private final ReportTemplateRepository templateRepository;

    @RequestMapping(value = "/attachment", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> attachment() throws JRException, SQLException {

        final String GET_EMAIL_PROVIDERS_LIST_QUERY = "{CALL GET_EMAIL_PROVIDER_STATS(?)}";
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_EMAIL_PROVIDERS_LIST_QUERY);

        stmt.setString(1, "teachers");
        ResultSet rs = stmt.executeQuery();

//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>               " + environment.getRequiredProperty("report") + "    $$$");


        JasperReport jasperReport = JasperCompileManager.compileReport("/home/perkele/Desktop/Projects/PlainJDBC-React-Example/jdbcexample/src/main/resources/reports/email_providers.jrxml");
        JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("PERSON_CATEGORY", "teachers");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, resultSetDataSource);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=report.pdf");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(JasperExportManager.exportReportToPdf(jasperPrint));
    }

    @RequestMapping(value = "/attachment2", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> attachment2() throws JRException, SQLException {

        final String EMAIL_PROVIDERS_TEMPLATE_NAME = "email_providers_report_template.jrxml";
        final String GET_EMAIL_PROVIDERS_LIST_QUERY = "{CALL GET_EMAIL_PROVIDER_STATS(?)}";
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_EMAIL_PROVIDERS_LIST_QUERY);

        stmt.setString(1, "teachers");
        ResultSet rs = stmt.executeQuery();

        JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);

        //Retrieve report template from db
        ReportTemplateDAO templateDAO = templateRepository.getTemplateByName(EMAIL_PROVIDERS_TEMPLATE_NAME);
        byte[] templateByteArray = templateDAO.getTemplate();

        //Compile it
        InputStream reportAsStream = new ByteArrayInputStream(templateByteArray);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportAsStream);


        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("PERSON_CATEGORY", "teachers");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, resultSetDataSource);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=report.pdf");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(JasperExportManager.exportReportToPdf(jasperPrint));
    }


    @RequestMapping(value = "/attachment3", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> attachment3() throws JRException, SQLException {

        final String EMAIL_PROVIDERS_TEMPLATE_NAME = "email_providers_report_template.jrxml";

        //Launch stored proc and get result set
        List<StatsEmailProvidersDAO> emailProviderStats = statisticsRepository.getEmailProvidersList("all");
//        JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(emailProviderStats.get(0).getResultSet());
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(emailProviderStats);


        //Retrieve report template from db
        ReportTemplateDAO templateDAO = templateRepository.getTemplateByName(EMAIL_PROVIDERS_TEMPLATE_NAME);
        byte[] templateByteArray = templateDAO.getTemplate();

        //Compile it
        InputStream reportAsStream = new ByteArrayInputStream(templateByteArray);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportAsStream);

        //Fill it with data
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("PERSON_CATEGORY", "all");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);


        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=report.pdf");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(JasperExportManager.exportReportToPdf(jasperPrint));
    }
}
