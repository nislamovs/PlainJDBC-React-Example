package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.SubjectMarkDAO;

import java.util.List;

public interface MarksRepository extends GenericRepository<SubjectMarkDAO> {

    List<SubjectMarkDAO> retrieveMarksByPupilId(String pupilId);

    List<SubjectMarkDAO> getMarksByDateInterval(String startDate, String endDate);
}
