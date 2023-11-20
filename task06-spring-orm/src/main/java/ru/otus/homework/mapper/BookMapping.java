package ru.otus.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.dto.BookCreateDto;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.exception.NotFoundException;
import ru.otus.homework.repository.AuthorRepository;
import ru.otus.homework.repository.GenreRepository;

@Component
@RequiredArgsConstructor
public class BookMapping {

    private final AuthorMapping authorMapping;

    private final GenreMapping genreMapping;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    public BookDto toBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                authorMapping.toAuthorDto(book.getAuthor()),
                genreMapping.toGenreDto(book.getGenre())
        );
    }

    public Book toBook(BookDto bookDto) {
        return new Book(
                bookDto.id(),
                bookDto.title(),
                authorMapping.toAuthor(bookDto.authorDto()),
                genreMapping.toGenre(bookDto.genreDto())
        );
    }

    public Book toBook(BookCreateDto dto) {
        Author author = authorRepository.getById(dto.author_id())
                .orElseThrow(() -> new NotFoundException("Not found author with id = " + dto.author_id()));
        Genre genre = genreRepository.getById(dto.genre_id())
                .orElseThrow(() -> new NotFoundException("Not found genre with id = " + dto.genre_id()));
        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(author);
        book.setGenre(genre);
        return book;
    }
}
