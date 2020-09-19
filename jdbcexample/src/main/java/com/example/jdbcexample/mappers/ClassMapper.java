package com.example.jdbcexample.mappers;

import com.example.jdbcexample.domain.dao.SchoolClassDAO;
import com.example.jdbcexample.domain.dto.SchoolClassDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE )
@Component
public interface ClassMapper {

    @Mapping(source = "id", target = "id")
    SchoolClassDTO toDTO(SchoolClassDAO schoolClassDAO);

    @Mapping(source = "id", target = "id")
    SchoolClassDAO toDAO(SchoolClassDTO schoolClassDTO);
}
