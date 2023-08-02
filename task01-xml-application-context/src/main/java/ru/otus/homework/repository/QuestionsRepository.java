package ru.otus.homework.repository;

import ru.otus.homework.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionsRepository {
    List<Question> findAll() throws IOException;
}
