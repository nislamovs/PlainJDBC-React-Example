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

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true)
@ApiObject(name = "Parent DTO", description = "Parent specific information", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA, group = "Person")
public class ParentDTO extends PersonDTO {

    @ApiObjectField(description = "Person's firstname")
    private Long classId;

    @ApiObjectField(description = "Person's firstname")
    private Long classHeadId;

}
