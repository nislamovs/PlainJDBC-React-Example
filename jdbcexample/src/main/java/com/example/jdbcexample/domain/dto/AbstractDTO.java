package com.example.jdbcexample.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;

import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true)
@ApiObject(name = "Abstract DTO", description = "Response information", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
public class AbstractDTO {

    @ApiObjectField(description = "Table record id")
    private String id;

    @ApiObjectField(description = "Record/data status")
    private String status;

    @ApiObjectField(description = "Changes date/time")
    private LocalDateTime dateTime;

    @ApiObjectField(description = "Record creation date/time")
    private LocalDate createdAt;

    @ApiObjectField(description = "Who created record")
    private String createdBy;

    @ApiObjectField(description = "Record modification date/time")
    private LocalDate modifiedAt;

    @ApiObjectField(description = "Who modified record")
    private String modifiedBy;
}
