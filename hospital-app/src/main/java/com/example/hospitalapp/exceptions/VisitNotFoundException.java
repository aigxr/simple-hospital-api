package com.example.hospitalapp.exceptions;

public class VisitNotFoundException extends RuntimeException{
    public VisitNotFoundException() {
        super("Visit was not found");
    }
}
