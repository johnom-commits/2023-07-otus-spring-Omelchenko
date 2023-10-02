package ru.otus.homework.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Author;

@Component
public class AuthorConverter implements Converter<Author, String> {

    @Override
    public String convert(Author author) {
        return String.format("%s. %s", author.getId(), author.getName());
    }
}
