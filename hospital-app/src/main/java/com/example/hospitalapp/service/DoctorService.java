package com.example.hospitalapp.service;

import com.example.hospitalapp.dto.create.DoctorCreateDto;
import com.example.hospitalapp.dto.DoctorDto;
import com.example.hospitalapp.dto.create.UpdateDto;
import com.example.hospitalapp.entity.Doctor;
import com.example.hospitalapp.entity.User;
import com.example.hospitalapp.exceptions.DoctorNotFoundException;
import com.example.hospitalapp.exceptions.IllegalDoctorOperationException;
import com.example.hospitalapp.exceptions.InvalidPasswordException;
import com.example.hospitalapp.exceptions.UserAlreadyExistsException;
import com.example.hospitalapp.mapper.DoctorDtoMapper;
import com.example.hospitalapp.repository.DoctorRepository;
import com.example.hospitalapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DoctorDtoMapper doctorDtoMapper;
    @Transactional
    public DoctorDto addDoctor(DoctorCreateDto dto) {
        User user = new User(dto.getUsername(), dto.getPassword());
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException("User with this username exists");
        }
        userRepository.save(user);
        Doctor doctorEntity = new Doctor(dto.getFirstName(), dto.getLastName());
        doctorEntity.setUser(user);
        Doctor doctorToMap = doctorRepository.save(doctorEntity);

        return doctorDtoMapper.map(doctorToMap);
    }

    @Transactional
    public DoctorDto updateDoctorById(Long id, UpdateDto dto) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(DoctorNotFoundException::new);

        if (checkPassword(doctor.getUser().getPassword(), dto)) {
            doctor.getUser().setUsername(dto.getUsername());
        } else {
            throw new IllegalDoctorOperationException("Wrong password");
        }
        Doctor doctorToMap = doctorRepository.save(doctor);
        return doctorDtoMapper.map(doctorToMap);
    }

    @Transactional
    public void deleteDoctorById(Long id, String password) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(DoctorNotFoundException::new);
        if (checkPassword(doctor.getUser().getPassword(), password)) {
            doctorRepository.deleteById(id);
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

    public Doctor getDoctor(Long id) {
        return doctorRepository.findById(id).orElseThrow(DoctorNotFoundException::new);
    }

    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll().stream().map(doctorDtoMapper::map).toList();
    }
}
