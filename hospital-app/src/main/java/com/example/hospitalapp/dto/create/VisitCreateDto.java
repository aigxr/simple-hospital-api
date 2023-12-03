package com.example.hospitalapp.dto.create;

import com.example.hospitalapp.entity.Doctor;
import com.example.hospitalapp.entity.Patient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class VisitCreateDto {
    private Long doctorId;
    private Long patientId;
    private Double price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
