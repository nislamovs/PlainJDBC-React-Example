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
public class StatsPupilReviewDAO {

    private Long pupilId;
    private String pupilName;
    private String pupilEmail;
    private String className;
    private String classHeadName;
    private String teacherEmail;
    private BigDecimal avgMarkValue;
    private String status;

}
