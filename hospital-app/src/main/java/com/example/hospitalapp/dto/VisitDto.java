package com.example.hospitalapp.dto;

import com.example.hospitalapp.entity.Doctor;
import com.example.hospitalapp.entity.Patient;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class VisitDto {
    private Long id;
    private Long doctor_id;
    private Long patient_id;
    private Double price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
