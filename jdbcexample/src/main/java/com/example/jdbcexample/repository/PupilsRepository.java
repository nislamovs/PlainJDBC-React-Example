package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.PupilDAO;
import com.example.jdbcexample.repository.crudRepos.GenericRepository;
import com.example.jdbcexample.repository.crudRepos.PagingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PupilsRepository extends GenericRepository<PupilDAO>, PagingRepository<PupilDAO> {

    List<PupilDAO> getPupilsByFirstnameLastname(String firstname, String lastname);
}
