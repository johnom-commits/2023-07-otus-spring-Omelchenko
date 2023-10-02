package ru.otus.homework.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(AuthorsRepositoryJdbc.class)
class AuthorsRepositoryJdbcTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void getAll() {
        List<Author> actual = authorRepository.getAll();

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void getById() {
        Author actual = authorRepository.getById(2L).get();
        Author expected = new Author(2, "Makarenko");

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}