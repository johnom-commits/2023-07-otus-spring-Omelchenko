package ru.otus.homework.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.dto.GenreDto;
import ru.otus.homework.mapper.GenresMapping;
import ru.otus.homework.repositories.GenreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenresMapping genresMapping;

    @Override
    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(genresMapping::convert)
                .toList();
    }

    @Override
    public Genre getGenreById(String id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found genre with id = " + id));
    }
}
