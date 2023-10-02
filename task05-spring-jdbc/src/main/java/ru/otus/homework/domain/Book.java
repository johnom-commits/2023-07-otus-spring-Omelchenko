package ru.otus.homework.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Book {

    @NotNull
    private long id;

    @NotNull
    private String title;

    @NotNull
    private Author author;

    @NotNull
    private Genre genre;
}
