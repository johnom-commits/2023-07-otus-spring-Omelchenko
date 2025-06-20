package ru.otus.homework.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    @Id
    String id;
    String title;
    Author author;
    Genre genre;

    public Book(String title, Author author, Genre genre) {
        this(null, title, author, genre);
    }
}
