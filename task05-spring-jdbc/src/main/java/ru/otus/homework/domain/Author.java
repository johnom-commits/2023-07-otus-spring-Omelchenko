package ru.otus.homework.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Author {

    @NotNull
    private long id;

    @NotNull
    private String name;
}
