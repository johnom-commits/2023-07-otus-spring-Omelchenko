package ru.otus.homework.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.dto.GenreDto;

@Component
public class GenreMapping {

    public GenreDto toGenreDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

    public Genre toGenre(GenreDto genreDto) {
        return new Genre(genreDto.id(), genreDto.name());
    }
}
