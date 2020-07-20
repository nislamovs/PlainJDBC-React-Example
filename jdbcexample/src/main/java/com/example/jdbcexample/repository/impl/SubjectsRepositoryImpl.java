package com.example.jdbcexample.repository.impl;

import com.example.jdbcexample.domain.dao.SchoolClassDAO;
import com.example.jdbcexample.domain.dao.SubjectDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.mappers.SubjectMapper;
import com.example.jdbcexample.repository.SubjectsRepository;
import com.example.jdbcexample.repository.TeachersRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

@RequiredArgsConstructor
@Slf4j
public class SubjectsRepositoryImpl implements SubjectsRepository {

    private final BasicDataSource dataSource;

    private final String SUBJECTS_RETRIEVAL_QUERY = "select * FROM subjects";
    private final String SUBJECTS_PAGE_RETRIEVAL_QUERY = "select * FROM subjects WHERE id BETWEEN ? AND ? order by ? ?";
    private final String SUBJECTS_GET_BY_ID_RETRIEVAL_QUERY = "select * FROM subjects WHERE id = ?";
    private final String SUBJECTS_NEW_SUBJECT_ADD_QUERY = "INSERT INTO subjects(id, teacher_id, name) VALUES (?, ?, ?)";
    private final String SUBJECTS_EXISTING_SUBJECT_UPDATE_QUERY = "UPDATE INTO subjects(id, teacher_id, name) VALUES (?, ?, ?)";
    private final String SUBJECTS_EXISTING_SUBJECT_DELETE_QUERY = "DELETE FROM subjects where id = ?";

    @Override
    @SneakyThrows
    public SubjectDAO create(SubjectDAO newSubject) {
        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(SUBJECTS_NEW_SUBJECT_ADD_QUERY);

        int i = 1;
        stmt.setRowId(i++, null);
        stmt.setLong(i++, newSubject.getTeacher_id());
        stmt.setString(i++, newSubject.getName());

        System.out.println(">>>   "+stmt.toString());

        Long subjectId = executeInsert(stmt);

        return SubjectDAO.builder().id(subjectId).build();
    }

    @Override
    @SneakyThrows
    public SubjectDAO findById(Integer subjectId) {
        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(SUBJECTS_GET_BY_ID_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, subjectId);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).get(0);
    }

    @Override
    @SneakyThrows
    public SubjectDAO update(SubjectDAO subject) {
        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(SUBJECTS_EXISTING_SUBJECT_UPDATE_QUERY);

        int i = 1;
        stmt.setLong(i++, subject.getId());
        stmt.setLong(i++, subject.getTeacher_id());
        stmt.setString(i++, subject.getName());


        System.out.println(">>>   "+stmt.toString());

        Long subjectId = executeUpdate(stmt);

        return SubjectDAO.builder().id(subjectId).build();
    }

    @Override
    @SneakyThrows
    public SubjectDAO delete(Integer subjectId) {
        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(SUBJECTS_EXISTING_SUBJECT_DELETE_QUERY);

        int i = 1;
        stmt.setLong(i++, subjectId);

        System.out.println(">>>   "+stmt.toString());

        return SubjectDAO.builder().id((long)subjectId).build();
    }

    @Override
    @SneakyThrows
    public List<SubjectDAO> findAll() {
        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(SUBJECTS_RETRIEVAL_QUERY);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt);
    }

    @Override
    @SneakyThrows
    public List<SubjectDAO> getPage(String pagenum, String pagesize, String sort, String group) {
        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(SUBJECTS_PAGE_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum));
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum) + parseInt(pagesize));
        stmt.setString(i++, group);
        stmt.setString(i++, sort);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private List<SubjectDAO> executeQuery(PreparedStatement stmt) {
        List<SubjectDAO> subjects = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SubjectDAO subject = SubjectDAO.builder()
                        .id(rs.getLong("id"))
                        .teacher_id(rs.getLong("teacher_id"))
                        .name(rs.getString("name"))
                        .build();

                subjects.add(subject);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return subjects;
    }

    private Long executeUpdate(PreparedStatement stmt) {
        return executeInsert(stmt);
    }

    private Long executeInsert(PreparedStatement stmt) {

        long retVal = 0L;

        try {
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating new subject failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    retVal = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating new subject failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return retVal;
    }

}
