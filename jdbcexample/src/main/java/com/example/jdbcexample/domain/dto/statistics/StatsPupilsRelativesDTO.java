package com.example.jdbcexample.domain.dto.statistics;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true)
@ApiObject(name = "StatsPupilsRelatives DTO", description = "Statistics information", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA, group = "Statistics")
public class StatsPupilsRelativesDTO {

    private Long id;

    private Long parentId;
    private String parentFirstname;
    private String parentLastname;
    private String familyId;
    private String pupilFirstname;
    private String pupilLastname;
    private LocalDate pupilBirthdate;
    private String pupilGender;
}
