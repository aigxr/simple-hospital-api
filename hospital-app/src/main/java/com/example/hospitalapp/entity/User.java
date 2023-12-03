package com.example.hospitalapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @OneToOne(mappedBy = "user")
    private Doctor doctor;
    @OneToOne(mappedBy = "user")
    private Patient patient;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
