package ru.otus.homework.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookDto;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({BookRepositoryJdbc.class, AuthorsRepositoryJdbc.class, GenreRepositoryJdbc.class})
class BookRepositoryJdbcTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void insert() {
        BookDto bookDto = new BookDto("March of 30", authorRepository.getById(2L).orElse(null), genreRepository.getById(2L).orElse(null));
        long id = bookRepository.insert(bookDto);

        Book actual = bookRepository.getById(id).orElse(null);
        Book expected = new Book(id, bookDto.title(), bookDto.author(), bookDto.genre());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAll() {
        List<Book> expected = List.of(
                new Book(1L, "pedagogical poem",
                        authorRepository.getById(2L).orElse(null), genreRepository.getById(2L).orElse(null))
        );
        List<Book> actual = bookRepository.getAll();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void update() {
        Book book = new Book(1L, "Flags on the towers",
                authorRepository.getById(2L).orElse(null), genreRepository.getById(2L).orElse(null));
        bookRepository.update(book);

        Book actual = bookRepository.getById(1L).orElse(null);

        assertThat(actual.getTitle()).isEqualTo("Flags on the towers");
    }

    @Test
    void delete() {
        bookRepository.delete(1L);

        assertThat(bookRepository.getById(1L)).isEqualTo(Optional.empty());
    }
}