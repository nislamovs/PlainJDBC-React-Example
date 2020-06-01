package com.example.jdbcexample.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiObject
public class SubjectMarkDTO extends AbstractDTO {

    @ApiObjectField(description = "Subject's ID")
    private String subject_id;

    @ApiObjectField(description = "Pupil's ID")
    private String pupil_id;

    @ApiObjectField(description = "Mark's date")
    private LocalDate date;

    @ApiObjectField(description = "Mark [value]")
    private Integer value;
}
