package com.example.jdbcexample.repository.impl;

import com.example.jdbcexample.domain.dao.ParentDAO;
import com.example.jdbcexample.domain.dao.PupilDAO;
import com.example.jdbcexample.domain.dao.SchoolClassDAO;
import com.example.jdbcexample.domain.enums.ParentType;
import com.example.jdbcexample.repository.ParentsRepository;
import com.example.jdbcexample.repository.TeachersRepository;
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
public class ParentsRepositoryImpl implements ParentsRepository {

    private final BasicDataSource dataSource;

    private final String PARENTS_RETRIEVAL_QUERY = "SELECT * FROM parents";
    private final String PARENTS_GET_BY_FIRSTNAME_LASTNAME_RETRIEVAL_QUERY = "SELECT * FROM parents WHERE FIRSTNAME = ? AND LASTNAME = ?";
    private final String PARENTS_PAGE_RETRIEVAL_QUERY = "SELECT * FROM parents WHERE id BETWEEN ? AND ? order by ? ?";
    private final String PARENTS_GET_BY_ID_RETRIEVAL_QUERY = "SELECT * FROM parents WHERE id = ?";
    private final String PARENTS_NEW_PARENT_ADD_QUERY = "INSERT INTO parents(id, firstname, lastname, email, parentType, parentInfo, birthdate, address, phonenumber, familyId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String PARENTS_EXISTING_PARENT_UPDATE_QUERY = "UPDATE INTO parents(id, firstname, lastname, email, parentType, parentInfo, birthdate, address, phonenumber, familyId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String PARENTS_EXISTING_PARENT_DELETE_QUERY = "DELETE FROM parents WHERE id = ?";

    @Override
    @SneakyThrows
    public ParentDAO create(ParentDAO newParent) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PARENTS_NEW_PARENT_ADD_QUERY);

        int i = 1;
        stmt.setRowId(i++, null);
        stmt.setString(i++, newParent.getFirstname());
        stmt.setString(i++, newParent.getLastname());
        stmt.setString(i++, newParent.getEmail());
        stmt.setDate(i++, (Date) newParent.getBirthdate());
        stmt.setString(i++, newParent.getParentType().name());
        stmt.setString(i++, newParent.getParentInfo());
        stmt.setString(i++, newParent.getAddress());
        stmt.setString(i++, newParent.getPhonenumber());
        stmt.setString(i++, newParent.getFamilyId());

        System.out.println(">>>   "+stmt.toString());

        Long parentId = executeInsert(stmt);

        return ParentDAO.builder().id(parentId).build();
    }

    @Override
    @SneakyThrows
    public List<ParentDAO> getParentsByFirstnameLastname(String firstname, String lastname) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PARENTS_GET_BY_FIRSTNAME_LASTNAME_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setString(i++, firstname);
        stmt.setString(i++, lastname);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt);
    }

    @Override
    @SneakyThrows
    public ParentDAO findById(Integer parentId) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PARENTS_GET_BY_ID_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parentId);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).get(0);
    }

    @Override
    @SneakyThrows
    public ParentDAO update(ParentDAO parent) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PARENTS_EXISTING_PARENT_UPDATE_QUERY);

        int i = 1;
        stmt.setLong(i++, parent.getId());
        stmt.setString(i++, parent.getFirstname());
        stmt.setString(i++, parent.getLastname());
        stmt.setString(i++, parent.getEmail());
        stmt.setDate(i++, (Date) parent.getBirthdate());
        stmt.setString(i++, parent.getParentType().name());
        stmt.setString(i++, parent.getParentInfo());
        stmt.setString(i++, parent.getAddress());
        stmt.setString(i++, parent.getPhonenumber());
        stmt.setString(i++, parent.getFamilyId());

        System.out.println(">>>   "+stmt.toString());

        Long parentId = executeUpdate(stmt);

        return ParentDAO.builder().id(parentId).build();
    }

    @Override
    @SneakyThrows
    public ParentDAO delete(Integer parentId) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PARENTS_EXISTING_PARENT_DELETE_QUERY);

        int i = 1;
        stmt.setLong(i++, parentId);
        stmt.executeUpdate();

        System.out.println(">>>   "+stmt.toString());

        return ParentDAO.builder().id((long)parentId).build();
    }

    @Override
    @SneakyThrows
    public List<ParentDAO> findAll() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PARENTS_RETRIEVAL_QUERY);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt);
    }

    @Override
    @SneakyThrows
    public List<ParentDAO> getPage(String pagenum, String pagesize, String sort, String group) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PARENTS_PAGE_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum));
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum) + parseInt(pagesize));
        stmt.setString(i++, group);
        stmt.setString(i++, sort);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private List<ParentDAO> executeQuery(PreparedStatement stmt) {
        List<ParentDAO> parents = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ParentDAO parent = ParentDAO.builder()
                        .id(rs.getLong("id"))
                        .firstname(rs.getString("firstname"))
                        .lastname(rs.getString("lastname"))
                        .email(rs.getString("email"))
                        .birthdate(rs.getDate("birthdate"))
                        .parentType(ParentType.valueOf(rs.getString("parentType")))
                        .parentInfo(rs.getString("parentInfo"))
                        .address(rs.getString("address"))
                        .phonenumber(rs.getString("phonenumber"))
                        .familyId(rs.getString("familyId"))
                        .build();

                parents.add(parent);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return parents;
    }

    private Long executeUpdate(PreparedStatement stmt) {
        return executeInsert(stmt);
    }

    private Long executeInsert(PreparedStatement stmt) {

        long retVal = 0L;

        try {
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating new parent failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    retVal = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating new parent failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return retVal;
    }

}
