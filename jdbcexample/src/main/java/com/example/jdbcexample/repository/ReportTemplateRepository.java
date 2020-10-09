package com.example.jdbcexample.repository;

import com.example.jdbcexample.domain.dao.ReportTemplateDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportTemplateRepository extends GenericRepository<ReportTemplateDAO> {

    List<ReportTemplateDAO> getTemplateByName(String templateName);
}
