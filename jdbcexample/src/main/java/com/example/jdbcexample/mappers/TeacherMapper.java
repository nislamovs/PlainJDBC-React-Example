package com.example.jdbcexample.mappers;

import com.example.jdbcexample.dao.TeacherDAO;
import com.example.jdbcexample.dto.TeacherDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR )
@Component
public interface TeacherMapper {

    @Mapping(source = "id", target = "id")
    TeacherDTO toDTO(TeacherDAO teacherDAO);

    @Mapping(source = "id", target = "id")
    TeacherDAO toDAO(TeacherDTO teacherDTO);
}
