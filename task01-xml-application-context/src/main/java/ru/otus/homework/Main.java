package ru.otus.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.service.PollingService;
import ru.otus.homework.service.PollingServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("/app-context.xml");
        PollingService polling = context.getBean("pollingService", PollingServiceImpl.class);
        polling.publishingQuestion();
    }
}
