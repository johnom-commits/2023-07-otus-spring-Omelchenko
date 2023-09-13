package ru.otus.homework.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework.config.ApplicationConfig;
import ru.otus.homework.domain.ResultTest;
import ru.otus.homework.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ResultServiceImplTest {

    @MockBean
    private ResultTest resultTest;

    @Autowired
    private ApplicationConfig appConfig;

    @MockBean
    private LocalizationService localizationService;

    @Autowired
    private ResultService resultService;

    Student student = new Student("Vasya");

    private final ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

    @Test
    void getResultWhenTestSuccess() {
        when(resultTest.getNumberRightAnswers()).thenReturn(5);
        when(resultTest.getStudent()).thenReturn(student);

        resultService.getResult(resultTest);

        verify(localizationService).getMessage(argumentCaptor.capture(), anyString());

        assertThat(argumentCaptor.getValue()).isNotNull().endsWith("success");
    }

    @Test
    void getResultWhenTestFailed() {
        when(resultTest.getNumberRightAnswers()).thenReturn(3);
        when(resultTest.getStudent()).thenReturn(student);

        resultService.getResult(resultTest);

        verify(localizationService).getMessage(argumentCaptor.capture(), anyString());

        assertThat(argumentCaptor.getValue()).isNotNull().endsWith("failed");
    }
}
