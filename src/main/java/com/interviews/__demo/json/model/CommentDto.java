package com.interviews.__demo.json.model;

import jakarta.validation.constraints.NotBlank;

public record CommentDto(@NotBlank String text, @NotBlank String username) {

}