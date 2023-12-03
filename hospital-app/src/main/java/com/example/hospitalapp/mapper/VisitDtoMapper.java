package com.example.hospitalapp.mapper;

import com.example.hospitalapp.dto.VisitDto;
import com.example.hospitalapp.entity.Visit;
import org.springframework.stereotype.Service;

@Service
public class VisitDtoMapper {
    public VisitDto map(Visit visit) {
        VisitDto dto = new VisitDto();
        dto.setId(visit.getId());
        dto.setDoctor_id(visit.getDoctor().getId());
        dto.setPatient_id(visit.getPatient().getId());
        dto.setPrice(visit.getPrice());
        dto.setStartTime(visit.getStartTime());
        dto.setEndTime(visit.getEndTime());
        return dto;
    }
}
