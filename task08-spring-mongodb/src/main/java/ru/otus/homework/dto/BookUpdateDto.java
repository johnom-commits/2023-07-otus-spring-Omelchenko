package ru.otus.homework.dto;

public record BookUpdateDto(
        String id,
        String title,
        String author_id,
        String genre_id
) {
}
