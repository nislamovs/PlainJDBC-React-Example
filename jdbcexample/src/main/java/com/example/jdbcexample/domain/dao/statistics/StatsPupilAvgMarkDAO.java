package com.example.jdbcexample.domain.dao.statistics;

import com.example.jdbcexample.mappers.sqlMappers.Column;
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
public class StatsPupilAvgMarkDAO {

    @Column(name = "id")
    private Long id;

    @Column(name = "pupil_id")
    private Long pupilId;

    @Column(name = "pupil_firstname")
    private String pupilFirstname;

    @Column(name = "pupil_lastname")
    private String pupilLastname;

    @Column(name = "pupil_email")
    private String pupilEmail;

    @Column(name = "average_mark")
    private BigDecimal avgMarkValue;
}
