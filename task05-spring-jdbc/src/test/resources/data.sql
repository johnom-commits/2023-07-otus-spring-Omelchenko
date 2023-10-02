INSERT INTO authors (id, name) VALUES (1, 'Vysotskiy');
INSERT INTO authors (id, name) VALUES (2, 'Makarenko');

INSERT INTO genres (id, name) VALUES (1, 'poetry');
INSERT INTO genres (id, name) VALUES (2, 'novel');

INSERT INTO books (title, authors_id, genres_id) VALUES ('pedagogical poem', 2, 2);
