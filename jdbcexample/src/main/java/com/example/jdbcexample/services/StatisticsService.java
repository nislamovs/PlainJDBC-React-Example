package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dto.statistics.*;
import com.example.jdbcexample.mappers.StatisticsMapper;
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
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final StatisticsMapper mapper;

    public List<StatsPupilsRelativesDTO> getPupilsRelativesList() {
        return statisticsRepository.getPupilsRelativesList().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public Map<Integer, Map<String, Integer>> getEmailProvidersList(String personsGroup) {
        return statisticsRepository.getEmailProvidersList(personsGroup);
    }


    public List<StatsPupilMarkPerClassDTO> getPupilsMarksPerClass(String className, int pageSize, int pageNum) {
        return statisticsRepository.getPupilsMarksPerClass(className, pageSize, pageNum).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilMarkPerClassDTO> getPupilsMarksPerClass(String className) {
        return statisticsRepository.getPupilsMarksPerClass(className).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilAvgMarkDTO> getAvgPupilsMarksByPupilIdInterval(int idStart, int idEnd, int pageSize, int pageNum) {
        return statisticsRepository.getAvgPupilsMarksByPupilIdInterval(idStart, idEnd, pageSize, pageNum).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilAvgMarkDTO> getAvgPupilsMarksByPupilIdInterval(int idStart, int idEnd) {
        return statisticsRepository.getAvgPupilsMarksByPupilIdInterval(idStart, idEnd).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilAvgMarkRatedDTO> getAvgPupilsMarksTop5PerSchool() {
        return statisticsRepository.getAvgPupilsMarksTop5PerSchool().stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilAvgMarkRatedDTO> getAvgPupilsMarksAbove7PerSchool(int pageSize, int pageNum) {
        return statisticsRepository.getAvgPupilsMarksAbove7PerSchool(pageSize, pageNum).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilAvgMarkRatedDTO> getAvgPupilsMarksAbove7PerSchool() {
        return statisticsRepository.getAvgPupilsMarksAbove7PerSchool().stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilAvgMarkRatedDTO> getAvgPupilsMarksBySubject(String subject) {
        return statisticsRepository.getAvgPupilsMarksBySubject(subject).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilAvgMarkRatedDTO> getAvgPupilsMarksByClass(String className) {
        return statisticsRepository.getAvgPupilsMarksByClass(className).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilAvgMarkRatedDTO> getAvgPupilsMarksByClass(String subject, int pageSize, int pageNum) {
        return statisticsRepository.getAvgPupilsMarksByClass(subject, pageSize, pageNum).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsKidsParentsDTO> getParentsAndKidsList(int pageSize, int pageNum) {
        return statisticsRepository.getParentsAndKidsList(pageSize, pageNum).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsKidsParentsDTO> getParentsAndKidsList() {
        return statisticsRepository.getParentsAndKidsList().stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilReviewDTO> getPupilsReview(int pageSize, int pageNum) {
        return statisticsRepository.getPupilsReview(pageSize, pageNum).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilReviewDTO> getPupilsReview() {
        return statisticsRepository.getPupilsReview().stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilFullReviewDTO> getPupilsFullReview(int pageSize, int pageNum) {
        return statisticsRepository.getPupilsFullReview(pageSize, pageNum).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilFullReviewDTO> getPupilsFullReview() {
        return statisticsRepository.getPupilsFullReview().stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StatsPupilFullReviewDTO> getPupilsFullReviewByEmail(String email) {
        return statisticsRepository.getPupilsFullReviewByEmail(email).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }
}
