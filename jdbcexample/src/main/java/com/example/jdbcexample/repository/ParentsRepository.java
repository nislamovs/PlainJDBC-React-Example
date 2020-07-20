package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.ParentDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentsRepository extends GenericRepository<ParentDAO> {

    List<ParentDAO> getParentsByFirstnameLastname(String firstname, String lastname);
}
