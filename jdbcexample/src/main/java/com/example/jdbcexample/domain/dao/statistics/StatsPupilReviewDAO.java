package com.example.jdbcexample.domain.dao.statistics;

import com.example.jdbcexample.mappers.sqlMappers.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class StatsPupilReviewDAO {

    @Column(name = "pupil_id")
    private Long pupilId;

    @Column(name = "pupil_name")
    private String pupilName;

    @Column(name = "pupil_email")
    private String pupilEmail;

    @Column(name = "class_name")
    private String className;

    @Column(name = "class_head_name")
    private String classHeadName;

    @Column(name = "teacher_email")
    private String teacherEmail;

    @Column(name = "average_mark")
    private BigDecimal avgMarkValue;

    @Column(name = "status")
    private String status;

}
