package ru.otus.homework.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.mapper.GenreMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Genre> getAll() {
        return jdbc.query("SELECT id, name FROM genres", new GenreMapper());
    }

    @Override
    public Optional<Genre> getById(long id) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject("SELECT id, name FROM genres WHERE id = :id",
                            Map.of("id", id),
                            new GenreMapper())
            );
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }
}
