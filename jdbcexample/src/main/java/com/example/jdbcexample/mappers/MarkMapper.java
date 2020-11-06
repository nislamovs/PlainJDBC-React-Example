package com.example.jdbcexample.mappers;

import com.example.jdbcexample.domain.dao.SubjectMarkDAO;
import com.example.jdbcexample.domain.dto.SubjectMarkDTO;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Mapper( componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE )
@Component
public interface MarkMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "date", target = "date", qualifiedByName = "DateToLocalDate")
    SubjectMarkDTO toDTO(SubjectMarkDAO subjectMarkDAO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "date", target = "date", qualifiedByName = "LocalDateToDate")
    SubjectMarkDAO toDAO(SubjectMarkDTO subjectMarkDTO);

    @Named("DateToLocalDate")
    default LocalDate extractBigDecimalFromString(Date value) {
        return Instant.ofEpochMilli(value.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Named("LocalDateToDate")
    default Date extractStringFromBigDecimal(LocalDate value) {
        return Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
