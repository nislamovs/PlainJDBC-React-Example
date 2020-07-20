package com.example.jdbcexample.repository;


import com.example.jdbcexample.domain.dao.PersonDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonsRepository {

    List<PersonDAO> findPersonByEmail(String email);
}
