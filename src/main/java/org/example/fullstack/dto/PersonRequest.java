package org.example.fullstack.dto;

import jakarta.validation.constraints.*;

public record PersonRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Enter a valid email address")
        @Size(max = 255, message = "Email must not exceed 255 characters")
        String email


) {
}
