package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public Book(String title, Author author, Genre genre) {
        this(null, title, author, genre);
    }
}
