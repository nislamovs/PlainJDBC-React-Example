package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.SubjectDAO;
import com.example.jdbcexample.repository.crudRepos.GenericRepository;
import com.example.jdbcexample.repository.crudRepos.PagingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectsRepository extends GenericRepository<SubjectDAO>, PagingRepository<SubjectDAO> {

}
