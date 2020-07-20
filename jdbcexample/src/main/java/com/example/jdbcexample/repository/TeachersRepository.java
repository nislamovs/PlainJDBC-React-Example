package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.TeacherDAO;
import org.springframework.stereotype.Repository;


@Repository
public interface TeachersRepository extends GenericRepository<TeacherDAO> {



}
