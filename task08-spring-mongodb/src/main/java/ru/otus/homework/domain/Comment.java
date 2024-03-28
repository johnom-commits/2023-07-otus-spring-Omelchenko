package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
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

    public Comment(String name) {
        this(null, name);
    }
}
