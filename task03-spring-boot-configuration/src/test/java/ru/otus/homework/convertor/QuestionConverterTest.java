package ru.otus.homework.convertor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Конвертор вопросов и ответов")
class QuestionConverterTest {

    @Test
    @DisplayName("Должен создать вопрос со списком ответов")
    void convert() {
        String source = "3,Who invented the radio?,1,Popov,Marconi,Tesla";
        QuestionConverter questionConverter = new QuestionConverter();
        Question expected = getExpectedQuestion();

        Question actual = questionConverter.convert(source);

        assertThat(actual).isEqualTo(expected);
    }

    private static Question getExpectedQuestion() {
        Question expected = new Question();
        expected.setId(3L);
        expected.setText("Who invented the radio?");
        expected.setAnswers(
                List.of(
                        new Answer(1, "Popov", true),
                        new Answer(2, "Marconi", false),
                        new Answer(3, "Tesla", false)
                )
        );
        return expected;
    }
}