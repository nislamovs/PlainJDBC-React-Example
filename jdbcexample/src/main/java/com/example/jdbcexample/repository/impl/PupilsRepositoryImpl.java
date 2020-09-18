package com.example.jdbcexample.repository.impl;

import com.example.jdbcexample.domain.dao.PupilDAO;
import com.example.jdbcexample.domain.dao.SchoolClassDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.repository.PupilsRepository;
import com.example.jdbcexample.repository.TeachersRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

@RequiredArgsConstructor
@Slf4j
@Component
public class PupilsRepositoryImpl implements PupilsRepository {

    private final BasicDataSource dataSource;

    private final String PUPILS_RETRIEVAL_QUERY = "SELECT * FROM pupils";
    private final String PUPILS_GET_BY_FIRSTNAME_LASTNAME_RETRIEVAL_QUERY = "SELECT * FROM pupils WHERE firstname = ? AND LASTNAME = ?";
    private final String PUPILS_PAGE_RETRIEVAL_QUERY = "SELECT * FROM pupils WHERE id BETWEEN ? AND ? order by ? ?";
    private final String PUPILS_GET_BY_ID_RETRIEVAL_QUERY = "SELECT * FROM pupils WHERE id = ?";
    private final String PUPILS_NEW_PUPIL_ADD_QUERY = "INSERT INTO pupils(id, firstname, lastname, email, gender, birthdate, class_id, class_head_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String PUPILS_EXISTING_PUPIL_UPDATE_QUERY = "UPDATE INTO pupils(id, firstname, lastname, email, gender, birthdate, class_id, class_head_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String PUPILS_EXISTING_PUPIL_DELETE_QUERY = "DELETE FROM pupils where id = ?";


    @Override
    @SneakyThrows
    public PupilDAO create(PupilDAO newPupil) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PUPILS_NEW_PUPIL_ADD_QUERY);

        int i = 1;
        stmt.setRowId(i++, null);
        stmt.setString(i++, newPupil.getFirstname());
        stmt.setString(i++, newPupil.getLastname());
        stmt.setString(i++, newPupil.getEmail());
        stmt.setString(i++, newPupil.getGender());
        stmt.setDate(i++, (Date) newPupil.getBirthdate());
        stmt.setLong(i++, newPupil.getClass_id());
        stmt.setLong(i++, newPupil.getClass_head_id());

        System.out.println(">>>   "+stmt.toString());

        Long pupilId = executeInsert(stmt);

        return PupilDAO.builder().id(pupilId).build();
    }

    @Override
    @SneakyThrows
    public List<PupilDAO> getPupilsByFirstnameLastname(String firstname, String lastname) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PUPILS_GET_BY_FIRSTNAME_LASTNAME_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setString(i++, firstname);
        stmt.setString(i++, lastname);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt);
    }

    @Override
    @SneakyThrows
    public PupilDAO findById(Integer pupilId) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PUPILS_GET_BY_ID_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, pupilId);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).get(0);
    }

    @Override
    @SneakyThrows
    public PupilDAO update(PupilDAO pupil) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PUPILS_EXISTING_PUPIL_UPDATE_QUERY);

        int i = 1;
        stmt.setLong(i++, pupil.getId());
        stmt.setString(i++, pupil.getFirstname());
        stmt.setString(i++, pupil.getLastname());
        stmt.setString(i++, pupil.getEmail());
        stmt.setString(i++, pupil.getGender());
        stmt.setDate(i++, (Date) pupil.getBirthdate());
        stmt.setLong(i++, pupil.getClass_id());
        stmt.setLong(i++, pupil.getClass_head_id());

        System.out.println(">>>   "+stmt.toString());

        Long pupilId = executeUpdate(stmt);

        return PupilDAO.builder().id(pupilId).build();
    }

    @Override
    @SneakyThrows
    public PupilDAO delete(Integer pupilId) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PUPILS_EXISTING_PUPIL_DELETE_QUERY);

        int i = 1;
        stmt.setLong(++i, pupilId);
        stmt.executeUpdate();

        System.out.println(">>>   "+stmt.toString());

        return PupilDAO.builder().id((long)pupilId).build();
    }

    @Override
    @SneakyThrows
    public List<PupilDAO> findAll() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PUPILS_RETRIEVAL_QUERY);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt);
    }

    @Override
    @SneakyThrows
    public List<PupilDAO> getPage(String pagenum, String pagesize, String sort, String group) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PUPILS_PAGE_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum));
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum) + parseInt(pagesize));
        stmt.setString(i++, group);
        stmt.setString(i++, sort);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private List<PupilDAO> executeQuery(PreparedStatement stmt) {
        List<PupilDAO> pupils = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PupilDAO pupil = PupilDAO.builder()
                        .id(rs.getLong("id"))
                        .firstname(rs.getString("firstname"))
                        .lastname(rs.getString("lastname"))
                        .email(rs.getString("email"))
                        .birthdate(rs.getDate("birthdate"))
                        .class_head_id(rs.getLong("class_head_id"))
                        .class_id(rs.getLong("class_id"))
                        .build();

                pupils.add(pupil);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return pupils;
    }

    private Long executeUpdate(PreparedStatement stmt) {
        return executeInsert(stmt);
    }

    private Long executeInsert(PreparedStatement stmt) {

        long retVal = 0L;

        try {
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating new pupil failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    retVal = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating new pupil failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return retVal;
    }
}
