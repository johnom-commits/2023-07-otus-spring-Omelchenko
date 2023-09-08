package ru.otus.homework.convertor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.Answer;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Конвертор ответов")
class AnswerConverterTest {

    private AnswerConverter answerConverter;

    @BeforeEach
    void setUp() {
        answerConverter = new AnswerConverter();
    }

    @DisplayName("Должен вернуть строку")
    @Test
    void convert() {
        Answer answer = new Answer(1L, "This is a answer", true);
        String actual = answerConverter.convert(answer);

        assertThat(actual).isEqualTo(" 1. This is a answer");
    }
}