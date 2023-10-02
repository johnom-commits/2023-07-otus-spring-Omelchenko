package ru.otus.homework.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(GenreRepositoryJdbc.class)
class GenreRepositoryJdbcTest {

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAll() {
        List<Genre> actual = genreRepository.getAll();

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void getById() {
        Genre actual = genreRepository.getById(1L).orElse(null);
        Genre expected = new Genre(1L, "poetry");

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}