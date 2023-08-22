package ru.otus.homework.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.config.ApplicationConfig;
import ru.otus.homework.service.QuestionConverter;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Интеграционный тест")
class QuestionsRepositoryCsvTest {

    @Test
    @DisplayName("Должен читать файл с данными и заполнять ими список")
    void findAll() {
        var converter = new QuestionConverter();
        var config = mock(ApplicationConfig.class);
        QuestionsRepository questionsRepository = new QuestionsRepositoryCsv(converter, config);
        when(config.getFileNameDataSource()).thenReturn("data-source-test.csv");

        List<Question> actual = questionsRepository.findAll();
        List<Question> expected = List.of(getExpectedQuestion());

        assertThat(actual).isEqualTo(expected);
    }

    private static Question getExpectedQuestion() {
        Question question = new Question();
        question.setId(1);
        question.setText("To be or not to be - what is the question?");
        question.setRightAnswer(1);
        List<Answer> answers = List.of(
                new Answer(1, "Shakespeare"),
                new Answer(2, "Byron")
        );
        question.setAnswers(answers);
        return question;
    }
}