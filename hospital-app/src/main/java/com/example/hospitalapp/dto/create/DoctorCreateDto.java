package com.example.hospitalapp.dto.create;

import com.example.hospitalapp.entity.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class DoctorCreateDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
