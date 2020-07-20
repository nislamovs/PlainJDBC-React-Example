package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dao.SubjectMarkDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.SubjectMarkDTO;
import com.example.jdbcexample.mappers.MarkMapper;
import com.example.jdbcexample.repository.MarksRepository;
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

import static java.lang.Integer.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;
import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarksService {

    private final MarksRepository marksRepository;
    private final MarkMapper mapper;

    @SneakyThrows
    public List<SubjectMarkDTO> getAllMarks() {

        List<SubjectMarkDAO> allMarks = marksRepository.findAll();

        return allMarks.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<SubjectMarkDTO> retrieveMarksPage(String pagenum, String pagesize, String sort, String group) {

        List<SubjectMarkDAO> marksPage = marksRepository.getPage(pagenum, pagesize, sort, group);

        return marksPage.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<SubjectMarkDTO> retrieveMarksByPupilId(String pupilId) {

        List<SubjectMarkDAO> marks = marksRepository.retrieveMarksByPupilId(pupilId);

        return marks.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<SubjectMarkDTO> getMarksByDateInterval(String startDate, String endDate) {

        List<SubjectMarkDAO> marksList = marksRepository.getMarksByDateInterval(startDate, endDate);

        return marksList.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public AbstractDTO addNewMark(SubjectMarkDTO mark) {

        SubjectMarkDAO newMark = marksRepository.create(mapper.toDAO(mark));

        return AbstractDTO.builder().id(valueOf(newMark.getId())).dateTime(now()).build();
    }

    @SneakyThrows
    public AbstractDTO editMark(SubjectMarkDTO mark) {

        SubjectMarkDAO updatedMark = marksRepository.update(mapper.toDAO(mark));

        return AbstractDTO.builder().id(valueOf(updatedMark.getId())).dateTime(now()).build();
    }

    @SneakyThrows
    public AbstractDTO deleteMark(String markId) {

        SubjectMarkDAO deletedMark =  marksRepository.delete(parseInt(markId));

        return AbstractDTO.builder().id(valueOf(deletedMark.getId())).dateTime(now()).build();
    }

}
