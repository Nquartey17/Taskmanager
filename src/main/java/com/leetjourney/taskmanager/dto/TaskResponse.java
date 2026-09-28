package com.leetjourney.taskmanager.dto;

import lombok.Builder;

import java.time.LocalDateTime;

// Task response: What the client receives, read only
@Builder
public record TaskResponse(
        Long id,
        String title,
        String description,
        Boolean completed,
        LocalDateTime createdAt
) {
}
