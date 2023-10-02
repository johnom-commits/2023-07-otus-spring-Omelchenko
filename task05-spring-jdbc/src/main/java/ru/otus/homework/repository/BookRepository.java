package ru.otus.homework.repository;

import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    long insert(BookDto book);

    Optional<Book> getById(long id);

    List<Book> getAll();

    void update(Book book);

    void delete(long id);
}
