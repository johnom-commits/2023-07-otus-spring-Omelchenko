package ru.otus.homework.domain;

public record Answer(long id,
                     String text,
                     boolean rightAnswer
) {}