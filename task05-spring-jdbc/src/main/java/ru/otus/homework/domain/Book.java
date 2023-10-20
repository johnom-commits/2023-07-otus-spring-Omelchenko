package ru.otus.homework.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    private long id;

    @NotNull
    private String title;

    @NotNull
    private Author author;

    @NotNull
    private Genre genre;
}
