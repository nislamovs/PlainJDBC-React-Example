package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.SubjectMarkDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends GenericRepository<SubjectMarkDAO> {

    List<SubjectMarkDAO> retrieveMarksByPupilId(String pupilId);

    List<SubjectMarkDAO> getMarksByDateInterval(String startDate, String endDate);
}
