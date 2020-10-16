package com.example.jdbcexample.repository.crudRepos;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenericRepository<T> {

    T create(T t);

    T findById(Integer id);

    T update(T t);

    T delete(Integer id);

    List<T> findAll();
}