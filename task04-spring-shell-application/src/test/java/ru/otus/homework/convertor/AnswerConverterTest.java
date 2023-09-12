package ru.otus.homework.convertor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.domain.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Конвертор ответов")
class AnswerConverterTest {

    @Autowired
    private AnswerConverter answerConverter;

    @Test
    @DisplayName("Должен вернуть строку")
    void convert() {
        Answer answer = new Answer(1L, "This is a answer", true);
        String actual = answerConverter.convert(answer);

        assertThat(actual).isEqualTo(" 1. This is a answer");
    }
}