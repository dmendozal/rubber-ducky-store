package com.danielml.rubbyduckystore.infrastructure.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public  ResourceNotFoundException() {super(); }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
