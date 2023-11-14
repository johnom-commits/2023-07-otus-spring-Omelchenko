package ru.otus.homework.repository;

import ru.otus.homework.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Optional<Book> getById(Long id);

    List<Book> getAll();

    boolean isExist(Long id);

    void delete(Long id);
}
