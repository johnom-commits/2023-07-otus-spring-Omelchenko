INSERT INTO authors (name) VALUES ('Vysotskiy');
INSERT INTO authors (name) VALUES ('Makarenko');

INSERT INTO genres (name) VALUES ('poetry');
INSERT INTO genres (name) VALUES ('novel');

INSERT INTO books (title, author_id, genre_id) VALUES ('pedagogical poem', 2, 2);
INSERT INTO books (title, author_id, genre_id) VALUES ('Nerv', 1, 1);

INSERT INTO comments (book_id, name) VALUES (1, 'комментарий 1');
INSERT INTO comments (book_id, name) VALUES (1, 'комментарий 1.2');
INSERT INTO comments (book_id, name) VALUES (2, 'комментарий 2');
