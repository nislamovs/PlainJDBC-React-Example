package com.example.jdbcexample.mappers;

import com.example.jdbcexample.dao.PersonDAO;
import com.example.jdbcexample.dao.SchoolClassDAO;
import com.example.jdbcexample.dto.PersonDTO;
import com.example.jdbcexample.dto.SchoolClassDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR )
@Component
public interface PersonMapper {

    @Mapping(source = "id", target = "id")
    PersonDTO toDTO(PersonDAO personDAO);

    @Mapping(source = "id", target = "id")
    PersonDAO toDAO(PersonDTO personDTO);
}
