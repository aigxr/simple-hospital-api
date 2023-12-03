package com.example.hospitalapp.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends MedicalException{
    public InvalidPasswordException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
