package ru.otus.homework.repository;

import ru.otus.homework.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> getAll();

    Optional<Author> getById(long id);
}
