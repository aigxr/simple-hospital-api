package com.example.hospitalapp.controller;

import com.example.hospitalapp.dto.create.PatientCreateDto;
import com.example.hospitalapp.dto.PatientDto;
import com.example.hospitalapp.dto.create.UpdateDto;
import com.example.hospitalapp.entity.Patient;
import com.example.hospitalapp.exceptions.IllegalPatientOperationException;
import com.example.hospitalapp.exceptions.UserAlreadyExistsException;
import com.example.hospitalapp.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/api/patient")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientCreateDto dto) {
        PatientDto patientDto;
        URI uri;
        try {
            patientDto = patientService.addPatient(dto);
            uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(patientDto.getId())
                    .toUri();
        } catch (IllegalPatientOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("reason", e.getMessage()).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("reason", "User with this username already exists").build();
        }
        return ResponseEntity.created(uri).body(patientDto);
    }

    @GetMapping("/api/patient/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return patientService.getPatient(id);
    }

    @GetMapping("/api/patients")
    public List<PatientDto> getAllPatients() {
        return patientService.getAllPatients();
    }

    @PatchMapping("/api/patient/{id}")
    public PatientDto updatePatientById(@PathVariable Long id, @RequestBody UpdateDto dto) {
        return patientService.updatePatient(id, dto);
    }

    @DeleteMapping("/api/patient/{id}")
    public void deletePatientById(@PathVariable Long id, @RequestHeader String passwd) {
        patientService.deletePatientById(id, passwd);
    }
}
