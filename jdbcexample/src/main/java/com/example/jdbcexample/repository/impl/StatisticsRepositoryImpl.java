package com.example.jdbcexample.repository.impl;

import com.example.jdbcexample.domain.dao.statistics.*;
import com.example.jdbcexample.mappers.sqlMappers.ResultSetMapper;
import com.example.jdbcexample.repository.StatisticsRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

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

        ResultSetMapper<StatsPupilsRelativesDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilsRelativesDAO.class);
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

        return executeQuery(new HashMap<>(), stmt);
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

        ResultSetMapper<StatsPupilMarkPerClassDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilMarkPerClassDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsPupilMarkPerClassDAO>  getPupilsMarksPerClass(String className) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_MARKS_PER_CLASS_QUERY);

        int i = 1;
        stmt.setString(i++, className);

        ResultSetMapper<StatsPupilMarkPerClassDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilMarkPerClassDAO.class);
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

        ResultSetMapper<StatsPupilAvgMarkDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilAvgMarkDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkDAO>  getAvgPupilsMarksByPupilIdInterval(int idStart, int idEnd) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_BY_PUPIL_ID_INTERVAL_ALL_SCHOOL_QUERY);

        int i = 1;
        stmt.setInt(i++, idStart);
        stmt.setInt(i++, idEnd);

        ResultSetMapper<StatsPupilAvgMarkDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilAvgMarkDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkRatedDAO>  getAvgPupilsMarksTop5PerSchool() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_ALL_SCHOOL_TOP5_QUERY);

        ResultSetMapper<StatsPupilAvgMarkRatedDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilAvgMarkRatedDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkRatedDAO>  getAvgPupilsMarksAbove7PerSchool(int pageSize, int pageNum) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7_PAGE_QUERY);

        int i = 1;
        stmt.setInt(i++, pageSize);
        stmt.setInt(i++, pageNum);

        ResultSetMapper<StatsPupilAvgMarkRatedDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilAvgMarkRatedDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkRatedDAO>  getAvgPupilsMarksAbove7PerSchool() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_ALL_SCHOOL_ABOVE_7_QUERY);

        ResultSetMapper<StatsPupilAvgMarkRatedDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilAvgMarkRatedDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkRatedDAO>  getAvgPupilsMarksBySubject(String subject) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_ALL_SCHOOL_PER_SUBJECT_TOP5_QUERY);

        int i = 1;
        stmt.setString(i++, subject);

        ResultSetMapper<StatsPupilAvgMarkRatedDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilAvgMarkRatedDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsPupilAvgMarkRatedDAO>  getAvgPupilsMarksByClass(String className) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_AVG_PUPILS_MARKS_BY_CLASS_QUERY);

        int i = 1;
        stmt.setString(i++, className);

        ResultSetMapper<StatsPupilAvgMarkRatedDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilAvgMarkRatedDAO.class);
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

        ResultSetMapper<StatsPupilAvgMarkRatedDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilAvgMarkRatedDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsKidsParentsDAO>  getParentsAndKidsList(int pageSize, int pageNum) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PARENTS_AND_KIDS_LIST_PAGE_QUERY);

        int i = 1;
        stmt.setInt(i++, pageSize);
        stmt.setInt(i++, pageNum);

        ResultSetMapper<StatsKidsParentsDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsKidsParentsDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsKidsParentsDAO>  getParentsAndKidsList() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PARENTS_AND_KIDS_LIST_QUERY);

        ResultSetMapper<StatsKidsParentsDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsKidsParentsDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsPupilReviewDAO>  getPupilsReview(int pageSize, int pageNum) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_REVIEW_PAGE_QUERY);

        int i = 1;
        stmt.setInt(i++, pageSize);
        stmt.setInt(i++, pageNum);

        ResultSetMapper<StatsPupilReviewDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilReviewDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsPupilReviewDAO>  getPupilsReview() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_REVIEW_QUERY);

        ResultSetMapper<StatsPupilReviewDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilReviewDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsPupilFullReviewDAO>  getPupilsFullReview(int pageSize, int pageNum) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_FULL_REVIEW_PAGE_QUERY);

        int i = 1;
        stmt.setInt(i++, pageSize);
        stmt.setInt(i++, pageNum);

        ResultSetMapper<StatsPupilFullReviewDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilFullReviewDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsPupilFullReviewDAO>  getPupilsFullReview() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_FULL_REVIEW_QUERY);

        ResultSetMapper<StatsPupilFullReviewDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilFullReviewDAO.class);
    }

    @Override
    @SneakyThrows
    public List<StatsPupilFullReviewDAO>  getPupilsFullReviewByEmail(String email) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(GET_PUPILS_FULL_REVIEW_BY_EMAIL_QUERY);

        int i = 1;
        stmt.setString(i++, email);

        ResultSetMapper<StatsPupilFullReviewDAO> mapper = new ResultSetMapper<>();

        return mapper.mapResultSetToObject(stmt.executeQuery(), StatsPupilFullReviewDAO.class);
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    private Map<Integer, Map<String, Integer>> executeQuery(Map<Integer, Map<String, Integer>> emailProviders, CallableStatement stmt) {
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                emailProviders.put(
                    rs.getInt("id"),
                    new HashMap<String, Integer>(){{ put(rs.getString("domain"), rs.getInt("count")); }}
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return emailProviders;
    }

}
