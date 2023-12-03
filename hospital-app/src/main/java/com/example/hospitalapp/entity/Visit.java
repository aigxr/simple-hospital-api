package com.example.hospitalapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private Double price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Visit(Doctor doctor, Patient patient, Double price, LocalDateTime startTime, LocalDateTime endTime) {
        this.doctor = doctor;
        this.patient = patient;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
