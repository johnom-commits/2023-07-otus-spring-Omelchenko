package ru.otus.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.service.PollingService;
import ru.otus.homework.service.PollingServiceImpl;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        PollingService polling = context.getBean("pollingService", PollingServiceImpl.class);
        polling.publishingQuestion();
    }
}
