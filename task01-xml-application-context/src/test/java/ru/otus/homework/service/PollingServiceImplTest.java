package ru.otus.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.config.ResourceConfig;
import ru.otus.homework.repository.QuestionsRepository;
import ru.otus.homework.repository.QuestionsRepositoryCsv;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Class PollingServiceImpl")
class PollingServiceImplTest {

    @DisplayName("Questions and answers are displayed correctly")
    @Test
    void shouldHaveCorrectOutputQuestionsAndAnswers() {
        String consoleOutput = null;
        PrintStream out = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);

            ResourceConfig resourceConfig = new ResourceConfig("data-source-test.csv");
            QuestionsRepository repo = new QuestionsRepositoryCsv(resourceConfig);
            PollingService pollingService = new PollingServiceImpl(repo);
            pollingService.publishingQuestion();

            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("1. To be or not to be?\n  1. Shakespeare\n  2. Byron\n", consoleOutput);
    }
}