package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dao.SchoolClassDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.SchoolClassDTO;
import com.example.jdbcexample.mappers.ClassMapper;
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
public class ClassesService {

    private final BasicDataSource dataSource;
    private final ClassMapper mapper;

    private final String CLASSES_RETRIEVAL_QUERY = "select * FROM classes";
    private final String CLASSES_PAGE_RETRIEVAL_QUERY = "select * FROM classes WHERE id BETWEEN ? AND ? order by ? ?";
    private final String CLASSES_GET_BY_ID_RETRIEVAL_QUERY = "select * FROM classes WHERE id = ?";
    private final String CLASSES_NEW_CLASS_ADD_QUERY = "INSERT INTO classes(id, type, class_head_id, name) VALUES (?, ?, ?, ?)";
    private final String CLASSES_EXISTING_CLASS_UPDATE_QUERY = "UPDATE INTO classes(id, type, class_head_id, name) VALUES (?, ?, ?, ?)";
    private final String CLASSES_EXISTING_CLASS_DELETE_QUERY = "DELETE FROM classes where id = ?";

    @SneakyThrows
    public List<SchoolClassDTO> getAllClasses() {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(CLASSES_RETRIEVAL_QUERY);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public  List<SchoolClassDTO> getClassesPage(String pagenum, String pagesize, String sort, String group) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(CLASSES_PAGE_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum));
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum) + parseInt(pagesize));
        stmt.setString(i++, group);
        stmt.setString(i++, sort);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public SchoolClassDTO getSchoolClassById(String classId) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(CLASSES_GET_BY_ID_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(classId));

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).stream().map(mapper::toDTO).collect(Collectors.toList()).get(0);
    }

    @SneakyThrows
    public AbstractDTO addNewSchoolClass(SchoolClassDTO schoolClass) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(CLASSES_NEW_CLASS_ADD_QUERY);

        int i = 1;
        stmt.setRowId(i++, null);
        stmt.setString(i++, schoolClass.getType());
        stmt.setLong(i++, schoolClass.getClass_head_id());
        stmt.setString(i++, schoolClass.getName());

        System.out.println(">>>   "+stmt.toString());

        Long markId = executeInsert(stmt);

        return AbstractDTO.builder().id(valueOf(markId)).dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO editSchoolClassData(SchoolClassDTO schoolClass) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(CLASSES_EXISTING_CLASS_UPDATE_QUERY);

        int i = 1;
        stmt.setLong(i++, parseLong(schoolClass.getId()));
        stmt.setString(i++, schoolClass.getType());
        stmt.setLong(i++, schoolClass.getClass_head_id());
        stmt.setString(i++, schoolClass.getName());

        System.out.println(">>>   "+stmt.toString());

        Long markId = executeUpdate(stmt);

        return AbstractDTO.builder().id(valueOf(markId)).dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO deleteSchoolClass(String classId) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(CLASSES_EXISTING_CLASS_DELETE_QUERY);

        int i = 1;
        stmt.setLong(i++, parseLong(classId));

        System.out.println(">>>   "+stmt.toString());

        return AbstractDTO.builder().dateTime(LocalDateTime.now()).build();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private List<SchoolClassDAO> executeQuery(PreparedStatement stmt) {
        List<SchoolClassDAO> classes = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SchoolClassDAO mark = SchoolClassDAO.builder()
                        .id(rs.getLong("id"))
                        .class_head_id(rs.getLong("class_head_id"))
                        .name(rs.getString("name"))
                        .type(rs.getString("type"))
                        .build();

                classes.add(mark);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return classes;
    }

    private Long executeUpdate(PreparedStatement stmt) {
        return executeInsert(stmt);
    }

    private Long executeInsert(PreparedStatement stmt) {

        long retVal = 0L;

        try {
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating new school class failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    retVal = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating new school class failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return retVal;
    }
}
