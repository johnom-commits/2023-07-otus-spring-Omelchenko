package ru.otus.homework.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String title = rs.getString("title");
        Author author = new Author(rs.getLong("id_author"), rs.getString("name_author"));
        Genre genre = new Genre(rs.getLong("id_genre"), rs.getString("name_genre"));
        Book book = new Book(id, title, author, genre);
        return book;
    }
}
