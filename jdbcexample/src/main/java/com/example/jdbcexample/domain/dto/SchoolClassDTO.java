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
@ApiObject(name = "School class DTO", description = "Information about school class", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA, group = "School")
public class SchoolClassDTO extends AbstractDTO {

    @ApiObjectField(description = "School class head id (teacher id)")
    private Long classHeadId;

    @ApiObjectField(description = "Class type (mathematical, linguistic, etc.)")
    private String type;

    @ApiObjectField(description = "Class name")
    private String name;
}
