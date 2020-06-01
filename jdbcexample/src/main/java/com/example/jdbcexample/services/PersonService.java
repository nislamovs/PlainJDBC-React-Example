package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dao.PersonDAO;
import com.example.jdbcexample.domain.dto.PersonDTO;
import com.example.jdbcexample.mappers.PersonMapper;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final BasicDataSource dataSource;
    private final PersonMapper mapper;

    private final String PERSONS_RETRIEVAL_QUERY = "select *  from pupils where email = ?\n" +
            "union all\n" +
            "select *  from teachers where email = ?";

    @SneakyThrows
    public List<PersonDTO> findPersonByEmail(String email) {
        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(PERSONS_RETRIEVAL_QUERY);

        System.out.println(">>>   " + stmt.toString());

        int i = 1;
        stmt.setString(i++, email);
        stmt.setString(i++, email);

        return executeQuery(stmt).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private List<PersonDAO> executeQuery(PreparedStatement stmt) {
        List<PersonDAO> persons = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PersonDAO person = PersonDAO.builder()
                        .id(rs.getLong("id"))
                        .firstname(rs.getString("firstname"))
                        .lastname(rs.getString("lastname"))
                        .email(rs.getString("email"))
                        .birthdate(rs.getDate("birthdate"))
                        .build();

                persons.add(person);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return persons;
    }
}
