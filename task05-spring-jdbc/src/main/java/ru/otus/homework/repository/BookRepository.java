package ru.otus.homework.repository;

import ru.otus.homework.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book create(Book book);

    Optional<Book> getById(long id);

    List<Book> getAll();

    boolean isExist(long id);

    void update(Book book);

    void delete(long id);
}
