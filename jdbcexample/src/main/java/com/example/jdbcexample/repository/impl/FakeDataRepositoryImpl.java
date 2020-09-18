package com.example.jdbcexample.repository.impl;

import com.example.jdbcexample.domain.dao.TeacherDAO;
import com.example.jdbcexample.repository.FakeDataRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@RequiredArgsConstructor
@Slf4j
@Component
public class FakeDataRepositoryImpl implements FakeDataRepository {

    private final DataSource dataSource;

    private final String NEW_CLASS_GENERATION_QUERY = "{CALL GENERATE_NEW_CLASS(?)}";

    @Override
    @SneakyThrows
    public String generateNewClass(String pupilCount) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup CallableStatement stmt = conn.prepareCall(NEW_CLASS_GENERATION_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(pupilCount));
        stmt.executeQuery();

        System.out.println(">>>   " + stmt.toString());

        return "Created successfully!";
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    private void executeQuery(CallableStatement stmt) {
        try {
            stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
