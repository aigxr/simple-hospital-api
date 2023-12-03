package com.example.hospitalapp.dto.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientCreateDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
