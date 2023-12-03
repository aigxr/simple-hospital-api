package com.example.hospitalapp.mapper;

import com.example.hospitalapp.dto.DoctorDto;
import com.example.hospitalapp.dto.PatientDto;
import com.example.hospitalapp.entity.Doctor;
import com.example.hospitalapp.entity.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientDtoMapper {
    public PatientDto map(Patient patient) {
        PatientDto dto = new PatientDto();
        dto.setId(patient.getId());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        return dto;
    }
}
