package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.SchoolClassDAO;
import com.example.jdbcexample.repository.crudRepos.GenericRepository;
import com.example.jdbcexample.repository.crudRepos.PagingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends GenericRepository<SchoolClassDAO>, PagingRepository<SchoolClassDAO> {

}
