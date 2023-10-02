package ru.otus.homework.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Genre {

    @NotNull
    private long id;

    @NotNull
    private String name;
}
