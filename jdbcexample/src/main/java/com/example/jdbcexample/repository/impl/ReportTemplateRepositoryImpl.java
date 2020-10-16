package com.example.jdbcexample.repository.impl;

import com.example.jdbcexample.domain.dao.ReportTemplateDAO;
import com.example.jdbcexample.repository.ReportTemplateRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class ReportTemplateRepositoryImpl implements ReportTemplateRepository {

    private final DataSource dataSource;

    private final String REPORT_TEMPLATES_RETRIEVAL_QUERY = "SELECT * FROM reportTemplates";
    private final String REPORT_TEMPLATES_GET_BY_NAME_RETRIEVAL_QUERY = "SELECT * FROM reportTemplates WHERE name ilike ?";
    private final String REPORT_TEMPLATES_GET_BY_ID_RETRIEVAL_QUERY = "SELECT * FROM reportTemplates WHERE id = ?";
    private final String REPORT_TEMPLATES_NEW_TEMPLATE_ADD_QUERY = "INSERT INTO reportTemplates(id, description, templateName, filename, template) VALUES (?, ?, ?, ?, ?)";
    private final String REPORT_TEMPLATES_EXISTING_TEMPLATE_UPDATE_QUERY = "UPDATE reportTemplates SET(id = ?, description = ?, templateName = ?, filename = ?, template = ?)";
    private final String REPORT_TEMPLATES_EXISTING_TEMPLATE_DELETE_QUERY = "DELETE FROM reportTemplates where id = ?";

    @Override
    @SneakyThrows
    public List<ReportTemplateDAO> getTemplateByName(String templateName) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(REPORT_TEMPLATES_GET_BY_NAME_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setString(i++, templateName);

        System.out.println(">>>   " + stmt.toString());

        return executeQuery(stmt);
    }

    @Override
    @SneakyThrows
    public ReportTemplateDAO create(ReportTemplateDAO reportTemplateDAO) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(REPORT_TEMPLATES_NEW_TEMPLATE_ADD_QUERY);

        int i = 1;
        stmt.setRowId(i++, null);
        stmt.setString(i++, reportTemplateDAO.getDescription());
        stmt.setString(i++, reportTemplateDAO.getTemplateName());
        stmt.setString(i++, reportTemplateDAO.getFilename());
        stmt.setBytes(i++, reportTemplateDAO.getTemplate());

        System.out.println(">>>   " + stmt.toString());

        Long templateId = executeInsert(stmt);

        return ReportTemplateDAO.builder().id(templateId).build();
    }

    @Override
    @SneakyThrows
    public ReportTemplateDAO findById(Integer id) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(REPORT_TEMPLATES_GET_BY_ID_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setLong(i++, id);

        System.out.println(">>>   " + stmt.toString());

        return executeQuery(stmt).get(0);
    }

    @Override
    @SneakyThrows
    public ReportTemplateDAO update(ReportTemplateDAO reportTemplateDAO) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(REPORT_TEMPLATES_EXISTING_TEMPLATE_UPDATE_QUERY);

        int i = 1;
        stmt.setLong(i++, reportTemplateDAO.getId());
        stmt.setString(i++, reportTemplateDAO.getDescription());
        stmt.setString(i++, reportTemplateDAO.getTemplateName());
        stmt.setString(i++, reportTemplateDAO.getFilename());
        stmt.setBytes(i++, reportTemplateDAO.getTemplate());

        System.out.println(">>>   " + stmt.toString());

        Long templateId = executeUpdate(stmt);

        return ReportTemplateDAO.builder().id(templateId).build();
    }

    @Override
    @SneakyThrows
    public ReportTemplateDAO delete(Integer templateId) {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(REPORT_TEMPLATES_EXISTING_TEMPLATE_DELETE_QUERY);

        int i = 1;
        stmt.setLong(i++, templateId);
        executeQuery(stmt);

        System.out.println(">>>   " + stmt.toString());

        return ReportTemplateDAO.builder().id(Long.valueOf(templateId)).build();
    }

    @Override
    @SneakyThrows
    public List<ReportTemplateDAO> findAll() {
        @Cleanup Connection conn = dataSource.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(REPORT_TEMPLATES_RETRIEVAL_QUERY);

        System.out.println(">>>   " + stmt.toString());

        return executeQuery(stmt);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private List<ReportTemplateDAO> executeQuery(PreparedStatement stmt) {
        List<ReportTemplateDAO> templates = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ReportTemplateDAO template = ReportTemplateDAO.builder()
                    .id(rs.getLong("id"))
                    .description(rs.getString("description"))
                    .templateName(rs.getString("templateName"))
                    .filename(rs.getString("filename"))
                    .template(rs.getBytes("template"))
                    .build();

                templates.add(template);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return templates;
    }

    private Long executeUpdate(PreparedStatement stmt) {
        return executeInsert(stmt);
    }

    private Long executeInsert(PreparedStatement stmt) {

        long retVal = 0L;

        try {
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Adding new report template failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    retVal = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Adding new report template failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return retVal;
    }

}
