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
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true)
@ApiObject(name = "StatsPupilAvgMark DTO", description = "Statistics information", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA, group = "Statistics")
public class StatsPupilAvgMarkDTO {

    private Long id;
    private Long pupilId;
    private String pupilFirstname;
    private String pupilLastname;
    private String pupilEmail;
    private LocalDate pupilBirthdate;
    private Integer avgMarkValue;
}
