package com.example.jdbcexample.mappers;

import com.example.jdbcexample.domain.dao.PupilDAO;
import com.example.jdbcexample.domain.dto.PupilDTO;
import com.example.jdbcexample.mappers.specialMappers.SqlDateMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = SqlDateMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE )
@Component
public interface PupilMapper {

    @Mapping(source = "id", target = "id")
    PupilDTO toDTO(PupilDAO pupilDAO);

    @Mapping(source = "id", target = "id")
    PupilDAO toDAO(PupilDTO pupilDTO);
}
