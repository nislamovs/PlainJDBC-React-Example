package com.example.jdbcexample.mappers;

import com.example.jdbcexample.domain.dao.ParentDAO;
import com.example.jdbcexample.domain.dao.PersonDAO;
import com.example.jdbcexample.domain.dto.ParentDTO;
import com.example.jdbcexample.domain.dto.PersonDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR )
@Component
public interface ParentMapper {

    @Mapping(source = "id", target = "id")
    ParentDTO toDTO(ParentDAO parentDAO);

    @Mapping(source = "id", target = "id")
    ParentDAO toDAO(ParentDTO parentDTO);
}
