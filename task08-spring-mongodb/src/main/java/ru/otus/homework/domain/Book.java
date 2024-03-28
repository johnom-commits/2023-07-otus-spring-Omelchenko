package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "books")
public class Book {

    @Id
    private String id;

    private String title;

    private Author author;

    private Genre genre;

    private List<Comment> comments;

    public Book(String title, Author author, Genre genre) {
        this(null, title, author, genre, null);
    }

    public Book(String title, Author author, Genre genre, List<Comment> comments) {
        this(null, title, author, genre, comments);
    }
}
