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
@ApiObject(name = "Teacher DTO", description = "Teacher specific information", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA, group = "Person")
public class TeacherDTO extends PersonDTO {

    @ApiObjectField(description = "Supervised class id")
    private Long classId;

    @ApiObjectField(description = "Taught subject id")
    private Long subjectId;

    @ApiObjectField(description = "Is or isn't supervisor")
    private Boolean isHead;
}
