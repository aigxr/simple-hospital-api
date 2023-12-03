package com.example.hospitalapp.mapper;

import com.example.hospitalapp.dto.DoctorDto;
import com.example.hospitalapp.entity.Doctor;
import com.example.hospitalapp.entity.User;
import org.springframework.stereotype.Service;

@Service
public class DoctorDtoMapper {
    public DoctorDto map(Doctor doctor) {
        DoctorDto dto = new DoctorDto();
        dto.setId(doctor.getId());
        dto.setFirstName(doctor.getFirstName());
        dto.setLastName(doctor.getLastName());
        return dto;
    }
}
