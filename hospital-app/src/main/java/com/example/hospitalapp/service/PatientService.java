package com.example.hospitalapp.service;

import com.example.hospitalapp.dto.DoctorDto;
import com.example.hospitalapp.dto.create.PatientCreateDto;
import com.example.hospitalapp.dto.PatientDto;
import com.example.hospitalapp.dto.create.UpdateDto;
import com.example.hospitalapp.entity.Doctor;
import com.example.hospitalapp.entity.Patient;
import com.example.hospitalapp.entity.User;
import com.example.hospitalapp.exceptions.*;
import com.example.hospitalapp.mapper.PatientDtoMapper;
import com.example.hospitalapp.repository.PatientRepository;
import com.example.hospitalapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientDtoMapper patientDtoMapper;
    private final UserRepository userRepository;
    @Transactional
    public PatientDto addPatient(PatientCreateDto dto) {
        User user = new User(dto.getUsername(), dto.getPassword());
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException("User with this username exists");
        }
        userRepository.save(user);
        Patient patientEntity = new Patient(dto.getFirstName(), dto.getLastName());
        patientEntity.setUser(user);
        Patient patientToMap = patientRepository.save(patientEntity);
        return patientDtoMapper.map(patientToMap);
    }
    @Transactional
    public PatientDto updatePatient(Long id, UpdateDto dto) {
        Patient patient = patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);

        if (checkPassword(patient.getUser().getPassword(), dto)) {
            patient.getUser().setUsername(dto.getUsername());
        } else {
            throw new IllegalPatientOperationException("Wrong password");
        }
        Patient patientToMap = patientRepository.save(patient);
        return patientDtoMapper.map(patientToMap);
    }

    @Transactional
    public void deletePatientById(Long id, String password) {
        Patient patient = patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);
        if (checkPassword(patient.getUser().getPassword(), password)) {
            patientRepository.deleteById(id);
        } else {
            throw new InvalidPasswordException("Wrong password");
        }
    }
    private boolean checkPassword(String password, UpdateDto dto) {
        return dto.getPassword() != null && password.equals(dto.getPassword());
    }
    private boolean checkPassword(String password, String password2) {
        return password2 != null && password2.equals(password);
    }

    public Patient getPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(PatientNotFoundException::new);
    }

    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll().stream().map(patientDtoMapper::map).toList();
    }

}
