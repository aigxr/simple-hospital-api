package com.example.hospitalapp.exceptions;

import org.springframework.http.HttpStatus;

public class IllegalVisitOperationException extends MedicalException {
    public IllegalVisitOperationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
