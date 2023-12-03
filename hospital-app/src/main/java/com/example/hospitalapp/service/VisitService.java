package com.example.hospitalapp.service;

import com.example.hospitalapp.dto.VisitDto;
import com.example.hospitalapp.dto.create.VisitCreateDto;
import com.example.hospitalapp.entity.Doctor;
import com.example.hospitalapp.entity.Patient;
import com.example.hospitalapp.entity.Visit;
import com.example.hospitalapp.exceptions.IllegalVisitOperationException;
import com.example.hospitalapp.exceptions.PatientNotFoundException;
import com.example.hospitalapp.exceptions.VisitNotFoundException;
import com.example.hospitalapp.mapper.VisitDtoMapper;
import com.example.hospitalapp.repository.DoctorRepository;
import com.example.hospitalapp.repository.PatientRepository;
import com.example.hospitalapp.repository.VisitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final VisitDtoMapper visitDtoMapper;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    @Transactional
    public VisitDto createAvailableVisit(VisitCreateDto dto) {
        if (dto.getStartTime().isBefore(LocalDateTime.now())) {
            throw new IllegalVisitOperationException("Cannot create a visit for past time");
        }
        if (!(dto.getStartTime().getMinute() % 30 == 0 && dto.getEndTime().getMinute() % 30 == 0)) {
            throw new IllegalVisitOperationException("Visit must start and end half past an hour");
        }
        Patient patient = patientRepository.findById(dto.getPatientId()).orElseThrow(() -> new IllegalVisitOperationException("Patient not found"));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow(() -> new IllegalVisitOperationException("Doctor not found"));
        Visit visitToSave = new Visit(doctor, patient, dto.getPrice(), dto.getStartTime(), dto.getEndTime());

        List<Visit> allOverlapping = visitRepository.findAllOverlapping(doctor.getId(), dto.getStartTime(), dto.getEndTime());
        if (!allOverlapping.isEmpty()) {
            throw new IllegalVisitOperationException("This visit overlaps existing visits");
        }
        Visit savedVisit = visitRepository.save(visitToSave);
        return visitDtoMapper.map(savedVisit);
    }

    public Visit getVisitById(Long id) {
        return visitRepository.findById(id)
                .orElseThrow(VisitNotFoundException::new);
    }

    public List<VisitDto> getAllVisits() {
        return visitRepository.findAll().stream().map(visitDtoMapper::map).toList();
    }
}
