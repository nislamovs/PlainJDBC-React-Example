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
@ApiObject(name = "StatsPupilReview DTO", description = "Statistics information", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA, group = "Statistics")
public class StatsPupilReviewDTO {

    private Long pupilId;
    private String pupilName;
    private String pupilEmail;
    private String className;
    private String classHeadName;
    private String teacherEmail;
    private BigDecimal avgMarkValue;
    private String status;

}
