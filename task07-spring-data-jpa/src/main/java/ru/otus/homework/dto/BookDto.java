package ru.otus.homework.dto;

public record BookDto(
        long id,
        String title,
        AuthorDto authorDto,
        GenreDto genreDto) {
}
