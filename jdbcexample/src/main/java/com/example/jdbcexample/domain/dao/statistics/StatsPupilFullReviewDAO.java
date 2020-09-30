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
public class StatsPupilFullReviewDAO {

    private Long id;
    private String pupilName;
    private String pupilEmail;
    private String className;
    private String subject;

    private Integer minMarkValue;
    private Integer maxMarkValue;
    private BigDecimal avgMarkValue;
    private Integer totalMarks;
    private String marks;

    private String teacherName;
    private String teacherEmail;
    private String status;

}
