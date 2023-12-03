package com.example.hospitalapp.dto;

import com.example.hospitalapp.entity.Doctor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientDto {
    private Long id;
    private String firstName;
    private String lastName;
}
