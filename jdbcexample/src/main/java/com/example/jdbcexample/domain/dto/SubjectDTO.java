package com.example.jdbcexample.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiObject(name = "Subject DTO", description = "Information about subject", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA, group = "School")
public class SubjectDTO extends AbstractDTO {

    @ApiObjectField(description = "Taught by (teacher id)")
    private Long teacherId;

    @ApiObjectField(description = "Subject name")
    private String name;
}

