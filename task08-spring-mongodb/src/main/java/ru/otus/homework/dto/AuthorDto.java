package ru.otus.homework.dto;

import jakarta.validation.constraints.NotNull;

public record AuthorDto(
        @NotNull String id,
        @NotNull String name) {
}
