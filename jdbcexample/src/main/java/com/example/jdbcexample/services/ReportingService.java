package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dao.ReportTemplateDAO;
import com.example.jdbcexample.domain.dao.statistics.StatsEmailProvidersDAO;
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
    private final String PUPILS_AVG_MARKS_PER_FORM_TEMPLATE_NAME = "pupils_marks_per_class_report_template.jrxml";
    private final String PUPILS_RELATIVES_TEMPLATE_NAME = "pupils_relatives_report_template.jrxml";

    private final StatisticsRepository statisticsRepository;
    private final ReportTemplateRepository templateRepository;

    @SneakyThrows
    public byte[] getEmailProvidersListReport(String personsGroup) {

        //Launch stored proc and get result set
        List<StatsEmailProvidersDAO> emailProviderStats = statisticsRepository.getEmailProvidersList(personsGroup);
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(emailProviderStats);

        //Retrieve report template from db
        ReportTemplateDAO templateDAO = templateRepository.getTemplateByName(EMAIL_PROVIDERS_TEMPLATE_NAME);
        byte[] templateByteArray = templateDAO.getTemplate();

        //Compile it
        InputStream reportAsStream = new ByteArrayInputStream(templateByteArray);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportAsStream);

        //Fill it with data
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("PERSON_CATEGORY", personsGroup);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);

        //Export report [pdf file] as byte array
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }






















//    public byte[] getPupilsRelativesListReport() {
//        return statisticsRepository.getPupilsRelativesList().stream().map(mapper::toDTO).collect(Collectors.toList());
//    }
//
//    public List<StatsPupilMarkPerClassDTO> getPupilsMarksPerClassReport(String className) {
//        return statisticsRepository.getPupilsMarksPerClass(className).stream().map(mapper::toDTO).collect(Collectors.toList());
//    }
//
//    public List<StatsPupilAvgMarkDTO> getAvgPupilsMarksByPupilIdIntervalReport(int idStart, int idEnd) {
//        return statisticsRepository.getAvgPupilsMarksByPupilIdInterval(idStart, idEnd).stream()
//                .map(mapper::toDTO).collect(Collectors.toList());
//    }
//
//    public List<StatsPupilAvgMarkRatedDTO> getAvgPupilsMarksTop5PerSchoolReport() {
//        return statisticsRepository.getAvgPupilsMarksTop5PerSchool().stream()
//                .map(mapper::toDTO).collect(Collectors.toList());
//    }
//
//    public List<StatsPupilAvgMarkRatedDTO> getAvgPupilsMarksAbove7PerSchoolReport() {
//        return statisticsRepository.getAvgPupilsMarksAbove7PerSchool().stream()
//                .map(mapper::toDTO).collect(Collectors.toList());
//    }
//
//    public List<StatsPupilAvgMarkRatedDTO> getAvgPupilsMarksBySubjectReport(String subject) {
//        return statisticsRepository.getAvgPupilsMarksBySubject(subject).stream()
//                .map(mapper::toDTO).collect(Collectors.toList());
//    }
//
//    public List<StatsPupilAvgMarkRatedDTO> getAvgPupilsMarksByClassReport(String className) {
//        return statisticsRepository.getAvgPupilsMarksByClass(className).stream()
//                .map(mapper::toDTO).collect(Collectors.toList());
//    }
//
//    public List<StatsKidsParentsDTO> getParentsAndKidsListReport() {
//        return statisticsRepository.getParentsAndKidsList().stream()
//                .map(mapper::toDTO).collect(Collectors.toList());
//    }
//
//    public List<StatsPupilReviewDTO> getPupilsReviewReport() {
//        return statisticsRepository.getPupilsReview().stream()
//                .map(mapper::toDTO).collect(Collectors.toList());
//    }
//
//    public List<StatsPupilFullReviewDTO> getPupilsFullReviewReport() {
//        return statisticsRepository.getPupilsFullReview().stream()
//                .map(mapper::toDTO).collect(Collectors.toList());
//    }
//
//    public List<StatsPupilFullReviewDTO> getPupilsFullReviewByEmailReport(String email) {
//        return statisticsRepository.getPupilsFullReviewByEmail(email).stream()
//                .map(mapper::toDTO).collect(Collectors.toList());
//    }
}
