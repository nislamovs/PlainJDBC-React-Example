package com.example.jdbcexample.repository;

import java.util.List;

public interface GenericRepository<T> {

    public T create(T t);

    public T findById(Integer id);

    public T update(T t);

    public T delete(Integer id);

    public List<T> findAll();

    public List<T> getPage(String pagenum, String pagesize, String sort, String group);
}