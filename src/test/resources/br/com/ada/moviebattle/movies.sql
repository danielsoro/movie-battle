INSERT INTO QUIZZES(version)
VALUES (0);

INSERT INTO MOVIES(title, rate)
VALUES ('movie--1', 10.0);

INSERT INTO MOVIES(title, rate)
VALUES ('movie--2', 5.0);

INSERT INTO MOVIES(title, rate)
VALUES ('movie--3', 2.5);

INSERT INTO MOVIES(title, rate)
VALUES ('movie--4', 3.5);

INSERT INTO MOVIES(title, rate)
VALUES ('movie--5', 1.5);

INSERT INTO MOVIES(title, rate)
VALUES ('movie--6', 4.5);

INSERT INTO QUIZ_USERS(user_id, quiz_id, enabled)
VALUES ((SELECT id FROM Users WHERE username = 'foo'), (SELECT id FROM QUIZZES LIMIT 1), true);
