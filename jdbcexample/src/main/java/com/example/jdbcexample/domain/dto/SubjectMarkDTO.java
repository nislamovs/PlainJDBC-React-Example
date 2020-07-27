package com.example.jdbcexample.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiObject(name = "Subject mark DTO", description = "Information about subject mark", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA, group = "Statistics")
public class SubjectMarkDTO extends AbstractDTO {

    @ApiObjectField(description = "Subject's ID")
    private String subjectId;

    @ApiObjectField(description = "Pupil's ID")
    private String pupilId;

    @ApiObjectField(description = "Mark's date")
    private LocalDate date;

    @ApiObjectField(description = "Mark itself [value]")
    private Integer value;
}
