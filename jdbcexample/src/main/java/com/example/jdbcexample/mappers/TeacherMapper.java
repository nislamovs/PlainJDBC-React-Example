package com.example.jdbcexample.mappers;

import com.example.jdbcexample.domain.dao.TeacherDAO;
import com.example.jdbcexample.domain.dto.TeacherDTO;
import com.example.jdbcexample.mappers.specialMappers.SqlDateMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = SqlDateMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE )
@Component
public interface TeacherMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "isHead", target = "isHead")
    @Mapping(source = "class_id", target = "classId")
    @Mapping(source = "subject_id", target = "subjectId")
    TeacherDTO toDTO(TeacherDAO teacherDAO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "isHead", target = "isHead")
    @Mapping(source = "classId", target = "class_id")
    @Mapping(source = "subjectId", target = "subject_id")
    TeacherDAO toDAO(TeacherDTO teacherDTO);
}
