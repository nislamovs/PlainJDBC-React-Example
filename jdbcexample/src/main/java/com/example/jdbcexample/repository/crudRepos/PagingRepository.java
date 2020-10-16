package com.example.jdbcexample.repository.crudRepos;

import com.example.jdbcexample.domain.dao.ParentDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagingRepository<T> {

    List<T> getPage(String pagenum, String pagesize, String sort, String group);
}