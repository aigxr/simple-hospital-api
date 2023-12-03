package com.example.hospitalapp.controller;

import com.example.hospitalapp.dto.VisitDto;
import com.example.hospitalapp.dto.create.VisitCreateDto;
import com.example.hospitalapp.entity.Visit;
import com.example.hospitalapp.exceptions.IllegalVisitOperationException;
import com.example.hospitalapp.mapper.VisitDtoMapper;
import com.example.hospitalapp.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class VisitController {
    private final VisitService visitService;

    @PostMapping("/api/visit")
    public ResponseEntity<VisitDto> createVisit(@RequestBody VisitCreateDto dto) {
        VisitDto visit;
        URI uri;
        try {
        visit = visitService.createAvailableVisit(dto);
        uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(visit.getId())
                .toUri();
        } catch (IllegalVisitOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("reason", e.getMessage()).build();
        }
        return ResponseEntity.created(uri).body(visit);
    }

    @GetMapping("/api/visit/{id}")
    public Visit getVisit(@PathVariable Long id) {
        return visitService.getVisitById(id);
    }

    @GetMapping("/api/visits")
    public List<VisitDto> getAllVisits() {
        return visitService.getAllVisits();
    }
}
