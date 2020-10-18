package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.ReportTemplateDAO;
import com.example.jdbcexample.repository.crudRepos.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportTemplateRepository extends GenericRepository<ReportTemplateDAO> {

    ReportTemplateDAO getTemplateByName(String templateName);
}
