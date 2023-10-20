package ru.otus.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookDto;

@Component
@RequiredArgsConstructor
public class BookDtoMapping {

    private final AuthorDtoMapping authorDtoMapping;

    private final GenreMapping genreMapping;

    public BookDto toBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                authorDtoMapping.toAuthorDto(book.getAuthor()),
                genreMapping.toGenreDto(book.getGenre())
        );
    }
}
