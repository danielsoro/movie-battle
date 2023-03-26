package br.com.ada.moviebattle.infra.database.repositories;

import br.com.ada.moviebattle.infra.database.entities.Quiz;
import br.com.ada.moviebattle.infra.database.entities.QuizUser;
import br.com.ada.moviebattle.infra.database.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class QuizUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizUserRepository quizUserRepository;

    @Test
    @DisplayName("Should be able to persist a quiz to user")
    public void shouldBeAbleToPersistAQuizToUser() {
        final var user = userRepository.save(User.builder()
                .username("teste")
                .password("teste")
                .build());

        final var quiz = quizRepository.save(Quiz.builder()
                .build());

        final var quizUser = quizUserRepository.save(QuizUser.builder()
                .quiz(quiz)
                .enabled(false)
                .user(user)
                .build());

        assertNotNull(quizUser.getId());
    }

    @Test
    @DisplayName("Should count enabled quiz for an user")
    public void shouldCountEnabledQuizForAnUser() {
        final var user = userRepository.save(User.builder()
                .username("danielsoro")
                .password("password")
                .build());

        final var quiz = quizRepository.save(Quiz.builder()
                .build());

        final var quizUser = quizUserRepository.save(QuizUser.builder()
                .quiz(quiz)
                .enabled(true)
                .user(user)
                .build());

        assertNotNull(quizUser.getId());
        final var totalEnabled = quizUserRepository.countByEnabledIsTrue();
        assertEquals("Total enabled quiz for a user is invalid", 1, totalEnabled);
    }
}
