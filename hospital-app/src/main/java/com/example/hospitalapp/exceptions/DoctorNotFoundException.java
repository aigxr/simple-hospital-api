package com.example.hospitalapp.exceptions;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException() {
        super("Doctor was not found");
    }
}
