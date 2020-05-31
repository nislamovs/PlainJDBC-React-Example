package com.example.jdbcexample.mappers;

import com.example.jdbcexample.dao.SubjectMarkDAO;
import com.example.jdbcexample.dto.SubjectMarkDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR )
@Component
public interface MarkMapper {

    @Mapping(source = "id", target = "id")
    SubjectMarkDTO toDTO(SubjectMarkDAO subjectMarkDAO);

    @Mapping(source = "id", target = "id")
    SubjectMarkDAO toDAO(SubjectMarkDTO subjectMarkDTO);
}
