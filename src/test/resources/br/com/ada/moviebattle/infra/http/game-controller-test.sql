DELETE FROM quiz_users;
DELETE FROM quizzes;
DELETE FROM users;

INSERT INTO users(username, password)
VALUES ('user-a', 'userapassword');

INSERT INTO users(username, password)
VALUES ('user-b', 'userbpassword');

INSERT INTO quizzes(version)
VALUES (0);

INSERT INTO quiz_users(user_id, quiz_id, enabled)
VALUES ((SELECT id FROM Users WHERE username = 'user-b'), (SELECT id FROM QUIZZES LIMIT 1), true);
