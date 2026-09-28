package com.leetjourney.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Record are meant for holding data, not behavior
// Task request is what the client sends
public record TaskRequest(@NotBlank(message = "Title required")
                          @Size(min = 3, max = 100, message = "Title must be between 3-100 characters")
                          String title,

                          @Size(min = 3, max = 500, message = "Description can be between 3-500 characters")
                          String description,
                          Boolean completed) {

}
