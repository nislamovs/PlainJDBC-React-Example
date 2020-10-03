package com.example.jdbcexample.domain.dto.statistics;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true)
@ApiObject(name = "StatsPupilFullReview DTO", description = "Statistics information", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA, group = "Statistics")
public class StatsPupilFullReviewDTO {

    private Long id;
    private String pupilName;
    private String pupilEmail;
    private String className;
    private String subject;

    private Integer minMarkValue;
    private Integer maxMarkValue;
    private BigDecimal avgMarkValue;
    private Integer totalMarks;
    private String marks;

    private String teacherName;
    private String teacherEmail;
    private String status;

}
