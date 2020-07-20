package com.example.jdbcexample.repository.impl;

import com.example.jdbcexample.domain.dao.SchoolClassStatisticsDAO;
import com.example.jdbcexample.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class StatisticsRepositoryImpl implements StatisticsRepository {

    private final BasicDataSource dataSource;

    private final String TEACHERS_RETRIEVAL_QUERY = "select * FROM teachers";
    private final String TEACHERS_PAGE_RETRIEVAL_QUERY = "select * FROM teachers WHERE id BETWEEN ? AND ? order by ? ?";

    @Override
    public SchoolClassStatisticsDAO create(SchoolClassStatisticsDAO schoolClassStatisticsDAO) {
        return null;
    }

    @Override
    public SchoolClassStatisticsDAO findById(Integer id) {
        return null;
    }

    @Override
    public SchoolClassStatisticsDAO update(SchoolClassStatisticsDAO schoolClassStatisticsDAO) {
        return null;
    }

    @Override
    public SchoolClassStatisticsDAO delete(Integer id) {
        return null;
    }

    @Override
    public List<SchoolClassStatisticsDAO> findAll() {
        return null;
    }

    @Override
    public List<SchoolClassStatisticsDAO> getPage(String pagenum, String pagesize, String sort, String group) {
        return null;
    }
}
