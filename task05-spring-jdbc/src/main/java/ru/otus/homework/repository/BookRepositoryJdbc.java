package ru.otus.homework.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookUpdateDto;
import ru.otus.homework.mapper.BookMapper;
import ru.otus.homework.mapper.BookResultSetExtractor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJdbc implements BookRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Book create(Book book) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("title", book.getTitle());
        map.addValue("author", book.getAuthor().getId());
        map.addValue("genre", book.getGenre().getId());

        GeneratedKeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("INSERT INTO books (title, authors_id, genres_id) VALUES (:title, :author, :genre)",
                map, kh, new String[]{"id"});

        long id = Objects.requireNonNull(kh.getKey()).longValue();
        book.setId(id);
        return book;
    }

    @Override
    public Optional<Book> getById(long id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject(
                    "SELECT b.id, b.title, a.id AS id_author, a.name AS name_author, " +
                            "g.id AS id_genre, g.name AS name_genre " +
                            "FROM books AS b " +
                            "LEFT JOIN authors AS a ON b.authors_id = a.id " +
                            "LEFT JOIN genres AS g ON b.genres_id = g.id " +
                            "WHERE b.id = :id",
                    Map.of("id", id),
                    new BookMapper()
            ));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "SELECT b.id, b.title, a.id AS id_author, a.name AS name_author, " +
                        "g.id AS id_genre, g.name AS name_genre " +
                        "FROM books AS b " +
                        "LEFT JOIN authors AS a ON b.authors_id = a.id " +
                        "LEFT JOIN genres AS g ON b.genres_id = g.id",
                new BookResultSetExtractor()
        );
    }

    @Override
    public void update(BookUpdateDto book) {
        Map<String, ? extends Serializable> map = Map.of(
                "id", book.id(),
                "title", book.title(),
                "author", book.author_id(),
                "genre", book.genre_id()
        );
        jdbc.update("UPDATE books SET title = :title, authors_id = :author, genres_id = :genre " +
                "WHERE id = :id", map);
    }

    @Override
    public void delete(long id) {
        jdbc.update("DELETE FROM books WHERE id = :id", Map.of("id", id));
    }
}
