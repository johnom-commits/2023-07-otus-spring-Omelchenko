package ru.otus.homework.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "books")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
