package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.TeacherDAO;
import com.example.jdbcexample.repository.crudRepos.GenericRepository;
import com.example.jdbcexample.repository.crudRepos.PagingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeachersRepository extends GenericRepository<TeacherDAO>, PagingRepository<TeacherDAO> {

}
