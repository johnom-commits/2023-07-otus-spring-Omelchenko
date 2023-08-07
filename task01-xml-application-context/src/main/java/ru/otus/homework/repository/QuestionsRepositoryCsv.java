package ru.otus.homework.repository;

import ru.otus.homework.config.ResourceConfig;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionsRepositoryCsv implements QuestionsRepository {
    private static final String COMMA_DELIMITER = ",";

    private final ResourceConfig resourceConfig;

    public QuestionsRepositoryCsv(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        InputStream resource = getResource();
        if (resource == null) {
            return questions;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] valuesOfLine = line.split(COMMA_DELIMITER);
                Optional.ofNullable(getQuestion(valuesOfLine))
                        .ifPresent(questions::add);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

    private InputStream getResource() {
        String fileNameData = resourceConfig.getFileNameDataSource();
        return QuestionsRepositoryCsv.class.getClassLoader().getResourceAsStream(fileNameData);
    }

    private Question getQuestion(String[] values) {
        if (values.length < 2) {
            return null;
        }
        Question question = new Question();
        question.setId(Long.parseLong(values[0]));
        question.setText(values[1]);

        int count = 0;
        List<Answer> answers = new ArrayList<>();
        for (int i = 2; i < values.length; i++) {
            answers.add(new Answer(++count, values[i]));
        }
        question.addAnswers(answers);
        return question;
    }
}
