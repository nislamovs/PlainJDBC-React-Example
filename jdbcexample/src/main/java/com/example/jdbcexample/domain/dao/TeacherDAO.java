package com.example.jdbcexample.domain.dao;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class TeacherDAO extends PersonDAO {

    private Long class_id;
    private Long subject_id;
    private Boolean isHead;
}
