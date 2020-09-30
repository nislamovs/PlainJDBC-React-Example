package com.example.jdbcexample.repository.impl;

import com.example.jdbcexample.domain.dao.statistics.*;
import com.example.jdbcexample.repository.StatisticsRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@RequiredArgsConstructor
@Slf4j
@Component
public class StatisticsRepositoryImpl implements StatisticsRepository {

    private final DataSource dataSource;

    private final String GET_PUPILS_RELATIVES_LIST_QUERY = "{CALL GET_PUPILS_RELATIVES()}";
    private final String GET_EMAIL_PROVIDERS_LIST_QUERY = "{CALL GET_EMAIL_PROVIDER_STATS(?)}";

    private final String GET_PUPILS_MARKS_PER_CLASS_PAGE_QUERY = "{CALL GET_PUPILS_MARKS_PER_CLASS_PAGE(?, ?, ?)}";
    private final String GET_PUPILS_MARKS_PER_CLASS_QUERY = "{CALL GET_PUPILS_MARKS_PER_CLASS(?)}";
    private final String GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL_PAGE_QUERY = "{CALL GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL_PAGE(?, ?, ?, ?)}";
    private final String GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL_QUERY = "{CALL GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL(?, ?)}";

    private final String GET_AVG_PUPILS_MARKS_ALL_SCHOOL_TOP5_QUERY = "{CALL GET_AVG_PUPILS_MARKS_ALL_SCHOOL_TOP5()}";
    private final String GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7_PAGE_QUERY = "{CALL GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7_PAGE(?, ?)}";
    private final String GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7_QUERY = "{CALL GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7()}";
    private final String GET_AVG_PUPILS_MARKS_ALL_SCHOOL_PER_SUBJECT_TOP5_QUERY = "{CALL GET_AVG_PUPILS_MARKS_ALL_SCHOOL_PER_SUBJECT_TOP5(?)}";

    private final String GET_AVG_PUPILS_MARKS_BY_CLASS_QUERY = "{CALL GET_AVG_PUPILS_MARKS_BY_CLASS(?)}";
    private final String GET_AVG_PUPILS_MARKS_BY_CLASS_PAGE_QUERY = "{CALL GET_AVG_PUPILS_MARKS_BY_CLASS_PAGE(?, ?, ?)}";
    private final String GET_PARENTS_AND_KIDS_LIST_PAGE_QUERY = "{CALL GET_PARENTS_AND_KIDS_LIST_PAGE(?, ?)}";
    private final String GET_PARENTS_AND_KIDS_LIST_QUERY = "{CALL GET_PARENTS_AND_KIDS_LIST()}";

    private final String GET_PUPILS_REVIEW_PAGE_QUERY = "{CALL GET_PUPILS_REVIEW_PAGE(?, ?)}";
    private final String GET_PUPILS_REVIEW_QUERY = "{CALL GET_PUPILS_REVIEW()}";
    private final String GET_PUPILS_FULL_REVIEW_PAGE_QUERY = "{CALL GET_PUPILS_FULL_REVIEW_PAGE(?, ?)}";
    private final String GET_PUPILS_FULL_REVIEW_QUERY = "{CALL GET_PUPILS_FULL_REVIEW()}";
    private final String GET_PUPILS_FULL_REVIEW_BY_EMAIL_QUERY = "{CALL GET_PUPILS_FULL_REVIEW_BY_EMAIL(?)}";


    @Override
    @SneakyThrows
    public List<StatsPupilsRelativesDAO> getPupilsRelativesList() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_RELATIVES_LIST_QUERY);

        int i = 1;
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public Map<Integer, Map<String, Integer>> getEmailProvidersList(String personsGroup) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_EMAIL_PROVIDERS_LIST_QUERY);

        int i = 1;
        stmt.setString(i++, personsGroup);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new HashMap<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilMarkPerClassDAO>  getPupilsMarksPerClass(String className, int pageSize, int pageNum) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_MARKS_PER_CLASS_PAGE_QUERY);

        int i = 1;
        stmt.setString(i++, className);
        stmt.setInt(i++, pageSize);
        stmt.setInt(i++, pageNum);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilMarkPerClassDAO>  getPupilsMarksPerClass(String className) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_MARKS_PER_CLASS_QUERY);

        int i = 1;
        stmt.setString(i++, className);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkDAO>  getAvgPupilsMarksByPupilIdInterval(int idStart, int idEnd, int pageSize, int pageNum) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL_PAGE_QUERY);

        int i = 1;
        stmt.setInt(i++, idStart);
        stmt.setInt(i++, idEnd);
        stmt.setInt(i++, pageSize);
        stmt.setInt(i++, pageNum);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkDAO>  getAvgPupilsMarksByPupilIdInterval(int idStart, int idEnd) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL_QUERY);

        int i = 1;
        stmt.setInt(i++, idStart);
        stmt.setInt(i++, idEnd);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkRatedDAO>  getAvgPupilsMarksTop5PerSchool() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_ALL_SCHOOL_TOP5_QUERY);

        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkRatedDAO>  getAvgPupilsMarksAbove7PerSchool(int pageSize, int pageNum) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7_PAGE_QUERY);

        int i = 1;
        stmt.setInt(i++, pageSize);
        stmt.setInt(i++, pageNum);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkRatedDAO>  getAvgPupilsMarksAbove7PerSchool() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7_QUERY);

        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkRatedDAO>  getAvgPupilsMarksBySubject(String subject) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_ALL_SCHOOL_PER_SUBJECT_TOP5_QUERY);

        int i = 1;
        stmt.setString(i++, subject);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkRatedDAO>  getAvgPupilsMarksByClass(String className) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_BY_CLASS_QUERY);

        int i = 1;
        stmt.setString(i++, className);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkRatedDAO>  getAvgPupilsMarksByClass(String className, int pageSize, int pageNum) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_BY_CLASS_PAGE_QUERY);

        int i = 1;
        stmt.setString(i++, className);
        stmt.setInt(i++, pageSize);
        stmt.setInt(i++, pageNum);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsKidsParentsDAO>  getParentsAndKidsList(int pageSize, int pageNum) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PARENTS_AND_KIDS_LIST_PAGE_QUERY);

        int i = 1;
        stmt.setInt(i++, pageSize);
        stmt.setInt(i++, pageNum);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsKidsParentsDAO>  getParentsAndKidsList() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PARENTS_AND_KIDS_LIST_QUERY);

        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilReviewDAO>  getPupilsReview(int pageSize, int pageNum) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_REVIEW_PAGE_QUERY);

        int i = 1;
        stmt.setInt(i++, pageSize);
        stmt.setInt(i++, pageNum);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilReviewDAO>  getPupilsReview() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_REVIEW_QUERY);

        int i = 1;
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilFullReviewDAO>  getPupilsFullReview(int pageSize, int pageNum) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_FULL_REVIEW_PAGE_QUERY);

        int i = 1;
        stmt.setInt(i++, pageSize);
        stmt.setInt(i++, pageNum);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilFullReviewDAO>  getPupilsFullReview() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_FULL_REVIEW_QUERY);

        int i = 1;
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public List<StatsPupilFullReviewDAO>  getPupilsFullReviewByEmail(String email) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_FULL_REVIEW_BY_EMAIL_QUERY);

        int i = 1;
        stmt.setString(i++, email);
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return new ArrayList<>();
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    private void executeQuery(CallableStatement stmt) {
        try {
            stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
