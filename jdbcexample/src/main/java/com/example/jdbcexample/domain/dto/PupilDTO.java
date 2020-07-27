package com.example.jdbcexample.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true)
@ApiObject(name = "Pupil DTO", description = "Pupil specific information", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA, group = "Person")
public class PupilDTO extends PersonDTO {

    @ApiObjectField(description = "School class id")
    private Long classId;

    @ApiObjectField(description = "School class head id (teracher id)")
    private Long classHeadId;

}
