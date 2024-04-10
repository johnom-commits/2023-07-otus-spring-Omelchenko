package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(value = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private String id;

    private String name;

    @DBRef
    private Book book;

    public Comment(String name, Book book) {
        this(null, name, book);
    }
}
