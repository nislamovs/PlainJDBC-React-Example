package com.example.jdbcexample.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectMarkDAO {

    private Long id;
    private Long subjectId;
    private Long pupilId;
    private Date date;
    private Integer value;
}
