package com.danielml.rubbyduckystore.infrastructure.exceptions;

import lombok.Data;

@Data
public class Error {
    private String code;
    private String message;
}
