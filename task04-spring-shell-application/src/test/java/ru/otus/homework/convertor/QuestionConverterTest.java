package ru.otus.homework.convertor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuestionConverterTest {

    @Autowired
    private QuestionConverter questionConverter;

    @Test
    void convert() {
        String source = "3,Who invented the radio?,1,Popov,Marconi,Tesla";
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