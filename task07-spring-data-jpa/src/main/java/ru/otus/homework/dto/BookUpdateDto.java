package ru.otus.homework.dto;

public record BookUpdateDto(
        long id,
        String title,
        long author_id,
        long genre_id
) {
}
