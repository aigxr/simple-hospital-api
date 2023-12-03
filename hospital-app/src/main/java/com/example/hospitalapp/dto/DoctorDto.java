package com.example.hospitalapp.dto;

import com.example.hospitalapp.entity.Patient;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DoctorDto {
    private Long id;
    private String firstName;
    private String lastName;
}
