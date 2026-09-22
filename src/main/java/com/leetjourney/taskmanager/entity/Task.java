package com.leetjourney.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// These annotations save a lot of time writing boiler plate code
@Data // getters, setters, tostring, and hashcode methods
@NoArgsConstructor // default constructor
@AllArgsConstructor // all args constructors
public class Task {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private LocalDateTime createdAt;
}
