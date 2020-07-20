package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.PupilDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PupilsRepository extends GenericRepository<PupilDAO> {

    List<PupilDAO> getPupilsByFirstnameLastname(String firstname, String lastname);
}
