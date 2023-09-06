package ru.otus.homework.config;

import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.IOServiceStream;

import java.io.InputStream;
import java.util.Locale;

@Getter
@ConfigurationProperties(prefix = "app")
public class ApplicationConfig implements ResourceProvider, AppConfig, LocaleProvider {

    private final String directoryDatasource;

    private final String patternDatasourceFileName;

    private final String numberRightAnswers;

    private final Locale locale;

    @ConstructorBinding
    public ApplicationConfig(String directoryDatasource,
                             String patternDatasourceFileName,
                             String numberRightAnswers,
                             String locale
    ) {
        this.directoryDatasource = directoryDatasource;
        this.patternDatasourceFileName = patternDatasourceFileName;
        this.numberRightAnswers = numberRightAnswers;
        this.locale = Locale.forLanguageTag(locale);
    }

    @Override
    public InputStream getResource() {
        return ApplicationConfig.class.getClassLoader().getResourceAsStream(getFilename());
    }

    private String getFilename() {
        var baseName = String.join("_",
                FilenameUtils.getBaseName(patternDatasourceFileName),
                locale.toLanguageTag()
        );
        return directoryDatasource + "/" + baseName + "." + FilenameUtils.getExtension(patternDatasourceFileName);
    }

    public int getRightAnswers() {
        return Integer.parseInt(numberRightAnswers);
    }

    @Bean
    public IOService ioServiceStream() {
        return new IOServiceStream(System.out, System.in);
    }

    @Override
    public Locale getCurrent() {
        return locale;
    }
}
