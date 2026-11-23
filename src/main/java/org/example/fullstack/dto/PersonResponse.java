package org.example.fullstack.dto;

public record PersonResponse(
        Long id,
        String name,
        String email
) {
}
