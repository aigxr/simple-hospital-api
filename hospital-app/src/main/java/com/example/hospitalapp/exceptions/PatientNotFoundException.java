package com.example.hospitalapp.exceptions;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException() {
        super("Patient was not found");
    }
}
