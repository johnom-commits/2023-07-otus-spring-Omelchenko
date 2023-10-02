package ru.otus.homework.service;

import ru.otus.homework.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<Genre> getAllGenres();

    Optional<Genre> getGenreById(String id);

}
