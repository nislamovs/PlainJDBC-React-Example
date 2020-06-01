package com.example.jdbcexample.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class PersonDAO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthdate;
}
