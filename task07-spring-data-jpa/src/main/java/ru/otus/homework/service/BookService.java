package ru.otus.homework.service;

import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookCreateDto;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.BookUpdateDto;

import java.util.List;

public interface BookService {
    BookDto saveBook(BookCreateDto bookDto);

    List<Book> getAll();

    BookDto getById(Long id);

    void update(BookUpdateDto book);

    void deleteById(Long id);
}
