package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.ResultTest;

@Service
public class ResultServiceImpl implements ResultService {

    private final int limit;

    public ResultServiceImpl(@Value("${app.number-right-answers}") int limit) {
        this.limit = limit;
    }

    public String getResult(ResultTest result) {
        var name = result.getStudent().getName();
        if (calcResult(result)) {
            return name + ": test success";
        }
        return name + ": test failed";
    }

    private boolean calcResult(ResultTest result) {
        return limit <= result.getNumberRightAnswers();
    }
}
