package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dao.TeacherDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.TeacherDTO;
import com.example.jdbcexample.mappers.TeacherMapper;
import com.example.jdbcexample.repository.TeachersRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.valueOf;
import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeachersService {

    private final TeacherMapper mapper;
    private final TeachersRepository teachersRepository;

    @SneakyThrows
    public List<TeacherDTO> getAllTeachers() {

        return teachersRepository.findAll().stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<TeacherDTO> getTeachersPage(String pagenum, String pagesize, String sort, String group) {

        return teachersRepository.getPage(pagenum, pagesize, sort, group).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public TeacherDTO getTeacherById(String teacherId) {

        return mapper.toDTO(teachersRepository.findById(valueOf(teacherId)));
    }

    @SneakyThrows
    public AbstractDTO addNewTeacher(TeacherDTO newTeacher) {

        TeacherDAO createdTeacher = teachersRepository.create(mapper.toDAO(newTeacher));

        return AbstractDTO.builder()
                .id(valueOf(createdTeacher.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO editTeacherData(TeacherDTO teacher) {

        TeacherDAO updatedTeacher = teachersRepository.create(mapper.toDAO(teacher));

        return AbstractDTO.builder()
                .id(valueOf(updatedTeacher.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO deleteTeacher(String teacherId) {

        teachersRepository.delete(valueOf(teacherId));

        return AbstractDTO.builder().dateTime(LocalDateTime.now()).build();
    }

}
