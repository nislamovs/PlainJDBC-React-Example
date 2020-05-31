package com.example.jdbcexample.services;

import com.example.jdbcexample.dao.TeacherDAO;
import com.example.jdbcexample.dto.AbstractDTO;
import com.example.jdbcexample.dto.PupilDTO;
import com.example.jdbcexample.dto.TeacherDTO;
import com.example.jdbcexample.mappers.TeacherMapper;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeachersService {

    private final BasicDataSource dataSource;
    private final TeacherMapper mapper;

    private final String TEACHERS_RETRIEVAL_QUERY = "select * FROM teachers";
    private final String TEACHERS_PAGE_RETRIEVAL_QUERY = "select * FROM teachers WHERE id BETWEEN ? AND ? order by ? ?";
    private final String TEACHERS_GET_BY_ID_RETRIEVAL_QUERY = "select * FROM teachers WHERE id = ?";
    private final String TEACHERS_NEW_TEACHER_ADD_QUERY = "INSERT INTO teachers(id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String TEACHERS_EXISTING_TEACHER_UPDATE_QUERY = "UPDATE INTO teachers(id, firstname, lastname, email, birthdate, class_id, subject_id, is_head) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String TEACHERS_EXISTING_TEACHER_DELETE_QUERY = "DELETE FROM teachers where id = ?";

    @SneakyThrows
    public List<TeacherDTO> getAllTeachers() {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(TEACHERS_RETRIEVAL_QUERY);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public  List<TeacherDTO> getTeachersPage(String pagenum, String pagesize, String sort, String group) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(TEACHERS_PAGE_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum));
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum) + parseInt(pagesize));
        stmt.setString(i++, group);
        stmt.setString(i++, sort);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public TeacherDTO getTeacherById(String teacherId) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(TEACHERS_GET_BY_ID_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(teacherId));

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).stream().map(mapper::toDTO).collect(Collectors.toList()).get(0);
    }

    @SneakyThrows
    public AbstractDTO addNewTeacher(TeacherDTO newTeacher) {

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

        System.out.println(">>>   "+stmt.toString());

        Long markId = executeInsert(stmt);

        return AbstractDTO.builder().id(valueOf(markId)).dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO editTeacherData(TeacherDTO teacher) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(TEACHERS_EXISTING_TEACHER_UPDATE_QUERY);

        int i = 1;
        stmt.setLong(i++, parseLong(teacher.getId()));
        stmt.setString(i++, teacher.getFirstname());
        stmt.setString(i++, teacher.getLastname());
        stmt.setString(i++, teacher.getEmail());
        stmt.setDate(i++, (Date) teacher.getBirthdate());
        stmt.setLong(i++, teacher.getClass_id());
        stmt.setLong(i++, teacher.getSubject_id());
        stmt.setBoolean(i++, teacher.getIs_head());

        System.out.println(">>>   "+stmt.toString());

        Long markId = executeUpdate(stmt);

        return AbstractDTO.builder().id(valueOf(markId)).dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO deleteTeacher(String teacherId) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(TEACHERS_EXISTING_TEACHER_DELETE_QUERY);

        int i = 1;
        stmt.setLong(i++, parseLong(teacherId));

        System.out.println(">>>   "+stmt.toString());

        return AbstractDTO.builder().dateTime(LocalDateTime.now()).build();
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
