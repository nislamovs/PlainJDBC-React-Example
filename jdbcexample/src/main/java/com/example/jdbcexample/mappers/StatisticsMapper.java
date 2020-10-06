package com.example.jdbcexample.mappers;

import com.example.jdbcexample.domain.dao.statistics.*;
import com.example.jdbcexample.domain.dto.statistics.*;
import com.example.jdbcexample.mappers.specialMappers.SqlDateMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = SqlDateMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE )
@Component
public interface StatisticsMapper {

    @Mapping(source = "id", target = "id")
    StatsKidsParentsDTO toDTO(StatsKidsParentsDAO statsKidsParentsDAO);

    @Mapping(source = "id", target = "id")
    StatsPupilAvgMarkDTO toDTO(StatsPupilAvgMarkDAO statsPupilAvgMarkDAO);

    @Mapping(source = "id", target = "id")
    StatsPupilAvgMarkRatedDTO toDTO(StatsPupilAvgMarkRatedDAO statsPupilAvgMarkRatedDAO);

    @Mapping(source = "id", target = "id")
    StatsPupilFullReviewDTO toDTO(StatsPupilFullReviewDAO statsPupilFullReviewDAO);

    StatsPupilMarkPerClassDTO toDTO(StatsPupilMarkPerClassDAO statsPupilMarkPerClassDAO);

    StatsPupilReviewDTO toDTO(StatsPupilReviewDAO statsPupilReviewDAO);

    @Mapping(source = "id", target = "id")
    StatsPupilsRelativesDTO toDTO(StatsPupilsRelativesDAO statsPupilsRelativesDAO);
}
