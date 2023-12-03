package com.example.hospitalapp.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends MedicalException {

    public UserAlreadyExistsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
