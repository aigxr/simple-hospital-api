package com.example.hospitalapp.controller;

import com.example.hospitalapp.dto.create.UpdateDto;
import com.example.hospitalapp.exceptions.DoctorNotFoundException;
import com.example.hospitalapp.exceptions.IllegalDoctorOperationException;
import com.example.hospitalapp.exceptions.UserAlreadyExistsException;
import com.example.hospitalapp.service.DoctorService;
import com.example.hospitalapp.dto.create.DoctorCreateDto;
import com.example.hospitalapp.dto.DoctorDto;
import com.example.hospitalapp.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/api/doctor")
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorCreateDto dto) {
        DoctorDto doctorDto;
        URI uri;
        try {
            doctorDto = doctorService.addDoctor(dto);
            uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(doctorDto.getId())
                    .toUri();
        } catch (IllegalDoctorOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("reason", e.getMessage()).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("reason", "User with this username already exists").build();
        }
        return ResponseEntity.created(uri).body(doctorDto);
    }

    @GetMapping("/api/doctor/{id}")
    public Doctor getDoctor(@PathVariable Long id) {
        return doctorService.getDoctor(id);
    }

    @GetMapping("/api/doctors")
    public List<DoctorDto> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PatchMapping("/api/doctor/{id}")
    public DoctorDto updateDoctorById(@PathVariable Long id, @RequestBody UpdateDto dto) {
        return doctorService.updateDoctorById(id, dto);
    }

    @DeleteMapping("/api/doctor/{id}")
    public void deleteDoctorById(@PathVariable Long id, @RequestHeader String passwd) {
        doctorService.deleteDoctorById(id, passwd);
    }
}
