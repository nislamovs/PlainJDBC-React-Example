package com.example.jdbcexample.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenericRepository<T> {

    public T create(T t);

    public T findById(Integer id);

    public T update(T t);

    public T delete(Integer id);

    public List<T> findAll();

    public List<T> getPage(String pagenum, String pagesize, String sort, String group);
}