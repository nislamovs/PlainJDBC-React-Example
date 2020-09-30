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
public class StatsPupilAvgMarkDAO {

    private Long id;
    private Long pupilId;
    private String pupilFirstname;
    private String pupilLastname;
    private String pupilEmail;
    private Date pupilBirthdate;
    private Integer avgMarkValue;
}
