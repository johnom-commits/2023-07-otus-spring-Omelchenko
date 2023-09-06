package ru.otus.homework.convertor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Answer;

@Component
@RequiredArgsConstructor
public class AnswerConverter implements Converter<Answer, String> {

    @Override
    public String convert(Answer source) {
        return String.format(" %s. %s", source.getId(), source.getText());
    }
}
