package com.example.jdbcexample.domain.enums;


public enum ParentType {

    MOTHER("MOTHER"),
    FATHER("FATHER"),
    GRANDFATHER("GRANDFATHER"),
    GRANDMOTHER("GRANDMOTHER"),
    GUARDIAN("GUARDIAN"),
    OTHER("OTHER");

    private String type;
    
    ParentType(String pType) {
        this.type = pType;
    }
}
