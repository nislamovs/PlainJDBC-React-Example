package com.example.jdbcexample.mappers;

import com.example.jdbcexample.domain.dao.PersonDAO;
import com.example.jdbcexample.domain.dto.PersonDTO;
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
