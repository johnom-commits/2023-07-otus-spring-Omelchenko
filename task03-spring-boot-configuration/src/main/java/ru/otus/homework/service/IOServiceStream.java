package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceStream implements IOService {

    private final PrintStream output;

    private final Scanner input;

    public IOServiceStream(@Value("#{T(System).out}") PrintStream output,
                           @Value("#{T(System).in}") InputStream input) {
        this.output = output;
        this.input = new Scanner(input);
    }

    @Override
    public void outputString(String s) {
        output.println(s);
    }

    @Override
    public int readIntWithPrompt(String prompt) {
        outputString(prompt);
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        outputString(prompt);
        return input.nextLine();
    }
}
