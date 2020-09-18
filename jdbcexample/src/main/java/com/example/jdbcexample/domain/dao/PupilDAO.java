package com.example.jdbcexample.domain.dao;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class PupilDAO extends PersonDAO {

    @Size(max = 6, min = 4)
    private String gender;

    @Positive
    private Long class_id;

    @Positive
    private Long class_head_id;
}
