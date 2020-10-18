package com.example.jdbcexample.domain.dao.statistics;

import com.example.jdbcexample.mappers.sqlMappers.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class StatsPupilMarkPerClassDAO {

    @Column(name = "id")
    private Long id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "pupil_id")
    private Long pupilId;

    @Column(name = "pupil_firstname")
    private String pupilFirstname;

    @Column(name = "pupil_lastname")
    private String pupilLastname;

    @Column(name = "pupil_email")
    private String pupilEmail;

    @Column(name = "pupil_birthdate")
    private Date pupilBirthdate;

    @Column(name = "subject_name")
    private String subjectname;

    @Column(name = "mark_value")
    private Integer markValue;

    @Column(name = "mark_date")
    private Date markDate;

    @Column(name = "class_head_firstname")
    private String classHeadFirstname;

    @Column(name = "class_head_lastname")
    private String classHeadLastname;

    @Column(name = "class_head_email")
    private String classHeadEmail;

    @Column(name = "status")
    private String status;
}