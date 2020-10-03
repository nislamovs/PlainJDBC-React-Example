package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.statistics.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface StatisticsRepository {

    List<StatsPupilsRelativesDAO> getPupilsRelativesList();

    Map<Integer, Map<String, Integer>> getEmailProvidersList(String personsGroup);

    List<StatsPupilMarkPerClassDAO> getPupilsMarksPerClass(String className, int pageSize, int pageNum);

    List<StatsPupilMarkPerClassDAO> getPupilsMarksPerClass(String className);

    List<StatsPupilAvgMarkDAO> getAvgPupilsMarksByPupilIdInterval(int idStart, int idEnd, int pageSize, int pageNum);

    List<StatsPupilAvgMarkDAO> getAvgPupilsMarksByPupilIdInterval(int idStart, int idEnd);

    List<StatsPupilAvgMarkRatedDAO> getAvgPupilsMarksTop5PerSchool();

    List<StatsPupilAvgMarkRatedDAO> getAvgPupilsMarksAbove7PerSchool(int pageSize, int pageNum);

    List<StatsPupilAvgMarkRatedDAO> getAvgPupilsMarksAbove7PerSchool();

    List<StatsPupilAvgMarkRatedDAO> getAvgPupilsMarksBySubject(String subject);

    List<StatsPupilAvgMarkRatedDAO> getAvgPupilsMarksByClass(String className);

    List<StatsPupilAvgMarkRatedDAO> getAvgPupilsMarksByClass(String className, int pageSize, int pageNum);

    List<StatsKidsParentsDAO> getParentsAndKidsList(int pageSize, int pageNum);

    List<StatsKidsParentsDAO> getParentsAndKidsList();

    List<StatsPupilReviewDAO> getPupilsReview(int pageSize, int pageNum);

    List<StatsPupilReviewDAO> getPupilsReview();

    List<StatsPupilFullReviewDAO> getPupilsFullReview(int pageSize, int pageNum);

    List<StatsPupilFullReviewDAO> getPupilsFullReview();

    List<StatsPupilFullReviewDAO> getPupilsFullReviewByEmail(String email);

}
