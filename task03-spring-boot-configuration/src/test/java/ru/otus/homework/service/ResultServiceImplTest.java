package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.config.AppConfig;
import ru.otus.homework.domain.ResultTest;
import ru.otus.homework.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultServiceImplTest {

    @Mock
    private ResultTest resultTest;

    @Mock
    private AppConfig appConfig;

    @Mock
    private LocalizationService localizationService;

    private ResultService resultService;

    Student student = new Student("Vasya", "Pupkin");

    private ArgumentCaptor<String> argumentCaptor;

    @BeforeEach
    void setUp() {
        resultService = new ResultServiceImpl(appConfig, localizationService);
        argumentCaptor = ArgumentCaptor.forClass(String.class);
    }

    @Test
    void getResultWhenTestSuccess() {
        when(resultTest.getNumberRightAnswers()).thenReturn(5);
        when(appConfig.getRightAnswers()).thenReturn(4);
        when(resultTest.getStudent()).thenReturn(student);

        resultService.getResult(resultTest);

        verify(localizationService).getMessage(argumentCaptor.capture(), anyString());

        assertThat(argumentCaptor.getValue()).isNotNull().endsWith("success");
    }

    @Test
    void getResultWhenTestFailed() {
        when(resultTest.getNumberRightAnswers()).thenReturn(3);
        when(appConfig.getRightAnswers()).thenReturn(4);
        when(resultTest.getStudent()).thenReturn(student);

        resultService.getResult(resultTest);

        verify(localizationService).getMessage(argumentCaptor.capture(), anyString());

        assertThat(argumentCaptor.getValue()).isNotNull().endsWith("failed");
    }
}