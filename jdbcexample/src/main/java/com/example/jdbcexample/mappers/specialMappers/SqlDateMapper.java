package com.example.jdbcexample.mappers.specialMappers;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class SqlDateMapper {

    public java.util.Date asDate(LocalDate date) {
        return date == null ? null
                            : java.util.Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public LocalDate asLocalDate(java.util.Date date) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else {
            return LocalDate.from(date.toInstant());
        }
    }
}
