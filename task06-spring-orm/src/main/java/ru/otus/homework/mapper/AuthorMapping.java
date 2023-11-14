package ru.otus.homework.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Author;
import ru.otus.homework.dto.AuthorDto;

@Component
public class AuthorMapping {

    public AuthorDto toAuthorDto(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }

    public Author toAuthor(AuthorDto authorDto) {
        return new Author(authorDto.id(), authorDto.name());
    }
}
