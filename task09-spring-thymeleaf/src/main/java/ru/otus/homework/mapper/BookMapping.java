package ru.otus.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookDto;

@Component
@RequiredArgsConstructor
public class BookMapping implements Converter<Book, BookDto> {

    private final AuthorMapping authorMapping;
    private final GenresMapping genresMapping;

    @Override
    public BookDto convert(Book source) {
        return new BookDto(
                source.getId(),
                source.getTitle(),
                authorMapping.convert(source.getAuthor()),
                genresMapping.convert(source.getGenre())
        );
    }
}
