package ru.otus.homework.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework.config.ResourceProvider;
import ru.otus.homework.convertor.QuestionConverter;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {
        QuestionConverter.class,
        ResourceProvider.class,
        QuestionsRepositoryCsv.class
})
class QuestionsRepositoryCsvTest {

    @Autowired
    private QuestionConverter questionConverter;

    @MockBean
    private ResourceProvider resourceProvider;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Test
    void findAll() {
        when(resourceProvider.getResource())
                .thenReturn(QuestionsRepositoryCsvTest.class.getClassLoader().getResourceAsStream("data-source-test.csv"));

        List<Question> actual = questionsRepository.findAll();
        List<Question> expected = List.of(getExpectedQuestion());

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    private Question getExpectedQuestion() {
        Question question = new Question();
        question.setId(1);
        question.setText("To be or not to be - what is the question?");
        List<Answer> answers = List.of(
                new Answer(1, "Shakespeare", true),
                new Answer(2, "Byron", false)
        );
        question.setAnswers(answers);
        return question;
    }
}