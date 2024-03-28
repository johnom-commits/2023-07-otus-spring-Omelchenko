package ru.otus.homework.dto;

import jakarta.validation.constraints.NotNull;

public record GenreDto(
        @NotNull String id,
        @NotNull String name) {
}
