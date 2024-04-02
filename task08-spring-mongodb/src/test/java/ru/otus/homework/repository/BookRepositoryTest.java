package ru.otus.homework.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.homework.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldReturnCorrectCountBooks() {
        List<Book> actual = bookRepository.findAll();

        assertThat(actual).isNotNull().hasSize(2);
    }
}
