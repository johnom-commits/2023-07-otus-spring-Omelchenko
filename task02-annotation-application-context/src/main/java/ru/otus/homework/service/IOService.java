package ru.otus.homework.service;

public interface IOService {

    void outputString(String s);

    int readIntWithPrompt(String prompt);

    String readStringWithPrompt(String prompt);
}
