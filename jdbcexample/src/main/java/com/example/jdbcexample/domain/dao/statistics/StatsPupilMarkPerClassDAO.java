package com.example.jdbcexample.domain.dao.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class StatsPupilMarkPerClassDAO {

    private Long id;
    private String className;
    private Long pupilId;
    private String pupilFirstname;
    private String pupilLastname;
    private String pupilEmail;
    private Date pupilBirthdate;

    private String subjectname;
    private Integer markValue;
    private Date markDate;

    private String classHeadFirstname;
    private String classHeadLastname;
    private String classHeadEmail;

    private String status;
}