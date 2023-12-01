package ru.otus.homework.dto;

import jakarta.validation.constraints.NotNull;

public record AuthorDto(
        @NotNull long id,
        @NotNull String name) {
}
