package ru.otus.homework.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewBookDto {
    @NotNull
    @Size(min = 3, max = 100)
    private String title;
    private String author;
    @NotNull
    private String genre;
}
