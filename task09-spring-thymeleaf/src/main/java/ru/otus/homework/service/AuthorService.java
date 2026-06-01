package ru.otus.homework.service;

import ru.otus.homework.domain.Author;
import ru.otus.homework.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAllAuthors();

    void add(String name);

    Author getAuthorById(String id);
}
