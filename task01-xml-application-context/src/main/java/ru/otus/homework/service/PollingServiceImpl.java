package ru.otus.homework.service;

import ru.otus.homework.domain.Question;
import ru.otus.homework.repository.QuestionsRepository;

import java.util.List;

public class PollingServiceImpl implements PollingService {
    private final QuestionsRepository questionsRepository;

    public PollingServiceImpl(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    @Override
    public void publishingQuestion() {
        List<Question> questions = questionsRepository.findAll();
        publish(questions);
    }

    private void publish(List<Question> questions) {
        questions.forEach(question -> {
            System.out.printf("%s. %s\n", question.getId(), question.getText());
            question.getAnswers().forEach(answer -> {
                System.out.printf("  %s. %s\n", answer.getId(), answer.getText());
            });
        });
    }
}
