CREATE TABLE users
(
    id       UUID        NOT NULL DEFAULT random_uuid() PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL
);

ALTER TABLE users
    ADD CONSTRAINT uk_users_username UNIQUE (username);

CREATE TABLE movies
(
    id      UUID         NOT NULL DEFAULT random_uuid() PRIMARY KEY,
    title   VARCHAR(255) NOT NULL,
    rate    DECIMAL      NOT NULL,
    votes   DECIMAL      NOT NULL,
    version INT          NOT NULL default 0
);

ALTER TABLE movies
    ADD CONSTRAINT uk_movies_title UNIQUE (title);

CREATE TABLE quizzes
(
    id      UUID NOT NULL DEFAULT random_uuid() PRIMARY KEY,
    version INT  NOT NULL default 0
);

CREATE TABLE quiz_users
(
    id      UUID NOT NULL DEFAULT random_uuid() PRIMARY KEY,
    user_id UUID NOT NULL,
    quiz_id UUID NOT NULL,
    enabled INT  NOT NULL,
    errors  INT  NOT NULL default 0,
    points  INT  NOT NULL default 0,
    version INT  NOT NULL default 0
);

ALTER TABLE quiz_users
    ADD CONSTRAINT fk_user_quiz_users FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE quiz_users
    ADD CONSTRAINT fk_quiz_quiz_users FOREIGN KEY (quiz_id) REFERENCES quizzes (id);
ALTER TABLE quiz_users
    ADD CONSTRAINT uk_quiz_users UNIQUE (user_id, quiz_id);

CREATE TABLE pairs
(
    id           UUID NOT NULL DEFAULT random_uuid() PRIMARY KEY,
    quiz_user_id UUID NOT NULL,
    movie_one_id UUID NOT NULL,
    movie_two_id UUID NOT NULL
);

ALTER TABLE pairs
    ADD CONSTRAINT fk_quiz_user_pairs FOREIGN KEY (quiz_user_id) REFERENCES quiz_users (id);
ALTER TABLE pairs
    ADD CONSTRAINT fk_movie_one_pairs FOREIGN KEY (movie_one_id) REFERENCES movies (id);
ALTER TABLE pairs
    ADD CONSTRAINT fk_movie_two_pairs FOREIGN KEY (movie_two_id) REFERENCES movies (id);
ALTER TABLE pairs
    ADD CONSTRAINT uk_pair_by_quiz UNIQUE (quiz_user_id, movie_one_id, movie_two_id);

CREATE TABLE ranking
(
    id      UUID NOT NULL default random_uuid() PRIMARY KEY,
    user_id UUID NOT NULL,
    points  INT,
    version INT  NOT NULL default 0
);

ALTER TABLE ranking
    ADD CONSTRAINT fk_user_ranking FOREIGN KEY (user_id) REFERENCES users (id);
