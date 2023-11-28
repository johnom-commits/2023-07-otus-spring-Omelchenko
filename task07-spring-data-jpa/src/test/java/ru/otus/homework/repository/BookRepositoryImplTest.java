package ru.otus.homework.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryImplTest {

    private static final int EXPECTED_NUMBER_OF_BOOKS = 2;

    private static final Long FIRST_BOOK_ID = 1L;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Должен корректно сохранять всю информацию о книге")
    void create() {
        Author author = new Author(3L, "Bylgakov");
        Genre genre = new Genre(3L, "Story");
        Book book = new Book(3L, "March of 30", author, genre);
        Comment comment = new Comment(2L, "Comment N 2", book);
        Book createdBook = bookRepository.save(book);

        Book actual = bookRepository.findById(createdBook.getId()).orElse(null);
        Book expected = new Book(createdBook.getId(), book.getTitle(), book.getAuthor(), book.getGenre());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @DisplayName("Должен загружать книгу по её идентификатору")
    void getById() {
        Optional<Book> actualBook = bookRepository.findById(FIRST_BOOK_ID);
        Book expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(actualBook.orElse(null)).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("Должен загружать список всех книг с полной информацией о них")
    void getAll() {
        List<Book> actual = bookRepository.findAll();

        assertThat(actual).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(b -> !b.getTitle().isEmpty())
                .allMatch(b -> b.getAuthor() != null)
                .allMatch(b -> b.getGenre() != null);
    }

    @Test
    @DisplayName("Должен проверять существует ли книга с переданным идентификатором")
    void isExist() {
        boolean exist = bookRepository.existsById(FIRST_BOOK_ID);
        assertThat(exist).isTrue();

        boolean notExist = bookRepository.existsById(1000L);
        assertThat(notExist).isFalse();
    }

    @Test
    @DisplayName("Должен изменять заголовок книги по ее идентификатору")
    void update() {
        Book firstBook = em.find(Book.class, FIRST_BOOK_ID);
        String oldTitle = firstBook.getTitle();
        em.detach(firstBook);

        firstBook.setTitle("hello world");
        bookRepository.save(firstBook);
        Book updetedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(updetedBook.getTitle()).isNotEqualTo(oldTitle);
    }

    @Test
    @DisplayName("Должен удалять книгу по ее идентификатору")
    void delete() {
        Book firstBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(firstBook).isNotNull();
        em.detach(firstBook);

        bookRepository.deleteById(FIRST_BOOK_ID);
        Book deletedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(deletedBook).isNull();
    }
}