package com.example.jdbcexample.domain.dao.statistics;

import com.example.jdbcexample.mappers.sqlMappers.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class StatsPupilAvgMarkRatedDAO extends AbstractStatsDAO {

    @Column(name = "id")
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "pupil_id")
    private Long pupilId;

    @Column(name = "pupil_firstname")
    private String pupilFirstname;

    @Column(name = "pupil_lastname")
    private String pupilLastname;

    @Column(name = "pupil_email")
    private String pupilEmail;

    @Column(name = "class_name")
    private String className;

    @Column(name = "class_head_teacher_firstname")
    private String teacherFirstname;

    @Column(name = "class_head_teacher_lastname")
    private String teacherLastname;

    @Column(name = "class_head_teacher_email")
    private String teacherEmail;

    @Column(name = "average_mark")
    private BigDecimal avgMarkValue;

}
