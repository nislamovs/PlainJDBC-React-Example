package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dao.SubjectDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.SubjectDTO;
import com.example.jdbcexample.mappers.SubjectMapper;
import com.example.jdbcexample.repository.SubjectsRepository;
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
import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectsService {

    private final SubjectMapper mapper;
    private final SubjectsRepository subjectsRepository;

    @SneakyThrows
    public List<SubjectDTO> getAllSubjects() {

        return subjectsRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public  List<SubjectDTO> getSubjectsPage(String pagenum, String pagesize, String sort, String group) {

        return subjectsRepository.getPage(pagenum, pagesize, sort, group).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public SubjectDTO getSubjectById(String subjectId) {

        return mapper.toDTO(subjectsRepository.findById(parseInt(subjectId)));
    }

    @SneakyThrows
    public AbstractDTO addNewSubject(SubjectDTO subject) {

        SubjectDAO newSubject = subjectsRepository.create(mapper.toDAO(subject));

        return AbstractDTO.builder()
                .id(valueOf(newSubject.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO editSubjectData(SubjectDTO subject) {

        SubjectDAO updatedSubject = subjectsRepository.update(mapper.toDAO(subject));

        return AbstractDTO.builder()
                .id(valueOf(updatedSubject.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO deleteSubject(String subjectId) {

        SubjectDAO deletedSubject = subjectsRepository.delete(parseInt(subjectId));

        return AbstractDTO.builder()
                .id(valueOf(deletedSubject.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

}
