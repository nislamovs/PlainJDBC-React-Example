package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dao.ParentDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.ParentDTO;
import com.example.jdbcexample.mappers.ParentMapper;
import com.example.jdbcexample.mappers.PersonMapper;
import com.example.jdbcexample.repository.ParentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParentsService {

    private final ParentsRepository parentsRepository;
    private final ParentMapper mapper;

    @SneakyThrows
    public List<ParentDTO> getAllParents() {
        return parentsRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<ParentDTO> getParentsByFirstnameLastname(String firstname, String lastname) {

        return parentsRepository.getParentsByFirstnameLastname(firstname, lastname).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<ParentDTO> getParentsPage(String pagenum, String pagesize, String sort, String group) {

        return parentsRepository.getPage(pagenum, pagesize, sort, group).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public ParentDTO getParentById(String parentId) {

        return mapper.toDTO(parentsRepository.findById(parseInt(parentId)));
    }

    @SneakyThrows
    public AbstractDTO addNewParent(ParentDTO parent) {

        ParentDAO newParent = parentsRepository.create(mapper.toDAO(parent));

        return AbstractDTO.builder()
                .id(String.valueOf(newParent.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO editParentData(ParentDTO parent) {

        ParentDAO updatedParent = parentsRepository.update(mapper.toDAO(parent));

        return AbstractDTO.builder()
                .id(valueOf(updatedParent.getId()))
                .dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO deleteParent(String parentId) {
        ParentDAO deletedParent = parentsRepository.delete(parseInt(parentId));

        return AbstractDTO.builder()
                .id(valueOf(deletedParent.getId()))
                .dateTime(LocalDateTime.now()).build();
    }
}
