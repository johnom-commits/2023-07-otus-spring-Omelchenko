package ru.otus.homework.convertor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Question;

@Component
@RequiredArgsConstructor
public class FromQuestionConverter implements Converter<Question, String> {

    @Override
    public String convert(Question source) {
        return String.format("%s. %s", source.getId(), source.getText());
    }
}
