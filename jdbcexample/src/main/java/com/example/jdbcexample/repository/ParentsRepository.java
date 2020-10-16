package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.ParentDAO;
import com.example.jdbcexample.repository.crudRepos.GenericRepository;
import com.example.jdbcexample.repository.crudRepos.PagingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentsRepository extends GenericRepository<ParentDAO>, PagingRepository<ParentDAO> {

    List<ParentDAO> getParentsByFirstnameLastname(String firstname, String lastname);
}
