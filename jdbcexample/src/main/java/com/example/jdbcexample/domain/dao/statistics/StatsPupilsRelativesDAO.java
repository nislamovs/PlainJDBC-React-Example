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
public class StatsPupilsRelativesDAO {

    private Long id;

    private Long parentId;
    private String parentFirstname;
    private String parentLastname;
    private String familyId;
    private String pupilFirstname;
    private String pupilLastname;
    private Date pupilBirthdate;
    private String pupilGender;
}
