package ru.otus.homework.dto;

import jakarta.validation.constraints.NotNull;

public record GenreDto(
        @NotNull long id,
        @NotNull String name) {
}
