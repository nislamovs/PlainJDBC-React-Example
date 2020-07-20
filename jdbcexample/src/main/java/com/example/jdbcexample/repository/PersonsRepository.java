package com.example.jdbcexample.repository;


import com.example.jdbcexample.domain.dao.PersonDAO;

import java.util.List;

public interface PersonsRepository {

    List<PersonDAO> findPersonByEmail(String email);
}
