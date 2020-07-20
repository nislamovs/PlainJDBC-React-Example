package com.example.jdbcexample.repository.impl;

import com.example.jdbcexample.domain.dao.TeacherDAO;
import com.example.jdbcexample.repository.TeachersRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@RequiredArgsConstructor
@Slf4j
public class TeachersRepositoryImpl implements TeachersRepository {

    private final BasicDataSource dataSource;

    private final String TEACHERS_RETRIEVAL_QUERY = "select * FROM teachers";
    private final String TEACHERS_PAGE_RETRIEVAL_QUERY = "select * FROM teachers WHERE id BETWEEN ? AND ? order by ? ?";
    private final String TEACHERS_GET_BY_ID_RETRIEVAL_QUERY = "select * FROM teachers WHERE id = ?";
    private final String TEACHERS_NEW_TEACHER_ADD_QUERY = "INSERT INTO teachers(id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String TEACHERS_EXISTING_TEACHER_UPDATE_QUERY = "UPDATE INTO teachers(id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String TEACHERS_EXISTING_TEACHER_DELETE_QUERY = "DELETE FROM teachers where id = ?";

    @Override
    @SneakyThrows
    public TeacherDAO create(TeacherDAO newTeacher) {
        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(TEACHERS_NEW_TEACHER_ADD_QUERY);

        int i = 1;
        stmt.setRowId(i++, null);
        stmt.setString(i++, newTeacher.getFirstname());
        stmt.setString(i++, newTeacher.getLastname());
        stmt.setString(i++, newTeacher.getEmail());
        stmt.setDate(i++, (Date) newTeacher.getBirthdate());
        stmt.setLong(i++, newTeacher.getClass_id());
        stmt.setLong(i++, newTeacher.getSubject_id());
        stmt.setBoolean(i++, newTeacher.getIs_head());

        System.out.println(">>>   " + stmt.toString());

        Long teacherId = executeInsert(stmt);

        return TeacherDAO.builder().id(teacherId).build();
    }

    @Override
    @SneakyThrows
    public TeacherDAO findById(Integer id) {
        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(TEACHERS_GET_BY_ID_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, id);

        System.out.println(">>>   " + stmt.toString());

        return executeQuery(stmt).get(0);
    }

    @Override
    @SneakyThrows
    public TeacherDAO update(TeacherDAO teacher) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(TEACHERS_EXISTING_TEACHER_UPDATE_QUERY);

        int i = 1;
        stmt.setLong(i++, teacher.getId());
        stmt.setString(i++, teacher.getFirstname());
        stmt.setString(i++, teacher.getLastname());
        stmt.setString(i++, teacher.getEmail());
        stmt.setDate(i++, (Date) teacher.getBirthdate());
        stmt.setLong(i++, teacher.getClass_id());
        stmt.setLong(i++, teacher.getSubject_id());
        stmt.setBoolean(i++, teacher.getIs_head());

        System.out.println(">>>   " + stmt.toString());

        Long teacherId = executeUpdate(stmt);

        return TeacherDAO.builder().id(teacherId).build();
    }

    @Override
    @SneakyThrows
    public TeacherDAO delete(Integer id) {
        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(TEACHERS_EXISTING_TEACHER_DELETE_QUERY);

        int i = 1;
        stmt.setInt(i++, id);

        System.out.println(">>>   " + stmt.toString());

        return TeacherDAO.builder().id(Long.valueOf(id)).build();
    }

    @Override
    @SneakyThrows
    public List<TeacherDAO> findAll() {
        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(TEACHERS_RETRIEVAL_QUERY);

        System.out.println(">>>   " + stmt.toString());

        return executeQuery(stmt);
    }

    @Override
    @SneakyThrows
    public List<TeacherDAO> getPage(String pagenum, String pagesize, String sort, String group) {
        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(TEACHERS_PAGE_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum));
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum) + parseInt(pagesize));
        stmt.setString(i++, group);
        stmt.setString(i++, sort);

        System.out.println(">>>   " + stmt.toString());

        return executeQuery(stmt);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private List<TeacherDAO> executeQuery(PreparedStatement stmt) {
        List<TeacherDAO> teachers = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TeacherDAO teacher = TeacherDAO.builder()
                        .id(rs.getLong("id"))
                        .firstname(rs.getString("firstname"))
                        .lastname(rs.getString("lastname"))
                        .email(rs.getString("email"))
                        .birthdate(rs.getDate("birthdate"))
                        .is_head(rs.getBoolean("id_head"))
                        .class_id(rs.getLong("class_id"))
                        .subject_id(rs.getLong("subject_id"))
                        .build();

                teachers.add(teacher);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return teachers;
    }

    private Long executeUpdate(PreparedStatement stmt) {
        return executeInsert(stmt);
    }

    private Long executeInsert(PreparedStatement stmt) {

        long retVal = 0L;

        try {
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating new teacher failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    retVal = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating new teacher failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return retVal;
    }
}
