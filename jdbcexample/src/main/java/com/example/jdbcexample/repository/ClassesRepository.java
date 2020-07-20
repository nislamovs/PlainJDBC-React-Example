package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.SchoolClassDAO;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends GenericRepository<SchoolClassDAO> {


}
