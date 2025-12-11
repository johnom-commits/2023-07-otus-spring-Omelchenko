package ru.otus.homework.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.dto.GenreDto;

@Component
public class GenresMapping implements Converter<Genre, GenreDto> {

    @Override
    public GenreDto convert(Genre source) {
        return GenreDto.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }
}
