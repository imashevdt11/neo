package org.example.springframework.entity;

import lombok.AccessLevel;
import lombok.Data;

import jakarta.persistence.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;
}