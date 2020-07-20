package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dto.PersonDTO;
import com.example.jdbcexample.mappers.PersonMapper;
import com.example.jdbcexample.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final PersonMapper mapper;
    private final PersonsRepository personsRepository;

    @SneakyThrows
    public List<PersonDTO> findPersonByEmail(String email) {

        return personsRepository.findPersonByEmail(email).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }
}
