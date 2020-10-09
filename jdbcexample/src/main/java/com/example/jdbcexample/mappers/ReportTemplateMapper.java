package com.example.jdbcexample.mappers;


import com.example.jdbcexample.domain.dao.ReportTemplateDAO;
import com.example.jdbcexample.domain.dto.ReportTemplateDTO;
import com.example.jdbcexample.mappers.specialMappers.SqlDateMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = SqlDateMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE )
@Component
public interface ReportTemplateMapper {

    @Mapping(source = "id", target = "id")
    ReportTemplateDTO toDTO(ReportTemplateDAO reportTemplateDAO);

    @Mapping(source = "id", target = "id")
    ReportTemplateDAO toDAO(ReportTemplateDTO reportTemplateDTO);
}
