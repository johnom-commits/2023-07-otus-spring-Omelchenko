package ru.otus.homework.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.homework.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GenreRepositoryTest extends AuthorRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void shouldReturnCorrectCountGenres() {
        List<Genre> actual = genreRepository.findAll();

        assertThat(actual).isNotNull().hasSize(2);
    }
}
