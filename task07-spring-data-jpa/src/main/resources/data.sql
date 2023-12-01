MERGE INTO authors AS a
USING (SELECT 'Высоцкий' AS name) AS n
ON a.name = n.name
WHEN NOT matched THEN
INSERT (name)
VALUES ('Высоцкий');

MERGE INTO authors AS a
USING (SELECT 'Макаренко' AS name) AS n
ON a.name = n.name
WHEN NOT matched THEN
INSERT (name)
VALUES ('Макаренко');

MERGE INTO genres AS g
USING (SELECT 'поэзия' AS name) AS n
ON g.name = n.name
WHEN NOT matched THEN
INSERT (name)
VALUES ('поэзия');

MERGE INTO genres AS g
USING (SELECT 'роман' AS name) AS n
ON g.name = n.name
WHEN NOT matched THEN
INSERT (name)
VALUES ('роман');

MERGE INTO books b
USING (SELECT 'Педагогическая поэма' AS title, 2 AS author_id, 2 AS genre_id) AS n
ON b.title = n.title AND b.author_id = n.author_id
WHEN NOT matched THEN
INSERT (title, author_id, genre_id)
VALUES ('Педагогическая поэма', 2, 2);

MERGE INTO books b
USING (SELECT 'Нерв' AS title, 1 AS author_id, 1 AS genre_id) AS n
ON b.title = n.title AND b.author_id = n.author_id
WHEN NOT matched THEN
INSERT (title, author_id, genre_id)
VALUES ('Нерв', 1, 1);

MERGE INTO books b
USING (SELECT 'Флаги на башнях' AS title, 1 AS author_id, 1 AS genre_id) AS n
ON b.title = n.title AND b.author_id = n.author_id
WHEN NOT matched THEN
INSERT (title, author_id, genre_id)
VALUES ('Флаги на башнях', 2, 2);

MERGE INTO comments AS c
USING (SELECT 1 AS book_id, 'комментарий 1' AS name) AS n
ON c.name = n.name AND c.book_id = n.book_id
WHEN NOT matched THEN
INSERT (book_id, name)
VALUES (1, 'комментарий 1');

MERGE INTO comments AS c
USING (SELECT 1 AS book_id, 'комментарий 1.2' AS name) AS n
ON c.name = n.name AND c.book_id = n.book_id
WHEN NOT matched THEN
INSERT (book_id, name)
VALUES (1, 'комментарий 1.2');

MERGE INTO comments AS c
USING (SELECT 2 AS book_id, 'комментарий 2' AS name) AS n
ON c.name = n.name AND c.book_id = n.book_id
WHEN NOT matched THEN
INSERT (book_id, name)
VALUES (2, 'комментарий 2');
