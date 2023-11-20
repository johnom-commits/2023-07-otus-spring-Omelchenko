package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.dto.BookCreateDto;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.BookUpdateDto;
import ru.otus.homework.exception.NotFoundException;
import ru.otus.homework.mapper.BookMapping;
import ru.otus.homework.repository.AuthorRepository;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.repository.GenreRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapping bookMapping;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public BookDto saveBook(BookCreateDto bookDto) {
        Book book = bookMapping.toBook(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookMapping.toBookDto(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getById(Long id) {
        Book book = bookRepository.getById(id)
                .orElseThrow(() -> new NotFoundException(getTextException("book", id)));
        return bookMapping.toBookDto(book);
    }

    @Override
    @Transactional
    public void update(BookUpdateDto bookDto) {
        Book book = bookRepository.getById(bookDto.id())
                .orElseThrow(() -> new NotFoundException(getTextException("book", bookDto.id())));
        Author author = getAuthor(bookDto.author_id());
        Genre genre = getGenre(bookDto.genre_id());
        book.setTitle(bookDto.title());
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.delete(id);
    }

    private Genre getGenre(Long id) {
        return genreRepository.getById(id)
                .orElseThrow(() -> new NotFoundException(getTextException("genre", id)));
    }

    private Author getAuthor(Long id) {
        return authorRepository.getById(id)
                .orElseThrow(() -> new NotFoundException(getTextException("author", id)));
    }

    private String getTextException(String object, Long id) {
        return "Not found %s with id = %d".formatted(object, id);
    }
}
