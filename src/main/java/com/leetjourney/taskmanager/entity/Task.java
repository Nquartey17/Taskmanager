package com.leetjourney.taskmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// These annotations save a lot of time writing boiler plate code
@Entity //labels class as JPA entity
@Table(name = "tasks")
@Data // getters, setters, tostring, and hashcode methods
@NoArgsConstructor // default constructor
@AllArgsConstructor // all args constructors
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto ID
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean completed = false;

    @Column(name = "created_at", nullable = false, updatable = false) // column name assigned here (not essential)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
