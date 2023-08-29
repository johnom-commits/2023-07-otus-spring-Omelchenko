package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.ResultTest;
import ru.otus.homework.domain.Student;
import ru.otus.homework.repository.QuestionsRepository;

import java.util.List;

@Service("pollingService")
public class PollingServiceImpl implements PollingService {
    private final QuestionsRepository questionsRepository;

    private final ResultService resultService;

    private final IOService ioService;

    public PollingServiceImpl(QuestionsRepository questionsRepository,
                              ResultService resultService,
                              IOService ioService) {
        this.questionsRepository = questionsRepository;
        this.resultService = resultService;
        this.ioService = ioService;
    }

    @Override
    public void publishingQuestion() {
        List<Question> questions = questionsRepository.findAll();
        publish(questions);
    }

    private void publish(List<Question> questions) {
        var name = ioService.readStringWithPrompt("Enter your name:");
        var surName = ioService.readStringWithPrompt("Enter your surname:");
        var result = new ResultTest(new Student(name, surName));

        ioService.outputString(name + ", give answers on next 5 questions:");

        questions.forEach(question -> {
                    printQuestion(question);
                    int numberAnswer = ioService.readIntWithPrompt("Enter number of answer:");
                    result.addAnswer(question, numberAnswer);
                }
        );
        ioService.outputString(resultService.getResult(result));
    }

    private void printQuestion(Question question) {
        ioService.outputString(question.getId() + ". " + question.getText());
        question.getAnswers().forEach(answer ->
                ioService.outputString("  " + answer.getId() + ". " + answer.getText())
        );
    }
}
