package com.example.jdbcexample.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClassDAO {
    private Long id;
    private String type;
    private Long class_head_id;
    private String name;
}
