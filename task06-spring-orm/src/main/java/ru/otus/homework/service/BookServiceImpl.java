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
import ru.otus.homework.mapper.BookDtoMapping;
import ru.otus.homework.repository.AuthorRepository;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.repository.GenreRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookDtoMapping bookDtoMapping;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public BookDto saveBook(BookCreateDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.title());
        book.setAuthor(getAuthor(bookDto.author_id()));
        book.setGenre(getGenre(bookDto.genre_id()));
        Book savedBook = bookRepository.create(book);
        return bookDtoMapping.toBookDto(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getById(long id) {
        Book book = bookRepository.getById(id)
                .orElseThrow(() -> new NotFoundException(getTextException("book", id)));
        return bookDtoMapping.toBookDto(book);
    }

    @Override
    @Transactional
    public void update(BookUpdateDto bookDto) {
        if (!bookRepository.isExist(bookDto.id())) {
            throw new NotFoundException(getTextException("book", bookDto.id()));
        }
        Author author = getAuthor(bookDto.author_id());
        Genre genre = getGenre(bookDto.genre_id());

        Book book = new Book();
        book.setId(bookDto.id());
        book.setTitle(bookDto.title());
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.update(book);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.delete(id);
    }

    private Genre getGenre(long id) {
        return genreRepository.getById(id)
                .orElseThrow(() -> new NotFoundException(getTextException("genre", id)));
    }

    private Author getAuthor(long id) {
        return authorRepository.getById(id)
                .orElseThrow(() -> new NotFoundException(getTextException("author", id)));
    }

    private String getTextException(String object, long id) {
        return "Not found %s with id = %d".formatted(object, id);
    }
}
