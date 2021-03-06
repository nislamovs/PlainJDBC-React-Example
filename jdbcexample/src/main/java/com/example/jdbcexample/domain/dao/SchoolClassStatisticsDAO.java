package com.example.jdbcexample.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClassStatisticsDAO {
    private Long id;
    private String type;
    private Long class_head_id;
    private String name;
}
