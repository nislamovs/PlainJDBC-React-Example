package com.example.jdbcexample.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import static java.lang.String.format;

@AllArgsConstructor
@Data
public class ErrorResponse {

    public String errorText;

    public ErrorResponse(String format, Object... args) {
        this(format(format, args));
    }
}
