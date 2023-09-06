package ru.otus.homework.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.config.ResourceProvider;
import ru.otus.homework.convertor.QuestionConverter;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionsRepositoryCsvTest {

    @Mock
    private ResourceProvider resourceProvider;

    @Test
    void findAll() {
        QuestionConverter questionConverter = new QuestionConverter();
        when(resourceProvider.getResource())
                .thenReturn(QuestionsRepositoryCsvTest.class.getClassLoader().getResourceAsStream("data-source-test.csv"));

        QuestionsRepository repo = new QuestionsRepositoryCsv(questionConverter, resourceProvider);

        List<Question> actual = repo.findAll();
        List<Question> expected = List.of(getExpectedQuestion());

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    private Question getExpectedQuestion() {
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