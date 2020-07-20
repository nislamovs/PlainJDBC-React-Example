package com.example.jdbcexample.repository.impl;

import com.example.jdbcexample.domain.dao.SubjectMarkDAO;
import com.example.jdbcexample.repository.MarksRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@RequiredArgsConstructor
@Slf4j
@Component
public class MarksRepositoryImpl implements MarksRepository {

    private final BasicDataSource dataSource;

    private final String MARKS_RETRIEVAL_QUERY = "SELECT * FROM marks";
    private final String MARKS_PAGE_RETRIEVAL_QUERY = "SELECT * FROM marks WHERE id BETWEEN ? AND ? order by ? ?";
    private final String MARKS_GET_BY_ID_RETRIEVAL_QUERY = "SELECT * FROM marks WHERE id = ?";
    private final String MARKS_GET_BY_DATE_RETRIEVAL_QUERY = "SELECT * FROM marks WHERE date >= ? AND date <= ?";
    private final String MARKS_NEW_MARK_ADD_QUERY = "INSERT INTO marks(id, subject_id, pupil_id, date, value) VALUES (?, ?, ?, ?, ?)";
    private final String MARKS_EXISTING_MARK_UPDATE_QUERY = "UPDATE INTO marks(id, subject_id, pupil_id, date, value) VALUES (?, ?, ?, ?, ?)";
    private final String MARKS_EXISTING_MARK_DELETE_QUERY = "DELETE FROM marks where id = ?";

    private final String MARKS_GET_BY_PUPIL_ID_RETRIEVAL_QUERY = "SELECT * FROM marks WHERE pupil_id = ?";


    @Override
    @SneakyThrows
    public SubjectMarkDAO create(SubjectMarkDAO mark) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_NEW_MARK_ADD_QUERY);

        int i = 1;
        stmt.setRowId(i++, null);
        stmt.setLong(i++, mark.getSubjectId());
        stmt.setLong(i++, mark.getPupilId());
        stmt.setDate(i++, (Date) mark.getDate());
        stmt.setInt(i++, mark.getValue());

        System.out.println(">>>   " + stmt.toString());

        Long markId = executeInsert(stmt);

        return SubjectMarkDAO.builder().id(markId).build();
    }

    @Override
    @SneakyThrows
    public List<SubjectMarkDAO> retrieveMarksByPupilId(String pupilId) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_GET_BY_PUPIL_ID_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(pupilId));

        System.out.println(">>>   " + stmt.toString());

        return executeQuery(stmt);
    }

    @Override
    @SneakyThrows
    public List<SubjectMarkDAO> getMarksByDateInterval(String startDate, String endDate) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_GET_BY_DATE_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setDate(i++, Date.valueOf(startDate));
        stmt.setDate(i++, Date.valueOf(endDate));

        System.out.println(">>>   " + stmt.toString());

        return executeQuery(stmt);
    }

    @Override
    @SneakyThrows
    public SubjectMarkDAO findById(Integer markId) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_GET_BY_ID_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, markId);

        System.out.println(">>>   " + stmt.toString());

        return executeQuery(stmt).get(0);
    }

    @Override
    @SneakyThrows
    public SubjectMarkDAO update(SubjectMarkDAO mark) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_EXISTING_MARK_UPDATE_QUERY);

        int i = 1;
        stmt.setLong(i++, mark.getId());
        stmt.setLong(i++, mark.getSubjectId());
        stmt.setLong(i++, mark.getPupilId());
        stmt.setDate(i++, (Date) mark.getDate());
        stmt.setInt(i++, mark.getValue());

        System.out.println(">>>   " + stmt.toString());

        Long markId = executeUpdate(stmt);

        return SubjectMarkDAO.builder().id(markId).build();
    }

    @Override
    @SneakyThrows
    public SubjectMarkDAO delete(Integer markId) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_EXISTING_MARK_DELETE_QUERY);

        int i = 1;
        stmt.setInt(i++, markId);

        System.out.println(">>>   " + stmt.toString());

        return SubjectMarkDAO.builder().id((long) markId).build();
    }

    @Override
    @SneakyThrows
    public List<SubjectMarkDAO> findAll() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_RETRIEVAL_QUERY);

        System.out.println(">>>   " + stmt.toString());

        return executeQuery(stmt);
    }

    @Override
    @SneakyThrows
    public List<SubjectMarkDAO> getPage(String pagenum, String pagesize, String sort, String group) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_PAGE_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum));
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum) + parseInt(pagesize));
        stmt.setString(i++, group);
        stmt.setString(i++, sort);

        System.out.println(">>>   " + stmt.toString());

        return executeQuery(stmt);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    private List<SubjectMarkDAO> executeQuery(PreparedStatement stmt) {
        List<SubjectMarkDAO> marks = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SubjectMarkDAO mark = SubjectMarkDAO.builder()
                        .id(rs.getLong("id"))
                        .subjectId(rs.getLong("subject_id"))
                        .pupilId(rs.getLong("pupil_id"))
                        .date(rs.getDate("date"))
                        .value(rs.getInt("id"))
                        .build();

                marks.add(mark);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return marks;
    }

    private Long executeUpdate(PreparedStatement stmt) {
        return executeInsert(stmt);
    }

    private Long executeInsert(PreparedStatement stmt) {

        long retVal = 0L;

        try {
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating new mark failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    retVal = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating new mark failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return retVal;
    }
}
