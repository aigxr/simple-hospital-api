package com.example.hospitalapp.repository;

import com.example.hospitalapp.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query("SELECT v FROM Visit v " +
            "WHERE v.doctor.id = :doctor_id " +
            "AND v.startTime <= :startTime " +
            "AND v.endTime >= :endTime")
    List<Visit> findAllOverlapping(@Param("doctor_id") Long id, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
