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
public class StatsKidsParentsDAO extends AbstractStatsDAO {

    @Column(name = "id")
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_firstname")
    private String parentFirstname;

    @Column(name = "parent_lastname")
    private String parentLastname;

    @Column(name = "familyId")
    private String familyId;

    @Column(name = "pupil_firstname")
    private String pupilFirstname;

    @Column(name = "pupil_lastname")
    private String pupilLastname;

    @Column(name = "pupil_birthdate")
    private String pupilBirthdate;

    @Column(name = "pupil_gender")
    private String pupilGender;
}
