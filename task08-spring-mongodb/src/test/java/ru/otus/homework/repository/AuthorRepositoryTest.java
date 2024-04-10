package ru.otus.homework.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.homework.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void shouldReturnCorrectCountAuthors() {
        List<Author> actual = authorRepository.findAll();

        assertThat(actual).isNotNull().hasSize(2);
    }

}