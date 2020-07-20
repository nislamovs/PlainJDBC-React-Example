package com.example.jdbcexample.repository.impl;

import com.example.jdbcexample.domain.dao.ParentDAO;
import com.example.jdbcexample.domain.dao.SchoolClassDAO;
import com.example.jdbcexample.repository.ParentsRepository;
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
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@RequiredArgsConstructor
@Slf4j
public class ParentsRepositoryImpl implements ParentsRepository {

    private final BasicDataSource dataSource;


    @Override
    public ParentDAO create(ParentDAO parentDAO) {
        return null;
    }

    @Override
    public ParentDAO findById(Integer id) {
        return null;
    }

    @Override
    public ParentDAO update(ParentDAO parentDAO) {
        return null;
    }

    @Override
    public ParentDAO delete(Integer id) {
        return null;
    }

    @Override
    public List<ParentDAO> findAll() {
        return null;
    }

    @Override
    public List<ParentDAO> getPage(String pagenum, String pagesize, String sort, String group) {
        return null;
    }
}
