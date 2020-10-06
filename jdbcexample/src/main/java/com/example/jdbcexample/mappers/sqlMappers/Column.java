package com.example.jdbcexample.mappers.sqlMappers;


import java.lang.annotation.*;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Column {

    String name();
}
