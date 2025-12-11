package ru.otus.homework.service;

import ru.otus.homework.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
}
