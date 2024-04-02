package ru.otus.homework.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Book;
import ru.otus.homework.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class BookCascadeSaveEventsListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        String id = event.getSource().get("_id").toString();
        commentRepository.deleteByBookId(id);
    }
}
