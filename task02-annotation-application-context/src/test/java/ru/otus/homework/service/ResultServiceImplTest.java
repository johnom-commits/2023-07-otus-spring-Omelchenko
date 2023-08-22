package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.ResultTest;
import ru.otus.homework.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultServiceImplTest {

    @Mock
    private ResultTest resultTest;

    private ResultService resultService;

    Student student = new Student("Vasya", "Pupkin");

    @BeforeEach
    void setUp() {
        resultService = new ResultServiceImpl(4);
    }

    @Test
    void getResultWhenSuccess() {
        when(resultTest.getNumberRightAnswers()).thenReturn(5);
        when(resultTest.getStudent()).thenReturn(student);

        String actual = resultService.getResult(resultTest);

        assertThat(actual).isEqualTo("Vasya: test success");
    }

    @Test
    void getResultWhenFailed() {
        when(resultTest.getNumberRightAnswers()).thenReturn(3);
        when(resultTest.getStudent()).thenReturn(student);

        String actual = resultService.getResult(resultTest);

        assertThat(actual).isEqualTo("Vasya: test failed");
    }
}