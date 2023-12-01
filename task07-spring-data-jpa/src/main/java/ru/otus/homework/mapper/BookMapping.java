package ru.otus.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.dto.BookCreateDto;
import ru.otus.homework.dto.BookDto;

@Component
@RequiredArgsConstructor
public class BookMapping {

    private final AuthorMapping authorMapping;

    private final GenreMapping genreMapping;

    public BookDto toBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                authorMapping.toAuthorDto(book.getAuthor()),
                genreMapping.toGenreDto(book.getGenre())
        );
    }

    public Book toBook(BookCreateDto dto, Author author, Genre genre) {
        return new Book(dto.title(), author, genre);
    }
}
