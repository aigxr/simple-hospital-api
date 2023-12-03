package com.example.hospitalapp.repository;

import com.example.hospitalapp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
