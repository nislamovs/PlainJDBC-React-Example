package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dao.ReportTemplateDAO;
import com.example.jdbcexample.domain.dao.statistics.*;
import com.example.jdbcexample.domain.dto.statistics.*;
import com.example.jdbcexample.mappers.StatisticsMapper;
import com.example.jdbcexample.mappers.sqlMappers.ResultSetMapper;
import com.example.jdbcexample.repository.ReportTemplateRepository;
import com.example.jdbcexample.repository.StatisticsRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportingService {

    private final String EMAIL_PROVIDERS_TEMPLATE_NAME = "email_providers_report_template.jrxml";
    private final String PUPILS_AVG_MARKS_ABOVE_7_TEMPLATE_NAME = "pupils_avg_marks_above_7_report_template.jrxml";
    private final String PUPILS_AVG_MARKS_BY_FORM_TEMPLATE_NAME = "pupils_avg_marks_by_class_report_template.jrxml";
    private final String PUPILS_AVG_MARKS_FULL_REVIEW_TEMPLATE_NAME = "pupils_avg_marks_full_review_report_template.jrxml";
    private final String PUPILS_AVG_MARKS_FULL_REVIEW_BY_EMAIL_TEMPLATE_NAME = "pupils_avg_marks_full_review_by_email_report_template.jrxml";
    private final String PUPILS_AVG_MARKS_PER_ID_INTERVAL_TEMPLATE_NAME = "pupils_avg_marks_per_id_interval_report_template.jrxml";
    private final String PUPILS_AVG_MARKS_PER_SUBJECT_TOP_5_TEMPLATE_NAME = "pupils_avg_marks_per_subject_top_5_report_template.jrxml";
    private final String PUPILS_AVG_MARKS_REVIEW_TEMPLATE_NAME = "pupils_avg_marks_review_report_template.jrxml";
    private final String PUPILS_AVG_MARKS_TOP_5_TEMPLATE_NAME = "pupils_avg_marks_top_5_report_template.jrxml";
    private final String PUPILS_MARKS_PER_FORM_TEMPLATE_NAME = "pupils_marks_per_class_report_template.jrxml";
    private final String PUPILS_RELATIVES_TEMPLATE_NAME = "pupils_relatives_report_template.jrxml";

    private final StatisticsRepository statisticsRepository;
    private final ReportTemplateRepository templateRepository;

    @SneakyThrows
    public byte[] getEmailProvidersListReport(String personsGroup) {

        //Launch stored proc and get result set
        List<StatsEmailProvidersDAO> emailProviderStats = statisticsRepository.getEmailProvidersList(personsGroup);
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(emailProviderStats);

        //Retrieve report template from db and compile it
        JasperReport jasperReport = precompileJasperReport(EMAIL_PROVIDERS_TEMPLATE_NAME);

        //Fill it with data
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("PERSON_CATEGORY", personsGroup);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);

        //Export report [pdf file] as byte array
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @SneakyThrows
    public byte[] getPupilsRelativesListReport() {

        List<StatsPupilsRelativesDAO> pupilRelativesStats = statisticsRepository.getPupilsRelativesList();
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pupilRelativesStats);

        JasperReport jasperReport = precompileJasperReport(PUPILS_RELATIVES_TEMPLATE_NAME);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), beanCollectionDataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @SneakyThrows
    public byte[] getPupilsMarksPerClassListReport(String formName) {

        List<StatsPupilMarkPerClassDAO> pupilMarksStats = statisticsRepository.getPupilsMarksPerClass(formName);
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pupilMarksStats);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CLASS_NAME", formName);

        JasperReport jasperReport = precompileJasperReport(PUPILS_MARKS_PER_FORM_TEMPLATE_NAME);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @SneakyThrows
    public byte[] getAvgPupilsMarksByPupilIdIntervalReport(int idStart, int idEnd) {

        List<StatsPupilAvgMarkDAO> pupilAvgMarks = statisticsRepository.getAvgPupilsMarksByPupilIdInterval(idStart, idEnd);
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pupilAvgMarks);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("PUPIL_ID_INTERVAL_START", idStart);
        parameters.put("PUPIL_ID_INTERVAL_END", idEnd);

        JasperReport jasperReport = precompileJasperReport(PUPILS_AVG_MARKS_PER_ID_INTERVAL_TEMPLATE_NAME);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @SneakyThrows
    public byte[] getAvgPupilsMarksTop5PerSchoolReport() {

        List<StatsPupilAvgMarkRatedDAO> pupilAvgMarks = statisticsRepository.getAvgPupilsMarksTop5PerSchool();
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pupilAvgMarks);

        JasperReport jasperReport = precompileJasperReport(PUPILS_AVG_MARKS_TOP_5_TEMPLATE_NAME);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), beanCollectionDataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @SneakyThrows
    public byte[] getAvgPupilsMarksAbove7PerSchoolReport() {

        List<StatsPupilAvgMarkRatedDAO> pupilAvgMarks = statisticsRepository.getAvgPupilsMarksAbove7PerSchool();
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pupilAvgMarks);

        JasperReport jasperReport = precompileJasperReport(PUPILS_AVG_MARKS_ABOVE_7_TEMPLATE_NAME);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), beanCollectionDataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @SneakyThrows
    public byte[] getAvgPupilsMarksBySubjectReport(String subject) {

        List<StatsPupilAvgMarkRatedDAO> pupilAvgMarks = statisticsRepository.getAvgPupilsMarksBySubject(subject);
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pupilAvgMarks);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("SUBJECT_NAME", subject);

        JasperReport jasperReport = precompileJasperReport(PUPILS_AVG_MARKS_PER_SUBJECT_TOP_5_TEMPLATE_NAME);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @SneakyThrows
    public byte[] getAvgPupilsMarksByClassReport(String className) {

        List<StatsPupilAvgMarkRatedDAO> pupilAvgMarks = statisticsRepository.getAvgPupilsMarksByClass(className);
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pupilAvgMarks);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CLASS_NAME", className);

        JasperReport jasperReport = precompileJasperReport(PUPILS_AVG_MARKS_BY_FORM_TEMPLATE_NAME);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @SneakyThrows
    public byte[] getPupilsReviewReport() {

        List<StatsPupilReviewDAO> pupilReview = statisticsRepository.getPupilsReview();
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pupilReview);

        JasperReport jasperReport = precompileJasperReport(PUPILS_AVG_MARKS_REVIEW_TEMPLATE_NAME);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), beanCollectionDataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @SneakyThrows
    public byte[] getPupilsFullReviewReport() {

        List<StatsPupilFullReviewDAO> pupilReview = statisticsRepository.getPupilsFullReview();
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pupilReview);

        JasperReport jasperReport = precompileJasperReport(PUPILS_AVG_MARKS_FULL_REVIEW_TEMPLATE_NAME);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), beanCollectionDataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @SneakyThrows
    public byte[] getPupilsFullReviewByEmailReport(String email) {

        List<StatsPupilFullReviewDAO> pupilReview = statisticsRepository.getPupilsFullReviewByEmail(email);
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pupilReview);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("PUPIL_EMAIL", email);

        JasperReport jasperReport = precompileJasperReport(PUPILS_AVG_MARKS_FULL_REVIEW_BY_EMAIL_TEMPLATE_NAME);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private JasperReport precompileJasperReport(String templateName) throws JRException {
        //Retrieve report template from db
        ReportTemplateDAO templateDAO = templateRepository.getTemplateByName(templateName);
        byte[] templateByteArray = templateDAO.getTemplate();

        //Compile it
        InputStream reportAsStream = new ByteArrayInputStream(templateByteArray);
        return JasperCompileManager.compileReport(reportAsStream);
    }
}
