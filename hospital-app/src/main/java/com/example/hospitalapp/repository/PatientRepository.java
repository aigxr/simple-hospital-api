package com.example.hospitalapp.repository;

import com.example.hospitalapp.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
