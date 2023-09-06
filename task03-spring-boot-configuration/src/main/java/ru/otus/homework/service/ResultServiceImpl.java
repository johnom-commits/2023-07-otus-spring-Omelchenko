package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.config.AppConfig;
import ru.otus.homework.domain.ResultTest;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final AppConfig config;

    private final LocalizationService localizationService;

    @Override
    public String getResult(ResultTest result) {
        if (isPositiveResult(result)) {
            return getResultMessage("test.success", result);
        }
        return getResultMessage("test.failed", result);
    }

    private String getResultMessage(String text, ResultTest result) {
        return localizationService.getMessage(text, result.getStudent().name());
    }

    private boolean isPositiveResult(ResultTest result) {
        return config.getRightAnswers() <= result.getNumberRightAnswers();
    }
}
