package ru.otus.homework.dto;

import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Genre;

public record BookDto(String title,
                      Author author,
                      Genre genre) {
}
