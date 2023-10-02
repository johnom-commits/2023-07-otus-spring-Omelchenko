package ru.otus.homework.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Author;
import ru.otus.homework.mapper.AuthorsMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorsRepositoryJdbc implements AuthorRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Author> getAll() {
        return jdbc.query("SELECT id, name FROM authors", new AuthorsMapper());
    }

    @Override
    public Optional<Author> getById(long id) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject("SELECT id, name FROM authors WHERE id = :id",
                            Map.of("id", id),
                            new AuthorsMapper())
            );
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }
}
