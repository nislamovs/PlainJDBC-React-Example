package com.example.jdbcexample.domain.dao;


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
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ApiObject(name = "Abstract DAO", description = "Generic db record information", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
public class AbstractDTO {

    @ApiObjectField(description = "Table record id")
    private String id;

    @ApiObjectField(description = "Record/data status")
    private String status;

    @FutureOrPresent
    @ApiObjectField(description = "Changes date/time")
    private LocalDateTime dateTime;

    @ApiObjectField(description = "Record creation date/time")
    private LocalDateTime createdAt;

    @ApiObjectField(description = "Who created record")
    private String createdBy;

    @ApiObjectField(description = "Record modification date/time")
    private LocalDateTime modifiedAt;

    @ApiObjectField(description = "Who modified record")
    private String modifiedBy;
}
