package com.example.hospitalapp.exceptions;

import org.springframework.http.HttpStatus;

public class IllegalDoctorOperationException extends MedicalException{
    public IllegalDoctorOperationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
