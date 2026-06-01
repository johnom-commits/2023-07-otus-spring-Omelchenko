package ru.otus.homework.service;

import jakarta.validation.constraints.NotNull;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> getAllGenres();

    Genre getGenreById(@NotNull String id);
}
