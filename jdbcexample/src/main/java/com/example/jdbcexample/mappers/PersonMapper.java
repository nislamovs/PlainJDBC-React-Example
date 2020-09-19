package com.example.jdbcexample.mappers;

import com.example.jdbcexample.domain.dao.PersonDAO;
import com.example.jdbcexample.domain.dto.PersonDTO;
import com.example.jdbcexample.mappers.specialMappers.SqlDateMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = SqlDateMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface PersonMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "birthdate", target = "birthdate")
    PersonDTO toDTO(PersonDAO personDAO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "birthdate", target = "birthdate")
    PersonDAO toDAO(PersonDTO personDTO);
}
