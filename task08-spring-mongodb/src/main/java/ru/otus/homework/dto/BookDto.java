package ru.otus.homework.dto;

public record BookDto(
        String id,
        String title,
        AuthorDto authorDto,
        GenreDto genreDto) {
}
