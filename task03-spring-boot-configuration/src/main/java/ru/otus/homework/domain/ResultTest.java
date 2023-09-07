package ru.otus.homework.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class ResultTest {

    private final Student student;

    private final Map<Question, Integer> answers = new HashMap<>();

    public void addAnswer(Question question, int answerStudent) {
        answers.put(question, answerStudent);
    }

    public int getNumberRightAnswers() {
        return (int) answers.entrySet().stream()
                .filter(entry -> entry.getKey().getAnswers().get(entry.getValue() - 1).rightAnswer())
                .count();
    }
}
