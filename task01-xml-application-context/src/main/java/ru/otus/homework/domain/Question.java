package ru.otus.homework.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Question {
    private long id;

    private String text;

    private List<Answer> answers = new ArrayList<>();

    public void addAnswers(List<Answer> answers) {
        this.answers.addAll(answers);
    }
}
