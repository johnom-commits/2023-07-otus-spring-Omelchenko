package ru.otus.homework.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Author;
import ru.otus.homework.dto.AuthorDto;

@Component
public class AuthorMapping implements Converter<Author, AuthorDto> {
    @Override
    public AuthorDto convert(Author source) {
        return AuthorDto.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }
}
