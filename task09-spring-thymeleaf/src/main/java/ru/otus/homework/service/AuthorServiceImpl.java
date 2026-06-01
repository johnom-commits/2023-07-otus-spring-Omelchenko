package ru.otus.homework.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Author;
import ru.otus.homework.dto.AuthorDto;
import ru.otus.homework.exceptions.NotFoundException;
import ru.otus.homework.mapper.AuthorMapping;
import ru.otus.homework.repositories.AuthorRepository;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository authorRepository;
    AuthorMapping authorMapping;

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapping::convert)
                .toList();
    }

    @Override
    public void add(String name) {
        Author author = new Author();
        author.setName(name);
        authorRepository.insert(author);
    }

    @Override
    public Author getAuthorById(String id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не найден автор с id = " + id));
    }
}
