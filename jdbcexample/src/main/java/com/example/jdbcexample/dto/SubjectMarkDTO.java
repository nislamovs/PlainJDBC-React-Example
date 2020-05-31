package com.example.jdbcexample.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectMarkDTO extends AbstractDTO {

    private String subject_id;
    private String pupil_id;
    private Date date;
    private Integer value;
}
