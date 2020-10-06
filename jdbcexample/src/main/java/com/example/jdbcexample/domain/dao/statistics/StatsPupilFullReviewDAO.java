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
public class StatsPupilFullReviewDAO {

    @Column(name = "id")
    private Long id;

    @Column(name = "pupil_name")
    private String pupilName;

    @Column(name = "pupil_email")
    private String pupilEmail;

    @Column(name = "class_name")
    private String className;

    @Column(name = "subject")
    private String subject;

    @Column(name = "Mark_MIN")
    private Integer minMarkValue;

    @Column(name = "Mark_MAX")
    private Integer maxMarkValue;

    @Column(name = "Mark_AVG")
    private BigDecimal avgMarkValue;

    @Column(name = "Total_marks")
    private Integer totalMarks;

    @Column(name = "Marks")
    private String marks;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "teacher_email")
    private String teacherEmail;

    @Column(name = "status")
    private String status;

}
