package ru.otus.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {
    private String id;
    private String name;

    public static GenreDto of(String id, String name) {
        return new GenreDto(id, name);
    }

    public static GenreDto of(String name) {
        return new GenreDto(null, name);
    }
}
