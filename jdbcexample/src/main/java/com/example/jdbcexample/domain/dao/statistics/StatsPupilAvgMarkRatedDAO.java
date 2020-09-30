package com.example.jdbcexample.domain.dao.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class StatsPupilAvgMarkRatedDAO {

    private Long id;

    private String subject;

    private Long pupilId;
    private String pupilFirstname;
    private String pupilLastname;
    private String pupilEmail;

    private String className;
    private String teacherFirstname;
    private String teacherLastname;
    private String teacherEmail;

    private BigDecimal avgMarkValue;

}
