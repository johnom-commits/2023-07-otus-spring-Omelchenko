package ru.otus.homework.repository;

import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookUpdateDto;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book create(Book book);

    Optional<Book> getById(long id);

    List<Book> getAll();

    void update(BookUpdateDto book);

    void delete(long id);
}
