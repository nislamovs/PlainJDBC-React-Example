package com.example.jdbcexample.mappers;

import com.example.jdbcexample.domain.dao.PupilDAO;
import com.example.jdbcexample.domain.dto.PupilDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR )
@Component
public interface PupilMapper {

    @Mapping(source = "id", target = "id")
    PupilDTO toDTO(PupilDAO pupilDAO);

    @Mapping(source = "id", target = "id")
    PupilDAO toDAO(PupilDTO pupilDTO);
}
