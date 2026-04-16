package com.interviews.__demo.json.model;

import jakarta.validation.constraints.NotBlank;

public record BlogPostDto (@NotBlank String title, @NotBlank String text, @NotBlank String username) {
}