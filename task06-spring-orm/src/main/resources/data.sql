INSERT INTO authors (name) VALUES ('Высоцкий Владимир Семенович');
INSERT INTO authors (name) VALUES ('Макаренко Антон Семенович');

INSERT INTO genres (name) VALUES ('поэзия');
INSERT INTO genres (name) VALUES ('роман');

INSERT INTO books (title, author_id, genre_id) VALUES ('Нерв', 1, 1);
INSERT INTO books (title, author_id, genre_id) VALUES ('Педагогическая поэма', 2, 2);
INSERT INTO books (title, author_id, genre_id) VALUES ('Флаги на башнях', 2, 2);

INSERT INTO comments (book_id, name) VALUES (1, 'комментарий 1');
INSERT INTO comments (book_id, name) VALUES (2, 'комментарий 2');
INSERT INTO comments (book_id, name) VALUES (3, 'комментарий 3');
INSERT INTO comments (book_id, name) VALUES (1, 'комментарий 1.2');
