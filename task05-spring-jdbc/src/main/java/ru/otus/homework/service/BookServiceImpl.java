package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookCreateDto;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.BookUpdateDto;
import ru.otus.homework.exception.NotFoundException;
import ru.otus.homework.mapper.BookDtoMapping;
import ru.otus.homework.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookDtoMapping bookDtoMapping;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Override
    public BookDto saveBook(BookCreateDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.title());
        book.setAuthor(authorService.getAuthorById(bookDto.author_id()));
        book.setGenre(genreService.getGenreById(bookDto.genre_id()));

        Book savedBook = bookRepository.create(book);
        return bookDtoMapping.toBookDto(savedBook);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book getById(long id) {
        return bookRepository.getById(id)
                .orElseThrow(() -> new NotFoundException(getTextException("book", id)));
    }

    @Override
    public void update(BookUpdateDto book) {
        bookRepository.update(book);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.delete(id);
    }

    private String getTextException(String object, long id) {
        return String.format("Not found %s wit id = %d", object, id);
    }
}
