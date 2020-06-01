package com.example.jdbcexample.exceptions;

public class PupilNotFoundException extends RuntimeException {

    public PupilNotFoundException(String msg) {
        super(msg);
    }

    public PupilNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}