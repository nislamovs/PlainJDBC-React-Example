package com.example.jdbcexample.mappers;

import com.example.jdbcexample.dao.SchoolClassDAO;
import com.example.jdbcexample.dto.SchoolClassDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR )
@Component
public interface ClassMapper {

    @Mapping(source = "id", target = "id")
    SchoolClassDTO toDTO(SchoolClassDAO schoolClassDAO);

    @Mapping(source = "id", target = "id")
    SchoolClassDAO toDAO(SchoolClassDTO schoolClassDTO);
}
