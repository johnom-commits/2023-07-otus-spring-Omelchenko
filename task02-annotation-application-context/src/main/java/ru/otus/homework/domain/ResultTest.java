package ru.otus.homework.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Data
public class ResultTest {

    private final Student student;

    private Map<Question, Integer> answers = new HashMap<>();

    public void addAnswer(Question question, int answerStudent) {
        answers.put(question, answerStudent);
    }

    public int getNumberRightAnswers() {
        return (int) getAnswers().entrySet().stream()
                .filter(entry -> entry.getKey().getRightAnswer() == entry.getValue())
                .count();
    }
}
