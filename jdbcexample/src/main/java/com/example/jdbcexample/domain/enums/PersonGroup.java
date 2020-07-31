package com.example.jdbcexample.domain.enums;

public enum PersonGroup {

    All("ALL"),
    PARENTS("PARENTS"),
    PUPILS("PUPILS"),
    TEACHERS("TEACHERS");

    private String persGroup;

    PersonGroup(String pGroup) {
        this.persGroup = pGroup;
    }
}