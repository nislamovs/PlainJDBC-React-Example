package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dao.SchoolClassDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.SchoolClassDTO;
import com.example.jdbcexample.mappers.ClassMapper;
import com.example.jdbcexample.repository.ClassesRepository;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;
import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassesService {

    private final ClassesRepository classesRepository;
    private final ClassMapper mapper;

    @SneakyThrows
    public List<SchoolClassDTO> getAllClasses() {
        return classesRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public  List<SchoolClassDTO> getClassesPage(String pagenum, String pagesize, String sort, String group) {

        return classesRepository.getPage(pagenum, pagesize, sort, group).stream().map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public SchoolClassDTO getSchoolClassById(String classId) {

        return mapper.toDTO(classesRepository.findById(parseInt(classId)));
    }

    @SneakyThrows
    public AbstractDTO addNewSchoolClass(SchoolClassDTO schoolClass) {

        SchoolClassDAO newClass = classesRepository.create(mapper.toDAO(schoolClass));

        return AbstractDTO.builder()
                .id(valueOf(newClass.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO editSchoolClassData(SchoolClassDTO schoolClass) {

        SchoolClassDAO editedClass = classesRepository.update(mapper.toDAO(schoolClass));

        return AbstractDTO.builder()
                .id(valueOf(editedClass.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO deleteSchoolClass(String classId) {

        SchoolClassDAO deletedClass = classesRepository.delete(valueOf(classId));

        return AbstractDTO.builder()
                .id(valueOf(deletedClass.getId()))
                .dateTime(LocalDateTime.now()).build();
    }


}
