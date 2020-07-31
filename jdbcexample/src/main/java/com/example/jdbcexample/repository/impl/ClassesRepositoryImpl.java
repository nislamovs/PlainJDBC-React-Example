package com.example.jdbcexample.repository.impl;

import com.example.jdbcexample.domain.dao.SchoolClassDAO;
import com.example.jdbcexample.mappers.ClassMapper;
import com.example.jdbcexample.repository.ClassesRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@RequiredArgsConstructor
@Slf4j
@Component
public class ClassesRepositoryImpl implements ClassesRepository {

    private final BasicDataSource dataSource;

    private final String CLASSES_RETRIEVAL_QUERY = "SELECT * FROM classes";
    private final String CLASSES_PAGE_RETRIEVAL_QUERY = "SELECT * FROM classes WHERE id BETWEEN ? AND ? order by ? ?";
    private final String CLASSES_GET_BY_ID_RETRIEVAL_QUERY = "SELECT * FROM classes WHERE id = ?";
    private final String CLASSES_NEW_CLASS_ADD_QUERY = "INSERT INTO classes(id, type, class_head_id, name) VALUES (?, ?, ?, ?)";
    private final String CLASSES_EXISTING_CLASS_UPDATE_QUERY = "UPDATE INTO classes(id, type, class_head_id, name) VALUES (?, ?, ?, ?)";
    private final String CLASSES_EXISTING_CLASS_DELETE_QUERY = "DELETE FROM classes where id = ?";

    @Override
    @SneakyThrows
    public SchoolClassDAO create(SchoolClassDAO schoolClass) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(CLASSES_NEW_CLASS_ADD_QUERY);

        int i = 0;
        stmt.setRowId(++i, null);
        stmt.setString(++i, schoolClass.getType());
        stmt.setLong(++i, schoolClass.getClass_head_id());
        stmt.setString(++i, schoolClass.getName());

        System.out.println(">>>   "+stmt.toString());

        return SchoolClassDAO.builder().id(executeInsert(stmt)).build();
    }

    @Override
    @SneakyThrows
    public SchoolClassDAO findById(Integer id) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(CLASSES_GET_BY_ID_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, id);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).get(0);
    }

    @Override
    @SneakyThrows
    public SchoolClassDAO update(SchoolClassDAO schoolClass) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(CLASSES_EXISTING_CLASS_UPDATE_QUERY);

        int i = 1;
        stmt.setLong(i++, schoolClass.getId());
        stmt.setString(i++, schoolClass.getType());
        stmt.setLong(i++, schoolClass.getClass_head_id());
        stmt.setString(i++, schoolClass.getName());

        System.out.println(">>>   "+stmt.toString());

        return SchoolClassDAO.builder().id(executeUpdate(stmt)).build();
    }

    @Override
    @SneakyThrows
    public SchoolClassDAO delete(Integer id) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(CLASSES_EXISTING_CLASS_DELETE_QUERY);

        int i = 1;
        stmt.setInt(i++, id);
        stmt.executeQuery();

        return SchoolClassDAO.builder().id(Long.valueOf(id)).build();
    }

    @Override
    @SneakyThrows
    public List<SchoolClassDAO> findAll() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(CLASSES_RETRIEVAL_QUERY);

        System.out.println(">>>   "+stmt.toString());
        return executeQuery(stmt);
    }

    @Override
    @SneakyThrows
    public List<SchoolClassDAO> getPage(String pagenum, String pagesize, String sort, String group) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(CLASSES_PAGE_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum));
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum) + parseInt(pagesize));
        stmt.setString(i++, group);
        stmt.setString(i++, sort);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

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
