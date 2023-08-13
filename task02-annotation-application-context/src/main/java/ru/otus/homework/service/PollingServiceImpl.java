package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.Student;
import ru.otus.homework.repository.QuestionsRepository;

import java.util.List;
import java.util.Scanner;

@Service("pollingService")
public class PollingServiceImpl implements PollingService {
    private final QuestionsRepository questionsRepository;

    private final int limit;

    public PollingServiceImpl(QuestionsRepository questionsRepository, @Value("${app.number-right-answers}") int limit) {
        this.questionsRepository = questionsRepository;
        this.limit = limit;
    }

    @Override
    public void publishingQuestion() {
        List<Question> questions = questionsRepository.findAll();
        publish(questions);
    }

    private void publish(List<Question> questions) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your surname:");
        String surname = scanner.next();
        System.out.println("Enter your name:");
        String name = scanner.next();
        Student student = new Student(surname, name);
        System.out.printf("%s, give answers on next 5 questions:\n", name);

        int countRightAnswers = 0;
        for (Question question : questions) {
            printQuestion(question);
            int answerStudent = Integer.parseInt(scanner.next());
            if (answerStudent == question.getRightAnswer()) {
                countRightAnswers++;
            }
        }
        if (countRightAnswers >= limit) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
    }

    private void printQuestion(Question question) {
        System.out.printf("%s. %s\n", question.getId(), question.getText());
        question.getAnswers().forEach(answer ->
            System.out.printf("  %s. %s\n", answer.getId(), answer.getText())
        );
        System.out.println("Enter number of answer:");
    }
}
