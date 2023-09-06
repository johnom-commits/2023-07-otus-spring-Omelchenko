package ru.otus.homework.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework.config.ResourceProvider;
import ru.otus.homework.convertor.QuestionConverter;
import ru.otus.homework.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuestionsRepositoryCsv implements QuestionsRepository {

    private final QuestionConverter questionConverter;

    private final ResourceProvider resourceProvider;

    @Override
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceProvider.getResource()))) {
            String line;
            while ((line = br.readLine()) != null) {
                var question = questionConverter.convert(line);
                Optional.ofNullable(question)
                        .ifPresent(questions::add);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }
}
