package ru.otus.homework.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BookRepositoryImpl.class)
class BookRepositoryImplTest {

    private static final int EXPECTED_NUMBER_OF_BOOKS = 2;

    private static final int FIRST_BOOK_ID = 1;

    @Autowired
    private BookRepositoryImpl bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Должен корректно сохранять всю информацию о книге")
    void create() {
        Author author = new Author(3, "Bylgakov");
        Genre genre = new Genre(3, "Story");
        Comment comment = new Comment(2, "Comment N 2");
        Book book = new Book(3, "March of 30", author, genre, List.of(comment));
        Book createdBook = bookRepository.create(book);

        Book actual = bookRepository.getById(createdBook.getId()).orElse(null);
        Book expected = new Book(createdBook.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getComments());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @DisplayName("Должен загружать книгу по её идентификатору")
    void getById() {
        Optional<Book> actualBook = bookRepository.getById(FIRST_BOOK_ID);
        Book expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(actualBook.orElse(null)).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("Должен загружать список всех книг с полной информацией о них")
    void getAll() {
        List<Book> actual = bookRepository.getAll();

        assertThat(actual).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(b -> !b.getTitle().isEmpty())
                .allMatch(b -> b.getAuthor() != null)
                .allMatch(b -> b.getGenre() != null)
                .allMatch(b -> b.getComments() != null && !b.getComments().isEmpty());
    }

    @Test
    @DisplayName("Должен проверять существует ли книга с переданным идентификатором")
    void isExist() {
        boolean exist = bookRepository.isExist(FIRST_BOOK_ID);
        assertThat(exist).isTrue();

        boolean notExist = bookRepository.isExist(1000L);
        assertThat(notExist).isFalse();
    }

    @Test
    @DisplayName("Должен изменять заголовок книги по ее идентификатору")
    void update() {
        Book firstBook = em.find(Book.class, FIRST_BOOK_ID);
        String oldTitle = firstBook.getTitle();
        em.detach(firstBook);

        firstBook.setTitle("hello world");
        bookRepository.update(firstBook);
        Book updetedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(updetedBook.getTitle()).isNotEqualTo(oldTitle);
    }

    @Test
    @DisplayName("Должен удалять книгу по ее идентификатору")
    void delete() {
        Book firstBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(firstBook).isNotNull();
        em.detach(firstBook);

        bookRepository.delete(FIRST_BOOK_ID);
        Book deletedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(deletedBook).isNull();
    }
}