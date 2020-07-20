package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.PupilDAO;

import java.util.List;

public interface PupilsRepository extends GenericRepository<PupilDAO> {

    List<PupilDAO> getPupilsByFirstnameLastname(String firstname, String lastname);
}
