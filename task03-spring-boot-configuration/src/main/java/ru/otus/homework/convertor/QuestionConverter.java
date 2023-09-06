package ru.otus.homework.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;

@Component
public class QuestionConverter implements Converter<String, Question> {

    private static final String COMMA_DELIMITER = ",";

    @Override
    public Question convert(String source) {
        String[] sources = source.split(COMMA_DELIMITER);
        if (sources.length < 3) {
            return null;
        }
        Question question = new Question();
        question.setId(Long.parseLong(sources[0]));
        question.setText(sources[1]);
        question.setRightAnswer(Integer.parseInt(sources[2]));

        int count = 0;
        for (int i = 3; i < sources.length; i++) {
            question.addAnswer(new Answer(++count, sources[i]));
        }
        return question;
    }
}
