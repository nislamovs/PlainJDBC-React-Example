package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dto.statistics.*;
import com.example.jdbcexample.mappers.StatisticsMapper;
import com.example.jdbcexample.repository.ReportTemplateRepository;
import com.example.jdbcexample.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportingService {

    private final StatisticsRepository statisticsRepository;
    private final ReportTemplateRepository templateRepository;

    public Map<Integer, Map<String, Integer>> getEmailProvidersListReport(String personsGroup) {

        //retrieve report template from db
        //launch stored proc
        //compile report
        //return report as byte array
        return statisticsRepository.getEmailProvidersList(personsGroup);
    }


//    public List<StatsPupilsRelativesDTO> getPupilsRelativesListReport() {
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
