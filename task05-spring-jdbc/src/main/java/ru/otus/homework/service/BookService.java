package ru.otus.homework.service;

import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void saveBook(BookDto bookDto);

    List<Book> getAll();

    Optional<Book> getById(long id);

    void update(Book book);

    void deleteById(long id);
}
