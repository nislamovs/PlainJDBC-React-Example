package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.SubjectMarkDAO;
import com.example.jdbcexample.repository.crudRepos.GenericRepository;
import com.example.jdbcexample.repository.crudRepos.PagingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends GenericRepository<SubjectMarkDAO>, PagingRepository<SubjectMarkDAO> {

    List<SubjectMarkDAO> retrieveMarksByPupilId(String pupilId);

    List<SubjectMarkDAO> getMarksByDateInterval(String startDate, String endDate);
}
