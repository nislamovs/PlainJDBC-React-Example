package com.example.jdbcexample.mappers;

import com.example.jdbcexample.domain.dao.SubjectDAO;
import com.example.jdbcexample.domain.dto.SubjectDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR )
@Component
public interface SubjectMapper {

    @Mapping(source = "id", target = "id")
    SubjectDTO toDTO(SubjectDAO subjectDAO);

    @Mapping(source = "id", target = "id")
    SubjectDAO toDAO(SubjectDTO subjectDTO);
}
