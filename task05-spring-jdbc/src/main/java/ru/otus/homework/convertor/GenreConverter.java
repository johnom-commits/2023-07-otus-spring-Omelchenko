package ru.otus.homework.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Genre;

@Component
public class GenreConverter implements Converter<Genre, String> {

    @Override
    public String convert(Genre genre) {
        return "%s. %s".formatted(genre.getId(), genre.getName());
    }
}
