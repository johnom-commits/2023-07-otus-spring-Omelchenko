package ru.otus.homework.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.NewBookDto;
import ru.otus.homework.mapper.BookMapping;
import ru.otus.homework.repositories.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    BookMapping bookMapping;
    AuthorService authorService;
    GenreService genreService;

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapping::convert)
                .toList();
    }

    @Override
    public void save(NewBookDto bookDto) {
        Author author = authorService.getAuthorById(bookDto.getAuthor());
        Genre genre = genreService.getGenreById(bookDto.getGenre());
        Book book = new Book(bookDto.getTitle(), author, genre);
        bookRepository.save(book);
    }
}
