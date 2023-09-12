package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.Student;
import ru.otus.homework.runner.AppRunner;
import ru.otus.homework.service.LocalizationService;

@ShellComponent
@RequiredArgsConstructor
public class ShellAppRunner {

    private final AppRunner appRunner;

    private final LocalizationService localizationService;

    private Student student;

    @ShellMethod(value = "Login command", key = {"login", "l"})
    public String login(@ShellOption String userName) {
        student = new Student(userName);
        return String.format(localizationService.getMessage("test.welcome", userName));
    }

    @ShellMethod(value = "Start test", key = {"start", "go"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public void start() {
        appRunner.run(student);
    }

    private Availability isPublishEventCommandAvailable() {
        return student == null ?
                Availability.unavailable(localizationService.getMessage("test.login")) :
                Availability.available();
    }
}
