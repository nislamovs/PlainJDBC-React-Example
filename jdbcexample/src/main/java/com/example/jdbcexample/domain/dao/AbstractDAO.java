package com.example.jdbcexample.domain.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ApiObject(name = "Abstract DAO", description = "Generic db record information", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA)
public class AbstractDAO {

    @ApiObjectField(description = "Table record id")
    private Long id;

    @ApiObjectField(description = "Record/data status")
    private String status;

    @ApiObjectField(description = "Changes date/time")
    private LocalDateTime dateTime;

    @ApiObjectField(description = "Record creation date/time")
    private Date createdAt;

    @ApiObjectField(description = "Who created record")
    private String createdBy;

    @ApiObjectField(description = "Record modification date/time")
    private Date modifiedAt;

    @ApiObjectField(description = "Who modified record")
    private String modifiedBy;
}
