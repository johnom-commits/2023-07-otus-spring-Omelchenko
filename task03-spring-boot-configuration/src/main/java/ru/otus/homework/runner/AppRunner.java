package ru.otus.homework.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.otus.homework.convertor.AnswerConverter;
import ru.otus.homework.convertor.FromQuestionConverter;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.ResultTest;
import ru.otus.homework.domain.Student;
import ru.otus.homework.exceptions.QuestionsNotFountException;
import ru.otus.homework.repository.QuestionsRepository;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.LocalizationService;
import ru.otus.homework.service.ResultService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    private final QuestionsRepository questionsRepository;

    private final LocalizationService localizationService;

    private final ResultService resultService;

    private final IOService ioService;

    private final FromQuestionConverter questionConverter;

    private final AnswerConverter answerConverter;

    @Override
    public void run(ApplicationArguments args) {
        List<Question> questions = questionsRepository.findAll();
        if (questions.isEmpty()) {
            throw new QuestionsNotFountException(localizationService.getMessage("test.error-not-questions"));
        }
        publish(questions);
    }

    private void publish(List<Question> questions) {
        var nameStudent = ioService.readStringWithPrompt(localizationService.getMessage("test.enterName"));
        var surNameStudent = ioService.readStringWithPrompt(localizationService.getMessage("test.enterSurName"));
        var result = new ResultTest(new Student(nameStudent, surNameStudent));

        ioService.outputString(localizationService.getMessage("test.invitation", nameStudent));

        questions.forEach(question -> {
                    printQuestion(question);
                    int numberAnswer = ioService.readIntWithPrompt(localizationService.getMessage("test.enterAnswer"));
                    result.addAnswer(question, numberAnswer);
                }
        );
        ioService.outputString(resultService.getResult(result));
    }

    private void printQuestion(Question question) {
        ioService.outputString(questionConverter.convert(question));
        question.getAnswers()
                .forEach(answer -> ioService.outputString(answerConverter.convert(answer)));
    }
}
