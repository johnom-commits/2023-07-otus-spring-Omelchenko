package ru.otus.homework.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookUpdateDto;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({BookRepositoryJdbc.class, AuthorRepositoryImpl.class, GenreRepositoryImpl.class})
class BookRepositoryJdbcTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void insert() {
        Book book = new Book();
        book.setTitle("March of 30");
        book.setAuthor(authorRepository.getById(2L).orElse(null));
        book.setGenre(genreRepository.getById(2L).orElse(null));
        Book createdBook = bookRepository.create(book);

        Book actual = bookRepository.getById(createdBook.getId()).orElse(null);
        Book expected = new Book(createdBook.getId(), book.getTitle(), book.getAuthor(), book.getGenre());

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
        BookUpdateDto book = new BookUpdateDto(1L, "Flags on the towers", 2L, 2L);
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