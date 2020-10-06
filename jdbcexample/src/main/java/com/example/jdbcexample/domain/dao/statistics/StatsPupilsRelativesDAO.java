package com.example.jdbcexample.domain.dao.statistics;

import com.example.jdbcexample.mappers.sqlMappers.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class StatsPupilsRelativesDAO {

    @Column(name = "id")
    private Long id;

    @Column(name = "familyId")
    private String familyId;

    @Column(name = "id1")
    private Long pupilId;

    @Column(name = "firstname1")
    private String pupilFirstname;

    @Column(name = "lastname1")
    private String pupilLastname;

    @Column(name = "email1")
    private String pupilEmail;

    @Column(name = "id2")
    private Long pupilRelativeId;

    @Column(name = "firstname2")
    private String pupilRelativeFirstname;

    @Column(name = "lastname2")
    private String pupilRelativeLastname;

    @Column(name = "email2")
    private String pupilRelativeEmail;
}
