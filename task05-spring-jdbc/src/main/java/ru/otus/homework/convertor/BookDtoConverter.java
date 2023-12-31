package ru.otus.homework.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Book;

@Component
public class BookDtoConverter implements Converter<Book, String> {

    @Override
    public String convert(Book book) {
        return "%s. %s, %s, %s".formatted(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getName(),
                book.getGenre().getName()
        );
    }
}
