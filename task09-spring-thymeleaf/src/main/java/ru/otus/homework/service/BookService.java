package ru.otus.homework.service;

import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.NewBookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();

    void save(NewBookDto book);
}
