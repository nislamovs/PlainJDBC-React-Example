package com.example.jdbcexample.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDAO {

    private Long id;
    private Long teacher_id;
    private String name;
}
