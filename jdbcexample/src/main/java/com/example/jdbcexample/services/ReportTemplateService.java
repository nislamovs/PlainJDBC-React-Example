package com.example.jdbcexample.services;


import com.example.jdbcexample.domain.dao.ReportTemplateDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.ReportTemplateDTO;
import com.example.jdbcexample.mappers.ReportTemplateMapper;
import com.example.jdbcexample.repository.ReportTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportTemplateService {

    private final ReportTemplateMapper mapper;
    private final ReportTemplateRepository reportTemplateRepository;

    @SneakyThrows
    public List<ReportTemplateDTO> getAllTemplates() {
        return reportTemplateRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public ReportTemplateDTO getTemplateByName(String templateName) {

        return reportTemplateRepository.getTemplateByName(templateName).stream()
                .map(mapper::toDTO).collect(Collectors.toList()).get(0);
    }

    @SneakyThrows
    public ReportTemplateDTO getTemplateById(String templateId) {

        return mapper.toDTO(reportTemplateRepository.findById(parseInt(templateId)));
    }

    @SneakyThrows
    public AbstractDTO addNewTemplate(ReportTemplateDTO template) {

        ReportTemplateDAO newTemplate = reportTemplateRepository.create(mapper.toDAO(template));

        return AbstractDTO.builder()
                .id(String.valueOf(newTemplate.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO editReportTemplateData(ReportTemplateDTO template) {

        ReportTemplateDAO updatedTemplate = reportTemplateRepository.update(mapper.toDAO(template));

        return AbstractDTO.builder()
                .id(valueOf(updatedTemplate.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO deleteReportTemplate(String templateId) {
        ReportTemplateDAO deletedTemplate = reportTemplateRepository.delete(parseInt(templateId));

        return AbstractDTO.builder()
                .id(valueOf(deletedTemplate.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

}
