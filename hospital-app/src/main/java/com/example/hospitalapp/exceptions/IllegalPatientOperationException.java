package com.example.hospitalapp.exceptions;

import org.springframework.http.HttpStatus;

public class IllegalPatientOperationException extends MedicalException {
    public IllegalPatientOperationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
