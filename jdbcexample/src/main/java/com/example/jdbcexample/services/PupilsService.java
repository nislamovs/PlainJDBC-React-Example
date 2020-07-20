package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dao.PupilDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.PupilDTO;
import com.example.jdbcexample.mappers.PupilMapper;
import com.example.jdbcexample.repository.PupilsRepository;
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
import static java.lang.String.valueOf;
import static java.lang.Long.parseLong;

@Service
@RequiredArgsConstructor
@Slf4j
public class PupilsService {

    private final PupilMapper mapper;
    private final PupilsRepository pupilsRepository;


    @SneakyThrows
    public List<PupilDTO> getAllPupils() {
        return pupilsRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<PupilDTO> getPupilsByFirstnameLastname(String firstname, String lastname) {

        return pupilsRepository.getPupilsByFirstnameLastname(firstname, lastname).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public  List<PupilDTO> getPupilsPage(String pagenum, String pagesize, String sort, String group) {

        return pupilsRepository.getPage(pagenum, pagesize, sort, group).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public PupilDTO getPupilById(String pupilId) {

        return mapper.toDTO(pupilsRepository.findById(parseInt(pupilId)));
    }

    @SneakyThrows
    public AbstractDTO addNewPupil(PupilDTO pupil) {

        PupilDAO newPupil = pupilsRepository.create(mapper.toDAO(pupil));

        return AbstractDTO.builder()
                .id(String.valueOf(newPupil.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO editPupilData(PupilDTO pupil) {

        PupilDAO updatedPupil = pupilsRepository.update(mapper.toDAO(pupil));

        return AbstractDTO.builder()
                .id(valueOf(updatedPupil.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO deletePupil(String pupilId) {
        PupilDAO deletedPupil = pupilsRepository.delete(parseInt(pupilId));

        return AbstractDTO.builder()
                .id(valueOf(deletedPupil.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

}
